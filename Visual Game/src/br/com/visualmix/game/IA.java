package br.com.visualmix.game;

import javax.swing.JTextField;

public class IA 
{
	
	//Inteligencia do Player Computador
	public static JTextField CPUJogar(Tela objTela, JTextField txtArea)
	{
		JTextField retorno = null;
		
		if(txtArea.equals(objTela.txtA1))
		{
			if(objTela.txtA2.getText().isEmpty())
			{
				if(objTela.txtA3.getText().equals(txtArea.getText()))
					retorno = objTela.txtA2;
			}
			if(objTela.txtA3.getText().isEmpty())
			{
				if(objTela.txtA2.getText().equals(txtArea.getText()))
					retorno = objTela.txtA3;
			}
			if(objTela.txtA4.getText().isEmpty())
			{
				if(objTela.txtA7.getText().equals(txtArea.getText()))
					retorno = objTela.txtA4;
			}
			if(objTela.txtA7.getText().isEmpty())
			{
				if(objTela.txtA4.getText().equals(txtArea.getText()))
					retorno = objTela.txtA7;
			}
						
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}
			
			return retorno;
		}
		if(txtArea.equals(objTela.txtA2))
		{
			if(objTela.txtA1.getText().isEmpty())
			{
				if(objTela.txtA3.getText().equals(txtArea.getText()))
					retorno = objTela.txtA1;
			}
			if(objTela.txtA3.getText().isEmpty())
			{
				if(objTela.txtA1.getText().equals(txtArea.getText()))
					retorno = objTela.txtA3;
			}
			if(objTela.txtA5.getText().isEmpty())
			{
				if(objTela.txtA8.getText().equals(txtArea.getText()))
					retorno = objTela.txtA5;
			}
			if(objTela.txtA8.getText().isEmpty())
			{
				if(objTela.txtA5.getText().equals(txtArea.getText()))
					retorno = objTela.txtA8;
			}
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}
			
			return retorno;
			
		}
		if(txtArea.equals(objTela.txtA3))
		{
			if(objTela.txtA2.getText().isEmpty())
			{
				if(objTela.txtA1.getText().equals(txtArea.getText()))
					retorno = objTela.txtA2;
			}
			if(objTela.txtA1.getText().isEmpty())
			{
				if(objTela.txtA2.getText().equals(txtArea.getText()))
					retorno = objTela.txtA1;
			}
			if(objTela.txtA6.getText().isEmpty())
			{
				if(objTela.txtA9.getText().equals(txtArea.getText()))
					retorno = objTela.txtA6;
			}
			if(objTela.txtA9.getText().isEmpty())
			{
				if(objTela.txtA6.getText().equals(txtArea.getText()))
					retorno = objTela.txtA9;
			}
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}		
			return retorno;
		}
		if(txtArea.equals(objTela.txtA4))
		{
			if(objTela.txtA5.getText().isEmpty())
			{
				if(objTela.txtA6.getText().equals(txtArea.getText()))
					retorno = objTela.txtA5;
			}
			if(objTela.txtA6.getText().isEmpty())
			{
				if(objTela.txtA5.getText().equals(txtArea.getText()))
					retorno = objTela.txtA6;
			}
			if(objTela.txtA1.getText().isEmpty())
			{
				if(objTela.txtA7.getText().equals(txtArea.getText()))
					retorno = objTela.txtA1;
			}
			if(objTela.txtA7.getText().isEmpty())
			{
				if(objTela.txtA1.getText().equals(txtArea.getText()))
					retorno = objTela.txtA7;
			}
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}		
			return retorno;
		}
		if(txtArea.equals(objTela.txtA5))
		{
			if(objTela.txtA4.getText().isEmpty())
			{
				if(objTela.txtA6.getText().equals(txtArea.getText()))
					retorno = objTela.txtA4;
			}
			if(objTela.txtA6.getText().isEmpty())
			{
				if(objTela.txtA4.getText().equals(txtArea.getText()))
					retorno = objTela.txtA6;
			}
			if(objTela.txtA2.getText().isEmpty())
			{
				if(objTela.txtA8.getText().equals(txtArea.getText()))
					retorno = objTela.txtA2;
			}
			if(objTela.txtA8.getText().isEmpty())
			{
				if(objTela.txtA2.getText().equals(txtArea.getText()))
					retorno = objTela.txtA8;
			}
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}		
			return retorno;
		}
		if(txtArea.equals(objTela.txtA6))
		{
			if(objTela.txtA4.getText().isEmpty())
			{
				if(objTela.txtA5.getText().equals(txtArea.getText()))
					retorno = objTela.txtA4;
			}
			if(objTela.txtA5.getText().isEmpty())
			{
				if(objTela.txtA4.getText().equals(txtArea.getText()))
					retorno = objTela.txtA5;
			}
			if(objTela.txtA3.getText().isEmpty())
			{
				if(objTela.txtA9.getText().equals(txtArea.getText()))
					retorno = objTela.txtA3;
			}
			if(objTela.txtA9.getText().isEmpty())
			{
				if(objTela.txtA3.getText().equals(txtArea.getText()))
					retorno = objTela.txtA9;
			}
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}		
			return retorno;
		}
		if(txtArea.equals(objTela.txtA7))
		{
			if(objTela.txtA9.getText().isEmpty())
			{
				if(objTela.txtA8.getText().equals(txtArea.getText()))
					retorno = objTela.txtA9;
			}
			if(objTela.txtA1.getText().isEmpty())
			{
				if(objTela.txtA4.getText().equals(txtArea.getText()))
					retorno = objTela.txtA1;
			}
			if(objTela.txtA4.getText().isEmpty())
			{
				if(objTela.txtA1.getText().equals(txtArea.getText()))
					retorno = objTela.txtA4;
			}
			if(objTela.txtA8.getText().isEmpty())
			{
				if(objTela.txtA9.getText().equals(txtArea.getText()))
					retorno = objTela.txtA8;
			}
			if(objTela.txtA3.getText().isEmpty())
			{
				if(objTela.txtA1.getText().equals(txtArea.getText()))
					retorno = objTela.txtA3;
			}
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}		
			return retorno;
		}
		if(txtArea.equals(objTela.txtA8))
		{
			if(objTela.txtA7.getText().isEmpty())
			{
				if(objTela.txtA9.getText().equals(txtArea.getText()))
					retorno = objTela.txtA7;
			}
			if(objTela.txtA9.getText().isEmpty())
			{
				if(objTela.txtA7.getText().equals(txtArea.getText()))
					retorno = objTela.txtA9;
			}
			if(objTela.txtA2.getText().isEmpty())
			{
				if(objTela.txtA5.getText().equals(txtArea.getText()))
					retorno = objTela.txtA2;
			}
			if(objTela.txtA5.getText().isEmpty())
			{
				if(objTela.txtA2.getText().equals(txtArea.getText()))
					retorno = objTela.txtA5;
			}
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}		
			return retorno;
		}
		if(txtArea.equals(objTela.txtA9))
		{
			if(objTela.txtA7.getText().isEmpty())
			{
				if(objTela.txtA8.getText().equals(txtArea.getText()))
					retorno = objTela.txtA7;
			}
			if(objTela.txtA8.getText().isEmpty())
			{
				if(objTela.txtA7.getText().equals(txtArea.getText()))
					retorno = objTela.txtA8;
			}
			if(objTela.txtA3.getText().isEmpty())
			{
				if(objTela.txtA6.getText().equals(txtArea.getText()))
					retorno = objTela.txtA3;
			}
			if(objTela.txtA6.getText().isEmpty())
			{
				if(objTela.txtA3.getText().equals(txtArea.getText()))
					retorno = objTela.txtA6;
			}
			if(retorno == null)
			{
				retorno = JogadaAleatoria(objTela);
			}		
			return retorno;
		}
		return retorno;
	}
	
	//Retorna uma jogada aleatoria
	public static JTextField JogadaAleatoria(Tela objTela)
	{
		JTextField retorno = null;
		
		if(objTela.txtA3.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA1;
		if(objTela.txtA3.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA3;
		if(objTela.txtA5.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA5;
		if(objTela.txtA9.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA9;
		if(objTela.txtA7.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA7;
		if(objTela.txtA6.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA6;
		if(objTela.txtA4.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA4;
		if(objTela.txtA2.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA2;
		if(objTela.txtA8.getText().isEmpty() && retorno == null)
			retorno = objTela.txtA8;
		
		return retorno;
	}
}
