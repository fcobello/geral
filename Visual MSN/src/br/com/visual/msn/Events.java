package br.com.visual.msn;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Events implements ActionListener, MouseListener, KeyListener
{
	private String contato[];

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getSource().equals(MSN.objTela.trayIcon))
		{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				MSN.objTela.toFront();
				MSN.objTela.setVisible(true);
			}

			if (e.getButton() == MouseEvent.BUTTON2)
			{
				System.exit(0);
			}

			if (e.getButton() == MouseEvent.BUTTON3)
			{
				if (Server.isStateOpen){
					MSN.objTela.trayMenu.getItem(2).setEnabled(false);
				}
				MSN.objTela.trayIcon.getPopupMenu();
			}
		}
		
		if(e.getSource().equals(MSN.objTela.lstContatos))
		{
			if(MSN.objTela.lstContatos.getSelectedIndex() < 0)
				return;

			if (e.isAltDown()){
				if(MSN.objTela.lstContatos.getSelectedIndex() == Contatos.ListaContatos.size()-1)
					return;

				Object[] options = { "Alterar", "Remover", "Cancelar" };
				int opcao = JOptionPane.showOptionDialog(null, "Deseja alterar esse Contato?", "Editar", 0, JOptionPane.QUESTION_MESSAGE,null, options, options[0]);

				if (opcao == 0)
				{
					contato = MSN.objTela.lstContatos.getSelectedValue().toString().split("[|]");
					Contatos.alterarContato(MSN.objTela.lstContatos.getSelectedValue().toString(), JOptionPane.showInputDialog("Nome:", contato[0]), JOptionPane.showInputDialog("IP:", contato[1]));
					MSN.objTela.listModel.clear();
					for(int i = 0; i <  Contatos.ListaContatos.size(); i++)
						MSN.objTela.listModel.addElement(Contatos.ListaContatos.get(i));
				}
				else if (opcao == 1)
				{
					Contatos.removerContato(MSN.objTela.lstContatos.getSelectedValue().toString());
					MSN.objTela.listModel.clear();
					for(int i = 0; i <  Contatos.ListaContatos.size(); i++)
						MSN.objTela.listModel.addElement(Contatos.ListaContatos.get(i));
				}
				else
					return;

				return;
			}
			abrir();
		}
	}
	
	private void abrir(){
		
		if(MSN.objTela.lstContatos.getSelectedIndex() < 0)
			return;
		
		if(MSN.objTela.lstContatos.getSelectedIndex() == Contatos.ListaContatos.size()-1)
		{
			Contatos.addContato(JOptionPane.showInputDialog("Nome:"), JOptionPane.showInputDialog("IP:"));
			MSN.objTela.listModel.clear();
			for(int i = 0; i <  Contatos.ListaContatos.size(); i++)
				MSN.objTela.listModel.addElement(Contatos.ListaContatos.get(i));

			return;
		}

		if (!Server.isStateOpen){
			return;
		}
		contato = MSN.objTela.lstContatos.getSelectedValue().toString().split("[|]");
		try {
			Client.sendMsg(JOptionPane.showInputDialog(MSN.objTela, "Enviar Msg:"), contato[1], contato[0]);
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			abrir();
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(MSN.objTela.trayMenu))
		{
			if (e.getActionCommand().equals("Abrir"))
			{
				MSN.objTela.toFront();
				MSN.objTela.setVisible(true);
			}

			if (e.getActionCommand().equals("Conectar"))
			{
				MSN.objTela.trayMenu.getItem(2).setEnabled(false);
				MSN.objTela.trayMenu.getItem(3).setEnabled(true);
				MSN.objTela.trayIcon.setImage(new ImageIcon("online.gif").getImage());
				MSN.objTela.setIcon("online.gif");
				Server.isStateOpen = true;
				new Server().t.start();
			}

			if (e.getActionCommand().equals("Desconectar"))
			{
				MSN.objTela.trayMenu.getItem(2).setEnabled(true);
				MSN.objTela.trayMenu.getItem(3).setEnabled(false);
				MSN.objTela.trayIcon.setImage(new ImageIcon("\\vmsn\\offline.gif").getImage());
				MSN.objTela.setIcon("offline.gif");
				Server.isStateOpen = false;
				Server.close();
			}

			if (e.getActionCommand().equals("Fechar"))
			{
				System.exit(0);
			}
		}
	}
}