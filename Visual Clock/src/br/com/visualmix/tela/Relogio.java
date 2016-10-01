package br.com.visualmix.tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.sun.awt.AWTUtilities;

public class Relogio implements MouseWheelListener, WindowListener, MouseListener
{
	protected JDialog frmTela = new JDialog();
	protected JPanel pTela = new JPanel();
	protected JLabel lblHoras = new JLabel();
	protected JLabel lblPonto = new JLabel();
	protected JLabel lblMinutos = new JLabel();
	protected JLabel lblX = new JLabel("X");
	protected Execucao objExecucao = new Execucao();
	protected Thread execucao = new Thread(objExecucao);
	private float opacity = 0.4f;
	
	public Relogio() throws Exception
	{
		Font font = new Font("Consolas", Font.BOLD + Font.ITALIC, 48);
		
		frmTela.setTitle("Visual Clock");
		frmTela.setSize(200, 60);
		frmTela.setAlwaysOnTop(true);
		frmTela.setLocation((int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getWidth() - frmTela.getSize().getWidth() - 10), (int)(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getHeight() - frmTela.getSize().getHeight() - 10));
		frmTela.setResizable(false);
		frmTela.setUndecorated(true);
		frmTela.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frmTela.setIconImage(frmTela.getToolkit().getImage(getClass().getResource("/img/relogio.png")));
		frmTela.addMouseWheelListener(this);
		frmTela.addWindowListener(this);
		//Label
		lblHoras.setForeground(Color.GREEN);
		lblHoras.setFont(font);
		lblPonto.setForeground(Color.GREEN);
		lblPonto.setFont(font);
		lblMinutos.setFont(font);
		lblMinutos.setForeground(Color.GREEN);
		lblX.setLocation(185, 5);
		lblX.setSize(8, 12);
		lblX.setForeground(Color.GREEN);
		lblX.addMouseListener(this);
		//Panel
		pTela.setBorder(BorderFactory.createEtchedBorder(Color.WHITE, Color.BLACK));
		pTela.setBackground(Color.BLACK);
		//Tela
		pTela.add(lblHoras);
		pTela.add(lblPonto);
		pTela.add(lblMinutos);
		frmTela.getContentPane().add(lblX);
		frmTela.getContentPane().add(pTela);
		AWTUtilities.setWindowOpacity(frmTela, 0.4f);
		pTela.addMouseListener(this);
		//Timer
		execucao.start();
	}
	
	public static void main(String[] args) throws Exception
	{
		Relogio relogio = new Relogio();
		relogio.frmTela.setVisible(true);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		if(AWTUtilities.getWindowOpacity(frmTela) >= 0.1f && AWTUtilities.getWindowOpacity(frmTela) <= 1)
		{
			AWTUtilities.setWindowOpacity(frmTela, AWTUtilities.getWindowOpacity(frmTela) + (e.getWheelRotation()/100f) < 0.1 ? 0.1f:AWTUtilities.getWindowOpacity(frmTela) + (e.getWheelRotation()/100f) > 1.0f?1.0f:AWTUtilities.getWindowOpacity(frmTela) + (e.getWheelRotation()/100f));
			opacity = AWTUtilities.getWindowOpacity(frmTela);
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e)
	{
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		objExecucao.active = false;
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		if(e.getSource().equals(lblX))
			frmTela.dispose();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		if(e.getSource().equals(pTela))
			AWTUtilities.setWindowOpacity(frmTela, 1f);
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		if(e.getSource().equals(pTela))
			AWTUtilities.setWindowOpacity(frmTela, opacity);
	}
	
	private class Execucao implements Runnable
	{
		public boolean active = true;
		
		@Override
		public synchronized void run()
		{
			GregorianCalendar calendar = new GregorianCalendar();
			NumberFormat format = NumberFormat.getInstance();
			
			try
			{
				format.setMinimumIntegerDigits(2);
				while(active)
				{
					pTela.setToolTipText(calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.getDefault()) + ", " + calendar.get(Calendar.DAY_OF_MONTH) + " de " + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " de " + calendar.get(Calendar.YEAR));
					calendar.setTimeInMillis(System.currentTimeMillis());
					lblHoras.setText(format.format(calendar.get(Calendar.HOUR_OF_DAY)));
					lblPonto.setText(":");
					lblMinutos.setText(format.format(calendar.get(Calendar.MINUTE)));
					wait(100);
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
