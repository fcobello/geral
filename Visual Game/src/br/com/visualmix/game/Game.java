package br.com.visualmix.game;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Game 
{
	private final static int player1 = 0;
	private final static int player2 = 1;
	private static boolean fimJogo = false;
	private Tela objTela;
	
	public Game(Tela tela)
	{
		objTela = tela;
		if((int)(Math.random() * 1.99) == player1)
		{
			objTela.player = player2;
			objTela.lblMsg.setForeground(Tela.GREEN);
			objTela.lblMsg.setText("Computador");
			Jogar(objTela, IA.JogadaAleatoria(objTela));
		}
		else
		{
			objTela.player = player1;
			objTela.lblMsg.setForeground(Tela.RED);
			objTela.lblMsg.setText("Você");
		}
			
	}
	
	//Efetua a Jogada
	public static void Jogar(Tela objTela, JTextField txtArea)
	{	
		//Verifica se Jogo Terminou
		if(fimJogo)
			return;
		//Verifica o Player
		if(objTela.player == player1)
		{
			objTela.player = player2;
			objTela.lblMsg.setForeground(Tela.GREEN);
			objTela.lblMsg.setText("Computador");
			txtArea.setForeground(Tela.RED);
			txtArea.setText("X");
		}
		else
		{
			objTela.player = player1;
			objTela.lblMsg.setForeground(Tela.RED);
			objTela.lblMsg.setText("Você");
			txtArea.setForeground(Tela.GREEN);
			txtArea.setText("O");
		}
		
		//Verifica os Resultados
		switch (checkResult(objTela))
		{
		case 1:
			JOptionPane.showMessageDialog(null, "Player 1 Vencedor!!!");
			fimJogo = true;
			break;
		case 2:
			fimJogo = true;
			JOptionPane.showMessageDialog(null, "Computador Vencedor!!!");
			break;
		case 3:
			fimJogo = true;
			JOptionPane.showMessageDialog(null, "Jogo Empatado!!!");
			break;
		default:
			break;
		}
	}
	//Checa os Resultados
	private static int checkResult(Tela objTela)
	{
		int retorno = 0;
		String A = objTela.txtA1.getText()+objTela.txtA2.getText()+objTela.txtA3.getText();
		String B = objTela.txtA4.getText()+objTela.txtA5.getText()+objTela.txtA6.getText();
		String C = objTela.txtA7.getText()+objTela.txtA8.getText()+objTela.txtA9.getText();
		String D = objTela.txtA1.getText()+objTela.txtA4.getText()+objTela.txtA7.getText();
		String E = objTela.txtA2.getText()+objTela.txtA5.getText()+objTela.txtA8.getText();
		String F = objTela.txtA3.getText()+objTela.txtA6.getText()+objTela.txtA9.getText();
		String G = objTela.txtA1.getText()+objTela.txtA5.getText()+objTela.txtA9.getText();
		String H = objTela.txtA3.getText()+objTela.txtA5.getText()+objTela.txtA7.getText();
		
		if(A.equals("XXX"))
			retorno = player1;
		else
		{
			if(A.equals("OOO"))
				retorno = player2;
			else
				if(B.equals("XXX"))
					retorno = player1;
				else
					if(B.equals("OOO"))
						retorno = player2;
					else
						if(C.equals("XXX"))
							retorno = player1;
						else
							if(C.equals("OOO"))
								retorno = player2;
							else
								if(D.equals("XXX"))
									retorno = player1;
								else
									if(D.equals("OOO"))
										retorno = player2;
									else
										if(E.equals("XXX"))
											retorno = player1;
										else
											if(E.equals("OOO"))
												retorno = player2;
											else
												if(F.equals("XXX"))
													retorno = player1;
												else
													if(F.equals("OOO"))
														retorno = player2;
													else
														if(G.equals("XXX"))
															retorno = player1;
														else
															if(G.equals("OOO"))
																retorno = player2;
															else
																if(H.equals("XXX"))
																	retorno = player1;
																else
																	if(H.equals("OOO"))
																		retorno = player2;
		}
		//Verifica se Todas os campos foram preenchidos e não houve ganhador declara empatado
		if((A.length() + B.length() + C.length() + D.length() + E.length() + F.length() + G.length() + H.length()) == 24) 
			retorno = 3; 
		return retorno;
	}
	
	public static void main(String[] args) 
	{
		new Game(new Tela());
	}
}
