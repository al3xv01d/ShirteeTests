package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import Util.ReadDataFromFile;
import base.FunctionalTest;

import pageobjects.DashboardPage;
import pageobjects.MainPage;
import pageobjects.ProductPage;

public class DashboardPageTests extends FunctionalTest{

	private static final String PAGINATION_RANGE_ERROR = "Pagination range is not as expected!";
	private static final String PRODUCTPAGE_AVAILABILITY_ERROR = "Product page availability is not as expected!";
	
	@Test
	public void campaignSwitchTest()
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");

		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(data.getPropertie("eMail"), data.getPropertie("userPassword"));
		
		DashboardPage dashboardPage = new DashboardPage(driver);

		dashboardPage.getFirstCampaignStatusSwitcher().click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.get(System.getProperty("campaignURL1"));
		
		ProductPage productPage = new ProductPage(driver);
		boolean check = productPage.checkPageAvailability();
		Assert.assertTrue(!check, PRODUCTPAGE_AVAILABILITY_ERROR);
		
		driver.get(System.getProperty("dashboardURL"));
		dashboardPage.getFirstCampaignStatusSwitcher().click();		
		alert.accept();
	}
	
	@Test
	public void paginationTest() 
	{
		SoftAssert softAssert = new SoftAssert();
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");

		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(data.getPropertie("oldEmail"), data.getPropertie("oldPassword"));
		
		driver.get(System.getProperty("dashboardURL"));
		
		DashboardPage dashboardPage = new DashboardPage(driver);
		String str = dashboardPage.getPageRange();
		System.out.println(str);
		dashboardPage.sendPageNumber("5");
		dashboardPage.waitForCampaignLoading();
		softAssert.assertEquals(dashboardPage.getPageRange(), "61-75 von 78", PAGINATION_RANGE_ERROR);

		dashboardPage.sendPageNumber("asd");
		dashboardPage.waitForCampaignLoading();
		
		softAssert.assertEquals(dashboardPage.getPageRange(), "1-15 von 78", PAGINATION_RANGE_ERROR);
		
		dashboardPage.sendPageNumber("700");
		dashboardPage.waitForCampaignLoading();
		softAssert.assertEquals(dashboardPage.getPageRange(), "76-77 von 78", PAGINATION_RANGE_ERROR);
		
		softAssert.assertAll();
		
	}
	
}
