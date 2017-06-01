package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Util.DatabaseHelper;
import Util.ReadDataFromFile;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import base.FunctionalTest;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock2;
import pageobjects.CheckoutPageBlock3;
import pageobjects.ProductPage;
import pageobjects.ShopPage;

public class CheckoutBlock3Tests extends FunctionalTest{

	private String price;
	private static final String SUM_FOR_2_ITEMS = "52,95 €";
	private static final String CHARGES = "2,52 €";
	private static final String SHIPPING = "4,50 €";
	private static final String CAMPAIGN_TITLE = "TestAutoCampaign2";
	
	@Test
	public void oneProductDataTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		price = productPage.getPrice();
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPageBlock3 cartBlock3 = new CheckoutPageBlock3(driver);
		
		softAssert.assertTrue(cartBlock3.getCampaignTitle().getAttribute("href").
				contains(System.getProperty("campaignURL1")));
		softAssert.assertEquals(cartBlock3.getCampaignTitle().getText(), CAMPAIGN_TITLE);
		
		softAssert.assertEquals(cartBlock3.getPrice(), price);
		softAssert.assertEquals(cartBlock3.getSum(), price);
		softAssert.assertEquals(cartBlock3.getTotal(), price);
		
		CheckoutPageBlock2 cartBlock2 = new CheckoutPageBlock2(driver);

		cartBlock2.checkKlarna();
		cartBlock2.waitForKlarnaInfo();
		softAssert.assertEquals(cartBlock3.getShippingKlarna(), SHIPPING);
		softAssert.assertEquals(cartBlock3.getCharges(), CHARGES);
		
		softAssert.assertAll();
	}
	
	@Test
	public void calculationTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		price = productPage.getPrice();
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPageBlock3 cartBlock3 = new CheckoutPageBlock3(driver);
		
		cartBlock3.increaseQuantity();
		cartBlock3.waitForCalculationToFinish();
		
		softAssert.assertEquals(cartBlock3.getTotal(), "64,50 €");
		cartBlock3.decreaseQuantity();
		cartBlock3.waitForCalculationToFinish();
		
		softAssert.assertEquals(cartBlock3.getTotal(), "34,50 €");
		
		softAssert.assertAll();
	}
	
	@Test
	public void twoProductsPricesTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		price = productPage.getPrice();
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();

		driver.get(System.getProperty("campaignURL2"));
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPageBlock3 cartBlock3 = new CheckoutPageBlock3(driver);

		softAssert.assertEquals(cartBlock3.getTotal(), SUM_FOR_2_ITEMS);
		
		CheckoutPageBlock2 cartBlock2 = new CheckoutPageBlock2(driver);
		cartBlock2.checkKlarna();
		cartBlock2.waitForKlarnaInfo();
		softAssert.assertEquals(cartBlock3.getShippingKlarna(), SHIPPING);
		softAssert.assertEquals(cartBlock3.getCharges(), CHARGES);
		
		softAssert.assertAll();
	}
	
	@Test(enabled = true)
	public void breadcrumbsTest()
	{

		//TODO:  prod version
		
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("shopURL"));
		driver.manage().window().maximize();
		
		ShopPage shopPage = new ShopPage(driver);
		shopPage.campaignItem1Click();
		
		ProductPage productPage = new ProductPage(driver);
				
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		Assert.assertTrue(checkoutPage.isElementPresent(By.linkText("Zurück zum Shop")));

		softAssert.assertTrue(checkoutPage.getBackLink().getAttribute("href").
				contains(System.getProperty("campaignURL2")));
		
		driver.get(System.getProperty("shopURL"));
		
		shopPage.campaignItem2Click();

		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		Assert.assertTrue(checkoutPage.isElementPresent(By.linkText("Zurück zum Shop")));

		softAssert.assertTrue(checkoutPage.getBackLink().getAttribute("href").
				contains(System.getProperty("campaignURL1")));
		
		softAssert.assertAll();
	}

	
	//TODO: breadcrumbs test for 2 campaigns
	
	@Test
	public void deleteProductTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		price = productPage.getPrice();
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();

		driver.get(System.getProperty("campaignURL2"));
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPageBlock3 cartBlock3 = new CheckoutPageBlock3(driver);
		
		cartBlock3.deleteSecondItemClick();
		cartBlock3.waitForCalculationToFinish();
		
		softAssert.assertEquals(cartBlock3.getTotal(), "34,50 €");
		softAssert.assertTrue(!cartBlock3.isElementPresent(
				By.xpath("//*[@id='checkout-review-table']/tbody/tr[2]/td[2]/h3")));
		
		softAssert.assertAll();
	}
	
	@Test(enabled = false)
	public void databaseTest() throws SQLException, ClassNotFoundException
	{

		DatabaseHelper databaseHelper = new DatabaseHelper("/home/dglazov/data.properties");

		
		String str = databaseHelper.getAffiliateByType(Util.DatabaseHelper.AffiliateType.JB);
		System.out.println(str);
	}
	
	
}
