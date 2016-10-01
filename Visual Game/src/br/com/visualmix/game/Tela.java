package br.com.visualmix.game;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Tela extends JFrame
{
	private static final long serialVersionUID = 7958573645734187328L;
	public static final Color RED = new Color(255,0,0);
	public static final Color GREEN = new Color(0,255,0);
	private Container Form = super.getContentPane();
	public JPanel pTela = new JPanel();
	public JLabel lblMsg = new JLabel("Msg:");
	public int player = 1;
	private Font font = new Font(Font.SANS_SERIF, 0, 48);
	public JTextField txtA1 = new JTextField();
	public JTextField txtA2 = new JTextField();
	public JTextField txtA3 = new JTextField();
	public JTextField txtA4 = new JTextField();
	public JTextField txtA5 = new JTextField();
	public JTextField txtA6 = new JTextField();
	public JTextField txtA7 = new JTextField();
	public JTextField txtA8 = new JTextField();
	public JTextField txtA9 = new JTextField();
	
	public Tela()
	{
		super.setVisible(true);
		super.setSize(143, 215);
		super.setLocation(500, 500);
		super.setResizable(false);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setAtributos();
		setBody();
		setEvents();
	}
	
	private void setAtributos()
	{
		pTela.setLayout(null);
		//A1
		txtA1.setLocation(5, 5);
		txtA1.setSize(40, 50);
		txtA1.setEditable(false);
		txtA1.setFont(font);

		//A2
		txtA2.setLocation(48, 5);
		txtA2.setSize(40, 50);
		txtA2.setEditable(false);
		txtA2.setFont(font);		

		//A3
		txtA3.setLocation(91, 5);
		txtA3.setSize(40, 50);
		txtA3.setEditable(false);
		txtA3.setFont(font);		

		//A4
		txtA4.setLocation(5, 57);
		txtA4.setSize(40, 50);
		txtA4.setEditable(false);
		txtA4.setFont(font);		

		//A5
		txtA5.setLocation(48, 57);
		txtA5.setSize(40, 50);
		txtA5.setEditable(false);
		txtA5.setFont(font);		

		//A6
		txtA6.setLocation(91, 57);
		txtA6.setSize(40, 50);
		txtA6.setEditable(false);
		txtA6.setFont(font);		

		//A7
		txtA7.setLocation(5, 109);
		txtA7.setSize(40, 50);
		txtA7.setEditable(false);
		txtA7.setFont(font);		
		
		//A8
		txtA8.setLocation(48, 109);
		txtA8.setSize(40, 50);
		txtA8.setEditable(false);
		txtA8.setFont(font);		
		
		//A9
		txtA9.setLocation(91, 109);
		txtA9.setSize(40, 50);
		txtA9.setEditable(false);
		txtA9.setFont(font);		
		
		//Msg
		lblMsg.setLocation(5, 145);
		lblMsg.setSize(50, 50);

	}
	
	private void setBody()
	{
		pTela.add(txtA1);
		pTela.add(txtA2);
		pTela.add(txtA3);
		pTela.add(txtA4);
		pTela.add(txtA5);
		pTela.add(txtA6);
		pTela.add(txtA7);
		pTela.add(txtA8);
		pTela.add(txtA9);
		pTela.add(lblMsg);
		Form.add(pTela);
	}
	
	private void setEvents()
	{
		txtA1.addMouseListener(new Events(this));
		txtA2.addMouseListener(new Events(this));
		txtA3.addMouseListener(new Events(this));
		txtA4.addMouseListener(new Events(this));
		txtA5.addMouseListener(new Events(this));
		txtA6.addMouseListener(new Events(this));
		txtA7.addMouseListener(new Events(this));
		txtA8.addMouseListener(new Events(this));
		txtA9.addMouseListener(new Events(this));
	}
}
