package br.com.visual.msn;

import java.io.BufferedWriter;
import java.io.DataInputStream;
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

import javax.swing.JFileChooser;

public class TransferFile implements Runnable 
{
	protected Thread t = new Thread(this);
	protected String[] file;
	protected static JFileChooser fcTransfer = new JFileChooser();
	
	public TransferFile(String file)
	{
		this.file = file.split("[$]");
	}
	
	public void uploadFile() throws IOException 
	{
		t.start();
	}
	
	public static void downloadFile(String file, String ip) throws UnknownHostException, IOException
	{
		Socket s = new Socket(ip, 25499);
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())), true);
		InputStream in = s.getInputStream();
		FileOutputStream fileOut = new FileOutputStream(file);
		byte data[] = new byte[1024];
		int size = in.read(data);
		while(size != -1)
		{
			fileOut.write(data, 0, size);
			fileOut.flush();
			size = in.read(data);
		}
		
		s.close();
		out.close();
		in.close();
		fileOut.close();
	}

	@Override
	public void run() 
	{
		try 
		{
			for (int x = 0; x < file.length; x++) {
				ServerSocket serv = new ServerSocket(25499);
				Socket s = serv.accept();
				FileInputStream inFile = new FileInputStream(file[x]);
				DataInputStream arq = new DataInputStream(inFile);
				OutputStream out = s.getOutputStream();
				byte data[] = new byte[1024];
				int size = arq.read(data);
				while(size != -1)
				{
					out.write(data, 0, size);
					out.flush();
					size = arq.read(data);
				}
				
				out.close();
				inFile.close();
				s.close();
				serv.close();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}