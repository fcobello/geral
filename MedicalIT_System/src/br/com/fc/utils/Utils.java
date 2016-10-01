package br.com.fc.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Utils
{
	public static final File FILE_LOG = new File("Log.txt");
	/**
	 * Grava o Arquivo
	 * @param texto Texto a ser gravado no Arquivo
	 * @throws IOException
	 */
	public static void writeFile(String texto)
	{
		FileOutputStream file;
		
		try
		{
			if(FILE_LOG.canWrite())
			{
				file = new FileOutputStream(FILE_LOG);
				file.write(texto.concat("\n").getBytes());
				file.close();
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void copyFile(File fileIn, File fileOut) throws IOException
	{
		FileReader in = new FileReader(fileIn);
		FileWriter out = new FileWriter(fileOut);
		int c;
		
		if(fileOut.exists())
			fileOut.delete();
		while((c = in.read()) != -1)
			out.write(c);
		in.close();
		out.close();
	}
}
