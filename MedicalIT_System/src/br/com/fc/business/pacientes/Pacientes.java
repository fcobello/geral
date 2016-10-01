package br.com.fc.business.pacientes;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import br.com.fc.db.SQLite;

public class Pacientes
{
	private int idPaciente;
	private String nome;
	private String endereco;
	private Date dataNascimento;
	private String sexo;
	private String uf;
	private String bairro;
	private String cep;
	private String cidade;
	private String profissao;
	private String convenio;
	private String telefone;
	private String celular;
	private String estadoCivil;
	private Date dataUltConsulta;
	private boolean edicao;
	private boolean inclusao;
	private SQLite objConexao;

	public Pacientes(SQLite conexao)
	{
		objConexao = conexao;
	}
	
	public int getIdPaciente()
	{
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente)
	{
		this.idPaciente = idPaciente;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public void setSexo(String sexo)
	{
		this.sexo = sexo;
	}

	public String getSexo()
	{
		return sexo;
	}

	public String getEndereco()
	{
		return endereco;
	}

	public void setEndereco(String endereco)
	{
		this.endereco = endereco;
	}

	public Date getDataNascimento()
	{
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento)
	{
		this.dataNascimento = dataNascimento;
	}

	public String getUf()
	{
		return uf;
	}

	public void setUf(String uf)
	{
		this.uf = uf;
	}

	public String getBairro()
	{
		return bairro;
	}

	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}

	public String getCep()
	{
		return cep;
	}

	public void setCep(String cep)
	{
		this.cep = cep;
	}

	public String getCidade()
	{
		return cidade;
	}

	public void setCidade(String cidade)
	{
		this.cidade = cidade;
	}

	public String getProfissao()
	{
		return profissao;
	}

	public void setProfissao(String profissao)
	{
		this.profissao = profissao;
	}

	public String getConvenio()
	{
		return convenio;
	}

	public void setConvenio(String convenio)
	{
		this.convenio = convenio;
	}

	public String getTelefone()
	{
		return telefone;
	}

	public void setTelefone(String telefone)
	{
		this.telefone = telefone;
	}

	public String getCelular()
	{
		return celular;
	}

	public void setCelular(String celular)
	{
		this.celular = celular;
	}

	public String getEstadoCivil()
	{
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil)
	{
		this.estadoCivil = estadoCivil;
	}

	public void setDataUltConsulta(Date dataUltConsulta)
	{
		this.dataUltConsulta = dataUltConsulta;
	}

	public Date getDataUltConsulta()
	{
		return dataUltConsulta;
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
				idPaciente = idPaciente == 0 ? getNextId():idPaciente;
				sql.append("INSERT INTO PACIENTES VALUES(");
				sql.append(idPaciente).append(",");
				sql.append("'").append(nome).append("',");
				sql.append("'").append(endereco).append("',");
				sql.append("'").append(bairro).append("',");
				sql.append("'").append(cidade).append("',");
				sql.append("'").append(uf).append("',");
				sql.append("'").append(cep).append("',");
				sql.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format(dataNascimento)).append("',");
				sql.append("'").append(telefone).append("',");
				sql.append("'").append(celular).append("',");
				sql.append("'").append(estadoCivil).append("',");
				sql.append("'").append(profissao).append("',");
				sql.append("'").append(convenio).append("',");
				sql.append("'").append(new SimpleDateFormat("yyyy-MM-dd").format(dataUltConsulta)).append("',");
				sql.append("'").append(sexo).append("'");
				sql.append(")");
				objConexao.execute(sql.toString());
				break;
			case SQLite.UPDATE:
				sql.append("UPDATE PACIENTES SET ");
				sql.append("ID_PACIENTE = " + idPaciente);
				sql.append(", NOME = '" + nome).append("'");
				sql.append(", SEXO = '" + sexo).append("'");
				sql.append(", ENDERECO = '" + endereco).append("'");
				sql.append(", BAIRRO = '" + bairro).append("'");
				sql.append(", CIDADE = '" + cidade).append("'");
				sql.append(", UF = '" + uf).append("'");
				sql.append(", CEP = '" + cep).append("'");
				sql.append(", DATANASCIMENTO = '" + new SimpleDateFormat("yyyy-MM-dd").format(dataNascimento)).append("'");
				sql.append(", TELEFONE = '" + telefone).append("'");
				sql.append(", CELULAR = '" + celular).append("'");
				sql.append(", ESTADOCIVIL = '" + estadoCivil).append("'");
				sql.append(", PROFISSAO = '" + profissao).append("'");
				sql.append(", CONVENIO = '" + convenio).append("'");
				sql.append(", DATAULTCONSULTA = '" + new SimpleDateFormat("yyyy-MM-dd").format(dataUltConsulta)).append("'");
				sql.append(" WHERE ID_PACIENTE = " + idPaciente);
				objConexao.execute(sql.toString());
				break;
			default:
				break;
		}
	}
	
	public List<Vector<String>> pesquisa(String nome) throws SQLException, ClassNotFoundException, ParseException
	{
		List<Vector<String>> retorno = new ArrayList<Vector<String>>();
		ResultSet rst;
		
		rst = objConexao.executeQuery("SELECT * FROM PACIENTES WHERE NOME LIKE '" + nome + "%'");
		while(rst.next())
		{
			Vector<String> registro = new Vector<String>();
			
			registro.add(rst.getString("ID_PACIENTE"));
			registro.add(rst.getString("NOME"));
			registro.add(new SimpleDateFormat("dd/MM/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(rst.getString("DATANASCIMENTO"))));
			retorno.add(registro);
		}
		rst.close();
		rst = null;
		return retorno;
	}
	
	public void editar(int idPaciente) throws Exception
	{
		ResultSet rst = null;
		
		try
		{
			rst = objConexao.executeQuery("SELECT * FROM PACIENTES WHERE ID_PACIENTE = " + idPaciente);
			while(rst.next())
			{
				setIdPaciente(rst.getInt("ID_PACIENTE"));
				setNome(rst.getString("NOME"));
				setDataNascimento(new SimpleDateFormat("yyyy-MM-dd").parse(rst.getString("DATANASCIMENTO")));
				setSexo(rst.getString("SEXO"));
				setEstadoCivil(rst.getString("ESTADOCIVIL"));
				setProfissao(rst.getString("PROFISSAO"));
				setEndereco(rst.getString("ENDERECO"));
				setBairro(rst.getString("BAIRRO"));
				setCidade(rst.getString("CIDADE"));
				setCep(rst.getString("CEP"));
				setUf(rst.getString("UF"));
				setTelefone(rst.getString("TELEFONE"));
				setCelular(rst.getString("CELULAR"));
				setConvenio(rst.getString("CONVENIO"));
				setDataUltConsulta(new SimpleDateFormat("yyyy-MM-dd").parse(rst.getString("DATAULTCONSULTA")));
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
	
	public void excluir(int idPaciente) throws SQLException, ClassNotFoundException
	{
		objConexao.execute("DELETE FROM PACIENTES WHERE ID_PACIENTE = " + idPaciente);
	}

	private int getNextId() throws SQLException, ClassNotFoundException
	{
		ResultSet rst;
		int retorno = 1;
		
		rst = objConexao.executeQuery("SELECT ID_PACIENTE FROM PACIENTES ORDER BY ID_PACIENTE DESC LIMIT 1");
		if(rst.next())
			retorno = rst.getInt("ID_PACIENTE") + 1;
		rst.close();
		rst = null;
		return retorno;
	}
}
