package br.com.heuwald.engine;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class TetrisPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    Engine en;
    int blockSize = 2;
    
    Pintura p;
    
    public TetrisPanel(Engine en) {
	this.en = en;
	 p = new Pintura(en, this);
    }
    
    @Override
    public void paint(Graphics g) {
	
	Image im = p.pintaTela();
	
	g.drawImage(im, 0, 0, this);
	
    }
}
