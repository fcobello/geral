package br.com.fc.cadastro.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import br.com.fc.importer.FrmImportacao;
import br.com.fc.utils.Picture;

public class FrmPrincipal implements ActionListener, ComponentListener
{
	private JFrame frmTela = new JFrame("Medical IT System");
	private JDesktopPane dktTela = new JDesktopPane();
	private JMenuBar mnuBar = new JMenuBar();
	private JMenu mnuCadastros = new JMenu("Cadastros");
	private JMenuItem itmPacientes = new JMenuItem("Pacientes", new ImageIcon(getClass().getResource("/img/cadastro.png")));
	private JMenuItem itmAgenda = new JMenuItem("Agenda", new ImageIcon(getClass().getResource("/img/agenda.png")));
	private JMenu mnuConfig = new JMenu("Configurações");
	private JMenuItem itmImportacao = new JMenuItem("Importação", new ImageIcon(getClass().getResource("/img/import.png")));
	private JMenuItem itmBackup = new JMenuItem("Backup", new ImageIcon(getClass().getResource("/img/backup.png")));
	private JMenuItem itmConfiguracoes = new JMenuItem("Configurações", new ImageIcon(getClass().getResource("/img/tools.png")));
	private JMenu mnuSair = new JMenu("Sair");
	private JMenuItem itmSair = new JMenuItem("Sair", new ImageIcon(getClass().getResource("/img/sair.png")));
	private JToolBar tbar = new JToolBar();
	private JButton btnPacientes = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/cadastro.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
	private JButton btnAgenda = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/agenda.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
	private JButton btnSair = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/sair.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
	private Picture picLogo = new Picture();
	
	public FrmPrincipal()
	{
		//Tela
		frmTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTela.setIconImage(Toolkit.getDefaultToolkit().getImage((getClass().getResource("/img/cadastro.png"))));
		frmTela.setSize(800, 600);
		frmTela.setLayout(new BorderLayout());
		frmTela.setLocation((int)GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getX() - frmTela.getWidth()/2, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getY() - frmTela.getHeight()/2);
		frmTela.addComponentListener(this);
		//DesktopPane
		dktTela.setBackground(Color.WHITE);
		//Pacientes
		itmPacientes.addActionListener(this);
		mnuCadastros.add(itmPacientes);
		itmAgenda.setEnabled(false);
		mnuCadastros.add(itmAgenda);
		mnuBar.add(mnuCadastros);
		//Importacao
		itmImportacao.addActionListener(this);
		mnuConfig.add(itmImportacao);
		//Backup
		itmBackup.setEnabled(false);
		itmBackup.addActionListener(this);
		mnuConfig.add(itmBackup);
		//Configuracoes
		itmConfiguracoes.addActionListener(this);
		itmConfiguracoes.setEnabled(false);
		mnuConfig.add(itmConfiguracoes);
		mnuBar.add(mnuConfig);
		//Sair
		itmSair.addActionListener(this);
		mnuSair.add(itmSair);
		mnuBar.add(mnuSair);
		//Barra de Menu
		frmTela.setJMenuBar(mnuBar);
		//Barra de Ferramentas
		btnPacientes.setToolTipText("Cadastro de Pacientes");
		btnPacientes.addActionListener(this);
		btnAgenda.setEnabled(false);
		btnAgenda.setToolTipText("Agenda");
		btnSair.setToolTipText("Sair");
		btnSair.addActionListener(this);
		tbar.add(btnPacientes);
		tbar.add(btnAgenda);
		tbar.add(btnSair);
		tbar.setFloatable(false);
		tbar.setLayout(new FlowLayout(FlowLayout.LEFT));
		frmTela.add(tbar, BorderLayout.NORTH);
		frmTela.add(dktTela, BorderLayout.CENTER);
		picLogo.setImg(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/medical_it.png")));
		picLogo.setSize(350, 143);
		dktTela.setLayout(null);
		dktTela.add(picLogo);
		frmTela.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(itmPacientes) || e.getSource().equals(btnPacientes))
			new FrmPacientes(dktTela);
		if(e.getSource().equals(itmImportacao))
			new FrmImportacao(dktTela);
		if(e.getSource().equals(itmSair) || e.getSource().equals(btnSair))
			System.exit(0);
	}

	@Override
	public void componentResized(ComponentEvent e)
	{
		picLogo.setLocation(frmTela.getWidth() - picLogo.getWidth() - 15, frmTela.getHeight() - picLogo.getHeight() - tbar.getHeight() - 55);
		frmTela.repaint();
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
		
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
		
	}

	@Override
	public void componentHidden(ComponentEvent e)
	{
		
	}
	
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
			new FrmPrincipal();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	}
}
