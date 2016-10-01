package br.com.visualmix.visualmensagem.componentes;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FontChooser extends JDialog implements ActionListener, ListSelectionListener, KeyListener, MouseListener {
	private static final long serialVersionUID = 1594827348443218060L;
	private JLabel lblFonte = new JLabel("Fonte:");
	private JList lstFonte = new JList();
	private DefaultListModel listModel = new DefaultListModel();
	private JScrollPane spList = new JScrollPane(lstFonte);
	private JLabel lblTamanho = new JLabel("Tamanho:");
	private JTextField txtTamanho = new JTextField();
	private JTextField txtTeste = new JTextField();
	private JButton btnOk = new JButton("OK");
	private JButton btnCancelar = new JButton("Cancelar");
	private JButton btnNegrito = new JButton("N");
	private JButton btnItalico = new JButton("I");
	private Font font;
	private boolean bold = false;
	private boolean italic = false;

	public FontChooser(Font font) {
		this.font = font == null ? Font.getFont("Arial") : font;
		setAtributos();
		setObjetosForm();
		if (font.getStyle() == Font.BOLD) {
			bold = true;
			btnNegrito.setBorder(BorderFactory.createBevelBorder(1));
		}else{
			btnNegrito.setBorder(BorderFactory.createBevelBorder(0));
		}
		if (font.getStyle() == Font.ITALIC) {
			italic = true;
			btnItalico.setBorder(BorderFactory.createBevelBorder(1));
		}else{
			btnItalico.setBorder(BorderFactory.createBevelBorder(1));
		}
		if (font.getStyle() == Font.BOLD + Font.ITALIC) {
			bold = true;
			btnNegrito.setBorder(BorderFactory.createBevelBorder(0));
			italic = true;
			btnItalico.setBorder(BorderFactory.createBevelBorder(0));
		}
	}

	private void setAtributos() {
		super.setLayout(null);
		super.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		super.setResizable(false);
		super.setSize(305, 255);
		super.setLocation(500, 500);
		super.setModal(true);
		super.setTitle("Visual Fonte");
		lblFonte.setSize(50, 20);
		lblFonte.setLocation(10, 10);
		spList.setSize(200, 100);
		spList.setLocation(10, 30);
		lstFonte.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lstFonte.addListSelectionListener(this);
		lstFonte.setBorder(BorderFactory.createBevelBorder(1));
		lstFonte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (int i = 0; i < GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAllFonts().length; i++) {
			listModel.addElement(GraphicsEnvironment
					.getLocalGraphicsEnvironment().getAllFonts()[i].getName());
		}
		lstFonte.setModel(listModel);
		lstFonte.setSelectedValue((font == null) ? "" : font.getName(), true);
		lblTamanho.setSize(100, 20);
		lblTamanho.setLocation(220, 10);
		txtTamanho.setSize(65, 25);
		txtTamanho.setLocation(220, 30);
		txtTamanho.setText((font != null) ? String.valueOf(font.getSize())
				: "10");
		txtTamanho.addKeyListener(this);
		btnOk.setSize(90, 25);
		btnOk.setLocation(50, 200);
		btnOk.addActionListener(this);
		btnCancelar.setSize(90, 25);
		btnCancelar.setLocation(150, 200);
		btnCancelar.addActionListener(this);
		btnNegrito.setSize(20, 20);
		btnNegrito.setLocation(220, 57);
		btnNegrito.addActionListener(this);
		btnNegrito.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnNegrito.setMargin(new Insets(0, 0, 0, 0));
		btnNegrito.addMouseListener(this);
		btnItalico.setSize(20, 20);
		btnItalico.setLocation(245, 57);
		btnItalico.addActionListener(this);
		btnItalico.setFont(new Font("Times New Roman", Font.ITALIC, 11));
		btnItalico.setMargin(new Insets(0, 0, 0, 0));
		btnItalico.addMouseListener(this);
		txtTeste.setSize(275, 50);
		txtTeste.setLocation(10, 140);
		txtTeste.setHorizontalAlignment(JTextField.CENTER);
		txtTeste.setText("Teste sua fonte aqui!");
		txtTeste.setFont(font);
	}

	private void setObjetosForm() {
		super.getContentPane().add(lblFonte);
		super.getContentPane().add(spList);
		super.getContentPane().add(lblTamanho);
		super.getContentPane().add(txtTamanho);
		super.getContentPane().add(btnNegrito);
		super.getContentPane().add(btnItalico);
		super.getContentPane().add(txtTeste);
		super.getContentPane().add(btnOk);
		super.getContentPane().add(btnCancelar);
	}

	public Font getSelectedFont() {
		return font;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnCancelar)) {
			super.dispose();
		}
		if (e.getSource().equals(btnOk)) {
			font = txtTeste.getFont();
			super.dispose();
		}
	}

	public static Font showDialog() {
		return FontChooser.showDialog(null);
	}

	public static Font showDialog(Font font) {
		FontChooser dialog = new FontChooser(font);

		dialog.setVisible(true);
		return dialog.getSelectedFont();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		txtTeste.setText(lstFonte.getSelectedValue().toString());
		txtTeste.setFont(new Font(lstFonte.getSelectedValue().toString(),
				txtTeste.getFont().getStyle(), txtTeste.getFont().getSize()));
	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getSource().equals(txtTamanho)) {
			try {
				Integer.parseInt(txtTamanho.getText().trim());
			} catch (Exception e) {
				return;
			}
			txtTeste.setFont(new Font(txtTeste.getFont().getFontName(),
					txtTeste.getFont().getStyle(), Integer.parseInt(txtTamanho
							.getText().trim())));
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent arg0) {
		if (arg0.getSource().equals(btnNegrito)) {
			bold = !bold;
			if(bold){
				btnNegrito.setBorder(BorderFactory.createBevelBorder(1));
			}else{
				btnNegrito.setBorder(BorderFactory.createBevelBorder(0));
			}
			txtTeste.setFont(new Font(txtTeste.getFont().getFontName(),
					(((bold) ? Font.BOLD : 0) + ((italic) ? Font.ITALIC : 0)),
					txtTeste.getFont().getSize()));
		}
		if (arg0.getSource().equals(btnItalico)) {
			italic = !italic;
			if(italic){
				btnItalico.setBorder(BorderFactory.createBevelBorder(1));
			}else{
				btnItalico.setBorder(BorderFactory.createBevelBorder(0));
			}
			txtTeste.setFont(new Font(txtTeste.getFont().getFontName(),
					(((bold) ? Font.BOLD : 0) + ((italic) ? Font.ITALIC : 0)),
					txtTeste.getFont().getSize()));
		}
	}
}