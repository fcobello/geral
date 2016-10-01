package br.com.visualmix.visualmensagem.componentes;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import br.com.visualmix.visualmensagem.transferfile.TransferFile;

public class TelaDownload extends JFrame implements Runnable, ActionListener{
	private static final long serialVersionUID = -6209012069776407754L;
	private JFileChooser fileChooser = new JFileChooser();
	private JLabel lblDescricao = new JLabel();
	private JLabel lblTaxaTransf = new JLabel();
	private JProgressBar pbDownload = new JProgressBar();
	private JButton btnCancelar = new JButton();
	private Container c = new Container();
	private long maxValue;
	private long valorAnt = 0;
	private String descricao;
	private String title;
	private boolean loop = true;
	private double vel;
	private DecimalFormat df = new DecimalFormat("0.00");

	public TelaDownload() {
		super.setSize(400, 150);
		super.setResizable(false);
		int xLocation = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - super.getSize().width / 2;
		int yLocation = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - super.getSize().height / 2;
		super.setLocation(xLocation, yLocation);
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		lblDescricao.setSize(390, 20);
		lblDescricao.setLocation(10, 20);
		lblTaxaTransf.setSize(200, 20);
		lblTaxaTransf.setLocation(10, 40);
		pbDownload.setSize(370, 20);
		pbDownload.setLocation(10, 60);
		pbDownload.getModel().setRangeProperties(0, 1, 0, 100, true);
		btnCancelar.setSize(100, 25);
		btnCancelar.setText("Cancelar");
		btnCancelar.setLocation(280, 95);
		btnCancelar.addActionListener(this);
		super.add(c);
		c.add(lblDescricao);
		c.add(lblTaxaTransf);
		c.add(pbDownload);
		c.add(btnCancelar);
	}

	public void setTitle(String title){
		super.setTitle(title);
		this.title = title;
	}

	public void setDescricao(String descricao){
		lblDescricao.setText(descricao);
		this.descricao = descricao;
	}


	public void setMaxValue(long max){
		maxValue = max;
	}

	public void setValue(long value){
		int valor = 0;
		
		valor = (((int) (value / (maxValue < 100?100:maxValue / 100)) > 100))?100:((int) (value / (maxValue < 100?100:maxValue / 100)));
		pbDownload.getModel().setValue(valor);
		if(valorAnt < valor){
			lblDescricao.setText(descricao + ": " + valor + "%");
			lblTaxaTransf.setText("Taxa de Transferência: " + df.format(vel/1024) + "MB/s");
			super.setTitle(title + " " + valor + "%");
			valorAnt = valor;
		}
	}

	public void play(){
		loop = true;
		velocidade();
		new Thread(this).start();
		
	}

	public void stop(){
		super.dispose();
	}

	@Override
	public void run() {
		loop = true;
		super.setVisible(true);
		while(loop){
			setValue(TransferFile.getTaxaArquivoAtual());
		}
	}

	public void termine(){
		loop = false;
		super.dispose();
	}

	public void download(String[] files, String ip){
		String extensao;
		
		try
		{
			for (short x = 0; x < files.length; x++) 
			{
				File file = new File(files[x].split("[@]")[0]);
				if (JOptionPane.showConfirmDialog(this, "Deseja receber o arquivo " + file.getName() + " de " + ip + "?", "Transferência de Arquivo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					fileChooser.setSelectedFile(file.getAbsoluteFile());
					if (fileChooser.showSaveDialog(this) != JFileChooser.CANCEL_OPTION)
					{
						setDescricao("Download do arquivo " + fileChooser.getSelectedFile().getName());
						setMaxValue(Long.parseLong(files[x].split("[@]")[1])/1024);
						setValue(0);
						play();
						TransferFile.downloadFile(fileChooser.getSelectedFile().toString(), ip);
						termine();
						if(fileChooser.getSelectedFile().exists())
						{
							JOptionPane.showMessageDialog(this, "Arquivo recebido: " + fileChooser.getSelectedFile().getPath(), "Transferência de Arquivo", JOptionPane.INFORMATION_MESSAGE);
							extensao = fileChooser.getSelectedFile().getName().substring(fileChooser.getSelectedFile().getName().lastIndexOf("."), fileChooser.getSelectedFile().getName().length()); 
							if(extensao.equalsIgnoreCase(".png") || extensao.equalsIgnoreCase(".jpg") || extensao.equalsIgnoreCase(".gif"))
								PictureViewer.showPicture(fileChooser.getSelectedFile().getAbsolutePath());
						}
						else
							JOptionPane.showMessageDialog(this, "Download Cancelado", "Transferência de Arquivo", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				else
				{
					TransferFile.downloadFile("temp", ip);
					new File("temp").delete();
				}
			}
		}
		catch (Exception e) {
			termine();
			JOptionPane.showMessageDialog(this, e.getMessage());						
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnCancelar)){
			TransferFile.cancelarDownload();
		}
	}

	private synchronized void velocidade(){

		new Thread(new Runnable() {
			double valIni;
			double valFim;
			@Override
			public synchronized void run() {
				while (loop) {
					valIni = TransferFile.getTaxaArquivoAtual();
					try {
						wait(250);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					valFim = TransferFile.getTaxaArquivoAtual();
					vel = (valFim - valIni) * 4;	
				}
			}
		}).start();
	}
}
