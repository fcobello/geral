package br.com.comunicator.tela;

import java.awt.Color;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
/**
 * Tela do Comunicador
 * @author Felipe Cobello
 *
 */
public class Tela extends JFrame
{
	private static final long serialVersionUID = -236770745823134097L;
	private SystemTray systemTray = SystemTray.getSystemTray();
	public PopupMenu trayMenu = new PopupMenu(); 
	public TrayIcon trayIcon = new TrayIcon(super.getToolkit().getImage(getClass().getResource("/img/icone.png")));
	public JButton btnAdd = new JButton();
	public JButton btnRemove = new JButton();
	public JList lstContatos = new JList();
	public DefaultListModel listModel = new DefaultListModel();
	private String nick = "Anônimo";
	private String ip = "";

	/**
	 * Construtor da Classe
	 */
	public Tela()
	{
		super.setTitle("\\o/");
		super.setSize(170, 300);
		super.setLocation(5, 5);
		super.setResizable(false);
		super.setDefaultCloseOperation(HIDE_ON_CLOSE);
		super.setIconImage(trayIcon.getImage());
		setAtributos();
		setObjetos();
	}

	/**
	 * Define os Atributos dos Objetos
	 */
	private void setAtributos()
	{
		try
		{
			//btn Add
			btnAdd.setIcon(new ImageIcon(getClass().getResource("/img/add.png")));
			btnAdd.setSize(20, 20);
			btnAdd.setLocation(50, 0);
			btnAdd.setContentAreaFilled(false);
			btnAdd.setBorderPainted(false);
			//btn Remove
			btnRemove.setIcon(new ImageIcon(getClass().getResource("/img/delete.png")));
			btnRemove.setSize(20, 20);
			btnRemove.setLocation(90, 0);
			btnRemove.setContentAreaFilled(false);
			btnRemove.setBorderPainted(false);
			//Lista de Contatos
			lstContatos.setSize(150, 240);
			lstContatos.setLocation(5, 25);
			lstContatos.setBorder(BorderFactory.createLineBorder(Color.black));
			lstContatos.setModel(listModel);
			//TrayMenu
			trayMenu.add("Abrir");
			trayMenu.addSeparator();
			trayMenu.add("Fechar");
			//TrayIcon
			trayIcon.setPopupMenu(trayMenu);
			trayIcon.setToolTip(super.getTitle());
			trayIcon.setImageAutoSize(true);
			systemTray.add(trayIcon);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Define os Objetos na Tela
	 */
	private void setObjetos()
	{
		super.setLayout(null);
		super.getContentPane().add(btnAdd);
		super.getContentPane().add(btnRemove);
		super.getContentPane().add(lstContatos);
	}
	
	/**
	 * Define o Nick
	 * @param nick Nick
	 */
	public void setNick(String nick)
	{
		this.nick = nick;
	}
	
	/**
	 * Retorna o Nick
	 * @return Nick
	 */
	public String getNick()
	{
		return nick;
	}
	
	/**
	 * Define o IP
	 * @param ip IP
	 */
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	
	/**
	 * Retorna o IP
	 * @return
	 */
	public String getIp()
	{
		return ip;
	}
}
