package br.com.fc.importer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import br.com.fc.db.SQLite;
import br.com.fc.utils.Utils;

public class FrmImportacao extends SwingWorker<Void, Void>
{
	private JInternalFrame frmTela = new JInternalFrame("MedicalIT - Importação Paradox - SQLite");
	private JButton btnIniciar = new JButton("Iniciar");
	private JButton btnCancelar = new JButton("Cancelar");
	private JProgressBar pbImportacao = new JProgressBar();
	private JPanel pBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private Importacao objImportacao;
	private Events events = new Events();
	
	public FrmImportacao(JDesktopPane desktopPane)
	{
		objImportacao = new Importacao(SQLite.getConexaoClinica());
		desktopPane.add(frmTela);
		frmTela.setLayout(new BorderLayout());
		frmTela.setSize(400, 110);
		frmTela.setFrameIcon(new ImageIcon(getClass().getResource("/img/import.png")));
		frmTela.setLocation((desktopPane.getWidth() - frmTela.getWidth())/2, (desktopPane.getHeight() - frmTela.getHeight())/2);
		frmTela.setClosable(true);
		frmTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTela.setResizable(false);
		//ProgressBar
		pbImportacao.setPreferredSize(new Dimension(0, 30));
		pbImportacao.setStringPainted(true);
		//Botoes
		btnIniciar.setPreferredSize(new Dimension(100, 25));
		btnIniciar.addActionListener(events);
		btnCancelar.setPreferredSize(new Dimension(100, 25));
		btnCancelar.addActionListener(events);
		pBotoes.add(btnIniciar);
		pBotoes.add(btnCancelar);
		frmTela.add(pbImportacao, BorderLayout.NORTH);
		frmTela.add(pBotoes, BorderLayout.SOUTH);
		frmTela.setVisible(true);
	}
	
	public void iniciar() throws IOException
	{
		JFileChooser fileChooser = new JFileChooser();
		
		Utils.copyFile(new File("Clinica.s3db"), new File("Bkp_Clinica.s3db"));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setMultiSelectionEnabled(false);
		if(fileChooser.showOpenDialog(frmTela) == JFileChooser.APPROVE_OPTION)
		{
			objImportacao.pathParadox = fileChooser.getSelectedFile().getPath();
			btnIniciar.setEnabled(false);
			super.execute();
			new Thread(new Runnable()
			{
				@Override
				public void run()
				{
					try
					{
						objImportacao.importarPacientes();
						objImportacao.importarProntuarios();
					}
					catch (Exception e)
					{
						JOptionPane.showMessageDialog(frmTela, e.getMessage(), "Erro Iniciar", JOptionPane.ERROR_MESSAGE);
						Utils.writeFile("Erro Importacao - " + e.getMessage());
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
	
	private void cancelar()
	{
		super.cancel(true);
		frmTela.dispose();
	}

	@Override
	protected synchronized Void doInBackground() throws Exception
	{
		try
		{
			pbImportacao.setMaximum(100);
			while(pbImportacao.getValue()/100 < pbImportacao.getMaximum())
			{
				pbImportacao.setValue(objImportacao.count/100);
				pbImportacao.setString(String.valueOf(pbImportacao.getValue()));
				wait(1000);
			}
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(frmTela, e.getMessage(), "Erro Importação", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Importacao - " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	private class Events implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(btnIniciar))
			{
				try
				{
					if(JOptionPane.showConfirmDialog(frmTela, "Atenção:\nTodos os Dados Atuais serão Sobrescritos durante a Importação\nDeseja Continuar?", "Importação", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION)
						iniciar();
				}
				catch (IOException ex) 
				{
					JOptionPane.showMessageDialog(frmTela, ex.getMessage(), "Erro Importacao", JOptionPane.ERROR_MESSAGE);
					Utils.writeFile("Erro Importacao - " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			if(e.getSource().equals(btnCancelar))
			{
				cancelar();
			}
		}
	}
}
