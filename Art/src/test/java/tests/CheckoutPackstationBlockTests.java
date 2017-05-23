package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.FunctionalTest;
import pageobjects.CheckoutPage;
import pageobjects.ProductPage;

public class CheckoutPackstationBlockTests extends FunctionalTest{
	
	
	@Test(enabled = false)
	public void packstationFinderLinkTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		checkoutPage.packstationClick();
		
		softAssert.assertEquals("https://standorte.deutschepost.de/packstationen_paketboxen?pmtype=1",
				checkoutPage.getPackstationLink());
		
		softAssert.assertAll();
	}

	@Test(enabled = false)
	public void emptyFieldsTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		checkoutPage.packstationClick();
		checkoutPage.submitOrder();
		
		softAssert.assertTrue(checkoutPage.getPackstationNumberEmptyInputMsg().isDisplayed());
		softAssert.assertTrue(checkoutPage.getPostNumberEmptyInputMsg().isDisplayed());
		softAssert.assertTrue(checkoutPage.getPostcodeIsEmptyMessage().isDisplayed());
		softAssert.assertTrue(checkoutPage.getCityIsEmptyMessage().isDisplayed());
		
		softAssert.assertTrue(checkoutPage.getPaymentOptionRequiredMsg().isDisplayed());
		
		softAssert.assertAll();
		
	}
	
	@Test(enabled = false)
	public void tabsTest()
	{

		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		
		softAssert.assertTrue(checkoutPage.getPackstationTab().isDisplayed());
		
		checkoutPage.firmaRadioButtonClick();
		
		softAssert.assertTrue(!checkoutPage.getPackstationTab().isDisplayed());
		
		checkoutPage.herrRadioButtonClick();
		softAssert.assertTrue(checkoutPage.getPackstationTab().isDisplayed());
		softAssert.assertAll();
		
	}
}
