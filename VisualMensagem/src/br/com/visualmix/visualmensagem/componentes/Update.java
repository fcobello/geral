package br.com.visualmix.visualmensagem.componentes;

import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import br.com.visualmix.visualmensagem.transferfile.TransferFile;

public class Update extends JDialog
{
	private static final long serialVersionUID = -7641359103005120286L;
	private JLabel lblMsg = new JLabel("Auto-Update Server");
	private JFileChooser fileChooser = new JFileChooser();
	
	public Update() throws Exception
	{
		super.setLayout(new FlowLayout());
		super.setSize(100, 70);
		super.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint());
		super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		super.getContentPane().add(lblMsg);
		fileChooser.setMultiSelectionEnabled(true);
		if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			TransferFile.uploadFile(fileChooser.getSelectedFiles(), true);
	}
	
	public static void main(String[] args)
	{
		Update update;
		
		try
		{
			update = new Update();
			update.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	} 
}
