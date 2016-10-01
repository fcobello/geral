package br.com.fc.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLite
{
	public static final byte INSERT = 1;
	public static final byte UPDATE = 2;
	private String file;
	private Connection objConexao = null;
	
	public SQLite(String file)
	{
		this.file = file;
	}
	public Connection getConexao() throws ClassNotFoundException, SQLException
	{
		if(objConexao == null)
		{
			Class.forName("org.sqlite.JDBC");
			objConexao = DriverManager.getConnection("jdbc:sqlite:" + file);
		}
		return objConexao;
	}
	
	public boolean execute(String sql) throws SQLException, ClassNotFoundException
	{
		return getConexao().createStatement().execute(sql);
	}
	
	public ResultSet executeQuery(String sql) throws SQLException, ClassNotFoundException
	{
		return getConexao().createStatement().executeQuery(sql);
	}
	
	public static SQLite getConexaoClinica()
	{
		return new SQLite("Clinica.s3db");
	}
}
