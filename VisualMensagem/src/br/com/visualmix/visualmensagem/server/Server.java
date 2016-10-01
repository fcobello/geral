package br.com.visualmix.visualmensagem.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Server implements Runnable{

	private ServerSocket srv;
//	private Socket sock;
	private BufferedReader br;

	Server(int porta){

		try {
			srv = new ServerSocket(porta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		while(true){
			try {
				Socket sock = srv.accept();
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String x = br.readLine();
				event(Mascarado.getDescriptografia(x), sock);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	public abstract void event(String mensagem, Socket sock) throws IOException;
}
