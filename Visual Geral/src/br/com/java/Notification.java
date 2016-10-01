package br.com.java;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Notification extends JDialog implements ActionListener
{
	private JPanel frmTela = new JPanel();
	private static final long serialVersionUID = -535621161765903311L;
	private JLabel lblMsg = new JLabel();
	private Timer timer = new Timer(3000, this);
	
	public Notification(String msg)	
	{
		super.setSize(200, 70);
		super.setLocation((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth() - super.getSize().getWidth()), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() - super.getSize().getHeight()));
		super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		super.setResizable(false);
		super.setAlwaysOnTop(true);
		super.setFocusable(false);
		super.setFocusableWindowState(false);
		super.setTitle("Internet Monitot");
		//Msg
		lblMsg.setIcon(new ImageIcon(getClass().getResource("/img/stop.png")));
		lblMsg.setText(msg);
		//Tela
		super.getContentPane().add(frmTela);
		frmTela.setLayout(new FlowLayout(FlowLayout.CENTER));
		frmTela.setBorder(BorderFactory.createEtchedBorder());
		frmTela.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frmTela.add(lblMsg);
		//Timer
		timer.start();
	}
	
	public static void showNotification(String msg)
	{
		Notification.showNotification(msg, "Notification", null);
	}
	
	public static void showNotification(String msg, String title)
	{
		Notification.showNotification(msg, title, null);
	}
	
	public static void showNotification(String msg, String title, MouseListener listener)	{
		Notification objNotification;
		
		objNotification = new Notification(msg);
		objNotification.setTitle(title);
		objNotification.lblMsg.addMouseListener(listener);
		objNotification.setVisible(true);
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
}
