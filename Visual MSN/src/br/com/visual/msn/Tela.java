package br.com.visual.msn;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JRootPane;

public class Tela extends JFrame
{
	private static final long serialVersionUID = -236770745823134097L;
	private SystemTray tray = SystemTray.getSystemTray();
	protected PopupMenu trayMenu = new PopupMenu(); 
	protected TrayIcon trayIcon = new TrayIcon(super.getToolkit().getImage(getClass().getResource("/br/com/comunicator/img/icone.png")));
	protected JList lstContatos = new JList();
	protected DefaultListModel listModel = new DefaultListModel();
	protected String Nick = "Anônimo";
	protected String ip = "";
	private Events events = new Events();

	public Tela(String Nick, String Ip) throws AWTException
	{
		super.setTitle("Visual MSN");
		super.setSize(170, 300);
		super.setLocation(5, 5);
		super.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		super.setResizable(false);
		super.setUndecorated(true);
		super.setDefaultCloseOperation(HIDE_ON_CLOSE);
		super.setVisible(true);
		super.addKeyListener(events);
		setIcon("online.gif");
		this.ip = Ip;
		this.Nick = Nick;
		setAtributos();
		setForm();
	}

	private void setAtributos() throws AWTException
	{
		lstContatos.setSize(150, 255);
		lstContatos.setLocation(5, 5);
		lstContatos.setBorder(BorderFactory.createLineBorder(Color.black));
		lstContatos.setModel(listModel);
		lstContatos.addMouseListener(events);
		trayMenu.add("Abrir");
		trayMenu.addSeparator();
		trayMenu.add("Conectar");
		trayMenu.add("Desconectar");
		trayMenu.addSeparator();
		trayMenu.add("Fechar");
		trayMenu.addActionListener(events);
		trayIcon.setImage(new ImageIcon("online.gif").getImage());
		trayIcon.setPopupMenu(trayMenu);
		trayIcon.setToolTip(super.getTitle());
		trayIcon.addMouseListener(events);
		trayIcon.setImageAutoSize(true);
		tray.add(trayIcon);
		lstContatos.addKeyListener(events);
	}

	private void setForm()
	{
//		super.setLayout(manager)
		super.getContentPane().add(lstContatos);
	}

	protected void setIcon(String image){
		super.setIconImage(new ImageIcon(image).getImage());
	}
}
