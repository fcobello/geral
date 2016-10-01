package br.com.fc.business.prontuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import br.com.fc.db.SQLite;

public class Prontuario
{
	private int idPaciente;
	private Date data;
	private String texto = "";
	private boolean edicao;
	private boolean inclusao;
	private SQLite objConexao;

	public Prontuario(SQLite Conexao)
	{
		objConexao = Conexao;
	}
	
	public void setTexto(String texto)
	{
		this.texto = texto;
	}

	public String getTexto()
	{
		return texto;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public Date getData()
	{
		return data;
	}

	public void setIdPaciente(int idPaciente)
	{
		this.idPaciente = idPaciente;
	}

	public int getIdPaciente()
	{
		return idPaciente;
	}
	
	public void setEdicao()
	{
		this.edicao = true;
		this.inclusao = false;
	}

	public boolean isEdicao()
	{
		return edicao;
	}
	
	public void setInclusao()
	{
		this.inclusao = true;
		this.edicao = false;
	}

	public boolean isInclusao()
	{
		return inclusao;
	}
	
	private byte getOperacao()
	{
		byte retorno = 0;
		if(inclusao)
			retorno = 1;
		else if(edicao)
			retorno = 2;
		return retorno;
	}
	
	public void salvar() throws SQLException, ClassNotFoundException
	{
		StringBuilder sql = new StringBuilder();
		
		switch (getOperacao())
		{
			case SQLite.INSERT:
				sql.append("INSERT INTO PRONTUARIO VALUES(");
				sql.append(idPaciente).append(",");
				sql.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format(data)).append("',");
				sql.append("'").append(texto).append("'");
				sql.append(")");
				objConexao.execute(sql.toString());
				break;
			case SQLite.UPDATE:
				sql.append("UPDATE PRONTUARIO SET ");
				sql.append("ID_PACIENTE = " + idPaciente);
				sql.append(", DATA = '" + new SimpleDateFormat("yyyy-MM-dd").format(data)).append("'");
				sql.append(", TEXTO = '" + texto).append("'");
				sql.append(" WHERE ID_PACIENTE = " + idPaciente);
				objConexao.execute(sql.toString());
				break;
			default:
				break;
		}
	}
	
	public void editar(int idPaciente) throws Exception
	{
		ResultSet rst = null;
		
		try
		{
			rst = objConexao.executeQuery("SELECT * FROM PRONTUARIO WHERE ID_PACIENTE = " + idPaciente);
			while(rst.next())
			{
				setIdPaciente(rst.getInt("ID_PACIENTE"));
				setTexto(getTexto() + "\n[" + rst.getString("DATA") + "]\n");
				setTexto(getTexto() + rst.getString("TEXTO") + "");
				setEdicao();
			}
		}
		catch (Exception e) {
			throw e;
		}
		finally {
			rst.close();
			rst = null;
		}
	}
}
