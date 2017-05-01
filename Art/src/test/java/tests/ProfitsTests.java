package tests;

import base.FunctionalTest;
import org.testng.annotations.Test;
import Util.ReadDataFromFile;

public class ProfitsTests extends FunctionalTest{

	@Test
	public void testProperties()
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		System.out.println(data.getPropertie("user"));
		System.out.println(data.getPropertie("password"));
	}
	
}
