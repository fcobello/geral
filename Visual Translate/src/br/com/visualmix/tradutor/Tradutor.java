package br.com.visualmix.tradutor;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class Tradutor 
{	
	public static String translate(String texto, Language origem, Language destino)
	{
		String traducao = "";
		
		try
		{
			if(!texto.trim().isEmpty())
			{
				Translate.setHttpReferrer("_");
				traducao = Translate.execute(texto, origem, destino);
				Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(traducao), null);
			}
		}
		catch (Exception e)
		{
			traducao = "Serviço Indisponível...";
		}
		return traducao;
	}
}
