package br.com.comunicator.eventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import br.com.comunicator.client.Client;
import br.com.comunicator.principal.Principal;

public class Events implements ActionListener, MouseListener, KeyListener
{
	private Principal principal;

	public Events(Principal principal)
	{
		this.principal = principal;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource().equals(principal.tela.trayIcon))
		{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				principal.tela.toFront();
				principal.tela.setVisible(true);
			}

			if (e.getButton() == MouseEvent.BUTTON2)
			{
				System.exit(0);
			}

			if (e.getButton() == MouseEvent.BUTTON3)
			{
				principal.tela.trayIcon.getPopupMenu();
			}
		}
		if(e.getSource().equals(principal.tela.lstContatos) && e.getClickCount() == 2)
		{
			abrir();
		}
	}
	
	private void abrir()
	{
		String msg;
		
		if(principal.tela.lstContatos.getSelectedValue().equals(""))
			return;
		msg = JOptionPane.showInputDialog(principal.tela, "Enviar Msg:");
		Client.sendMsg(msg == null ? "":msg, principal.tela.lstContatos.getSelectedValue().toString().split("[|]")[1], principal.tela.lstContatos.getSelectedValue().toString().split("[|]")[0]);
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			abrir();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(principal.tela.trayMenu))
		{
			if (e.getActionCommand().equals("Abrir"))
			{
				principal.tela.toFront();
				principal.tela.setVisible(true);
			}

			if (e.getActionCommand().equals("Fechar"))
			{
				System.exit(0);
			}
		}
		if(e.getSource().equals(principal.tela.btnAdd))
		{
			principal.contatos.addContato(JOptionPane.showInputDialog("Nome:"), JOptionPane.showInputDialog("IP:"));
			((DefaultListModel)principal.tela.lstContatos.getModel()).clear();
			for(int i = 0; i <  principal.contatos.listaContatos.size(); i++)
				((DefaultListModel)principal.tela.lstContatos.getModel()).addElement(principal.contatos.listaContatos.get(i));
		}
		if(e.getSource().equals(principal.tela.btnRemove))
		{
			if(principal.tela.lstContatos.getSelectedIndex() == -1)
				return;
			principal.contatos.removerContato(principal.tela.lstContatos.getSelectedValue().toString());
			((DefaultListModel)principal.tela.lstContatos.getModel()).clear();
			for(int i = 0; i <  principal.contatos.listaContatos.size(); i++)
				((DefaultListModel)principal.tela.lstContatos.getModel()).addElement(principal.contatos.listaContatos.get(i));
		}
	}
}