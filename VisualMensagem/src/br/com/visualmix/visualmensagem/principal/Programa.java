package br.com.visualmix.visualmensagem.principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.dnd.DropTarget;
import java.io.File;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.prefs.Preferences;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import br.com.visualmix.visualmensagem.componentes.ExpandeObject;
import br.com.visualmix.visualmensagem.componentes.FontChooser;
import br.com.visualmix.visualmensagem.componentes.TelaDownload;
import br.com.visualmix.visualmensagem.componentes.TelaLogin;
import br.com.visualmix.visualmensagem.componentes.TelaSkin;
import br.com.visualmix.visualmensagem.events.Events;
import br.com.visualmix.visualmensagem.events.KeyEvents;
import br.com.visualmix.visualmensagem.events.WindowEvents;
import br.com.visualmix.visualmensagem.server.ServerFile;
import br.com.visualmix.visualmensagem.server.ServerMSG;
import br.com.visualmix.visualmensagem.server.ServerStatus;
import br.com.visualmix.visualmensagem.server.Snippet;
import br.com.visualmix.visualmensagem.transferfile.TransferFile;
import ch.randelshofer.quaqua.QuaquaManager;
import com.birosoft.liquid.LiquidLookAndFeel;

public class Programa{
	private static final long serialVersionUID = 9222727489050030531L;
	private TelaLogin objLogin = new TelaLogin();
	public Tela formulario;
	public Preferences pref = Preferences.systemNodeForPackage(getClass());
	private ListaContatos listaContatos = new ListaContatos();
	public RefreshStatus statusRefresh;
	public ArrayList<String> mensagens = new ArrayList<String>();
	public boolean carregar = true;
	public ServerStatus srvStatus;
	public ServerMSG srvMSG;
	public ServerFile srvFile;
	public String status = "On Line";
	public String meuIp = "";
	public StringWriter dsw = new StringWriter();
	public DropTarget dropTarget;
	public boolean expandido;
	
	public Programa(boolean visible) {	
		if(pref.get("Nome", "pauno").equals("pauno")){
			pref.put("Nome", JOptionPane.showInputDialog("Informe sua graça", "pauno"));
		}
		if(pref.get("IP", "").trim().equals("")){
			setIP();
		}else{
			meuIp = pref.get("IP", "");
		}
		if(objLogin.checkSenha(pref)){
			iniciaServers();
			instalarSkins();
			montaSkin();
			formulario = new Tela();
			formulario.setVisible(visible);
			SwingUtilities.updateComponentTreeUI(formulario);
			formulario.trayIcon.getPopupMenu().getItem(Tela.BTN_STATUS).setLabel("Status(" + status + ")");
			formulario.trayIcon.getPopupMenu().getItem(Tela.BTN_IP).setLabel("IP(" + meuIp + ")");
			formulario.trayIcon.getPopupMenu().getItem(Tela.BTN_NICK).setLabel("Nick(\"" + pref.get("Nome", "pauno") + "\")");
			formulario.popup.getItem(Tela.BTN_SUSTENIDO).setLabel("Autoriza#(" + srvMSG.isAutorizaSustenido() + ")");
			formulario.popup.getItem(Tela.BTN_MASCARADO).setLabel("Mascarado(" + srvMSG.isMascarado() + ")");
			statusRefresh = new RefreshStatus();
			statusRefresh.execute();
			formulario.txtTexto.requestFocus();
			iniciaHist();
			formulario.txtHist.setFont(new Font(pref.get("fonteNome", "Arial"), pref.getInt("fonteEstilo", Font.PLAIN), pref.getInt("fonteTamanho", 12)));
			setEventos();
			autoUpdate("100.100.1.23");
		}
	}

	public void setIP() {
		String ip = "";
		
		try{
			ip = JOptionPane.showInputDialog("Informe seu IP", (pref.get("IP", "").trim().equals(""))?Inet4Address.getLocalHost().getHostAddress():pref.get("IP", ""));
			pref.put("IP", ip);
			meuIp = ip;
			formulario.trayIcon.getPopupMenu().getItem(Tela.BTN_IP).setLabel("setIP(\"" + meuIp + "\")");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void iniciaServers(){

		try {
			Snippet.enviar(meuIp, 25451, pref.get("Nome", "pauno") + "|isOpen");
//			System.exit(0);
		}catch (Exception e1) {
			e1.printStackTrace();
		}
		srvStatus = new ServerStatus(this);
		try {
			new Thread(srvStatus).start();
		} catch (Exception e) {
			System.out.println("Não foi possivel iniciar o srv de status");
		}
		
		srvMSG = new ServerMSG(this);
		try {
			new Thread(srvMSG).start();
		} catch (Exception e) {
			System.out.println("Não foi possivel iniciar o srv de msg");
		}
		
		srvFile = new ServerFile(this);
		try {
			new Thread(srvFile).start();
		} catch (Exception e) {
			System.out.println("Não foi possivel iniciar o srv de arquivos");
		}
	}

	public void setNick(){
		String nome = JOptionPane.showInputDialog("Informe sua graça", pref.get("Nome", "pauno"));
		if(nome == null){
			return;
		}
		pref.put("Nome", nome);
		formulario.trayIcon.getPopupMenu().getItem(Tela.BTN_NICK).setLabel("setNick(\"" + nome + "\")");
	}
	
	public void setStatus(){
		String statusX = JOptionPane.showInputDialog("Informe seu status", status);
		if(statusX == null){
			return;
		}
		status = statusX;
		formulario.trayIcon.getPopupMenu().getItem(Tela.BTN_STATUS).setLabel("setStatus("+ status +")");
	}

	private void carregarCombo(){

		ArrayList<Contato> contatos = listaContatos.getContatos();

		for (int i = 0; i < contatos.size(); i++) {
			if(!getStatus(contatos.get(i))){
				contatos.get(i).setStatus("(Off Line)");
			}
		}

		formulario.cmbContato.removeAllItems();

		for (int i = 0; i < contatos.size(); i++) {
			formulario.cmbContato.addItem(contatos.get(i));
		}
	}

	public boolean getStatus(Contato contato){
		try {
			Snippet.enviar(contato.getIp(), 25451, pref.get("Nome", "pauno") + "*" + meuIp + "|" + "#?");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getMensagem(){
			return pref.get("Nome", "pauno") + "*" + meuIp + "|" + formulario.txtTexto.getText();
		
	}

	public String getIp(){
		return ((Contato)formulario.cmbContato.getItemAt(formulario.cmbContato.getSelectedIndex())).getIp();
	}

	private void setEventos(){
		
		Events events = new Events(this);
		KeyEvents keyEvents = new KeyEvents(this);
		WindowEvents windowEvents = new WindowEvents(this);

		dropTarget = new DropTarget(formulario, events);
		formulario.btnEnviar.addActionListener(events);
		formulario.txtTexto.addKeyListener(keyEvents);
		formulario.addKeyListener(keyEvents);
		formulario.btnAdd.addActionListener(events);
		formulario.btnDel.addActionListener(events);
		formulario.btnHist.addActionListener(events);
		formulario.addWindowListener(windowEvents);
		formulario.addMouseListener(events);
		formulario.cmbContato.addMouseListener(events);
		formulario.txtTexto.addMouseListener(events);
		formulario.btnAdd.addMouseListener(events);
		formulario.btnDel.addMouseListener(events);
		formulario.btnHist.addMouseListener(events);
		formulario.btnOpcoes.addActionListener(events);
		formulario.pPanel.addMouseWheelListener(events);
//		formulario.cmbContato.addMouseWheelListener(events);
//		formulario.txtTexto.addMouseWheelListener(events);
		formulario.popup.getItem(Tela.BTN_FILE).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_STATUS).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_IP).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_NICK).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_SUSTENIDO).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_MASCARADO).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_SHOW).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_EXIT).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_SKIN).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_CORES).addActionListener(events);
		formulario.popup.getItem(Tela.BTN_FONT).addActionListener(events);
		formulario.trayIcon.addMouseListener(events);
		formulario.cmbContato.addFocusListener(events);
		formulario.txtHist.addKeyListener(keyEvents);
	}	

	public void addContato(){
		addContato(new Contato(JOptionPane.showInputDialog("Graça"), JOptionPane.showInputDialog("IP")));
	}

	public void addContato(Contato contato){
		listaContatos.addContato(contato);
		carregarCombo();
	}

	public void removeContato(){
		listaContatos.removeContato((Contato) formulario.cmbContato.getItemAt(formulario.cmbContato.getSelectedIndex()));
		carregarCombo();
	}

	public ListaContatos getListaContatos(){
		return listaContatos;
	}

	public void gravarMensagem(String mensagem){
		mensagens.add(mensagem);
	}

	public void enviarMensagem(){
		if(formulario.txtTexto.getText().trim().equals("")){
			return;
		}
		new Snippet();
		gravarMensagem(formulario.txtTexto.getText());
		try {
			if(formulario.cmbContato.getSelectedItem() == null){
				class Send implements Runnable{

					String ip = "";
					Send(String ip){
						this.ip = ip;
					}
					@Override
					public void run() {
						try {
							Snippet.enviar(ip, 25450, getMensagem());
						} catch (Exception e) {
						}
					}					
				}
				String nome = pref.get("Nome", "pauno");
				pref.put("Nome", nome + " para Todos ");
				for (int i = 0; i < listaContatos.getContatos().size(); i++) {
					new Thread(new Send(listaContatos.getContatos().get(i).getIp())).run();
				}
				pref.put("Nome", nome);
			}else{
				Snippet.enviar(getIp(), 25450, getMensagem());
			}
			escreveHist("Mensagem de " + getMensagem().split("\\*")[0] + " em " + getMensagem().split("\\*")[1].split("\\|")[0] + " - " + new SimpleDateFormat("HH:mm").format(new Date(System.currentTimeMillis())), "Nome");
			escreveHist("\r\n" + getMensagem().split("\\|")[1] + "\r\n\r\n", "Texto");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		formulario.txtTexto.setText("");
		formulario.txtTexto.requestFocus();
	}

	public class RefreshStatus extends SwingWorker<Void, Void>{

		public boolean loop = true;
		@Override
		protected synchronized Void doInBackground() throws Exception {
			boolean p = true;
			int indice = 0;
			while(loop){
				indice = formulario.cmbContato.getSelectedIndex();
				if(p){
					ArrayList<Contato> contatos = listaContatos.getContatos();

					formulario.cmbContato.removeAllItems();

					for (int i = 0; i < contatos.size(); i++) {
						formulario.cmbContato.addItem(contatos.get(i));
					}
					formulario.cmbContato.setSelectedIndex(pref.getInt("Combo", 0));
					carregarCombo();
					formulario.cmbContato.setSelectedIndex(pref.getInt("Combo", 0));
					p = false;
				}else{
					if(carregar){
						carregarCombo();
						formulario.cmbContato.setSelectedIndex(indice);
					}
				}

				wait(5000);
			}
			return null;
		}
	}
	
	private void iniciaHist(){
		Style style = formulario.txtHist.addStyle("Nome", null);
		StyleConstants.setForeground(style, new Color(pref.getInt("corTitle", 0)));
		style = formulario.txtHist.addStyle("Texto", null);
		StyleConstants.setForeground(style, new Color(pref.getInt("corTexto", 0)));
	}
	
	public void escreveHist(String texto, String style){
		try {
			formulario.txtHist.getDocument().insertString(formulario.txtHist.getDocument().getLength(), texto, formulario.txtHist.getStyle(style));
			formulario.txtHist.setCaretPosition(formulario.txtHist.getDocument().getLength() -1);
		} catch (BadLocationException e) {
			JOptionPane.showMessageDialog(formulario, e.getMessage(), "Erro Historico", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private void instalarSkins(){
		try {
			//QuaQua
			UIManager.installLookAndFeel("Quaqua", QuaquaManager.getLookAndFeelClassName());
			//Liquid
			LiquidLookAndFeel.class.newInstance();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(formulario, e.getMessage(), "Erro Instalação Skins", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void montaSkin(){		
		try {
			UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[Integer.parseInt(pref.get("skin", "0"))].getClassName());
			//Decorations On/Off
			JFrame.setDefaultLookAndFeelDecorated(UIManager.getLookAndFeel().getSupportsWindowDecorations());
			JDialog.setDefaultLookAndFeelDecorated(UIManager.getLookAndFeel().getSupportsWindowDecorations());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(formulario, e.getMessage(), "Erro Skin", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	public void setSkin(){
		TelaSkin objTelaSkin = new TelaSkin(this);
		objTelaSkin.show();
	}
	
	public void setFont(){
		formulario.txtHist.setFont(FontChooser.showDialog(new Font(pref.get("fonteNome", "Arial"), pref.getInt("fonteEstilo", Font.PLAIN), pref.getInt("fonteTamanho", 12))));
		pref.put("fonteTamanho", String.valueOf(formulario.txtHist.getFont().getSize()));
		pref.put("fonteNome", formulario.txtHist.getFont().getName());
		pref.put("fonteEstilo", String.valueOf(formulario.txtHist.getFont().getStyle()));
	}
	
	public void setCores(){
		
		Style style;
		Color corTitle;
		Color corTexto;
		
		corTitle = JColorChooser.showDialog(formulario, "Cor Titulo", new Color(Integer.valueOf(pref.get("corTitle", "000"))));
		corTitle = corTitle == null ? new Color(Integer.valueOf(pref.get("corTitle", "000"))):corTitle;
		style = formulario.txtHist.addStyle("Nome", null);
		StyleConstants.setForeground(style, corTitle);
		pref.put("corTitle", String.valueOf(corTitle.getRGB()));
		corTexto = JColorChooser.showDialog(formulario, "Cor Texto", new Color(Integer.valueOf(pref.get("corTexto", "000"))));
		corTexto = corTexto == null ? new Color(Integer.valueOf(pref.get("corTexto", "000"))):corTexto;
		style = formulario.txtHist.addStyle("Texto", null);
		StyleConstants.setForeground(style, corTexto);
		pref.put("corTexto", String.valueOf(corTexto.getRGB()));
	}
	
	public void enviarArquivo(){
		JFileChooser fc = new JFileChooser();
		fc.setApproveButtonToolTipText("Enviar Visual Arquivo");
		fc.setDialogTitle("Visual Upload");
		fc.setMultiSelectionEnabled(true);
		if(fc.showOpenDialog(formulario) != JFileChooser.CANCEL_OPTION)
		{
			try
			{
				File[] files = fc.getSelectedFiles();
				TransferFile.uploadFile(files, false);
				String msg = "F|";
				for (int i = 0; i < files.length; i++) {
					msg += files[i]  + "@" + String.valueOf(files[i].length()) + ";";
				}
				Snippet.enviar(((Contato)formulario.cmbContato.getSelectedItem()).getIp(), 25498, msg);
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
	}
	
	public void enviarArquivoDireto(File[] files)
	{
		try
		{
			TransferFile.uploadFile(files, false);
			String msg = "F|";
			for (int i = 0; i < files.length; i++) {
				msg += files[i]  + "@" + String.valueOf(files[i].length()) + ";";
			}
			Snippet.enviar(((Contato)formulario.cmbContato.getSelectedItem()).getIp(), 25498, msg);
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void receberArquivo(String[] files, String ip){
		TelaDownload down;		
		
		for (int i = 0; i < files.length; i++) {
			down = new TelaDownload();
			down.setTitle("Visual Download");
			down.download(new String[]{files[i]}, ip);
		}
	}
	
	private void autoUpdate(String ipServer)
	{
		File arquivo = new File("Update.jar");
		
		try
		{
			TransferFile.downloadFile(arquivo.getAbsolutePath(), ipServer);
			if(arquivo.exists())
			{
				 JOptionPane.showMessageDialog(formulario, "Atualização Recebida Reinicie para\nInstalação da Atualização", "Atualização", JOptionPane.INFORMATION_MESSAGE);
				 Runtime.getRuntime().exec("java -jar " +  new File("Visual Mensagem.jar").getAbsolutePath() + " start");
				 System.exit(0);
			}
		}
		catch (ConnectException e) 
		{
			System.out.println("Sem Conexão com Servidor de Atualizações.");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void expandeHistorico(){
		new Thread(new ExpandeObject(formulario, (!expandido?formulario.getSize().height + 100:formulario.getSize().height - 100), 20, ExpandeObject.VERTICAL, true)).start();
		expandido = !expandido;
	}
	
	public static void main(String[] args)
	{
		new Programa(!args[0].equals("hide"));
	}
}
