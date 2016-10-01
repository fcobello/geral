package br.com.java;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class KeyCode extends JFrame implements KeyListener{

	private static final long serialVersionUID = -278219172193680577L;
	JLabel lblMsg = new JLabel("KeyCode: ");
	Container c = super.getContentPane();
	JPanel p = new JPanel();
	
	public KeyCode()
	{
		super.addKeyListener(this);
		super.setTitle("KeyCode");
		super.setSize(200, 60);
		super.setLocation(500, 500);
		super.getRootPane().setWindowDecorationStyle(5);
		super.setResizable(false);
		super.setUndecorated(true);
		
		p.setLayout(null);
		lblMsg.setLocation(1, 5);
		lblMsg.setSize(199, 15);
		p.add(lblMsg);
		c.add(p);
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		lblMsg.setText("KeyCode: "+ arg0.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	public static void main(String[] args) {
		new KeyCode().setVisible(true);
		System.out.println(KeyStroke.getAWTKeyStroke('c').getKeyCode());
	}
}
