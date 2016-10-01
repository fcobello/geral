package br.com.fc.importer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import br.com.fc.business.pacientes.Pacientes;
import br.com.fc.business.prontuario.Prontuario;
import br.com.fc.db.SQLite;
import br.com.fc.utils.Utils;

public class Importacao
{
	public String pathParadox = "C:\\Tabelas\\";
	private SQLite objConexao;
	public int count = 0;
	
	public Importacao(SQLite conexao)
	{
		objConexao = conexao;
	}
	
	private Connection getConexaoParadox()
	{
		Connection con = null;
		
		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Paradox Driver (*.db )};DriverID=538;Fil=Paradox 5.X;DefaultDir=" + pathParadox + ";Dbq=" + pathParadox + ";CollatingSequence=ASCII;", "", "");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			Utils.writeFile("Erro Conexão Paradox - " + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	public void importarPacientes() throws SQLException, ClassNotFoundException
	{
		Pacientes objPacientes = new Pacientes(objConexao);
		ResultSet rst;
		
		objConexao.execute("DELETE FROM PACIENTES");
		rst = getConexaoParadox().createStatement().executeQuery("SELECT * FROM PACIENTES");
		while(rst.next())
		{
			try
			{
				objPacientes.setInclusao();
				objPacientes.setIdPaciente(rst.getInt("PAC_CODPACIENTE"));
				objPacientes.setNome(trataString(rst.getString("PAC_NOME")));
				objPacientes.setEndereco(trataString(rst.getString("PAC_ENDERECO")));
				objPacientes.setBairro(trataString(rst.getString("PAC_BAIRRO")));
				objPacientes.setCidade(trataString(rst.getString("PAC_CIDADE")));
				objPacientes.setUf(trataString(rst.getString("PAC_UF")));
				objPacientes.setCep(trataString(rst.getString("PAC_CEP")));
				objPacientes.setTelefone(trataString(rst.getString("PAC_TELEFONE")));
				objPacientes.setCelular(trataString(rst.getString("PAC_CELULAR")));
				objPacientes.setDataNascimento(trataDate(rst.getDate("PAC_DATANASC")));
				objPacientes.setConvenio(trataString(rst.getString("PAC_CONVENIO")));
				objPacientes.setProfissao(trataString(rst.getString("PAC_PROFISSAO")));
				objPacientes.setEstadoCivil(trataString(rst.getString("PAC_ESTCIVIL")));
				objPacientes.setDataUltConsulta(trataDate(rst.getDate("PAC_DATCONSULTA")));
				objPacientes.salvar();
				count++;
			}
			catch (SQLException e) {
				Utils.writeFile("Erro Importacao - " + e.getMessage());
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				Utils.writeFile("Erro Importacao - " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void importarProntuarios() throws SQLException, ClassNotFoundException
	{
		Prontuario objProntuario = new Prontuario(objConexao);
		ResultSet rst;
		
		objConexao.execute("DELETE FROM PRONTUARIO");
		rst = getConexaoParadox().createStatement().executeQuery("SELECT * FROM PRONTUARIO");
		while(rst.next())
		{
			try
			{
				objProntuario.setInclusao();
				objProntuario.setIdPaciente(rst.getInt("PRO_CODPACIENTE"));
				objProntuario.setData(new Date());
				objProntuario.setTexto(trataString(rst.getString("PRO_DESCRICAO")));
				objProntuario.salvar();
				count++;
			}
			catch (SQLException e) {
				Utils.writeFile("Erro Importacao - " + e.getMessage());
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				Utils.writeFile("Erro Importacao - " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	private String trataString(String texto)
	{
		return texto == null ? "":texto.replace("'", "");
	}
	
	private Date trataDate(Date data)
	{
		return data == null ? new Date():data;
	}
	
	public int getTotalRegistros() throws SQLException
	{
		ResultSet rst;
		int qtdPac = 0, qtdProt = 0;
		
		rst = getConexaoParadox().createStatement().executeQuery("SELECT COUNT(*) AS TOTAL FROM PACIENTES");
		rst.next();
		qtdPac = rst.getInt("TOTAL");
		rst.close();
		rst = getConexaoParadox().createStatement().executeQuery("SELECT COUNT(*) AS TOTAL FROM PRONTUARIO");
		rst.next();
		qtdPac = rst.getInt("TOTAL");
		rst.close();
		return qtdPac + qtdProt;
	}
}
