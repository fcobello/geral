package br.com.fc.cadastro.telas;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import br.com.fc.business.pacientes.Pacientes;
import br.com.fc.db.SQLite;
import br.com.fc.utils.Utils;

public class FrmPesquisa implements ActionListener, MouseListener, KeyListener
{
	private JDialog dlgPesquisa = new JDialog();
	private JLabel lblNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField(30);
	private JButton btnVer = new JButton(new ImageIcon(getClass().getResource("/img/pesquisa.png")));
	private JTable tblResultados = new JTable();
	private JButton btnOk = new JButton("OK");
	private JScrollPane spTable = new JScrollPane(tblResultados);
	private Pacientes objPacientes = new Pacientes(SQLite.getConexaoClinica());
	private String retorno = "0";
	
	public FrmPesquisa()
	{
		//Tela
		dlgPesquisa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dlgPesquisa.setLayout(new FlowLayout(FlowLayout.CENTER));
		dlgPesquisa.setSize(500, 425);
		dlgPesquisa.setLocation((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getX() - dlgPesquisa.getSize().getWidth()/2), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getY() - dlgPesquisa.getSize().getHeight()/2));
		dlgPesquisa.setResizable(false);
		dlgPesquisa.setLayout(null);
		dlgPesquisa.setTitle("MedicalIT - Pesquisa de Pacientes");
		dlgPesquisa.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/pesquisa.png")));
		//Pesquisa
		lblNome.setSize(100, 25);
		lblNome.setLocation(10, 10);
		dlgPesquisa.add(lblNome);
		txtNome.setSize(350, 25);
		txtNome.setLocation(60, 10);
		txtNome.addKeyListener(this);
		dlgPesquisa.add(txtNome);
		btnVer.setMargin(new Insets(0, 0, 0, 0));
		btnVer.setSize(50, 25);
		btnVer.setLocation(420, 10);
		btnVer.addActionListener(this);
		dlgPesquisa.add(btnVer);
		//Resultados
		spTable.setBorder(BorderFactory.createEtchedBorder());
		spTable.setSize(475, 315);
		spTable.setLocation(10, 50);
		tblResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblResultados.addMouseListener(this);
		tblResultados.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("ENTER"), "enterEvent");
		tblResultados.getActionMap().put("enterEvent", enterEvent()); 
		dlgPesquisa.add(spTable);
		btnOk.setSize(100, 25);
		btnOk.setLocation(185, 368);
		btnOk.addActionListener(this);
		dlgPesquisa.add(btnOk);
	}
	
	private AbstractAction enterEvent()
	{
		AbstractAction retorno = new AbstractAction()
		{
			private static final long serialVersionUID = -5197547521753841264L;

			@Override
			public void actionPerformed(ActionEvent e)
			{
				selecionar();			
			}
		};
		return retorno;
	}
	
	public String showDialog()
	{
		retorno = "0";
		dlgPesquisa.setModal(true);
		dlgPesquisa.setVisible(true);
		return retorno;
	}
	
	private void selecionar()
	{
		if(tblResultados.getSelectedRow() != -1)
			retorno = (String) tblResultados.getModel().getValueAt(tblResultados.getSelectedRow(), 0);
		dlgPesquisa.dispose();
	}
	
	public void carregarTable()
	{
		DefaultTableModel model;
		List<Vector<String>> registros;
		
		try
		{
			model = new DefaultTableModel(new String[]{"Código", "Nome", "Data Nascimento"}, 0)
			{
				private static final long serialVersionUID = 2460653029557795249L;

				@Override
				public boolean isCellEditable(int row, int col)
				{
					return false;
				}
			};
			registros = objPacientes.pesquisa(txtNome.getText());
			for (int i = 0; i < registros.size(); i++)
			{
				model.addRow(registros.get(i));
			}
			tblResultados.setModel(model);
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(dlgPesquisa, e.getMessage(), "Erro Pesquisa", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Pesquisa - " + e.getMessage());
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(dlgPesquisa, e.getMessage(), "Erro Pesquisa", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Pesquisa - " + e.getMessage());
			e.printStackTrace();
		}
		catch (ParseException e)
		{
			JOptionPane.showMessageDialog(dlgPesquisa, e.getMessage(), "Erro Pesquisa", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Pesquisa - " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnVer))
		{
			carregarTable();
			tblResultados.requestFocusInWindow();
			tblResultados.getSelectionModel().setSelectionInterval(0, 0);
		}
		if(e.getSource().equals(btnOk))
			selecionar();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		if(e.getSource().equals(tblResultados))
		{
			if(e.getClickCount() == 2)
			{
				selecionar();
			}
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if(e.getSource().equals(txtNome))
		{
			carregarTable();
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getSource().equals(txtNome))
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				tblResultados.requestFocusInWindow();
				tblResultados.getSelectionModel().setSelectionInterval(0, 0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}
}
