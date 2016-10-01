package br.com.visualmix.visualmensagem.componentes;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import com.sun.awt.AWTUtilities;

/**
 * Tela de Notificação de Nova Mensagem
 * @author Felipe Cobello
 *
 */
public class Notification extends JDialog implements ActionListener, MouseListener{
	private JPanel frmTela = new JPanel();
	private static final long serialVersionUID = -535621161765903311L;
	private JLabel lblMsg = new JLabel();
	private Timer timer = new Timer(3000, this);
	private Component componente;
	
	public Notification(String msg)	
	{
		super.setSize(200, 70);
		super.setLocation((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth() - super.getSize().getWidth()), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() - super.getSize().getHeight()));
		super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		super.setResizable(false);
		super.setAlwaysOnTop(true);
		super.setFocusable(false);
		super.setFocusableWindowState(false);
		super.setTitle("Visual Mensagem");
		//Msg
		lblMsg.setIcon(new ImageIcon(getClass().getResource("/img/env.png")));
		lblMsg.setText(msg);
		lblMsg.addMouseListener(this);
		//Tela
		super.getContentPane().add(frmTela);
		frmTela.setLayout(new FlowLayout(FlowLayout.CENTER));
		frmTela.setBorder(BorderFactory.createEtchedBorder());
		frmTela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frmTela.add(lblMsg);
		//Timer
		timer.start();
	}
	
	public static void showNotification(String msg, Component tela)	{
		Notification objNotification;
		
		objNotification = new Notification(msg);
		objNotification.setVisible(true);
		objNotification.componente = tela;
		if(msg.length() > 30){
			objNotification.setSize(500, 75);
			objNotification.setLocation((int)(objNotification.getLocation().getX() - 300), (int)objNotification.getLocation().getY());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(timer)) {
			timer.stop();
			super.dispose();
		}
	}
	
	@Override
	public void setVisible(boolean visible){
		final JDialog dialog = this;
		
		try {
			AWTUtilities.setWindowOpacity(dialog, 0);
			super.setVisible(visible);
			new Thread(new Runnable() {
			@Override
			public synchronized void run() {
				float cont = 0;
				while (cont < 10) {
					try {
						wait(30);
					} catch (InterruptedException e) {
					}
					cont++;
					AWTUtilities.setWindowOpacity(dialog, (cont/10));
				}
					
			}}).start();
		} catch (Exception e) {
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		componente.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		
	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		
	}
}
