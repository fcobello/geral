package br.com.fc.cadastro.telas;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.sf.jasperreports.view.JasperViewer;
import br.com.fc.business.pacientes.Pacientes;
import br.com.fc.db.SQLite;
import br.com.fc.relatorio.Ficha;
import br.com.fc.utils.Picture;
import br.com.fc.utils.Utils;
import br.com.fc.utils.Validator;
import com.toedter.calendar.JDateChooser;

public class FrmPacientes
{
	private JInternalFrame frmTela = new JInternalFrame("Medical IT - Cadastro de Pacientes");
	//Botoes
	private JPanel pBotoes = new JPanel();
	private JButton btnSalvar = new JButton("Salvar", new ImageIcon(getClass().getResource("/img/floppy.png")));
	private JButton btnExcluir = new JButton("Excluir", new ImageIcon(getClass().getResource("/img/excluir.png")));
	private JButton btnCancelar = new JButton("Cancelar", new ImageIcon(getClass().getResource("/img/cancelar.png")));
	private JButton btnImprimir = new JButton("Imprimir", new ImageIcon(getClass().getResource("/img/imprimir.png")));
	private JButton btnProntuario = new JButton("Prontuário", new ImageIcon(getClass().getResource("/img/edit.png")));
	private JPanel pCampos = new JPanel();
	//Codigo
	private JLabel lblCodigo = new JLabel("Código");
	private JTextField txtCodigo = new JTextField();
	//Botao Pesquisa
	private JButton btnPesqPaciente = new JButton(new ImageIcon(getClass().getResource("/img/pesquisa.png")));
	//Nome
	private JLabel lblNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField();
	//Data Nasc
	private JLabel lblDataNasc = new JLabel("Nascimento");
	private JDateChooser txtDataNasc = new JDateChooser();
	//Idade
	private JLabel lblIdade = new JLabel("Idade");
	//Sexo
	private JLabel lblSexo = new JLabel("Sexo");
	private JComboBox<String> cmbSexo = new JComboBox<String>();
	//Estado Civil
	private JLabel lblEstadoCivil = new JLabel("Estado Civil");
	private JComboBox<String> cmbEstadoCivil = new JComboBox<String>();
	//Profissao
	private JLabel lblProfissao = new JLabel("Profissão");
	private JTextField txtProfissao = new JTextField();
	//Endereco
	private JLabel lblEndereco = new JLabel("Endereço");
	private JTextField txtEndereco = new JTextField();
	private JButton btnMap = new JButton(new ImageIcon(getClass().getResource("/img/maps.png")));
	private JLabel lblCidade = new JLabel("Cidade");
	private JTextField txtCidade = new JTextField();
	private JLabel lblBairro = new JLabel("Bairro");
	private JTextField txtBairro = new JTextField();
	private JLabel lblCEP = new JLabel("CEP");
	private JTextField txtCEP = new JTextField();
	private JLabel lblEstado = new JLabel("Estado");
	private JComboBox<String> cmbEstado = new JComboBox<String>();
	//Telefone
	private JLabel lblTelefone = new JLabel("Telefone");
	private JTextField txtTelefone = new JTextField();
	private JLabel lblCelular = new JLabel("Celular");
	private JTextField txtCelular = new JTextField();
	//Data Consulta
	private JLabel lblDataConsulta = new JLabel("Consulta");
	private JDateChooser txtDataConsulta = new JDateChooser(new Date(System.currentTimeMillis()));
	//Convenio
	private JLabel lblConvenio = new JLabel("Convênio");
	private JTextField txtConvenio = new JTextField();
	private Events events = new Events();
	private Pacientes objPacientes;
	//Validator
	private Validator objValidator = new Validator();
	//Mapa
	private JDialog dlgMapa = new JDialog();
	private Picture picMapa = new Picture();
	
	public FrmPacientes(JDesktopPane desktopPane)
	{
		objPacientes = new Pacientes(SQLite.getConexaoClinica());
		//Tela
		desktopPane.add(frmTela);
		frmTela.setClosable(true);
		frmTela.setLayout(new BorderLayout());
		frmTela.setSize(600, 400);
		frmTela.setFrameIcon(new ImageIcon(getClass().getResource("/img/novo.png")));
		frmTela.setLocation((desktopPane.getWidth() - frmTela.getWidth())/2, (desktopPane.getHeight() - frmTela.getHeight())/2);
		frmTela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTela.setResizable(false);
		//Botoes
		btnCancelar.addActionListener(events);
		btnSalvar.addActionListener(events);
		btnImprimir.addActionListener(events);
		btnExcluir.addActionListener(events);
		btnProntuario.addActionListener(events);
		pBotoes.add(btnSalvar);
		pBotoes.add(btnExcluir);
		pBotoes.add(btnCancelar);
		pBotoes.add(btnImprimir);
		pBotoes.add(btnProntuario);
		frmTela.add(pBotoes, BorderLayout.NORTH);
		//->Campos
		pCampos.setLayout(null);
		//Código
		lblCodigo.setLocation(10, 10);
		lblCodigo.setSize(100, 25);
		pCampos.add(lblCodigo);
		txtCodigo.setLocation(10, 35);
		txtCodigo.setSize(100, 25);
		txtCodigo.setEnabled(false);
		pCampos.add(txtCodigo);
		//Pesquisa
		btnPesqPaciente.setSize(30, 27);
		btnPesqPaciente.setLocation(115, 35);
		btnPesqPaciente.addActionListener(events);
		pCampos.add(btnPesqPaciente);
		//Nome
		lblNome.setSize(100, 25);
		lblNome.setLocation(10, 60);
		pCampos.add(lblNome);
		txtNome.addKeyListener(events);
		txtNome.setSize(573, 25);
		txtNome.setLocation(10, 85);
		objValidator.addIsFilled(txtNome);
		pCampos.add(txtNome);
		//DataNasc
		lblDataNasc.setSize(100, 25);
		lblDataNasc.setLocation(10, 110);
		pCampos.add(lblDataNasc);
		txtDataNasc.setSize(100, 25);
		txtDataNasc.setLocation(10, 135);
		txtDataNasc.addPropertyChangeListener(events);
		objValidator.addIsFilled(txtDataNasc);
		pCampos.add(txtDataNasc);
		//Idade
		lblIdade.setFont(new Font(lblIdade.getFont().getName(), 0, 9));
		lblIdade.setSize(100, 25);
		lblIdade.setLocation(112, 135);
		pCampos.add(lblIdade);
		//Sexo
		lblSexo.setSize(100, 25);
		lblSexo.setLocation(150, 110);
		pCampos.add(lblSexo);
		cmbSexo.setSize(80, 25);
		cmbSexo.setLocation(150, 135);
		objValidator.addIsSelected(cmbSexo);
		pCampos.add(cmbSexo);
		//Estado Civil
		lblEstadoCivil.setSize(100, 25);
		lblEstadoCivil.setLocation(250, 110);
		pCampos.add(lblEstadoCivil);
		cmbEstadoCivil.setSize(100, 25);
		cmbEstadoCivil.setLocation(250, 135);
		objValidator.addIsSelected(cmbEstadoCivil);
		pCampos.add(cmbEstadoCivil);
		//Profissao
		lblProfissao.setSize(100, 25);
		lblProfissao.setLocation(365, 110);
		pCampos.add(lblProfissao);
		txtProfissao.setSize(215, 25);
		txtProfissao.setLocation(365, 135);
		pCampos.add(txtProfissao);
		//Endereço
		lblEndereco.setSize(100, 25);
		lblEndereco.setLocation(10, 160);
		pCampos.add(lblEndereco);
		txtEndereco.setSize(542, 25);
		txtEndereco.setLocation(10, 185);
		btnMap.setSize(25, 25);
		btnMap.setLocation(555, 185);
		btnMap.addActionListener(events);
		pCampos.add(btnMap);
		objValidator.addIsFilled(txtEndereco);
		pCampos.add(txtEndereco);
		//Bairro
		lblBairro.setSize(112, 25);
		lblBairro.setLocation(10, 210);
		pCampos.add(lblBairro);
		txtBairro.setSize(182, 25);
		txtBairro.setLocation(10, 235);
		objValidator.addIsFilled(txtBairro);
		pCampos.add(txtBairro);
		//Cidade
		lblCidade.setSize(112,25);
		lblCidade.setLocation(202, 210);
		pCampos.add(lblCidade);
		txtCidade.setSize(120, 25);
		txtCidade.setLocation(202, 235);
		objValidator.addIsFilled(txtCidade);
		pCampos.add(txtCidade);
		//CEP
		lblCEP.setSize(112, 25);
		lblCEP.setLocation(330, 210);
		pCampos.add(lblCEP);
		txtCEP.setSize(112, 25);
		txtCEP.setLocation(330, 235);
		pCampos.add(txtCEP);
		//Estado
		lblEstado.setSize(112, 25);
		lblEstado.setLocation(448, 210);
		pCampos.add(lblEstado);
		cmbEstado.setSize(133, 25);
		cmbEstado.setLocation(448, 235);
		pCampos.add(cmbEstado);
		//Telefone
		lblTelefone.setSize(135, 25);
		lblTelefone.setLocation(10, 260);
		pCampos.add(lblTelefone);
		txtTelefone.setSize(135, 25);
		txtTelefone.setLocation(10, 285);
		objValidator.addIsFilled(txtTelefone);
		pCampos.add(txtTelefone);
		//Celular
		lblCelular.setSize(135, 25);
		lblCelular.setLocation(150, 260);
		pCampos.add(lblCelular);
		txtCelular.setSize(135, 25);
		txtCelular.setLocation(150, 285);
		pCampos.add(txtCelular);
		//Convenio
		lblConvenio.setSize(135, 25);
		lblConvenio.setLocation(290, 260);
		pCampos.add(lblConvenio);
		txtConvenio.setSize(150, 25);
		txtConvenio.setLocation(290, 285);
		objValidator.addIsFilled(txtConvenio);
		pCampos.add(txtConvenio);
		//Data Consulta
		lblDataConsulta.setSize(135, 25);
		lblDataConsulta.setLocation(445, 260);
		pCampos.add(lblDataConsulta);
		txtDataConsulta.setSize(135, 25);
		txtDataConsulta.setLocation(445, 285);
		objValidator.addIsFilled(txtDataConsulta);
		pCampos.add(txtDataConsulta);
		//Mapa
		dlgMapa.setIconImage(frmTela.getToolkit().getImage(getClass().getResource("/img/maps.png")));
		dlgMapa.setTitle("Localização");
		dlgMapa.setModal(true);
		dlgMapa.setSize(250, 260);
		dlgMapa.add(picMapa);
		dlgMapa.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//Tela
		frmTela.add(pCampos, BorderLayout.CENTER);
		carregarCombos();
		frmTela.setVisible(true);
		btnCancelar();
	}
	
	private void habilitarObjetos()
	{
		btnSalvar.setEnabled(objPacientes.isInclusao() || objPacientes.isEdicao());
		btnExcluir.setEnabled(objPacientes.isEdicao());
		btnCancelar.setEnabled(objPacientes.isInclusao() || objPacientes.isEdicao());
		btnImprimir.setEnabled(objPacientes.isEdicao());
		btnProntuario.setEnabled(objPacientes.isEdicao());
	}
	
	private void carregarCombos()
	{
		//Sexo
		cmbSexo.addItem("Masculino");
		cmbSexo.addItem("Feminino");
		//Estado Civil
		cmbEstadoCivil.addItem("Solteiro(a)");
		cmbEstadoCivil.addItem("Casado(a)");
		cmbEstadoCivil.addItem("Viúvo(a)");
		cmbEstadoCivil.addItem("Divorciado(a)");
		//Estados
		cmbEstado.addItem("AC");
		cmbEstado.addItem("AL");
		cmbEstado.addItem("AP");
		cmbEstado.addItem("AM");
		cmbEstado.addItem("BA");
		cmbEstado.addItem("CE");
		cmbEstado.addItem("DF");
		cmbEstado.addItem("ES");
		cmbEstado.addItem("GO");
		cmbEstado.addItem("MA");
		cmbEstado.addItem("MT");
		cmbEstado.addItem("MS");
		cmbEstado.addItem("MG");
		cmbEstado.addItem("PA");
		cmbEstado.addItem("PB");
		cmbEstado.addItem("PR");
		cmbEstado.addItem("PE");
		cmbEstado.addItem("PI");
		cmbEstado.addItem("RJ");
		cmbEstado.addItem("RN");
		cmbEstado.addItem("RS");
		cmbEstado.addItem("RO");
		cmbEstado.addItem("RR");
		cmbEstado.addItem("SC");
		cmbEstado.addItem("SP");
		cmbEstado.addItem("SE");
		cmbEstado.addItem("TO");
	}
	
	private void carregarTela()
	{
		objValidator.clear();
		txtCodigo.setText(String.valueOf(objPacientes.getIdPaciente()));
		txtNome.setText(objPacientes.getNome());
		txtDataNasc.setDate(objPacientes.getDataNascimento());
		cmbSexo.setSelectedIndex(objPacientes.getSexo() == "F"?1:0);
		cmbEstadoCivil.setSelectedIndex(objPacientes.getEstadoCivil().equals("S") ? 0:objPacientes.getEstadoCivil().equals("D") ? 3:objPacientes.getEstadoCivil().equals("C") ? 1:objPacientes.getEstadoCivil().equals("V") ? 2:-1);
		txtProfissao.setText(objPacientes.getProfissao());
		txtEndereco.setText(objPacientes.getEndereco());
		txtBairro.setText(objPacientes.getBairro());
		txtCidade.setText(objPacientes.getCidade());
		txtCEP.setText(objPacientes.getCep());
		cmbEstado.setSelectedItem(objPacientes.getUf());
		txtTelefone.setText(objPacientes.getTelefone());
		txtCelular.setText(objPacientes.getCelular());
		txtConvenio.setText(objPacientes.getConvenio());
		txtDataConsulta.setDate(objPacientes.getDataUltConsulta());
		habilitarObjetos();
	}
	
	private void btnCancelar()
	{
		objValidator.clear();
		objPacientes.setInclusao();
		txtNome.requestFocusInWindow();
		txtCodigo.setText("");
		txtNome.setText("");
		txtDataNasc.setDate(new Date());
		lblIdade.setText("Idade");
		cmbSexo.setSelectedIndex(0);
		cmbEstadoCivil.setSelectedIndex(0);
		txtEndereco.setText("");
		txtBairro.setText("");
		txtCidade.setText("");
		cmbEstado.setSelectedIndex(0);
		txtCEP.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
		txtConvenio.setText("");
		txtDataConsulta.setDate(new Date());
		habilitarObjetos();
	}
	
	private void btnSalvar()
	{
		try
		{
			if(objValidator.validate())
			{
				objPacientes.setIdPaciente(Integer.valueOf(txtCodigo.getText().isEmpty() ? "0":txtCodigo.getText()));
				objPacientes.setNome(txtNome.getText());
				objPacientes.setDataNascimento(txtDataNasc.getDate());
				objPacientes.setSexo(String.valueOf(((String)cmbSexo.getSelectedItem()).charAt(0)));
				objPacientes.setEstadoCivil(String.valueOf(((String)cmbEstadoCivil.getSelectedItem()).charAt(0)));
				objPacientes.setProfissao(txtProfissao.getText());
				objPacientes.setEndereco(txtEndereco.getText());
				objPacientes.setBairro(txtBairro.getText());
				objPacientes.setCep(txtCEP.getText());
				objPacientes.setCidade(txtCidade.getText());
				objPacientes.setUf((String)cmbEstado.getSelectedItem());
				objPacientes.setTelefone(txtTelefone.getText());
				objPacientes.setCelular(txtCelular.getText());
				objPacientes.setConvenio(txtConvenio.getText());
				objPacientes.setDataUltConsulta(txtDataConsulta.getDate());
				objPacientes.salvar();
				editar(objPacientes.getIdPaciente());
				JOptionPane.showMessageDialog(frmTela, "Salvo com Sucesso.", "Salvar", JOptionPane.INFORMATION_MESSAGE);
			}
			else
				JOptionPane.showMessageDialog(frmTela, "Verifique o Preenchimento dos Campos.", "Salvar", JOptionPane.WARNING_MESSAGE);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(frmTela, e.getMessage(), "Erro Salvar", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Salvar - " + e.getMessage());
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			JOptionPane.showMessageDialog(frmTela, e.getMessage(), "Erro Salvar", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Salvar - " + e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(frmTela, e.getMessage(), "Erro Salvar", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Salvar - " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void btnPesqPaciente()
	{
		try
		{
			FrmPesquisa objPesquisa = new FrmPesquisa();
			
			txtCodigo.setText(objPesquisa.showDialog());
			if(!txtCodigo.getText().equals("0"))
				editar(Integer.valueOf(txtCodigo.getText()));
		}
		catch (Exception ex) {
			JOptionPane.showMessageDialog(frmTela, ex.getMessage(), "Erro Editar", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Editar - " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private void btnMap()
	{
		try
		{
			picMapa.setImg(dlgMapa.getToolkit().getImage(new URL("http://maps.google.com/maps/api/staticmap?markers=" + txtEndereco.getText().replace(" ", "+") + "," + txtCidade.getText().replace(" ", "+") + "&zoom=15&size=250x250&sensor=false")));
			dlgMapa.setLocation(btnMap.getLocationOnScreen());
			dlgMapa.setVisible(true);
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(frmTela, e.getMessage(), "Erro Mapa", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Mapa - " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void editar(int idPaciente) throws Exception
	{
		objPacientes.editar(idPaciente);
		carregarTela();
	}
	
	private class Events implements ActionListener, PropertyChangeListener, KeyListener
	{
		@Override
		public void propertyChange(PropertyChangeEvent e)
		{
			if(e.getSource().equals(txtDataNasc) && e.getPropertyName().equals("date"))
			{
				Calendar calAtual = Calendar.getInstance();
				Calendar calNasc = Calendar.getInstance();

				if(txtDataNasc.getDate() != null)
				{
					calNasc.setTime(txtDataNasc.getDate());
					if (calAtual.get(Calendar.DAY_OF_YEAR) < calNasc.get(Calendar.DAY_OF_YEAR))
						lblIdade.setText((calAtual.get(Calendar.YEAR) - calNasc.get(Calendar.YEAR) - 1) + " Anos");
					else
						lblIdade.setText((calAtual.get(Calendar.YEAR) - calNasc.get(Calendar.YEAR)) + " Anos");
				}
			}			
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource().equals(btnPesqPaciente))
			{
				btnPesqPaciente();
			}
			if(e.getSource().equals(btnCancelar))
			{
				btnCancelar();
			}
			if(e.getSource().equals(btnSalvar))
			{
				btnSalvar();
			}
			if(e.getSource().equals(btnExcluir))
			{
				try
				{
					objPacientes.excluir(Integer.valueOf(txtCodigo.getText()));
					btnCancelar();
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(frmTela, ex.getMessage(), "Erro Excluir", JOptionPane.ERROR_MESSAGE);
					Utils.writeFile("Erro Excluir - " + ex.getMessage());
					ex.printStackTrace();
				}
			}
			if(e.getSource().equals(btnImprimir))
			{
				JasperViewer viewer	= new JasperViewer(new Ficha().gerar(Integer.valueOf(txtCodigo.getText()), lblIdade.getText()), false);
				
				viewer.setIconImage(new ImageIcon(getClass().getResource("/img/novo.png")).getImage());
				viewer.setTitle("Medical IT - Impressão de Ficha Paciente");
				viewer.setVisible(true);
			}
			if(e.getSource().equals(btnProntuario))
				new FrmProntuario().show(Integer.valueOf(txtCodigo.getText()));
			if(e.getSource().equals(btnMap))
				btnMap();
		}

		@Override
		public void keyTyped(KeyEvent e)
		{
			
		}

		@Override
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode() == KeyEvent.VK_F1)
			{
				btnPesqPaciente();
			}
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}