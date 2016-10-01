package br.com.visual.msn;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Server implements Runnable
{	
	private static ServerSocket serv;
	private static Socket s;
	private static DataInputStream in;
	protected static boolean isStateOpen = true;
	protected Thread t = new Thread(this);

	protected void listen() throws IOException 
	{
		while(isStateOpen)
		{
			String msg;
			serv = new ServerSocket(25452);
			s = serv.accept();
			in = new DataInputStream(s.getInputStream());
			msg = in.readUTF();
			switch (msg.split("[|]")[0].charAt(0)) 
			{
			case 'C':
				break;
			case 'F':
				int qtdItens = msg.split("#")[0].substring(1).split("[$]").length;
				File[] file	= new File[qtdItens];
				for (int x = 0; x < qtdItens; x++) {
					file[x] = new File(msg.split("#")[0].substring(1).split("[$]")[x]);
					if (JOptionPane.showConfirmDialog(null, "Deseja receber o arquivo " + file[x].getName() + " de " + msg.split("#")[1] + "?", "Transferência de Arquivo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					{
						TransferFile.fcTransfer.setSelectedFile(file[x].getAbsoluteFile());
						if (TransferFile.fcTransfer.showSaveDialog(null) != JFileChooser.CANCEL_OPTION)
						{
							TransferFile.downloadFile(TransferFile.fcTransfer.getSelectedFile().toString(), msg.split("#")[2]);
							JOptionPane.showMessageDialog(null, "Arquivo recebido: " + TransferFile.fcTransfer.getSelectedFile().getPath(), "Transferência de Arquivo", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
				break;
			default:
				JOptionPane.showMessageDialog(MSN.objTela, msg.split("[|]")[1] + ": " + msg.split("[|]")[0]);
				break;
			}
			in.close();
			s.close();
			serv.close();
		}
	}

	protected static void close()
	{
		try
		{
			isStateOpen = false;
			Client.sendMsg("C", InetAddress.getLocalHost().getHostAddress(), "System");
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void run() 
	{
		try
		{
			listen();
		}
		catch(BindException e)
		{
			JOptionPane.showMessageDialog(null, "Porta: 25451 - Ocupada", "Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		catch(Exception e)		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			Server.close();
			isStateOpen = true;
			run();
		}
	}
}