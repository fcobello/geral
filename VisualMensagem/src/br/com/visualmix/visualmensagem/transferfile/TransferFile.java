package br.com.visualmix.visualmensagem.transferfile;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 * Classe que get um arquivo da sua maquina e seta na de seu amigo.<br><br>
 * <b>Author:</b> Felipe Cobello<br>
 * <b>Special Participation:</b> J.Carlos
 */
public class TransferFile implements Runnable {
	private static File[] files;
	private static boolean cancelar = false;
	private static long cont;
	private static boolean reUp = false;
	
	public static void uploadFile(File[] files, boolean reUp) throws Exception {
		if(TransferFile.files != null){
			throw new Exception("Já existe upload de arquivos em andamento");
		}
		TransferFile.reUp = reUp;
		TransferFile.files = files;
		new Thread(new TransferFile()).start();
	}
	
	public static void downloadFile(String file, String ip) throws UnknownHostException, IOException
	{
		Socket s = null;
		PrintWriter out = null;
		InputStream in = null;
		FileOutputStream fileOut = null;
		
		s = new Socket(ip, 25599);
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
		in = s.getInputStream();
		fileOut = new FileOutputStream(file);
		cont = 0;
		byte data[] = new byte[1024];
		int size = in.read(data);
		while(size != -1)
		{
			if(cancelar){
				break;
			}
			cont++;
			fileOut.write(data, 0, size);
			fileOut.flush();
			size = in.read(data);
		}
		if(cancelar){
			cancelar = false;
			fileOut.flush();
			fileOut.close();
			new File(file).delete();
		}
		s.close();
		out.close();
		in.close();
		fileOut.close();
	}
	
	public static long getTaxaArquivoAtual(){
		return cont;
	}
	
	public static void cancelarDownload(){
		cancelar = true;
	}

	@Override
	public synchronized void run() {
		ServerSocket serv = null;
		Socket s = null;
		FileInputStream inFile = null;
		DataInputStream arq;
		OutputStream out = null;
		try	{
			do
			{
				for (int x = 0; x < files.length; x++) {
					serv = new ServerSocket(25599);
					s = serv.accept();
					inFile = new FileInputStream(files[x]);
					arq = new DataInputStream(inFile);
					out = s.getOutputStream();
					byte data[] = new byte[1024];
					int size = arq.read(data);
					while(size != -1)
					{
						out.write(data, 0, size);
						out.flush();
						size = arq.read(data);
					}
					out.flush();
					out.close();
					inFile.close();
					s.close();
					serv.close();
				}
			}while(TransferFile.reUp);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}finally{
			try {
				files = null;
				out.flush();
				out.close();
				inFile.close();
				s.close();
				serv.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}			
		}
	}	
}