package br.com.comunicator.client;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client 
{	
	public static boolean sendMsg(String msg, String ip, String nick) 
	{	
		Socket s;
		DataOutputStream out;
		boolean retorno = false;
		
		try
		{
			s = new Socket(ip, 25452);
			out = new DataOutputStream(s.getOutputStream());
			if (msg.trim().isEmpty())
			{
				s.close();
				out.close();
			}
			msg = msg.concat("|" + nick).concat("|" + ip);
			out.writeUTF(msg);	
			s.close();
			out.close();
			retorno = true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
