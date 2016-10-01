package br.com.heuwald.engine;
import java.awt.Color;
import java.util.ArrayList;

public class Engine implements Runnable {

    public Grade grade;
    public ArrayList<Peca> pecas = new ArrayList<Peca>();
    private Peca pecaFoco = null;
    private Peca proxPeca = null;
    private int time = 500;
    private boolean gameOver;
    private boolean pauseGame;
    public static final int MOVE_NORTE = 1;
    public static final int MOVE_SUL = 1;
    public static final int MOVE_LESTE = 1;
    public static final int MOVE_OESTE = 1;
    private long pontos = 0;
    private int linhas = 0;

    public Engine() {
	grade = new Grade(10, 30);
    }

    private Peca criaPecaPadrao() {

	Peca peca = new Peca(getNovoId());

	int x = (int) (Math.random() * 7);
	switch (x) {
	case 0:
	    peca.criaBloco(0, 1, Color.blue);
	    peca.criaBloco(1, 1, Color.blue);
	    peca.criaBloco(2, 1, Color.blue);
	    peca.criaBloco(3, 1, Color.blue);//
	    break;
	case 1:
	    peca.criaBloco(0, 0, Color.YELLOW);
	    peca.criaBloco(0, 1, Color.YELLOW);
	    peca.criaBloco(1, 0, Color.YELLOW);
	    peca.criaBloco(1, 1, Color.YELLOW);//
	    break;
	case 2:
	    peca.criaBloco(1, 0, Color.RED);
	    peca.criaBloco(1, 1, Color.RED);
	    peca.criaBloco(1, 2, Color.RED);
	    peca.criaBloco(2, 2, Color.RED);//
	    break;
	case 3:
	    peca.criaBloco(1, 0, Color.GREEN);
	    peca.criaBloco(1, 1, Color.GREEN);
	    peca.criaBloco(1, 2, Color.GREEN);
	    peca.criaBloco(0, 2, Color.GREEN);//
	    break;
	case 4:
	    peca.criaBloco(1, 1, Color.ORANGE);
	    peca.criaBloco(0, 2, Color.ORANGE);
	    peca.criaBloco(1, 2, Color.ORANGE);
	    peca.criaBloco(2, 2, Color.ORANGE);//
	    break;
	case 5:
	    peca.criaBloco(0, 0, Color.PINK);
	    peca.criaBloco(0, 1, Color.PINK);
	    peca.criaBloco(1, 1, Color.PINK);
	    peca.criaBloco(1, 2, Color.PINK);//
	    break;
	case 6:
	    peca.criaBloco(1, 0, Color.BLACK);
	    peca.criaBloco(1, 1, Color.BLACK);
	    peca.criaBloco(0, 1, Color.BLACK);
	    peca.criaBloco(0, 2, Color.BLACK);
	    break;
	case 7:
	    peca.criaBloco(1, 0, Color.WHITE);
	    peca.criaBloco(1, 1, Color.WHITE);
	    peca.criaBloco(0, 1, Color.WHITE);
	    peca.criaBloco(0, 2, Color.WHITE);
	    break;

	default:
	  	    
	    break;
	}

	return peca;
    }

    private int getNovoId() {
	int nId = 0;
	while (true) {
	    nId = (int) (Math.random() * 100000);
	    for (Peca peca : pecas) {
		if (peca.getIdPeca() == nId)
		    break;
	    }
	    return nId;
	}
    }

    public synchronized void play() {
	gameOver = false;
	new Thread(this).start();
    }

    public void printConsole() {
	String teste = "";
	for (int i = 0; i < grade.getAltura(); i++) {
	    for (int j = 0; j < grade.getLargura(); j++) {
		if (grade.getBloco(j, i) != null && grade.getBloco(j, i).existe)
		    teste += "@ ";
		else
		    teste += ". ";
	    }
	    teste += "\n";
	}
	System.out.println(teste);
    }

    @Override
    public synchronized void run() {

	while (true) {

	    try {
		if (pauseGame) {
		    wait(100);
		    continue;
		}
		if (movePecaBaixo()) {
		    wait(time);
		} else {
		    if (gameOver) {
			return;
		    }
		}
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	}
    }

    public boolean movePecaBaixo() {
	if (pecaFoco != null) {
	    pecaFoco.setLocationY(pecaFoco.getLocationY() + 1);
	}

	if (pecaFoco == null || !grade.insere(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY())) {
	    if (pecaFoco != null && pecaFoco.getLocationY() < 0) {
		gameOver = true;
		return false;
	    }
	    Peca peca = criaPecaPadrao();

	    verificaPontos();
	    peca.trimPeca();
	    addPeca(peca);
	    return false;
	} else {
	    return true;
	}
    }

    public void movePecaLeft() {
	if (pecaFoco.getLocationX() - 1 < 0) {
	    return;
	}
	pecaFoco.setLocationX(pecaFoco.getLocationX() - 1);
	if (grade.encaixa(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY()))
	    grade.insere(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY());
	else
	    pecaFoco.setLocationX(pecaFoco.getLocationX() + 1);
    }

    public void movePecaRight() {
	if (pecaFoco.getLocationX() + pecaFoco.getLargura() + 1 > grade.getLargura()) {
	    return;
	}
	pecaFoco.setLocationX(pecaFoco.getLocationX() + 1);
	if (grade.encaixa(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY()))
	    grade.insere(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY());
	else
	    pecaFoco.setLocationX(pecaFoco.getLocationX() - 1);
    }

    public void giraPeca() {
	pecaFoco.giraPeca();
	
	Peca pecaAux = pecaFoco.copiaPeca();
	
	if (grade.encaixa(pecaAux, pecaFoco.getLocationX(), pecaFoco.getLocationY()))
	    grade.insere(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY());
	
    }

    private void verificaPontos() {
	boolean removeFila;
	int desceBlocos = 0;

	for (int i = grade.getAltura() - 1; i >= 0; i--) {
	    removeFila = true;
	    for (int j = 0; j < grade.getLargura(); j++) {

		if (grade.getBloco(j, i) == null || !grade.getBloco(j, i).existe) {
		    removeFila = false;
		}

		if (grade.getBloco(j, i) != null) {
		    if (desceBlocos > 0) {
			grade.setBloco(grade.getBloco(j, i), j, i + desceBlocos);
			grade.setBloco(null, j, i);
		    }
		}

	    }
	    if (removeFila) {
		for (int j = 0; j < grade.getLargura(); j++) {
		    if (grade.getBloco(j, i + desceBlocos) != null) {
			grade.setBloco(null, j, i + desceBlocos);
		    }
		}
		desceBlocos++;
		pontos += 192 * (desceBlocos * desceBlocos);
		int p = (int) (pontos / 10000);
		time = 500 - (p * 10);
	    }
	}
	linhas += desceBlocos;
    }

    public void addPeca(Peca peca) {
	if (proxPeca != null) {
	    setPecaFoco(proxPeca.copiaPeca());
	}

	proxPeca = peca;

	if (pecaFoco != null) {
	    pecaFoco.trimPeca();
	    pecaFoco.setLocationX((grade.getLargura() / 2) - (pecaFoco.getLargura() / 2));
	    pecaFoco.setLocationY(-1 * pecaFoco.getAltura());

	    grade.insere(pecaFoco, pecaFoco.getLocationX(), pecaFoco.getLocationY());
	}
    }

    public void movePeca(int direcao) {

    }

    public void setTime(int time) {
	this.time = time;
    }

    public int getTime() {
	return time;
    }

    public void setPecaFoco(Peca pecaFoco) {
	this.pecaFoco = pecaFoco;
    }

    public Peca getPecaFoco() {
	return pecaFoco;
    }

    public long getPontos() {
	return pontos;
    }

    public void setPontos(long pontos) {
	this.pontos = pontos;
    }

    public void setGameOver(boolean gameOver) {
	this.gameOver = gameOver;
    }

    public boolean isGameOver() {
	return gameOver;
    }

    public Peca getProxPeca() {
	return proxPeca;
    }

    public void setProxPeca(Peca proxPeca) {
	this.proxPeca = proxPeca;
    }

    public void setPauseGame(boolean pauseGame) {
	this.pauseGame = pauseGame;
    }

    public boolean isPauseGame() {
	return pauseGame;
    }

    public void setLinhas(int linhas) {
	this.linhas = linhas;
    }

    public int getLinhas() {
	return linhas;
    }

    public static void main(String[] args) {
	Engine e = new Engine();
	e.play();
    }

}
