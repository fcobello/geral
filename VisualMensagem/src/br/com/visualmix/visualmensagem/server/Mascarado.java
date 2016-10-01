package br.com.visualmix.visualmensagem.server;

/**
 * Classe que serve para mascarar um texto.<br><br>
 * @author J.Carlos
 * <b>Author:</b> Arthur Buzzo Fernandes<br>
 * <b>Special Participation:</b> J.Carlos
 */
public class Mascarado {
	private static String separador = String.valueOf((char) 245); 
	private static String byteSeparador = "@";
	private final static int chave = 42;
	private final static int crescimento = 7;
		
	public static String getCripitografia(String valor){
		String retorno = "";
		int pChave = chave;
		for (char val : valor.toCharArray()) {
			retorno += getFinalChar(val, pChave) + separador;
			pChave = getNextChave();
		}
		String bytes = "";
		for (char b : retorno.toCharArray()) {
			bytes += (int)b + byteSeparador;
		}
		return bytes;
	}
	
	public static String getDescriptografia(String valor){
		String[] bytes = valor.split("[" + byteSeparador + "]");
		
		if(!valor.contains(byteSeparador)){
			return "";
		}
		
		valor = "";
		
		for (String b : bytes) {
			int i = Integer.valueOf(b);
			valor += (char)i;
		}
		String[] valores = valor.split("[" + separador + "]");
		String pValores = "";
		for (String v : valores) {
			pValores += v;
		}
		int pChave = chave;
		String retorno = "";
		for (char vlr : pValores.toCharArray()) {
			retorno += getIniChar(vlr, pChave);
			pChave = getNextChave();
		}
		return retorno;
	}
	
	private static char getIniChar(char valor, int chave){
		byte pChave = (byte) chave;
		return (char) ((int)valor - pChave);
	}
	
	private static char getFinalChar(char valor, int chave){
		byte pChave = (byte) chave;
		return (char) ((int)valor + pChave);
	}
	
	private static int getNextChave(){
		int retorno = 0;

		if (chave + crescimento < 255)
			retorno = chave + crescimento;
		return retorno;
	}
}