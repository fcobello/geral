package br.com.java;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.ImageIcon;

public class InternetCheck
{
	private TrayIcon icon = new TrayIcon(new ImageIcon(getClass().getResource("/img/network.png")).getImage());
	private boolean run = true;
	
	public InternetCheck() throws AWTException
	{		
		icon.addMouseListener(new MouseListener()
		{
			
			@Override
			public void mouseReleased(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				run = false;
				SystemTray.getSystemTray().remove(icon);
			}
		});
		icon.setImageAutoSize(true);
		icon.setToolTip("Internet Monitor");
		SystemTray.getSystemTray().add(icon);
	}
	
	public boolean checkInternet(String ip)
	{
		HttpURLConnection con;
		boolean retorno = false;
		
		try
		{
			con = (HttpURLConnection) new URL("http://www.google.com.br").openConnection();
			con.connect();
			retorno = true;
		}
		catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}
	
	public synchronized void monitorInternet(String ip)
	{
		boolean aux = false;
		
		try
		{
			while(run)
			{
				if(!checkInternet(ip))
				{
					icon.displayMessage("Internet Monitor", "Conexão Indisponível", MessageType.WARNING);
					icon.setImage(new ImageIcon(getClass().getResource("/img/networkoff.png")).getImage());
					
				}
				else
				{
					icon.setImage(new ImageIcon(getClass().getResource("/img/network" + (aux ? "":"2") + ".png")).getImage());
					aux = !aux;
				}
				wait(100);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static void main(String[] args)
	{
		try
		{
			new InternetCheck().monitorInternet("200.232.63.58");
		}
		catch (AWTException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
