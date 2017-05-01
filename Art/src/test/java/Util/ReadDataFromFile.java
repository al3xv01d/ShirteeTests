package Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadDataFromFile {

	Properties prop;
	
	public  ReadDataFromFile(String filePath)
	{
		 prop = new Properties();
		try {
			prop.load(new FileInputStream(filePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getPropertie(String str)
	{
		String returnData = prop.getProperty(str);
		return returnData;
	}
	
}
