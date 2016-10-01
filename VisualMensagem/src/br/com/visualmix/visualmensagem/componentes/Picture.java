package br.com.visualmix.visualmensagem.componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
* Componente para Manipular Imagem em uma tela Swing
* @author Felipe Cobello
* @version 1.0
*/
public class Picture extends JPanel 
{   
    private static final long serialVersionUID = 1L;   
    private Image img;    

    @Override
    protected void paintComponent(final Graphics g) 
    {    
    	super.paintComponent(g);
    	g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }   

    public void setImg(Image img) 
    {   
    	this.img = img;   
    	super.repaint(); 
    }   

    public void setImg(String url) 
    {   
    	setImg(Toolkit.getDefaultToolkit().getImage(url));
    }   
 }   
