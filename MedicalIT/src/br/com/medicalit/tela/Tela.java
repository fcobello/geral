package br.com.medicalit.tela;

import java.awt.GraphicsEnvironment;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Tela
{
	public JFrame frame = new JFrame("Medical IT - SQL Script Runner");
	public JFileChooser fileChooser = new JFileChooser();
	public JTextArea txtList = new JTextArea();
	public JScrollPane scrollPane = new JScrollPane(txtList);
	public JButton btnExecutar = new JButton("Executar");
	public JProgressBar pbProgresso = new JProgressBar();
	
	public Tela()
	{
		frame.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint());
		frame.setLayout(null);
		frame.setSize(400, 290);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(btnExecutar);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(pbProgresso);
		//Lista
		scrollPane.setLocation(10, 10);
		scrollPane.setSize(375, 200);
		txtList.setEditable(false);
		//Botao
		btnExecutar.setSize(100, 25);
		btnExecutar.setLocation(150, 230);
		//ProgressBar
		pbProgresso.setSize(375, 15);
		pbProgresso.setLocation(10, 210);
		//FileChooser
		fileChooser.setCurrentDirectory(new File("C:/"));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}
	
	public void show()
	{
		frame.setVisible(true);
	}
}
