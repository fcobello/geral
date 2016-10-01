package br.com.visualmix.visualmensagem.events;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import br.com.visualmix.visualmensagem.principal.Programa;

public class KeyEvents implements KeyListener{

	private Programa prog;;
	int c = 7;
	String comando = "";
	int m = 0;
	private boolean shift = false;
	public KeyEvents(Programa prog) {
		this.prog = prog;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		comando += e.getKeyChar();
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			if(shift){
				prog.formulario.txtTexto.setText(prog.formulario.txtTexto.getText() + "\r\n");
				return;
			}
			prog.enviarMensagem();
			m = 0;
			comando = "";
		}
		if(comando.toLowerCase().trim().contains("-nick")){
			prog.setNick();
			prog.formulario.txtTexto.setText("");
			comando = "";
		}
		if(comando.toLowerCase().trim().contains("-file")){
			prog.enviarArquivo();
			prog.formulario.txtTexto.setText("");
			comando = "";
		}
		if(comando.toLowerCase().trim().contains("-font")){
			prog.setFont();
			prog.formulario.txtTexto.setText("");
			comando = "";
		}
		if(comando.toLowerCase().trim().contains("-skin")){
			prog.setSkin();
			prog.formulario.txtTexto.setText("");
			comando = "";
		}
		if(comando.toLowerCase().trim().contains("-ip")){
			prog.setIP();
			prog.formulario.txtTexto.setText("");
			comando = "";
		}
		if(comando.toLowerCase().trim().contains("-status")){
			prog.setStatus();
			prog.formulario.txtTexto.setText("");
			comando = "";
		}
		if(comando.toLowerCase().trim().contains("-color")){
			prog.setCores();
			prog.formulario.txtTexto.setText("");
			comando = "";
		}
		if(comando.toLowerCase().trim().contains("-help")){
			JOptionPane.showMessageDialog(prog.formulario, "-nick: Nick\n-ip: IP\n-status: Status\n-skin: Skin\n-color: Cores\n-font: Fonte\n-file: Enviar Arquivos", "Help Visual Mensagem", JOptionPane.INFORMATION_MESSAGE);
			prog.formulario.txtTexto.setText("");
			comando = "";
		}
		if(e.getSource().equals(prog.formulario.txtTexto)){
			if(e.getKeyCode() == KeyEvent.VK_DOWN){

				m++;
				try {
					if(prog.mensagens.size() - m < 0){
						m--;
					}
					prog.formulario.txtTexto.setText(prog.mensagens.get(prog.mensagens.size() - m));
				} catch (Exception e2) {
					//					m--;
					prog.formulario.txtTexto.setText("");
				}
			}

			if(e.getKeyCode() == KeyEvent.VK_UP){
				m--;
				try {
					if(prog.mensagens.size() - m == prog.mensagens.size()){
						prog.formulario.txtTexto.setText("");
						m = 0;
					}else{
						prog.formulario.txtTexto.setText(prog.mensagens.get(prog.mensagens.size() - m));
					}

				} catch (Exception e2) {
					//					m++;
					m=0;
					prog.formulario.txtTexto.setText("");
				}
			}
			if(e.getKeyCode() == KeyEvent.VK_SHIFT){
				shift = true;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			prog.formulario.setVisible(false);
//			prog.hist.setVisible(false);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			shift = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
