package br.com.visualmix.visualmensagem.componentes;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.JDialog;
/**
 * Visual Picture Viewer
 * <pre>Visualizador de Imagens</pre>
 * @author Felipe Cobello
 *
 */
public class PictureViewer extends JDialog
{	
	private static final long serialVersionUID = -4895952405356540580L;
	
	public static void showPicture(String pathImage)
	{
		PictureViewer viewer = new PictureViewer();
		Picture imagem = new Picture();
		File file = new File(pathImage);
		BorderLayout layout = new BorderLayout(5, 10);
		
		if(file.exists())
		{
			imagem.setImg(pathImage);
			viewer.setLayout(layout);
			viewer.setAlwaysOnTop(true);
			viewer.setTitle("Visual Picture Viewer");
			viewer.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			viewer.setSize(640, 480);
			viewer.setLocation(100, 100);
			viewer.getContentPane().add(imagem);
			viewer.setVisible(true);
		}
		else
			viewer.dispose();
	}
}
