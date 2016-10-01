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
		texto = texto.replace("�", "&aacute;");
		texto = texto.replace("�", "&eacute;");
		texto = texto.replace("�", "&iacute;");
		texto = texto.replace("�", "&oacute;");
		texto = texto.replace("�", "&uacute;");
		texto = texto.replace("�", "&Aacute;");
		texto = texto.replace("�", "&Eacute;");
		texto = texto.replace("�", "&Iacute;");
		texto = texto.replace("�", "&Oacute;");
		texto = texto.replace("�", "&Uacute;");
		//Acento Circunflexo
		texto = texto.replace("�", "&acirc;");
		texto = texto.replace("�", "&ecirc;");
		texto = texto.replace("�", "&icirc;");
		texto = texto.replace("�", "&ocirc;");
		texto = texto.replace("�", "&ucirc;");
		texto = texto.replace("�", "&Acirc;");
		texto = texto.replace("�", "&Ecirc;");
		texto = texto.replace("�", "&Icirc;");
		texto = texto.replace("�", "&Ocirc;");
		texto = texto.replace("�", "&Ucirc;");
		//Acento Til
		texto = texto.replace("�", "&atilde;");
		texto = texto.replace("�", "&otilde;");
		texto = texto.replace("�", "&Atilde;");
		texto = texto.replace("�", "&Otilde;");
		return texto;
	}
    
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		System.out.println(URLEncoder.encode("S�o Roque", "UTF-8"));
	}
}
