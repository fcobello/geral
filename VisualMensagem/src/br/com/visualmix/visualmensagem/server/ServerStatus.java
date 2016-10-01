package br.com.visualmix.visualmensagem.server;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import br.com.visualmix.visualmensagem.principal.Programa;

public class ServerStatus extends Server{
	private Programa prog;

	public ServerStatus(Programa prog) {
		super(25451);
		this.prog = prog;
	}

	@Override
	public void event(String mensagem, Socket sock) throws IOException {
		try{
			if(mensagem.trim().equals("")){
				return;
			}
			if(mensagem.split("\\|")[1].substring(0, 2).equals("#?")){
				try {
					Snippet.enviar(mensagem.split("\\|")[0].split("\\*")[1], 25451, prog.pref.get("Nome", "pauno") + "*" + prog.meuIp + "|setStatus(" + prog.status + ")");
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
			if(mensagem.split("\\|")[1].equals("isOpen")){
				prog.formulario.setVisible(true);
			}
			if(mensagem.split("\\|")[1].split("\\(")[0].toLowerCase().equals("setstatus")){
				for (int i = 0; i < prog.getListaContatos().getContatos().size(); i++) {
					if(prog.getListaContatos().getContatos().get(i).getIp().equals(mensagem.split("\\*")[1].split("\\|")[0])){
						prog.getListaContatos().getContatos().get(i).setStatus("(" + mensagem.split("\\|")[1].split("\\(")[1].split("\\)")[0] + ")");
					}
				}
			}
		}catch(Exception e){

		}finally{
			sock.close();
		}
	}
}
