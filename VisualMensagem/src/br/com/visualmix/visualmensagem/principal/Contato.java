package br.com.visualmix.visualmensagem.principal;

public class Contato {

	private String nome = "";
	private String ip = "";
	private String status = "";
	
	public Contato(String nome, String ip) {
		setNome(nome);
		setIp(ip);
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public String toString(){
		return (getNome() + " " + getStatus()).trim();
	}
}
