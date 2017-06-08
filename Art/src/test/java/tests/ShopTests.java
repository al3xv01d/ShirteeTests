package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Util.ReadDataFromFile;
import base.FunctionalTest;
import pageobjects.DashboardCreateShopPage;
import pageobjects.DashboardShopsPage;
import pageobjects.MainPage;
import pageobjects.ShopPage;

public class ShopTests extends FunctionalTest{

	private String eMail;
	private String userPassword;
	private String URL;
	
	private static final String ALERT_TEXT = "Please enter a valid URL";
	private static final String TITLE_TEXT = "Title";
	private static final String DESCRIPTION_TEXT = "Description is here";
	private static final String EMPTY_FIELD_MSG = "Dies ist ein Pflichtfeld.";
	
	private static final String ALERT_TEXT_ERROR = "Alert text is not as expected!";
	private static final String SHOP_TITLE_ERROR = "Shop title is not as expected!";
	private static final String DESCRIPTION_ERROR = "Shop description is not as expected!";
	private static final String SHOP_URL_ERROR = "Shop URL is not as expected!";
	private static final String EMPTY_SHOP_NAME_MSG_ERROR = 
			"Empty shop name field validation msg is not as expected!";
	private static final String EMPTY_SHOP_URL_MSG_ERROR = 
			"Empty shop URL field validation msg is not as expected!";


	public ShopTests() {
		
		 ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		 eMail = data.getPropertie("eMail");
		 userPassword = data.getPropertie("userPassword");
	}
	
	@Test
	public void emptyFieldsValidationTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("dashboardNewShopURL"));
		DashboardCreateShopPage newShopPage = new DashboardCreateShopPage(driver);
		newShopPage.getSubmitButton().click();
		
		softAssert.assertEquals(newShopPage.getNoTitleMsg(), EMPTY_FIELD_MSG, EMPTY_SHOP_NAME_MSG_ERROR);
		softAssert.assertEquals(newShopPage.getNoUrlMsg(), EMPTY_FIELD_MSG, EMPTY_SHOP_URL_MSG_ERROR);
		
		softAssert.assertAll();
	}
	
	@Test
	public void urlValidationTest()
	{
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("dashboardNewShopURL"));
		DashboardCreateShopPage newShopPage = new DashboardCreateShopPage(driver);
		newShopPage.enterShopUrl("!@#/");
		newShopPage.getSubmitButton().click();
		newShopPage.waitForAlert();
		
		if (newShopPage.isAlertPresent()) {
			Alert alert = driver.switchTo().alert();
			Assert.assertEquals(alert.getText(), ALERT_TEXT, ALERT_TEXT_ERROR);
			alert.accept();
		}
		else {Assert.fail("Alert is not present!");}
	}
	
	@Test
	public void shopCreationTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("dashboardNewShopURL"));
		DashboardCreateShopPage newShopPage = new DashboardCreateShopPage(driver);
		newShopPage.enterShopTitle(TITLE_TEXT);
		newShopPage.enterDescription(DESCRIPTION_TEXT);
		
		URL = RandomStringUtils.randomAlphanumeric(7).toLowerCase();
		newShopPage.enterShopUrl(URL);
		newShopPage.submit();
		newShopPage.submit();
		newShopPage.waitForShopCreation();
		
		driver.get(System.getProperty("dashboardShopsURL"));
		DashboardShopsPage shopsPage = new DashboardShopsPage(driver);
		softAssert.assertEquals(shopsPage.getShopName().getText(), TITLE_TEXT, SHOP_TITLE_ERROR);
		softAssert.assertEquals(shopsPage.getShopURL().getText(), "/store/" + URL, SHOP_URL_ERROR);
		
		shopsPage.enableShop();
		driver.get(System.getProperty("mainPageURL") + shopsPage.getShopURL().getText());
		ShopPage shopPage = new ShopPage(driver);
		softAssert.assertEquals(shopPage.getShopTitle().getText(),
				TITLE_TEXT.toUpperCase(), SHOP_TITLE_ERROR);
		softAssert.assertEquals(shopPage.getShopDescription().getText(),
				DESCRIPTION_TEXT.toUpperCase() + " ", DESCRIPTION_ERROR);
		
		driver.navigate().back();
		shopsPage.deleteShop();
		
		softAssert.assertAll();
	}
	
}
