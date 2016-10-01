package br.com.visualmix.visualmensagem.server;

import java.io.IOException;
import java.net.Socket;
import br.com.visualmix.visualmensagem.principal.Programa;

public class ServerFile extends Server{

	private Programa prog;
	
	public ServerFile(Programa prog) {
		super(25498);
		this.prog = prog;
	}

	@Override
	public void event(String mensagem, Socket sock) throws IOException {
			if(mensagem.split("[|]")[0].equals("F")){
				prog.receberArquivo(mensagem.split("[|]")[1].split("[;]"), sock.getInetAddress().getHostAddress());
			}
	}

}
