package br.com.medicalit.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import javax.swing.JOptionPane;

public class Util
{
	public static String readFile(File arquivo) 
	{
		StringBuilder retorno = new StringBuilder();
		FileReader reader;
		BufferedReader br;
		String line;

		try 
		{
			reader = new FileReader(arquivo);
			br = new BufferedReader(reader);
			while((line = br.readLine()) != null)
				retorno.append(line);
		}
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Ler ao Arquivo " + arquivo, JOptionPane.ERROR_MESSAGE);						
		}
		return retorno.toString();
	}
	
	public static String readEncodeFile(File arquivo)
	{
		StringBuilder retorno = new StringBuilder();
		FileInputStream in;
		InputStreamReader reader;
		BufferedReader br;
		String line;

		try 
		{
			in = new FileInputStream(arquivo);
			reader = new InputStreamReader(in, Charset.forName("UTF-16"));
			br = new BufferedReader(reader);
			while((line = br.readLine()) != null)
			{
				retorno.append(line + "\n");
			}
		}
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Ler ao Arquivo " + arquivo, JOptionPane.ERROR_MESSAGE);						
		}
		return retorno.toString();
	}
}
