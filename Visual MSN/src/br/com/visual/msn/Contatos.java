package br.com.visual.msn;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Contatos 
{
	public static final String arquivo = "\\vmsn\\Contatos.cnt";
	public static ArrayList<String> ListaContatos = new ArrayList<String>();;

	public static void addContato(String Nome, String Ip)
	{
		if (Nome == null || Nome.trim().isEmpty() || Ip == null || Ip.trim().isEmpty())
			return;

		String Contato = Nome + "|" + Ip + "#";
		setFile(Contato, arquivo);
		loadContatos();
	}

	public static void setNick(String Nick)
	{
		if (Nick == null || Nick.trim().isEmpty())
			return;
		setFile(Nick + "#", arquivo);
	}

	public static String getNick()
	{
		String cont[] = getFile(arquivo).split("[#]");
		for(int i = 0; i<cont.length; i++)
		{
			if (cont[i].split("[|]").length < 2)
			{	
				return cont[i];
			}
		}
		return "";
	}

	public static void alterarContato(String contato, String Nome, String Ip){
		if (contato == null || contato.trim().isEmpty() 
				|| Nome == null || Nome.trim().isEmpty() 
				|| Ip == null || Ip.trim().isEmpty())
			return;

		String cont[] = getFile(arquivo).split("[#]");
		if (deleteFile(arquivo))
		{
			for(int i = 0; i<cont.length; i++)
			{
				if (cont[i].trim().equals(contato.trim()))
				{	
					cont[i] = Nome + "|" + Ip;
				}
				setFile(cont[i] + "#", arquivo);
			}
		}
		loadContatos();
	}

	public static void removerContato(String contato){
		if (contato == null || contato.trim().isEmpty())
			return;

		String cont[] = getFile(arquivo).split("[#]");
		if (deleteFile(arquivo))
		{
			for(int i = 0; i<cont.length; i++)
			{
				if (!cont[i].trim().equals(contato.trim()))
				{
					setFile(cont[i] + "#", arquivo);
				}
			}
		}
		loadContatos();
	}

	public static void loadContatos()
	{
		String contato[] = getFile(arquivo).split("[#]");
		ListaContatos.clear();
		for(int i = 0; i<contato.length; i++)
		{
			if (contato[i].split("[|]").length == 2)
			{
				ListaContatos.add(contato[i].trim());
			}
		}
		ListaContatos.add("Novo...");
	}

	private static boolean deleteFile(String arquivo) {
		try 
		{
			File arq = new File(arquivo);		
			if (arq.exists() && arq.canRead()) 
			{
				return arq.delete();
			}
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return false;			
		}
		return false;
	}

	private static String getFile(String arquivo) 
	{
		StringBuffer retorno = new StringBuffer();

		try 
		{
			File arq = new File(arquivo);		
			if (!arq.exists()) 
			{
				return "";
			}

			FileInputStream Entrada = new FileInputStream(arq);
			byte[] buffer = new byte[1024];
			int lido = 0;
			while (true) 
			{
				lido = Entrada.read(buffer);
				if (lido <= 0 ) 
				{
					break;
				}
				retorno.append(new String(buffer,0,lido));
			}
			Entrada.close();
		} 	
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			return "";			
		}
		return retorno.toString();
	}

	private static void setFile(String texto, String caminho)
	{
		String conteudo = texto;
		try {
			File file = new File(caminho);

			File folder = new File(file.getParent());
			folder.mkdirs();

			FileWriter x = new FileWriter(file,true);
			x.write(conteudo);
			x.close();
		}	
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
