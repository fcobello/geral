package br.com.visualmix.tela;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Tela
{
	private JFrame frmTela = new JFrame("MP3 ID3");
	private JButton btnAbrir = new JButton("Abrir");
	private JTextArea txtLista = new JTextArea();
	private JPanel pRodape = new JPanel();
	private JButton btnId3toName = new JButton("ID3 > Name");
	private JButton btnNametoId3 = new JButton("Name > ID3");
	
	public Tela()
	{
		frmTela.setSize(500, 500);
		frmTela.setVisible(true);
		frmTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Lista
		txtLista.setBorder(BorderFactory.createEtchedBorder());
		frmTela.add(btnAbrir, BorderLayout.NORTH);
		frmTela.add(txtLista, BorderLayout.CENTER);
		pRodape.add(btnId3toName);
		pRodape.add(btnNametoId3);
		frmTela.add(pRodape, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args)
	{
		new Tela();
	}
}
