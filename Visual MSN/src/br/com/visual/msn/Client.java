package br.com.visual.msn;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client 
{	
	public static boolean sendMsg(String msg, String ip, String nick) throws UnknownHostException, IOException 
	{	
		Socket s;
		DataOutputStream out;
		
		s = new Socket(ip, 25452);
		out = new DataOutputStream(s.getOutputStream());
		
		if (msg.trim().isEmpty())
		{
			s.close();
			out.close();
			return false;
		}
		
		//Verifica se é Msg Normal ou Comando a Ser Executado
		switch (msg.split("[|]")[0].charAt(0))
		{
		case 'C':
			break;
		case 'F':
			TransferFile objTransfer = new TransferFile(msg.substring(1, msg.length()));
			objTransfer.uploadFile();
			objTransfer = null;
			msg = msg.concat("|" + MSN.objTela.Nick).concat("|" + InetAddress.getLocalHost().getHostAddress());
			break;
		case 'T':
			msg = msg + "|" + InetAddress.getLocalHost().getHostAddress();
			break;
		default:
			msg = msg.concat("|" + MSN.objTela.Nick).concat("|" + InetAddress.getLocalHost().getHostAddress());
		break;
		}
		out.writeUTF(msg);	
		s.close();
		out.close();
		return true;
	}
}
