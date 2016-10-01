package br.com.visual.msn;

import java.awt.AWTException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class MSN
{
	public String nick = "";
	public static Tela objTela;

	public MSN() throws AWTException, UnknownHostException
	{
		nick = Contatos.getNick();
		if (nick.trim().equals("")){
			while (nick.trim().isEmpty())
			{
				nick = JOptionPane.showInputDialog("Nick: ");
			}
			Contatos.setNick(nick);
		}

		objTela = new Tela(nick, InetAddress.getLocalHost().getHostAddress());
		new Server().t.start();
		loadListaContatos();
	}

	private void loadListaContatos() 
	{
		Contatos.loadContatos();
		for(int i = 0; i<Contatos.ListaContatos.size(); i++)
		{
			objTela.listModel.addElement(Contatos.ListaContatos.get(i));
		}
	}

	public static void main(String[] args) throws AWTException, UnknownHostException 
	{
		new MSN();
	}
}