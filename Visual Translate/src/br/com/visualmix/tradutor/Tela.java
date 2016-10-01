package br.com.visualmix.tradutor;

import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import com.google.api.translate.Language;

public class Tela extends JFrame
{
	private static final long serialVersionUID = -1577128447895506919L;
	protected float version = 1.9f;
	protected JLabel lblIdioma = new JLabel("Idioma:");
	protected JCheckBox ckbRealTime = new JCheckBox("Simultânea", false);
	protected JComboBox cmbIdiomaOri = new JComboBox();
	protected JComboBox cmbIdiomaDest = new JComboBox();
	protected JTextPane txtTexto = new JTextPane();
	protected JScrollPane spTexto = new JScrollPane(txtTexto);
	protected JLabel lblTraducao = new JLabel("Tradução:");
	protected JTextPane txtTraducao = new JTextPane();
	protected JScrollPane spTraducao = new JScrollPane(txtTraducao);
	protected JButton btnViceVersa = new JButton("<>");
	protected JButton btnTraduzir = new JButton("Translate");
	
	public Tela()
	{
		super.setResizable(false);
		super.setLayout(null);
		super.setTitle("Visual Translate " + version);
		super.setIconImage(new ImageIcon(getClass().getResource("/img/icone.png")).getImage());
		super.setSize(340, 260);
		super.setLocation(150, 150);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setAtributes();
		setObjects();
		setEvents();
	}
	
	private void setAtributes()
	{
		//Idioma
		lblIdioma.setSize(100, 25);
		lblIdioma.setLocation(10, 5);
		//Simultanea
		ckbRealTime.setSize(100, 20);
		ckbRealTime.setLocation(240, 05);
		ckbRealTime.setBorderPainted(false);
		ckbRealTime.setSelected(true);
		//Idioma Origem
		cmbIdiomaOri.setSize(130, 25);
		cmbIdiomaOri.setLocation(10, 30);
		//Vice Versa
		btnViceVersa.setSize(45, 25);
		btnViceVersa.setMargin(new Insets(0, 0, 0, 0));
		btnViceVersa.setLocation(145, 30);
		//Idioma Destino
		cmbIdiomaDest.setSize(130, 25);
		cmbIdiomaDest.setLocation(195, 30);
		//Texto
		spTexto.setSize(315, 60);
		spTexto.setLocation(10, 60);
		spTexto.setBorder(BorderFactory.createEtchedBorder());
		//Tradução
		lblTraducao.setSize(100, 25);
		lblTraducao.setLocation(10, 115);
		spTraducao.setSize(315, 60);
		spTraducao.setLocation(10, 140);
		spTraducao.setBorder(BorderFactory.createEtchedBorder());
		txtTraducao.setEditable(false);
		//Traduzir
		btnTraduzir.setSize(315, 25);
		btnTraduzir.setLocation(10, 205);
		//Carregar idiomas
		for (int i = 0; i < Language.values().length; i++) {
			cmbIdiomaOri.addItem(Language.values()[i].name());
			cmbIdiomaDest.addItem(Language.values()[i].name());
		}
		cmbIdiomaOri.setSelectedIndex(Language.ENGLISH.ordinal());
		cmbIdiomaDest.setSelectedIndex(Language.PORTUGUESE.ordinal());
	}
	
	private void setObjects()
	{
		super.getContentPane().add(lblIdioma);
		super.getContentPane().add(ckbRealTime);
		super.getContentPane().add(cmbIdiomaOri);
		super.getContentPane().add(btnViceVersa);
		super.getContentPane().add(cmbIdiomaDest);
		super.getContentPane().add(lblTraducao);
		super.getContentPane().add(spTexto);
		super.getContentPane().add(spTraducao);
		super.getContentPane().add(btnTraduzir);
	}
	
	private void setEvents()
	{
		Events events = new Events();
		
		btnViceVersa.addActionListener(events);
		txtTexto.addKeyListener(events);
		btnTraduzir.addActionListener(events);
		btnTraduzir.addKeyListener(events);
	}
}
