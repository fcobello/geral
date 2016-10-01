package br.com.java;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class Aviso extends SwingWorker<Void, Void>{
	JDialog janela = new JDialog();
	Container c = new Container();
	JLabel lblContato = new JLabel();
	Aviso(String texto){
		janela.setSize(200, 0);
		janela.setLocation((int)Math.round(Math.random() * 1024), (int)Math.round(Math.random() * 768));
		janela.setUndecorated(true);
		lblContato.setText(texto);
		lblContato.setSize(150, 20);
		lblContato.setLocation(75, 5);
		c.add(lblContato);
		janela.add(c);
	}

	
	public synchronized static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
			new Aviso("Felipe").execute();
			try {
				new Thread();
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}

	@Override
	protected synchronized Void doInBackground() throws Exception {
		janela.setVisible(true);
		janela.toFront();
		janela.requestFocusInWindow();
		int height = 0;
		int localY = janela.getLocation().y;
		while(height < 30){
			janela.setSize(janela.getSize().width, height);
			janela.setLocation(janela.getLocation().x, localY);
			height += 2;
			localY -= 2;
			try {
				super.wait(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			super.wait(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			janela.dispose();
		}
		return null;
	}
}
