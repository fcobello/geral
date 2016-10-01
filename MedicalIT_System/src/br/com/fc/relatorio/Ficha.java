package br.com.fc.relatorio;

import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import br.com.fc.db.SQLite;

public class Ficha
{
	public JasperPrint gerar(int idPaciente, String idade)
	{
		JasperPrint rel = null;
		HashMap<String, String> parametros = new HashMap<String, String>();
		
		try
		{
			parametros.put("ID_PACIENTE", String.valueOf(idPaciente));
			parametros.put("IDADE", idade);
			rel = JasperFillManager.fillReport("FichaPaciente.jasper", parametros, SQLite.getConexaoClinica().getConexao());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return rel;
	}
}
