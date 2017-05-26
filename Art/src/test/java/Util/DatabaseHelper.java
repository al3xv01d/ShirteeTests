package Util;

import Util.ReadDataFromFile;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DatabaseHelper {
	
	private String login;
	private String password;
	private Connection conn;
	
	public enum AffiliateType { SharerFirstLvl, SharerSecondLvl, JoinerFirstLvl, JoinerSecondLvl, JB };

	
	public DatabaseHelper(String credentialsFilepath) throws SQLException
	{
		ReadDataFromFile data = new ReadDataFromFile(credentialsFilepath);
		 
		login = data.getPropertie("databaseLogin");
		password = data.getPropertie("databasePassword");
		
		conn = DriverManager.getConnection(System.getProperty("DBURL"),login,password);
	}

	
	public String getAffiliateByType(AffiliateType affType) throws SQLException
	{
		String sqlStr = null;
		String resultStr = null;
		
		try {
			switch (affType) {
			case SharerFirstLvl:
				 sqlStr = "SELECT * FROM `core_config_data` WHERE `path` = "
						+ "'gomage_affiliate/sharer/first_level'; ";
				break;

			case SharerSecondLvl:
				 sqlStr = "SELECT * FROM `core_config_data` WHERE `path`"
						+ " = 'gomage_affiliate/sharer/second_level'";
				break;
				
			case JoinerFirstLvl:
			 sqlStr = "SELECT * FROM `core_config_data` WHERE `path` = "
					+ "'gomage_affiliate/joiner/first_level'";
			 	break;
			
			case JoinerSecondLvl:
			 sqlStr = "SELECT * FROM `core_config_data` WHERE `path` = "
			 		+ "'gomage_affiliate/joiner/second_level'";

			default: {throw new IllegalArgumentException("Can't yet handle " + affType);}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sqlStr);
		
		if (rs.next()) {
			 resultStr = rs.getString("value");
		}
		
		return resultStr;
	}
	
	
	
}
