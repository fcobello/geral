package br.com.android.rastreador.registros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import android.os.Environment;

/**
 * Gravar e Ler Arquivo Texto no SD do SmartPhone<br>
 * Os Arquivos serão gravados no cartão na Pasta "Android/data/seuPacote/cache"
 * assim quando desinstalar a Aplicação o conteudo será removido
 * 
 * @author Felipe Cobello
 *
 */
public class WriteAndRead
{
	private File regFile;
	
	/**
	 * Cria uma Nova Instancia da Classe
	 * @param packageName Nome do Pacote Principal
	 * @param fileName Nome do arquivo que sera criado
	 * @throws IOException
	 */
	public WriteAndRead(String packageName, String fileName) throws IOException
	{
		regFile = new File(Environment.getExternalStorageDirectory().getPath() +  "/Android/data/" + packageName + "/cache/" + fileName);
		if(!regFile.getParentFile().exists())
			regFile.mkdirs();
		if(!regFile.exists())
			regFile.createNewFile();
	}
	
	/**
	 * Grava o Arquivo
	 * @param texto Texto a ser gravado no Arquivo
	 * @throws IOException
	 */
	public void writeFile(String texto) throws IOException
	{
		FileOutputStream file;
		
		if(regFile.canWrite())
		{
			file = new FileOutputStream(regFile);
			file.write(texto.getBytes());
			file.close();
		}
	}
	
	/**
	 * Lê o arquivo
	 * @return Conteudo do Arquivo
	 * @throws IOException
	 */
	public String readFile() throws IOException 
	{
		StringBuilder retorno = new StringBuilder();
		FileReader reader;
		BufferedReader br;
		String line;

		reader = new FileReader(regFile);
		br = new BufferedReader(reader);
		while((line = br.readLine()) != null)
			retorno.append(line);
		br.close();
		reader.close();
		return retorno.toString();
	}

}
