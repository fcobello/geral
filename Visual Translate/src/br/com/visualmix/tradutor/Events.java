package br.com.visualmix.tradutor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.google.api.translate.Language;

public class Events implements ActionListener, KeyListener 
{
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if(e.getSource().equals(Principal.objTela.btnViceVersa))
		{
			String origem = Principal.objTela.cmbIdiomaOri.getSelectedItem().toString();
			String destino = Principal.objTela.cmbIdiomaDest.getSelectedItem().toString();
			
			Principal.objTela.cmbIdiomaOri.setSelectedItem(destino);
			Principal.objTela.cmbIdiomaDest.setSelectedItem(origem);
			Principal.objTela.txtTraducao.setText("");
		}
		if(e.getSource().equals(Principal.objTela.btnTraduzir))
		{
			Principal.objTela.txtTraducao.setText(Tradutor.translate(Principal.objTela.txtTexto.getText(), Language.valueOf(Principal.objTela.cmbIdiomaOri.getSelectedItem().toString()), Language.valueOf(Principal.objTela.cmbIdiomaDest.getSelectedItem().toString())));
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getSource().equals(Principal.objTela.btnTraduzir) && e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			actionPerformed(new ActionEvent(Principal.objTela.btnTraduzir, 0, null));
		}
		
		if(e.getSource().equals(Principal.objTela.txtTexto))
		{
			if(Principal.objTela.ckbRealTime.isSelected() && (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_PERIOD))
			{
				actionPerformed(new ActionEvent(Principal.objTela.btnTraduzir, 0, null));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
