package br.com.visualmix.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;

public class Events implements MouseListener 
{
	private Tela objTela;
	
	public Events(Tela tela)
	{
		objTela = tela;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		Game.Jogar(objTela, (JTextField)arg0.getComponent());
		Game.Jogar(objTela, IA.CPUJogar(objTela, (JTextField)arg0.getComponent()));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
