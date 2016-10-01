package br.com.visualmix.visualmensagem.componentes;

import java.awt.Component;

import javax.swing.SwingWorker;

public class ExpandeObject extends SwingWorker<Void, Void>{

	private Component objeto;
	private int fim;
	private int sentido;
	private int passo;
	private boolean suavizar;
	public static final int HORIZONTAL = 1;
	public static final int VERTICAL = 2;

	public ExpandeObject(Component objeto, int pixels, int passo, int sentido, boolean suavizar) {
		this.objeto = objeto;
		this.fim = /*((sentido == HORIZONTAL)?objeto.getSize().width:objeto.getSize().height) + */pixels;
		this.sentido = sentido;
		this.passo = passo;
		this.suavizar = suavizar;
	}

	public static void main(String[] args) {
		//		JFrame frame = new JFrame();
		//		frame.setSize(200, 200);
		//		frame.setVisible(true);
		//		new ExpandeObject(frame, 100, 5, ExpandeObject.HORIZONTAL).execute();
		//		System.out.println("pauno");
		//		new ExpandeObject(frame, 100, 5, ExpandeObject.VERTICAL).execute();
	}

	@Override
	protected synchronized Void doInBackground() throws Exception {
		try {
			boolean aumenta = ((sentido == HORIZONTAL)?objeto.getSize().width:objeto.getSize().height) < fim;

			while(fim != ((sentido == HORIZONTAL)?objeto.getSize().width:objeto.getSize().height)){
				try {
					wait(1);
					if(suavizar){
						if(aumenta){
							if(((sentido == HORIZONTAL)?objeto.getSize().width:objeto.getSize().height) + (4*passo) > fim){
								if(passo > 1){
									passo = passo/2;
								}
							}
						}else{
							if(((sentido == HORIZONTAL)?objeto.getSize().width:objeto.getSize().height) - (4*passo) < fim){
								if(passo > 1){
									passo = passo/2;
								}
							}
						}
					}
					objeto.setSize(((sentido == HORIZONTAL)?objeto.getSize().width+((aumenta)?passo:-passo):objeto.getSize().width), (sentido == VERTICAL)?objeto.getSize().height + ((aumenta)?passo:-passo):objeto.getSize().height);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if(aumenta){
					if(((sentido == HORIZONTAL)?objeto.getSize().width:objeto.getSize().height) >= fim){
						break;
					}
				}else{
					if(((sentido == HORIZONTAL)?objeto.getSize().width:objeto.getSize().height) <= fim){
						break;
					}
				}
			}
			if(sentido == HORIZONTAL){
				objeto.setSize(fim, objeto.getSize().height);
			}else{
				objeto.setSize(objeto.getSize().width, fim);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}
}
