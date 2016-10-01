package br.com.fc.cadastro.telas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import br.com.fc.business.pacientes.Pacientes;
import br.com.fc.business.prontuario.Prontuario;
import br.com.fc.db.SQLite;
import com.toedter.calendar.JDateChooser;

public class FrmProntuario implements ActionListener
{
	private JDialog frmTela = new JDialog();
	private JPanel pBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JButton btnNovo = new JButton("Novo", new ImageIcon(getClass().getResource("/img/doc.png")));
	private JTextArea txtProntuario = new JTextArea();
	private JScrollPane spProntuario = new JScrollPane(txtProntuario);
	private int idPaciente;

	public FrmProntuario()
	{
		frmTela.setLayout(new BorderLayout());
		//Botoes
		btnNovo.addActionListener(this);
		pBotoes.add(btnNovo);
		frmTela.add(pBotoes, BorderLayout.NORTH);
		//Prontuario
		txtProntuario.setEditable(false);
		txtProntuario.setLineWrap(true);
		frmTela.add(spProntuario, BorderLayout.CENTER);
		//Tela
		frmTela.setTitle("Medical IT - Manutenção de Prontuário");
		frmTela.setModal(true);
		frmTela.setSize(500, 500);
		frmTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTela.setLocation((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getX() - frmTela.getWidth()/2), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getY() - frmTela.getSize().getHeight()/2));
		frmTela.setIconImage(frmTela.getToolkit().getImage(getClass().getResource("/img/edit.png")));
	}
	
	public void show(int idPaciente)
	{
		this.idPaciente = idPaciente;
		carregarTela();
		frmTela.setVisible(true);
	}
	
	private void carregarTela()
	{
		Pacientes objPacientes = new Pacientes(SQLite.getConexaoClinica());
		Prontuario objProntuario = new Prontuario(SQLite.getConexaoClinica());
		
		try
		{
			objPacientes.editar(idPaciente);
			objProntuario.editar(idPaciente);
			txtProntuario.setText("Paciente: " + idPaciente +  " - " + objPacientes.getNome() + "\n\n" + objProntuario.getTexto());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void btnNovo()
	{
		new FrmProntEditor().show(idPaciente);
		show(idPaciente);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnNovo))
		{
			btnNovo();
		}
	}
	
	private class FrmProntEditor implements ActionListener
	{
		private JDialog frmTela = new JDialog();
		private JToolBar toolBar = new JToolBar();
		private JButton btnSalvar = new JButton(new ImageIcon(getClass().getResource("/img/floppy.png")));
		private JButton btnCancelar = new JButton(new ImageIcon(getClass().getResource("/img/cancelar.png")));
		private JLabel lblData = new JLabel("Data"); 
		private JDateChooser txtData = new JDateChooser();
		private JEditorPane txtTexto = new JEditorPane();
		private Prontuario objProntuario = new Prontuario(SQLite.getConexaoClinica());
		private int idPaciente = 0;
		
		public FrmProntEditor()
		{
			frmTela.setTitle("Medical IT - Edição de Prontuário");
			frmTela.setSize(500, 350);
			frmTela.setLocation((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getX() - frmTela.getWidth()/2), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getY() - frmTela.getSize().getHeight()/2));
			frmTela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			frmTela.setIconImage(frmTela.getToolkit().getImage(getClass().getResource("/img/edit.png")));
			//Salvar
			btnSalvar.setMargin(new Insets(0,0,0,0));
			btnSalvar.setToolTipText("Salvar");
			btnSalvar.addActionListener(this);
			//Cancelar
			btnCancelar.setMargin(new Insets(0,0,0,0));
			btnCancelar.setToolTipText("Cancelar");
			btnCancelar.addActionListener(this);
			//Data
			txtData.setDate(new Date());
			//Texto
			txtTexto.setBorder(BorderFactory.createEtchedBorder());
			//ToolBar
			toolBar.setFloatable(false);
			toolBar.add(btnSalvar);
			toolBar.add(btnCancelar);
			toolBar.add(lblData);
			toolBar.add(txtData);
			frmTela.add(toolBar, BorderLayout.NORTH);
			frmTela.add(txtTexto, BorderLayout.CENTER);
		}
		
		public void show(int idPaciente)
		{
			this.idPaciente = idPaciente;
			frmTela.setModal(true);
			frmTela.setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(btnSalvar))
			{
				try
				{
					objProntuario.setInclusao();
					objProntuario.setIdPaciente(idPaciente);
					objProntuario.setData(txtData.getDate());
					objProntuario.setTexto(txtTexto.getText());
					objProntuario.salvar();
					frmTela.dispose();
				}
				catch (SQLException e1)
				{
					e1.printStackTrace();
				}
				catch (ClassNotFoundException e1)
				{
					e1.printStackTrace();
				}
			}
			if(e.getSource().equals(btnCancelar))
			{
				txtData.cleanup();
				txtTexto.setText("");
			}
		}
	}
}
