package br.com.heuwald.engine;
import java.awt.Color;

public class Peca {

    private Bloco peca[][];
    private int idPeca;

    private int locationX, locationY;
    private int altura = 4, largura = 4;

    public Peca(int id) {
	peca = new Bloco[4][4];
	idPeca = id;
    }

    public void criaBloco(int x, int y) {
	criaBloco(x, y, Color.BLACK);
    }

    public void criaBloco(int x, int y, Color cor) {
	Bloco b = new Bloco();
	b.existe = true;
	b.idPeca = this.idPeca;
	b.cor = cor;

	peca[x][y] = b;
    }

    public Bloco novoBloco(Color cor) {
	Bloco b = new Bloco();
	b.existe = true;
	b.idPeca = this.idPeca;
	return b;
    }

    public void removeBloco(int x, int y) {
	peca[x][y] = null;
    }

    public void trimPeca() {
	int x = 0, y = 0;
	int norte = 0, leste = 0, oeste = 0, sul = 0;

	boolean flag = false;

	for (int i = 0; i < peca.length; i++) {
	    for (int j = 0; j < peca.length; j++) {
		if (peca[i][j] != null && peca[i][j].existe) {
		    oeste = i;
		    flag = true;
		    break;
		}
	    }
	    if (flag)
		break;
	}

	flag = false;
	for (int i = 0; i < peca.length; i++) {
	    for (int j = 0; j < peca.length; j++) {
		if (peca[j][i] != null && peca[j][i].existe) {
		    norte = i;
		    flag = true;
		    break;
		}
	    }
	    if (flag)
		break;
	}

	flag = false;
	for (int i = peca.length - 1; i >= 0; i--) {
	    for (int j = 0; j < peca.length; j++) {
		if (peca[i][j] != null && peca[i][j].existe) {
		    x = i;
		    leste = i;
		    flag = true;
		    break;
		}
	    }
	    if (flag)
		break;
	}

	flag = false;
	for (int i = peca.length - 1; i >= 0; i--) {
	    for (int j = 0; j < peca.length; j++) {
		if (peca[j][i] != null && peca[j][i].existe) {
		    y = i;
		    sul = i;
		    flag = true;
		    break;
		}
	    }
	    if (flag)
		break;
	}

	int lado = ((x > y) ? x : y) + 1;

	Bloco p[][] = new Bloco[lado][lado];
	for (int i = 0; i <= sul; i++) {
	    for (int j = 0; j <= leste; j++) {
		if (peca[j][i] != null && peca[j][i].existe) {
		    p[j - oeste][i - norte] = peca[j][i];
		}
	    }
	}

	setLargura((leste - oeste) + 1);
	setAltura((sul - norte) + 1);

	peca = p;
    }

    public void giraPeca() {
	Bloco p[][] = new Bloco[peca.length][peca.length];
	for (int i = 0; i < peca.length; i++) {
	    for (int j = 0; j < peca.length; j++) {
		p[peca.length - 1 - j][i] = peca[i][j];

	    }
	}
	peca = p;
	trimPeca();
    }

    public int getTamanhoGradePeca() {
	return peca.length;
    }

    public Bloco getBloco(int x, int y) {
	return peca[x][y];
    }

    public Peca copiaPeca() {
	Peca peca = new Peca(idPeca);
	Bloco b;
	for (int i = 0; i < largura; i++) {
	    for (int j = 0; j < altura; j++) {
		if (this.peca[i][j] != null && this.peca[i][j].existe) {

		    b = new Bloco();
		    b.existe = this.peca[i][j].existe;
		    b.cor = new Color(this.peca[i][j].cor.getRGB());
		    b.idPeca = this.peca[i][j].idPeca;

		    peca.peca[i][j] = b;
		}
	    }
	}
	return peca;
    }

    public void setLocationX(int locationX) {
	this.locationX = locationX;
    }

    public int getLocationX() {
	return locationX;
    }

    public void setLocationY(int locationY) {
	this.locationY = locationY;
    }

    public int getLocationY() {
	return locationY;
    }

    public int getIdPeca() {
	return idPeca;
    }

    public void setIdPeca(int idPeca) {
	this.idPeca = idPeca;
    }

    public void setAltura(int altura) {
	this.altura = altura;
    }

    public int getAltura() {
	return altura;
    }

    public void setLargura(int largura) {
	this.largura = largura;
    }

    public int getLargura() {
	return largura;
    }
}
