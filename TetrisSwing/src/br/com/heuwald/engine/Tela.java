package br.com.heuwald.engine;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

public class Tela extends JFrame {

    private static final long serialVersionUID = 1L;
    Engine en;
    JLabel lblPoints = new JLabel();
    BorderLayout layoutPrincipal = new BorderLayout();
    TetrisPanel tp;
    JLabel lblLinhas = new JLabel();

    public Tela() {

	en = new Engine();

	tp = new TetrisPanel(en);
	tp.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

	super.setSize(330, 665);
	super.setResizable(false);
	super.setLocation(200, 200);
	super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	super.setTitle("Visual Tetris");
	super.setLayout(layoutPrincipal);
	super.add(tp, BorderLayout.CENTER);
	super.add(lblPoints, BorderLayout.NORTH);
	super.setMaximumSize(new Dimension(800, 1200));
	super.setIconImage(new ImageIcon(getClass().getResource("/br/com/heuwald/resources/musics/icone.png")).getImage());

	super.setVisible(true);

	en.setPauseGame(true);
	en.play();
	criaListener();
    }

    private void criaListener() {
	super.addKeyListener(new KeyListener() {

	    @Override
	    public void keyTyped(KeyEvent e) {

	    }

	    @Override
	    public void keyReleased(KeyEvent e) {

	    }

	    @Override
	    public void keyPressed(KeyEvent e) {
		if (!en.isPauseGame()) {
		    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			en.movePecaRight();
		    }
		    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			en.movePecaLeft();
		    }

		    if (e.getKeyCode() == KeyEvent.VK_UP) {
			en.giraPeca();
		    }

		    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			en.movePecaBaixo();
		    }
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
		    en.setPauseGame(!en.isPauseGame());
		}

	    }
	});
    }

    public static void main(String[] args) {
	new Tela();
    }
}