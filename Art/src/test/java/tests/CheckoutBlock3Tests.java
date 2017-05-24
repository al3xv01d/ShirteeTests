package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
	private String sumFor2Items = "52,95 €";
	private String charges = "2,52 €";
	private String shipping = "4,50 €";
	
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
		softAssert.assertEquals(cartBlock3.getCampaignTitle().getText(), "TESTAUTOCAMPAIGN2");
		
		softAssert.assertEquals(cartBlock3.getPrice(), price);
		softAssert.assertEquals(cartBlock3.getSum(), price);
		softAssert.assertEquals(cartBlock3.getTotal(), price);
		
		CheckoutPageBlock2 cartBlock2 = new CheckoutPageBlock2(driver);

		cartBlock2.checkKlarna();
		cartBlock2.waitForKlarnaInfo();
		softAssert.assertEquals(cartBlock3.getShippingKlarna(), shipping);
		softAssert.assertEquals(cartBlock3.getCharges(), charges);
		
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

		softAssert.assertEquals(cartBlock3.getTotal(), sumFor2Items);
		
		CheckoutPageBlock2 cartBlock2 = new CheckoutPageBlock2(driver);
		cartBlock2.checkKlarna();
		cartBlock2.waitForKlarnaInfo();
		softAssert.assertEquals(cartBlock3.getShippingKlarna(), shipping);
		softAssert.assertEquals(cartBlock3.getCharges(), charges);
		
		softAssert.assertAll();
	}
	
	@Test()
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
		
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		 
		String login = data.getPropertie("databaseLogin");
		String password = data.getPropertie("databasePassword");
	
		//Class.forName("com.mysql.jdbc.Driver");	

		//Creating a connection to the database
		Connection conn = DriverManager.getConnection("jdbc:mysql://db.shirtee.de:33006/shirtee",login,password);
		
		//Executing SQL query and fetching the result
		Statement st = conn.createStatement();
		String sqlStr = "SELECT * FROM `gomage_productdesigner_campaign_products` WHERE `product_id` = 20000";
		ResultSet rs = st.executeQuery(sqlStr);
		while (rs.next()) {
			System.out.println(rs.getString("price"));
		}		
		
		
	}
	
	
}
