package br.com.medicalit.application;

import java.io.File;
import java.nio.charset.Charset;
import br.com.medicalit.util.Util;

public class Application
{	
	public static final File conf = new File("c:/conf.con");
	public String servidor = "";
	public String porta = "";
	public String banco = "";
	public String user = "";
	public String senha = "";
	public Charset charset;
	protected String erros;
	
	public Application()
	{
		String[] param = Util.readFile(conf).split("[#]");
		
		for (int i = 0; i < param.length; i++) 
		{
			if(param[i].split("[=]")[0].equalsIgnoreCase("SERVIDOR"))
				servidor = param[i].split("[=]")[1].trim();
			if(param[i].split("[=]")[0].equalsIgnoreCase("PORTA"))
				porta = param[i].split("[=]")[1].trim();
			if(param[i].split("[=]")[0].equalsIgnoreCase("BANCO"))
				banco = param[i].split("[=]")[1].trim();
			if(param[i].split("[=]")[0].equalsIgnoreCase("USER"))
				user = param[i].split("[=]")[1].trim();
			if(param[i].split("[=]")[0].equalsIgnoreCase("SENHA"))
				senha = param[i].split("[=]")[1].trim();
			if(param[i].split("[=]")[0].equalsIgnoreCase("CHARSET"))
				charset = Charset.forName(param[i].split("[=]")[1].trim());
		}
	}
}				

