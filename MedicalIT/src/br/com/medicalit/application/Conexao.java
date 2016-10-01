package br.com.medicalit.application;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Conexao
{
	private Application application = new Application();
	private Connection con;
	private String conexao;

	public Connection getConexao()
	{
		try
		{
			conexao = "jdbc:sqlserver://" + application.servidor + ":" + application.porta + ";databaseName=" + application.banco + ";user=" + application.user + ";password=" + application.senha;
			if (con != null)
			{
				if (!con.isClosed())
					return con;
			}
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(conexao);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return con;
	}

	public void execute(String sql) throws SQLException
	{
		Statement smt;

		if (con == null)
			getConexao();
		smt = con.createStatement();
		smt.execute(sql);
	}

	public void closeConexao()
	{
		try
		{
			if (con != null)
			{
				if (!con.isClosed())
				{
					con.close();
				}
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Fechar Conexão", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void closeConexao(Statement smtm)
	{
		try
		{
			if (smtm != null)
			{
				smtm.close();
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Fechar Conexão", JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			closeConexao();
		}
	}

	public void closeConexao(Statement smtm, ResultSet rs)
	{
		try
		{
			if (!rs.isClosed())
			{
				rs.close();
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao Fechar Conexão", JOptionPane.ERROR_MESSAGE);
		}
		finally
		{
			closeConexao(smtm);
		}
	}

	public void executeScript(File arquivo) throws SQLException, FileNotFoundException
	{
		Scanner s = new Scanner(arquivo, "UTF-16");
		Statement st = null;
		
		if (con == null)
			getConexao();
		st = con.createStatement();
		s.useDelimiter("(;(\r)?\n)|(--\n)");
		while (s.hasNext())
		{
			String line = s.next();
			if (line.startsWith("/*!") && line.endsWith("*/"))
			{
				int i = line.indexOf(' ');
				line = line.substring(i + 1, line.length() - " */".length());
			}
			if (line.trim().toUpperCase().length() > 0)
			{
				for (int i = 0; i < line.split("GO").length; i++)
				{
					System.out.println(line.split("GO")[i]);
					st.execute(line.split("GO")[i]);
				}
			}
		}
		if (st != null)
			st.close();
	}
}
