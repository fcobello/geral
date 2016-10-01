package br.com.visualmix.visualmensagem.events;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import br.com.visualmix.visualmensagem.principal.Programa;

public class WindowEvents implements WindowListener{

	private Programa prog;;
	
	public WindowEvents(Programa prog) {
		this.prog = prog;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		if(e.getSource().equals(prog.formulario)){
			if(prog.srvMSG.isMascarado()){
				if(prog.srvMSG.isMascarado()){
					if(prog.srvMSG.alerta != null){
						if(prog.srvMSG.alerta.loop == true){
							if(!prog.expandido){prog.expandeHistorico();}
							prog.srvMSG.alerta.loop = false;
							prog.srvMSG.alerta = null;
						}
					}
				}
			}
}
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowClosing(WindowEvent e) {
//		prog.hist.setVisible(false);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
