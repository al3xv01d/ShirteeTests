package Util;

import Util.ReadDataFromFile;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseHelper {
	
	private String url = "http://db.shirtee.de:43006";
	private String login;
	private String password;
	
	public DatabaseHelper()
	{
		
	}
	
	public void getConnection(String filepath)
	{
		ReadDataFromFile data = new ReadDataFromFile(filepath);
		 
		login = data.getPropertie("databaseLogin");
		password = data.getPropertie("databasePassword");
	}
	
	
	
}
