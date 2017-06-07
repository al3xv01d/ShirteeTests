package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Util.ReadDataFromFile;
import base.FunctionalTest;
import pageobjects.DashboardCreateShopPage;
import pageobjects.MainPage;

public class ShopTests extends FunctionalTest{

	private String eMail;
	private String userPassword;
	private static final String ALERT_TEXT = "Please enter a valid URL";
	private static final String TITLE_TEXT = "Title";
	private static final String DESCRIPTION_TEXT = "Description is here!";
	private  String URL;

	private static final String ALERT_TEXT_ERROR = "Alert text is not as expected!";

	
	public ShopTests() {
		 	ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
			eMail = data.getPropertie("eMail");
			userPassword = data.getPropertie("userPassword");
	}
	
	@Test
	public void urlValidationTest()
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("dashboardNewShopURL"));
		DashboardCreateShopPage newShopPage = new DashboardCreateShopPage(driver);
		newShopPage.enterShopUrl("!@#/");
		newShopPage.submit();
		newShopPage.waitForAlert();
		
		if (newShopPage.isAlertPresent()) {
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), ALERT_TEXT, ALERT_TEXT_ERROR);
			alert.accept();
		}
		else {Assert.fail("Unexpected Fail!");}
		
	}
	
	@Test
	public void shopCreationTest()
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("dashboardNewShopURL"));
		DashboardCreateShopPage newShopPage = new DashboardCreateShopPage(driver);
		newShopPage.enterShopTitle(TITLE_TEXT);
		newShopPage.enterDescription(DESCRIPTION_TEXT);
		
		URL = RandomStringUtils.randomAlphanumeric(7);
		newShopPage.enterShopUrl(URL);
		newShopPage.submit();
		newShopPage.submit();
		newShopPage.waitForShopCreate();
		
		driver.get(System.getProperty("dashboardShopsURL"));
	}
	
}
