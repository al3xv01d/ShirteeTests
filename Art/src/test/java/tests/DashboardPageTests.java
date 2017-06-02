package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Util.ReadDataFromFile;
import base.FunctionalTest;

import pageobjects.DashboardPage;
import pageobjects.MainPage;
import pageobjects.ProductPage;

public class DashboardPageTests extends FunctionalTest{

	@Test
	public void campaignSwitchTest()
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");

		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(data.getPropertie("eMail"), data.getPropertie("userPassword"));
		
		DashboardPage dashboardPage = new DashboardPage(driver);

		dashboardPage.getFirstCampaignStatusSwitcher().click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.get(System.getProperty("campaignURL1"));
		
		ProductPage productPage = new ProductPage(driver);
		boolean check = productPage.checkPageAvailability();
		Assert.assertTrue(!check);
		
		driver.get(System.getProperty("dashboardURL"));
		dashboardPage.getFirstCampaignStatusSwitcher().click();		
		alert.accept();
	}
	
	@Test
	public void paginationTest()
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");

		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(data.getPropertie("oldEmail"), data.getPropertie("oldPassword"));
		
		driver.get(System.getProperty("dashboardURL"));
		
		DashboardPage dashboardPage = new DashboardPage(driver);
		String str = dashboardPage.getPageRange();
		System.out.println(str);
		dashboardPage.sendPageNumber("5");
		dashboardPage.waitForCampaignLoading();
		Assert.assertEquals(dashboardPage.getPageRange(), "61-75 von 77");

		dashboardPage.sendPageNumber("asd");
		dashboardPage.waitForCampaignLoading();
		
		Assert.assertEquals(dashboardPage.getPageRange(), "1-15 von 77");
		
		dashboardPage.sendPageNumber("700");
		dashboardPage.waitForCampaignLoading();
		Assert.assertEquals(dashboardPage.getPageRange(), "76-77 von 77");
		
	}
	
}
