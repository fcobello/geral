package br.com.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Web
{
	public static String read(String url) throws IOException
	{
		String line;
		StringBuilder builder = new StringBuilder();
		URLConnection connection = new URL(url.replace(" ", "%20")).openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		while((line = reader.readLine()) != null) 
		{
			 builder.append(line);
		}
		return builder.toString();
	}
	
	public static void main(String[] args){ 
		try {
			System.out.println(Web.read("http://www.uol.com.br"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
