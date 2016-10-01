package br.com.comunicator.principal;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.prefs.Preferences;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import br.com.comunicator.contatos.Contatos;
import br.com.comunicator.eventos.Events;
import br.com.comunicator.tela.Tela;

public class Principal
{
	public Preferences preferences = Preferences.systemNodeForPackage(Principal.class);
	public Tela tela = new Tela();
	public Contatos contatos = new Contatos(preferences);
	
	public void start() throws UnknownHostException
	{
		if(preferences.get("nick", "").isEmpty())
		{
			while(tela.getNick().isEmpty())
			{
				tela.setNick(JOptionPane.showInputDialog(tela, "Digite seu Nick:"));
				if(tela.getNick() == null)
					tela.dispose();
			}
		}
		tela.setIp(InetAddress.getLocalHost().getHostAddress());
		contatos.loadContatos();
		for (int i = 0; i < contatos.listaContatos.size(); i++)
		{
			((DefaultListModel)tela.lstContatos.getModel()).addElement(contatos.listaContatos.get(i));
		}
		setEvents();
		tela.setVisible(true);
	}
	
	private void setEvents()
	{
		Events events = new Events(this);
		
		tela.btnRemove.addActionListener(events);
		tela.btnAdd.addActionListener(events);
		tela.lstContatos.addMouseListener(events);
		tela.lstContatos.addKeyListener(events);
		tela.trayMenu.addActionListener(events);
		tela.trayIcon.addMouseListener(events);
	}
	
	public static void main(String[] args)
	{
		Principal objPrincipal = new Principal();		
		
		try
		{
			objPrincipal.start();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
