package br.com.java;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class ContadorLinhas extends SwingWorker<Void, Void>
{
	private JDialog tela = new JDialog();
	private JProgressBar pb = new JProgressBar();
	private JLabel lbl = new JLabel();
	private File diretorio;
	private int nroArquivos = 0;
	private long nroLinhas = 0;
	
	public ContadorLinhas()
	{
		//Tela
		tela.setTitle("Contar Linhas 2.0");
		tela.setLayout(new FlowLayout(FlowLayout.CENTER));
		tela.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint());
		tela.setSize(200, 80);
		tela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		tela.getContentPane().add(pb);
		tela.getContentPane().add(lbl);
		tela.setVisible(true);
	}
	
	public void contarArquivos(File diretorio)
	{
		File[] arquivos;
		
		arquivos = diretorio.listFiles();
		for (int i = 0; i < arquivos.length; i++)
		{
			if(arquivos[i].isDirectory())
				contarArquivos(arquivos[i]);
			else
			{
				if(arquivos[i].getName().endsWith(".java"))
					nroArquivos++;
			}
		}
	}
	
	public long contarLinhas(File diretorio) throws IOException
	{
		if(diretorio.isDirectory())
		{
			lerDiretorio(diretorio);
		}
		return nroLinhas;
	}
	
	private void lerArquivos(File arquivo) throws IOException
	{
		BufferedReader reader;
		
		if(arquivo.getName().endsWith(".java"))
		{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));
			while(reader.readLine() != null)
				nroLinhas++;
			reader.close();
			lbl.setText(String.valueOf(nroLinhas));
			pb.setValue(pb.getValue() + 1);
		}
		reader = null;
	}
	
	private void lerDiretorio(File diretorio) throws IOException
	{
		File[] arquivos;
		
		arquivos = diretorio.listFiles();
		for (int i = 0; i < arquivos.length; i++)
		{
			if(arquivos[i].isDirectory())
				lerDiretorio(arquivos[i]);
			else
				lerArquivos(arquivos[i]);
		}
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		contarLinhas(diretorio);
		return null;
	}
	
	public static void main(String[] args)
	{
		JFileChooser chooser = new JFileChooser();
		ContadorLinhas cont;
		
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				cont = new ContadorLinhas();
				cont.diretorio = chooser.getSelectedFile();
				cont.contarArquivos(cont.diretorio);
				cont.pb.setMaximum(cont.nroArquivos);
				cont.run();
			}
	}
}
