package br.com.comunicator.contatos;

import java.util.ArrayList;
import java.util.prefs.Preferences;

/**
 * Classe p/ Gerencimento de Contatos
 * @author Felipe Cobello
 *
 */
public class Contatos 
{
	/** Lista de Contatos */
	public ArrayList<String> listaContatos = new ArrayList<String>();;
	/** Preferences - Registro */
	private Preferences preferences;

	/**
	 * Construtor da Classe
	 * @param preferences Preferences utilizada pelo Aplicativo
	 */
	public Contatos(Preferences preferences)
	{
		this.preferences = preferences;
	}
	
	/**
	 * Adiciona um Contato na Lista
	 * @param Nome Nome
	 * @param Ip IP
	 */
	public void addContato(String Nome, String Ip)
	{
		String Contato = Nome + "|" + Ip;

		listaContatos.add(Contato);
		gravarContatos();
	}

	/**
	 * Remove um Contato da Lista
	 * @param contato Contato
	 */
	public void removerContato(String contato)
	{
		listaContatos.remove(contato);
		gravarContatos();
	}

	/**
	 * Carrega os Contatos
	 */
	public void loadContatos()
	{
		String contato[] = preferences.get("contatos", "").split("[#]");
		
		listaContatos.clear();
		for(int i = 0; i<contato.length; i++)
		{
			listaContatos.add(contato[i].trim());
		}
	}
	
	/**
	 * Grava os Contato no Preferences
	 */
	public void gravarContatos()
	{
		StringBuilder contatos = new StringBuilder();
		
		for (int i = 0; i < listaContatos.size(); i++)
		{
			contatos.append(listaContatos.get(i)).append("#");
		}
		preferences.put("contatos", contatos.toString());
		loadContatos();
	}
}
