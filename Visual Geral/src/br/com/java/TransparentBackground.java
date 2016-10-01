package br.com.java;

import java.awt.AlphaComposite;
import java.awt.Dimension;   
import java.awt.Graphics;   
import java.awt.Graphics2D;
import java.awt.Image;   
import java.awt.Point;   
import java.awt.Rectangle;   
import java.awt.Robot;   
import java.awt.Toolkit;   
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class TransparentBackground extends JComponent 
{   
	private static final long serialVersionUID = 1L;
	private Image background;   
  
    public TransparentBackground() {   
        updateBackground();   
    }   
  
    public void updateBackground() {   
        try {   
            Robot rbt = new Robot();   
            Toolkit tk = Toolkit.getDefaultToolkit();   
            Dimension dim = tk.getScreenSize();   
               
            background = rbt.createScreenCapture(   
                    new Rectangle(0, 0, (int) dim.getWidth(),   
                    (int) dim.getHeight()));   
        } catch (Exception ex) {   
            ex.printStackTrace();   
        }   
    }   
    
	private AlphaComposite makeComposite(float alpha) {   
        int type = AlphaComposite.SRC_OVER;   
        return (AlphaComposite.getInstance(type, alpha));   
    }   
  
    private void drawSquares(Graphics2D g2d, float alpha) {              
    	ImageIcon Imagem = new javax.swing.ImageIcon( "/fundo_pbase.jpg" );     
        Image Ico = new ImageIcon(Imagem.getImage().getScaledInstance(getWidth(), getHeight(), 1)).getImage();
        
    	g2d.setComposite(makeComposite(alpha));      
        g2d.drawImage(Ico, 0, 0, this);      
    }   
  
    public void paintComponent(Graphics g) {   
        Point pos = this.getLocationOnScreen();   
        Point offset = new Point(-pos.x, -pos.y);
        Graphics2D g2d = (Graphics2D) g; 
        
        g.drawImage(background, offset.x, offset.y, null);   
        drawSquares(g2d, 7 * 0.1F);  
    }
}    