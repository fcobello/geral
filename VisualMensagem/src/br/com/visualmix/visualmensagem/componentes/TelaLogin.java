package br.com.visualmix.visualmensagem.componentes;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.prefs.Preferences;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class TelaLogin extends JDialog implements ActionListener, KeyListener
{
	private static final long serialVersionUID = -3465197659858510694L;
	private Picture imgLogin = new Picture();
	private JLabel lblTexto = new JLabel();
	private JPasswordField txtSenha = new JPasswordField();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancelar = new JButton("Cancelar");
	private Preferences preferences;
	private boolean senhaValida;
	
	public TelaLogin()
	{
		super.setTitle("Login");
		super.setModal(true);
		super.setResizable(false);
		super.setSize(300, 130);
		super.setLocation((int)GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getX() - super.getWidth()/2, (int)GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint().getY() - super.getHeight()/2);
		super.setLayout(null);
		super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//Atributos
		imgLogin.setImg(super.getToolkit().getImage(getClass().getResource("/img/login.png")));
		imgLogin.setSize(48, 48);
		imgLogin.setLocation(10, 15);
		lblTexto.setText("Password");
		lblTexto.setSize(100, 25);
		lblTexto.setLocation(75, 15);
		txtSenha.setSize(200, 25);
		txtSenha.setLocation(75, 35);
		txtSenha.addKeyListener(this);
		btnOk.setSize(115, 25);
		btnOk.setLocation(30, 70);
		btnOk.addActionListener(this);
		btnCancelar.setSize(115, 25);
		btnCancelar.setLocation(160, 70);
		btnCancelar.addActionListener(this);
		//Tela
		super.getContentPane().add(imgLogin);
		super.getContentPane().add(lblTexto);
		super.getContentPane().add(txtSenha);
		super.getContentPane().add(btnOk);
		super.getContentPane().add(btnCancelar);
	}

	public boolean isSenhaValida()
	{
		return senhaValida;
	}
	
	private boolean checkSenha(String senha)
	{
		boolean retorno = false;
		
		if(!senha.isEmpty())
		{
			if(preferences.get("senha", "").equals(senha))
				retorno = true;
		}
		return retorno;
	}
	
	private String getRandonPassword()
	{
		Calendar calendar = Calendar.getInstance();
		
		return String.valueOf((calendar.get(Calendar.YEAR) / (calendar.get(Calendar.MONTH) + 1)) * calendar.get(Calendar.DAY_OF_YEAR));		
	}
	
	public boolean checkSenha(Preferences preferences)
	{
		this.preferences = preferences;
		super.setVisible(true);
		return isSenhaValida();
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(btnOk))
		{
			if(preferences.get("senha", "").isEmpty() || String.valueOf(txtSenha.getPassword()).equals(getRandonPassword()))
			{
				txtSenha.setText("");
				while(String.valueOf(txtSenha.getPassword()).isEmpty())
				{
					txtSenha.setText(JOptionPane.showInputDialog(null, "Digite sua Nova Senha:"));
					if(!String.valueOf(txtSenha.getPassword()).isEmpty() && String.valueOf(txtSenha.getPassword()) != null)
						preferences.put("senha", String.valueOf(txtSenha.getPassword()));
				}
			}
			senhaValida = checkSenha(String.valueOf(txtSenha.getPassword()));
			if(senhaValida)
				super.dispose();
			else
				txtSenha.setText("");
		}
		if(e.getSource().equals(btnCancelar))
		{
			super.dispose();
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
			actionPerformed(new ActionEvent(btnOk, 0, null));
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		
	}
}
