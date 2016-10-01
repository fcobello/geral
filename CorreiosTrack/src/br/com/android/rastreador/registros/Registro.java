package br.com.android.rastreador.registros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.alfredlibrary.AlfredException;
import org.alfredlibrary.formatadores.Data;
import org.alfredlibrary.utilitarios.correios.Rastreamento;
import org.alfredlibrary.utilitarios.correios.RegistroRastreamento;
import android.os.Environment;

public class Registro
{
	private File regFile = new File(Environment.getExternalStorageDirectory().getPath() +  "/Android/data/br.com.android.rastreamento/cache/registros.txt");

	
	private List<String> list = new ArrayList<String>();
	
	public Registro() throws IOException
	{
		if(!regFile.getParentFile().exists())
			regFile.getParentFile().mkdirs();
		if(!regFile.exists())
			regFile.createNewFile();
	}

	public void addRegistro(String codigo, String tag) throws AlfredException
	{
		String[] data = readFile(regFile).split("[|]");
		String conteudo = readFile(regFile);
		
		Rastreamento.rastrear(codigo);
		for (String s : data)
		{
			if(codigo.equalsIgnoreCase(s))
				throw new AlfredException("Código já Cadastrado");
		}
		writeFile(conteudo + "|" + codigo + "@" + tag, regFile);
	}
	
	public void removeRegistro(String codigo)
	{
		String[] data = readFile(regFile).split("[|]");
		StringBuilder conteudo = new StringBuilder();
		
		for (String s : data)
		{
			if(!codigo.equalsIgnoreCase(s.split("@")[0]))
				conteudo.append(s).append("|");
		}
		writeFile(conteudo.toString(), regFile);
	}
	
	public List<String> loadRegistros() throws AlfredException
	{
		String[] registros = readFile(regFile).split("[|]");
		
		list.clear();
		for (int i = 0; i < registros.length; i++)
		{
			String s = registros[i];
			if(!s.equals(""))
			{
				List<RegistroRastreamento> dados = Rastreamento.rastrear(s.split("@")[0]);
				
				list.add((s.split("@").length > 1 ? s.split("@")[0].toUpperCase() + " - " + s.split("@")[1]:s.toUpperCase()) + "\nData: " + Data.formatar(dados.get(0).getDataHora(), "dd/MM/yyyy") + "\nLocal: " + dados.get(0).getLocal() + "\nAção: " + dados.get(0).getAcao() + "\n"
						+ (dados.get(0).getDetalhe() != null ? dados.get(0).getDetalhe() : ""));
			}
		}
		return list;
	}
	
	public void writeFile(String texto, File arquivo)
	{
		FileOutputStream file;
		
		try
		{
			if(regFile.canWrite())
			{
				file = new FileOutputStream(arquivo);
				file.write(texto.getBytes());
				file.close();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public String readFile(File arquivo) 
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
			br.close();
			reader.close();
		}
		catch (IOException e) 
		{
			e.printStackTrace();				
		}
		return retorno.toString();
	}
}
