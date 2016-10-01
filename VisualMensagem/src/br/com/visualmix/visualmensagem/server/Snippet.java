package br.com.visualmix.visualmensagem.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Snippet {

	public static String enviar(String ip, int porta, String texto) throws Exception {
		Socket tcpSocket = null;  
		DataOutputStream os = null;
		DataInputStream is = null;
		String retorno = "";
		try {
			tcpSocket = new Socket();
			tcpSocket.connect(new InetSocketAddress(ip, porta), 1000);
			os = new DataOutputStream(tcpSocket.getOutputStream());
			is = new DataInputStream(tcpSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Host(" + ip + ") nao reconhecido");
		} catch (IOException e){
			throw new Exception("Computador Off-Line (" + ip + ")");
		}
		String mensagem = texto;
		mensagem = Mascarado.getCripitografia(mensagem);
		if (tcpSocket != null && os != null && is != null) {
			try {
				os.writeBytes(mensagem);   
				os.close();
				is.close();
			} catch (UnknownHostException e) {
				System.err.println("Trying to connect to unknown host: " + e);
				throw e;
			} catch (IOException e) {
				System.err.println("IOException:  " + e);
				throw e;
			}
		}
		try {
			tcpSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return retorno;
	}
}