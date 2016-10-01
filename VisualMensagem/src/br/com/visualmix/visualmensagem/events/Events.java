package br.com.visualmix.visualmensagem.events;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import com.sun.awt.AWTUtilities;
import br.com.visualmix.visualmensagem.componentes.ExpandeObject;
import br.com.visualmix.visualmensagem.principal.Programa;
import br.com.visualmix.visualmensagem.principal.Tela;

public class Events implements ActionListener, MouseListener, MouseWheelListener, FocusListener, DropTargetListener{

	private Programa prog;;

	public Events(Programa prog) {
		this.prog = prog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(prog.formulario.btnEnviar)){
			prog.enviarMensagem();
		}
		if(e.getSource().equals(prog.formulario.btnAdd)){
			prog.addContato();
		}
		if(e.getSource().equals(prog.formulario.btnDel)){
			prog.removeContato();
		}
		if(e.getSource().equals(prog.formulario.btnHist)){
			prog.expandeHistorico();
		}
		if(e.getSource().equals(prog.formulario.btnOpcoes)){
			if(prog.formulario.getSize().width < 400){
				prog.formulario.btnOpcoes.setLocation(453, 10);
				prog.formulario.btnOpcoes.setBorder(BorderFactory.createBevelBorder(1));
				new Thread(new ExpandeObject(prog.formulario, 476, 10, ExpandeObject.HORIZONTAL, true)).start();
				new Thread(new ExpandeObject(prog.formulario.spHist, 475, 10, ExpandeObject.HORIZONTAL, true)).start();
				prog.formulario.btnOpcoes.setText("<");

			}else{
				prog.formulario.btnOpcoes.setLocation(362, 10);
				prog.formulario.btnOpcoes.setBorder(BorderFactory.createBevelBorder(0));
				new Thread(new ExpandeObject(prog.formulario, 385, 10, ExpandeObject.HORIZONTAL, true)).start();
				new Thread(new ExpandeObject(prog.formulario.spHist, 384, 10, ExpandeObject.HORIZONTAL, true)).start();
				prog.formulario.btnOpcoes.setText(">");

			}
		}

		if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_SHOW))){

			prog.formulario.setVisible(true);
			if(prog.srvMSG.isMascarado()){
				if(prog.srvMSG.alerta != null){
					if(prog.srvMSG.alerta.loop == true){
						prog.formulario.setVisible(true);
						if(!prog.expandido){prog.expandeHistorico();}
						prog.srvMSG.alerta.loop = false;
						prog.srvMSG.alerta = null;
					}
				}
			}

		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_EXIT))){

			prog.getListaContatos().gravarContatos();
			prog.pref.putInt("Combo", prog.formulario.cmbContato.getSelectedIndex());
			prog.statusRefresh.loop = false;
			System.exit(0);

		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_NICK))){

			prog.setNick();

		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_SUSTENIDO))){

			prog.srvMSG.setAutorizaSustenido(!prog.srvMSG.isAutorizaSustenido());
			prog.formulario.popup.getItem(Tela.BTN_SUSTENIDO).setLabel("setAutoriza#(" + prog.srvMSG.isAutorizaSustenido() + ")");

		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_MASCARADO))){

			prog.srvMSG.setMascarado(!prog.srvMSG.isMascarado());
			prog.formulario.popup.getItem(Tela.BTN_MASCARADO).setLabel("setMascarado(" + prog.srvMSG.isMascarado() + ")");
		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_STATUS))){

			prog.setStatus();
		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_IP))){
			prog.setIP();
		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_SKIN))){
			prog.setSkin();
		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_CORES))){
			prog.setCores();
		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_FONT))){
			prog.setFont();
		}else if(e.getSource().equals(prog.formulario.popup.getItem(Tela.BTN_FILE))){
			prog.enviarArquivo();
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getButton() == MouseEvent.BUTTON1 && e.getSource().equals(prog.formulario.trayIcon)){
			prog.formulario.setVisible(true);
			if(prog.srvMSG.isMascarado()){
				if(prog.srvMSG.alerta != null){
					if(prog.srvMSG.alerta.loop == true){
						prog.formulario.setVisible(true);
						if(!prog.expandido){prog.expandeHistorico();}
						prog.srvMSG.alerta.loop = false;
						prog.srvMSG.alerta = null;
					}
				}
			}
		}
		if(e.getSource().equals(prog.formulario) && e.getButton() == MouseEvent.BUTTON3){
			prog.formulario.popup.show(prog.formulario, e.getXOnScreen(), e.getYOnScreen());
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	byte cont;
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
//		try {
//			if(cont >= 3){
//				cont = 0;
//				prog.formulario.cmbContato.setSelectedIndex(prog.formulario.cmbContato.getSelectedIndex() + e.getWheelRotation());
//				return;
//			}
//			cont++;
//		} catch (Exception e2) {
//		}
		if(e.getSource().equals(prog.formulario.pPanel))
		{
			if(AWTUtilities.getWindowOpacity(prog.formulario) >= 0.1f && AWTUtilities.getWindowOpacity(prog.formulario) <= 1)
				AWTUtilities.setWindowOpacity(prog.formulario, AWTUtilities.getWindowOpacity(prog.formulario) + (e.getWheelRotation()/50f) < 0.1 ? 0.1f:AWTUtilities.getWindowOpacity(prog.formulario) + (e.getWheelRotation()/50f) > 1.0f?1.0f:AWTUtilities.getWindowOpacity(prog.formulario) + (e.getWheelRotation()/50f));
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(prog.formulario.cmbContato)){
			prog.carregar = false;
		}		
	}

	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource().equals(prog.formulario.cmbContato)){
			prog.carregar = true;
		}		
	}

	@Override
	public void dragEnter(DropTargetDragEvent dtde)
	{
		
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde)
	{
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde)
	{
	}

	@Override
	public void dragExit(DropTargetEvent dte)
	{
	}

	@Override
	public void drop(DropTargetDropEvent dtde)
	{
		Transferable transfer = dtde.getTransferable();
		List<?> arquivos;
		
		try
		{
			dtde.acceptDrop(DnDConstants.ACTION_LINK);
			arquivos = (List<?>) transfer.getTransferData(dtde.getCurrentDataFlavors()[0]);
			prog.enviarArquivoDireto((File[])arquivos.toArray());
			dtde.dropComplete(true);
		}
		catch (UnsupportedFlavorException e)
		{
			JOptionPane.showMessageDialog(prog.formulario, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
