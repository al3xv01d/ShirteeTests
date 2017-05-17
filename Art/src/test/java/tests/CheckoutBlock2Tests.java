package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.FunctionalTest;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock2;
import pageobjects.ProductPage;

public class CheckoutBlock2Tests extends FunctionalTest{

	@Test
	public void paymentBlockTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		//driver.get("https://www.shirtee.de/testautocampaign2");
		driver.get("https://dev.shirtee.de/shir2-3");
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPageBlock2 cartPage = new CheckoutPageBlock2(driver);
		
		cartPage.checkPayPal();
		cartPage.waitForPayPalInfo();
		softAssert.assertTrue(cartPage.getPayPalInfo().isDisplayed());
		
		cartPage.checkSofort();
		cartPage.waitForSofortInfo();
		softAssert.assertTrue(cartPage.getSofortInfo().isDisplayed());
		
		cartPage.checkKlarna();
		cartPage.waitForKlarnaInfo();
		softAssert.assertTrue(cartPage.getKlarnaInfo().isDisplayed());
		
		cartPage.checkVorkrasse();
		cartPage.waitForVorkrasseInfo();
		softAssert.assertTrue(cartPage.getVorkrasseInfo().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(enabled = false)// disabled because telephone field is no longer present
	public void klarnaPhoneNumberTest()
	{
		String str = "123456789";
		
		driver.get("https://www.shirtee.de/testautocampaign2");
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPage cartPage1 = new CheckoutPage(driver);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage1.sendPhoneNumber(str);
		cartPage2.checkKlarna();
		cartPage2.waitForKlarnaInfo();
		
		Assert.assertEquals(cartPage2.getKlarnaPhoneNumber(), str);
			
	}
	
}
