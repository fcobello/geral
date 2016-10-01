package br.com.visualmix.visualmensagem.principal;

import java.awt.Font;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;


public class Tela extends JFrame{

	public static final int TITULO = 0;
	public static final int BTN_FILE = 2;
	public static final int BTN_STATUS = 4;
	public static final int BTN_IP = 5;
	public static final int BTN_NICK = 7;
	public static final int BTN_SUSTENIDO = 8;
	public static final int BTN_MASCARADO = 9;
	public static final int BTN_SKIN = 11;
	public static final int BTN_CORES = 12;
	public static final int BTN_FONT = 13;
	public static final int BTN_SHOW = 14;
	public static final int BTN_EXIT = 16;

	private static final long serialVersionUID = 5039705059326371183L;
//	public Container con = new Container();
	public JLabel lblTexto = new JLabel("Mensagem");
	public JTextField txtTexto = new JTextField();
	public JLabel lblContatos = new JLabel("Contatos");
	public JComboBox cmbContato = new JComboBox();
	public JButton btnEnviar = new JButton("Enviar");
	public JButton btnAdd = new JButton();
	public JButton btnDel = new JButton();
	public JButton btnHist = new JButton();
	public JButton btnOpcoes = new JButton();
	public JPanel pPanel = new JPanel();
	public SystemTray tray = SystemTray.getSystemTray();
	public PopupMenu popup = new PopupMenu("Visual Mensagem");
	public ImageIcon icone = new ImageIcon("icone.png");
	public TrayIcon trayIcon;
	public JTextPane txtHist = new JTextPane();
	public JScrollPane spHist = new JScrollPane(txtHist);

	public Tela(){
		super.setTitle("Visual Mensagem");
		super.setSize(385, 125);
		super.setResizable(false);
		super.setLocation(400, 300);
//		super.add(con);
		super.setDefaultCloseOperation(HIDE_ON_CLOSE);
		super.setIconImage(icone.getImage());
		setAtributos();
		carregarContainer();
		iniciaTray();
	}

	private void iniciaTray(){

		Font fonte = new Font("ARIAL", Font.PLAIN, 11);
		
		try	{
			trayIcon = new TrayIcon(icone.getImage());
			trayIcon.setImageAutoSize(true);
			trayIcon.setToolTip("Visual Mensagem");
			tray.add(trayIcon);
			MenuItem m = new MenuItem("Visual Mensagem");
			m.setEnabled(false);
			m.setFont(fonte);
			popup.add(m);
	
			popup.addSeparator();
			m = new MenuItem("File");
			m.setFont(fonte);
			popup.add(m);
			
			popup.addSeparator();
			m = new MenuItem("Status");
			m.setFont(fonte);
			popup.add(m);
	
			m = new MenuItem("IP");
			m.setFont(fonte);
			popup.add(m);
	
			popup.addSeparator();
			m = new MenuItem("Nick");
			m.setFont(fonte);
			popup.add(m);
	
			m = new MenuItem("Autoriza#");
			m.setFont(fonte);
			popup.add(m);
	
			m = new MenuItem("Mascarado");
			m.setFont(fonte);
			popup.add(m);
	
			popup.addSeparator();
			
			m = new MenuItem("Skin");
			m.setFont(fonte);
			popup.add(m);
			
			m = new MenuItem("Cores");
			m.setFont(fonte);
			popup.add(m);
			
			m = new MenuItem("Font");
			m.setFont(fonte);
			popup.add(m);
			
			m = new MenuItem("Show");
			m.setFont(new Font("ARIAL", Font.BOLD, 11));
			popup.add(m);
	
			popup.addSeparator();
			m = new MenuItem("Exit");
			m.setFont(fonte);
			popup.add(m);
	
			trayIcon.setPopupMenu(popup);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void carregarContainer(){
		pPanel.setLayout(null);
		pPanel.add(lblTexto);
		pPanel.add(txtTexto);
		pPanel.add(lblContatos);
		pPanel.add(cmbContato);
		pPanel.add(btnEnviar);
		pPanel.add(btnAdd);
		pPanel.add(btnDel);
		pPanel.add(btnHist);
		pPanel.add(btnOpcoes);
		pPanel.add(popup);
		pPanel.add(spHist);
		super.getContentPane().add(pPanel);
	}

	private void setAtributos(){
		lblContatos.setSize(100, 25);
		lblContatos.setLocation(10,10);

		cmbContato.setSize(280, 25);
		cmbContato.setLocation(80, 10);
		cmbContato.setFocusable(false);

		lblTexto.setSize(100, 25);
		lblTexto.setLocation(10, 40);
		
		txtTexto.setSize(280, 25);
		txtTexto.setLocation(80, 40);

		btnEnviar.setSize(190, 25);
		btnEnviar.setLocation(169, 70);
		btnEnviar.setFocusable(false);

		btnAdd.setSize(70, 25);
		btnAdd.setLocation(380, 10);
		btnAdd.setFocusable(false);
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/img/add.png")));

		btnDel.setSize(70, 25);
		btnDel.setLocation(380, 40);
		btnDel.setFocusable(false);
		btnDel.setIcon(new ImageIcon(getClass().getResource("/img/delete.png")));

		btnHist.setSize(80, 25);
		btnHist.setLocation(80, 70);
		btnHist.setIcon(new ImageIcon(getClass().getResource("/img/btnHist.png")));
		btnHist.setPressedIcon(new ImageIcon(getClass().getResource("/img/btnHist2.png")));
		btnHist.setFocusable(false);
		
		btnOpcoes.setSize(15, 85);
		btnOpcoes.setText(">");
		btnOpcoes.setLocation(362, 10);
		btnOpcoes.setMargin(new Insets(0,0,0,0));
		btnOpcoes.setBorder(BorderFactory.createBevelBorder(0));
		btnOpcoes.setFocusable(false);

		spHist.setSize(super.getSize().width - 1, 100);
		spHist.setLocation(0, 100);
		txtHist.setEditable(false);
	}
}
