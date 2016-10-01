package br.com.java;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class Xml extends DefaultHandler
{
	private String tag;
	private XMLReader reader;
	private ArrayList<String> valores = new ArrayList<String>();
	
	public Xml() throws SAXException
	{
		reader = XMLReaderFactory.createXMLReader();
		reader.setContentHandler(this);
	}
			
	public void startElement(String uri, String localName, String qName, Attributes attributes)
	        throws SAXException {
		if(qName.equals(tag))
			valores.add(attributes.getValue("respondeReturn"));
	}
	
    public void characters (char ch[], int start, int length) throws SAXException
    {

    }
    
    public String[] read(String tag, File file) throws FileNotFoundException, IOException, SAXException
    {
    	String[] retorno;
    	
    	this.tag = tag;
    	reader.parse(new InputSource(new FileReader(file)));
    	retorno = new String[valores.size()];
    	for (int i = 0; i < valores.size(); i++)
		{
			retorno[i] = valores.get(i);
		}
    	return retorno;
    }
    
    public String[] read(String tag, URL url) throws FileNotFoundException, IOException, SAXException
    {
    	String[] retorno;
    	ByteArrayInputStream in = new ByteArrayInputStream(Web.read(url.toString()).getBytes("UTF-8"));
    	this.tag = tag;
    	reader.parse(new InputSource(in));
    	retorno = new String[valores.size()];
    	for (int i = 0; i < valores.size(); i++)
		{
			retorno[i] = valores.get(i);
		}
    	return retorno;
    }
    
	public static String captalizeWords(String words)
	{
		char[] caracteres = words.toLowerCase().toCharArray();
		
		for (int i = 0; i < caracteres.length; i++)
		{
			if(i == 0 || caracteres[i-1] == ' ')
			{
				caracteres[i] = Character.toUpperCase(caracteres[i]);
			}
		}
		return new String(caracteres);
	}
	
	public static String trataAcento(String texto)
	{
		//Acento Agudo
		texto = texto.replace("á", "&aacute;");
		texto = texto.replace("é", "&eacute;");
		texto = texto.replace("í", "&iacute;");
		texto = texto.replace("ó", "&oacute;");
		texto = texto.replace("ú", "&uacute;");
		texto = texto.replace("Á", "&Aacute;");
		texto = texto.replace("É", "&Eacute;");
		texto = texto.replace("Í", "&Iacute;");
		texto = texto.replace("Ó", "&Oacute;");
		texto = texto.replace("Ú", "&Uacute;");
		//Acento Circunflexo
		texto = texto.replace("â", "&acirc;");
		texto = texto.replace("ê", "&ecirc;");
		texto = texto.replace("î", "&icirc;");
		texto = texto.replace("ô", "&ocirc;");
		texto = texto.replace("û", "&ucirc;");
		texto = texto.replace("Â", "&Acirc;");
		texto = texto.replace("Ê", "&Ecirc;");
		texto = texto.replace("Î", "&Icirc;");
		texto = texto.replace("Ô", "&Ocirc;");
		texto = texto.replace("Û", "&Ucirc;");
		//Acento Til
		texto = texto.replace("ã", "&atilde;");
		texto = texto.replace("õ", "&otilde;");
		texto = texto.replace("Ã", "&Atilde;");
		texto = texto.replace("Õ", "&Otilde;");
		return texto;
	}
    
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		System.out.println(URLEncoder.encode("São Roque", "UTF-8"));
	}
}
