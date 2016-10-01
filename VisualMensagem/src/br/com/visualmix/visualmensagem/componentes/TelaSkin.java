package br.com.visualmix.visualmensagem.componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.com.visualmix.visualmensagem.principal.Programa;

/**
 * Tela para seleção do skin
 * @author Felipe Cobello
 * @author José Carlos
 *
 */
public class TelaSkin implements ActionListener, KeyListener {
	private Programa objPrograma;
	private JDialog objTela = new JDialog();
	private JLabel lblSkin = new JLabel("Selecione o Skin:");
	private JComboBox cmbSkin = new JComboBox();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancelar = new JButton("Cancelar");

	public TelaSkin(Programa objPrograma) {
		this.objPrograma = objPrograma;
		setAtributos();
		setObjetosForm();
	}

	private void setAtributos() {
		objTela.setLayout(null);
		objTela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		objTela.setResizable(false);
		objTela.setSize(340, 100);
		objTela.setLocation(500, 500);
		objTela.setTitle("Visual Skin");
		objTela.setModal(true);
		lblSkin.setSize(100, 20);
		lblSkin.setLocation(10, 10);
		cmbSkin.setSize(200, 20);
		cmbSkin.setLocation(120, 10);
		cmbSkin.addKeyListener(this);
		btnOk.setSize(90, 20);
		btnOk.setLocation(60, 40);
		btnOk.addActionListener(this);
		btnCancelar.setSize(90, 20);
		btnCancelar.setLocation(160, 40);
		btnCancelar.addActionListener(this);
		listSkins();
	}

	private void setObjetosForm() {
		objTela.getContentPane().add(lblSkin);
		objTela.getContentPane().add(cmbSkin);
		objTela.getContentPane().add(btnOk);
		objTela.getContentPane().add(btnCancelar);
	}
	
	private void listSkins()
	{
		for (int i = 0; i < UIManager.getInstalledLookAndFeels().length; i++) {
			cmbSkin.addItem(UIManager.getInstalledLookAndFeels()[i].getName());
		}
	}
	
	public void show()
	{
		objTela.setVisible(true);
	}
	
	private void montaSkin(){
		LookAndFeel lafanterior = UIManager.getLookAndFeel();
		
		try {
			UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[cmbSkin.getSelectedIndex()].getClassName());
			SwingUtilities.updateComponentTreeUI(objPrograma.formulario);
			objPrograma.pref.put("skin", String.valueOf(cmbSkin.getSelectedIndex()));
			objPrograma.formulario.txtTexto.setText("");
			if(lafanterior.getSupportsWindowDecorations() != UIManager.getLookAndFeel().getSupportsWindowDecorations())
			{
				JOptionPane.showMessageDialog(objPrograma.formulario, "É Necessário Reiniciar a Aplicação", "Aviso!", JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnCancelar)) {
			objPrograma.formulario.txtTexto.setText("");
			objTela.dispose();
		}
		if (e.getSource().equals(btnOk)) {
			montaSkin();
			objTela.dispose();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
			actionPerformed(new ActionEvent(btnCancelar, 0, null));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
