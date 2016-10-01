package br.com.heuwald.engine;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Pintura {

    Blocos blocos = new Blocos();

    Engine en;
    ImageObserver io;
    Image fundo;
    int sizeX = 400, sizeY = 700, blockSize, xBloco, yBloco;

    Pintura(Engine en, ImageObserver io) {

	this.en = en;

	this.io = io;
	blockSize = 20;

	BufferedImage bi = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_BYTE_INDEXED);
	Graphics g;
	g = bi.getGraphics();
	g.setColor(Color.BLACK);
	Image imFundo = new ImageIcon(getClass().getResource("/br/com/heuwald/resources/musics/fundo.jpg")).getImage();
	g.drawImage(imFundo, 0, 0, sizeX, sizeY, io);

	int gradeAltura = en.grade.getAltura();
	int gradeLargura = en.grade.getLargura();

	Graphics2D gd = (Graphics2D) g;

	for (int i = 0; i < gradeLargura; i++) {
	    for (int j = 0; j < gradeAltura; j++) {

			xBloco = (i * blockSize + 11);
			yBloco = (j * blockSize + 11);
		
			gd.setColor(Color.GRAY);
			gd.drawImage(blocos.VAZIO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
			System.out.println();
	    }
	}

	fundo = bi.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
    }

    public synchronized Image pintaTela() {

	try {
	    Thread.sleep(25);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	BufferedImage bi = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
	Graphics g;
	g = bi.getGraphics();

	g.drawImage(fundo, 0, 0, io);

	pintaGridPrincipal(g);
	pintaProxPeca(g);
	pintaInfo(g);

	return bi.getScaledInstance(sizeX, sizeY, Image.SCALE_DEFAULT);
    }

    public void pintaGridPrincipal(Graphics g) {

	int gradeAltura = en.grade.getAltura();
	int gradeLargura = en.grade.getLargura();

	Graphics2D gd = (Graphics2D) g;

	for (int i = 0; i < gradeLargura; i++) {
	    for (int j = 0; j < gradeAltura; j++) {
		Bloco bloco = en.grade.getBloco(i, j);
		//
		xBloco = (i * blockSize + 11);
		yBloco = (j * blockSize + 11);

		if (bloco != null && bloco.existe) {

		    if (bloco.cor.getRGB() == Color.RED.getRGB()) {
			gd.drawImage(blocos.VERMELHO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.BLUE.getRGB()) {
			gd.drawImage(blocos.AZUL, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.GREEN.getRGB()) {
			gd.drawImage(blocos.VERDE, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.YELLOW.getRGB()) {
			gd.drawImage(blocos.AMARELO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.ORANGE.getRGB()) {
			gd.drawImage(blocos.LARANJA, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.PINK.getRGB()) {
			gd.drawImage(blocos.ROSA, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.BLACK.getRGB()) {
			gd.drawImage(blocos.PRETO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.WHITE.getRGB()) {
			gd.drawImage(blocos.BRANCO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else {
			gd.drawImage(blocos.AZUL, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    }
		}
	    }
	}

    }

    public void pintaProxPeca(Graphics g) {
	Graphics2D gd = (Graphics2D) g;

	if (en.getProxPeca() == null)
	    return;

	int x = 230, y = 10, xBloco, yBloco;

	gd.setColor(Color.GRAY);
	gd.drawImage(blocos.VAZIO, x, y, 4 * blockSize, 4 * blockSize, io);

	if(en.isGameOver()){
	    pintaFim();
	}
	
	for (int i = 0; i < en.getProxPeca().getLargura(); i++) {
	    for (int j = 0; j < en.getProxPeca().getAltura(); j++) {
		Bloco bloco = en.getProxPeca().getBloco(i, j);
		if (bloco != null && bloco.existe) {

		    int xf = (4 * blockSize) / 2 - (en.getProxPeca().getLargura() * blockSize) / 2;
		    int yf = (4 * blockSize) / 2 - (en.getProxPeca().getAltura() * blockSize) / 2;
		    ;
		    xBloco = (i * blockSize + 1) + x + xf;
		    yBloco = (j * blockSize + 1) + y + yf;

		    if (bloco.cor.getRGB() == Color.RED.getRGB()) {
			gd.drawImage(blocos.VERMELHO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.BLUE.getRGB()) {
			gd.drawImage(blocos.AZUL, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.GREEN.getRGB()) {
			gd.drawImage(blocos.VERDE, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.YELLOW.getRGB()) {
			gd.drawImage(blocos.AMARELO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.ORANGE.getRGB()) {
			gd.drawImage(blocos.LARANJA, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.PINK.getRGB()) {
			gd.drawImage(blocos.ROSA, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.BLACK.getRGB()) {
			gd.drawImage(blocos.PRETO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else if (bloco.cor.getRGB() == Color.WHITE.getRGB()) {
			gd.drawImage(blocos.BRANCO, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    } else {
			gd.drawImage(blocos.AZUL, xBloco, yBloco, blockSize - 1, blockSize - 1, io);
		    }
		}
	    }
	}

    }

    public void pintaInfo(Graphics g) {

	Graphics2D gd = (Graphics2D) g;

	gd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	gd.setFont(new Font("Arial", Font.BOLD, 12));
	gd.setColor(Color.WHITE);
	gd.drawString("Score: " + en.getPontos(), 230, 120);
	gd.drawString("Lines: " + en.getLinhas(), 230, 140);
    }
    
    public void pintaFim(){
	Bloco b = new Bloco();
	b.idPeca = 1000000;
	b.cor = Color.BLACK;
	b.existe = true;
	for (int i = 0; i < en.grade.getAltura(); i++) {
	    for (int j = 0; j < en.grade.getLargura(); j++) {
		if(en.grade.getBloco(j, i) == null || en.grade.getBloco(j, i).idPeca != 1000000){
		    en.grade.setBloco(b, j, i);
		    return;
		}    
	    }    
	}
    }
}
