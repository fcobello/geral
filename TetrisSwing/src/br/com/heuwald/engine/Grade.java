package br.com.heuwald.engine;
public class Grade {

    private Bloco grade[][];

    private int largura, altura;

    public Grade(int x, int y) {
	grade = new Bloco[x][y];
	this.setLargura(x);
	this.setAltura(y);
    }

    public void removePeca(int id) {
	for (int i = 0; i < largura; i++) {
	    for (int j = 0; j < altura; j++) {
		if (grade[i][j] != null) {
		    if (grade[i][j].idPeca == id) {
			grade[i][j] = null;
		    }
		}
	    }
	}
    }

    public boolean insere(Peca peca, int x, int y) {
	if (!encaixa(peca, x, y)) {
	    return false;
	}
	removePeca(peca.getIdPeca());
	for (int i = x; i < (x + peca.getLargura()); i++) {
	    for (int j = y; j < (y + peca.getAltura()); j++) {
		if (j < 0)
		    continue;
		if (peca.getBloco(i - x, j - y) != null) {
		    if (peca.getBloco(i - x, j - y).existe && (getBloco(i, j) == null || peca.getBloco(i - x, j - y).idPeca == getBloco(i, j).idPeca)) {
			setBloco(peca.getBloco(i - x, j - y), i, j);
		    }
		}
	    }
	}
	return true;
    }

    public boolean encaixa(Peca peca, int x, int y) {
	if (x + peca.getLargura() > largura) {
	    return false;
	}
	if (y + peca.getAltura() > altura) {
	    return false;
	}
	for (int i = x; i < (x + peca.getLargura()); i++) {
	    for (int j = y; j < (y + peca.getAltura()); j++) {
		if (j < 0)
		    continue;
		if (getBloco(i, j) != null) {
		    if (getBloco(i, j).existe) {
			if (peca.getBloco(i - x, j - y) != null) {
			    if (peca.getBloco(i - x, j - y).existe && (getBloco(i, j) != null && peca.getBloco(i - x, j - y).idPeca != getBloco(i, j).idPeca)) {
				return false;
			    }
			}
		    }
		}
	    }
	}
	return true;
    }

    public Bloco getBloco(int x, int y) {
	return grade[x][y];
    }

    public Bloco setBloco(Bloco b, int x, int y) {
	return grade[x][y] = b;
    }

    public void setLargura(int l) {
	this.largura = l;
    }

    public int getLargura() {
	return largura;
    }

    public void setAltura(int a) {
	this.altura = a;
    }

    public int getAltura() {
	return altura;
    }

    public Bloco[][] getGrade() {
	return grade;
    }
}
