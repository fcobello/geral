package br.com.visualmix.tradutor;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Principal 
{
	public static Tela objTela = new Tela();
	
	public Principal()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[1].getClassName());
			SwingUtilities.updateComponentTreeUI(objTela);
			objTela.setVisible(true);
			objTela.txtTexto.requestFocus();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(objTela, "Erro Skin", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new Principal();
	}
}
