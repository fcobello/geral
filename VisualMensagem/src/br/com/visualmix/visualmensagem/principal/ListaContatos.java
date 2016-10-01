package br.com.visualmix.visualmensagem.principal;

import java.util.ArrayList;
import java.util.prefs.Preferences;

public class ListaContatos {
	
	private Preferences pref = Preferences.systemNodeForPackage(getClass());
	private ArrayList<Contato> contatos = new ArrayList<Contato>();
	
	public ListaContatos() {
		carregarContatos();
	}
	
	public void addContato(Contato contato){
		contatos.add(contato);
	}
	
	public void removeContato(Contato contato){
		contatos.remove(contato);
	}
	
	public void carregarContatos(){
		String lista = pref.get("contatos", "");
		String[] listaContatos = lista.split("[|]");
		for (int i = 0; i < listaContatos.length; i++) {
			if(!pref.get("contatos", "").trim().equals(""))
				contatos.add(new Contato(listaContatos[i].split("[-]")[0], listaContatos[i].split("[-]")[1]));
		}		
	}
	
	public ArrayList<Contato> getContatos(){
		return contatos;
	}
	
	public void gravarContatos(){
		pref.put("contatos", "");
		for (int i = 0; i < contatos.size(); i++) {
			pref.put("contatos", pref.get("contatos", "") + contatos.get(i).getNome() + "-" + contatos.get(i).getIp() + "|");
		}
	}
	
}
