package br.com.medicalit.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.SwingWorker;
import br.com.medicalit.application.Conexao;
import br.com.medicalit.tela.Tela;

public class Principal extends SwingWorker<Void, Void>
{
	public Tela objTela = new Tela();
	private Conexao objConexao;
	
	public Principal()
	{
		objTela.btnExecutar.addActionListener(new Events());
		objTela.show();
	}
	
	public void iniciar()
	{
		super.execute();
	}
	
	private void executar()
	{		
		File[] arquivos;
				
		if(objTela.fileChooser.showOpenDialog(objTela.frame) == JFileChooser.APPROVE_OPTION)
		{
			objConexao = new Conexao();
			arquivos = objTela.fileChooser.getSelectedFile().listFiles();
			objTela.pbProgresso.setMaximum(arquivos.length);
			for (File i : arquivos)
			{
				executar(i);
				objTela.pbProgresso.setValue(objTela.pbProgresso.getValue() + 1);
			}
			super.cancel(true);
			objConexao.closeConexao();
		}
	}
	
	private void executar(File arquivo)
	{	
		try
		{
			objTela.txtList.setText(objTela.txtList.getText() +  arquivo.getName());
			objConexao.executeScript(arquivo);
			objTela.txtList.setText(objTela.txtList.getText() + " - OK \n");
		}
		catch (SQLException e)
		{
			objTela.txtList.setText(objTela.txtList.getText() + " - Erro (" + e.getMessage() + ")\n");
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected Void doInBackground() throws Exception
	{
		executar();
		return null;
	}
	
	private class Events implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			iniciar();
		}
	}
	
	public static void main(String[] args)
	{
		new Principal();
	}
}
