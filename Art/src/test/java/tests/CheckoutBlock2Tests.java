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

	private static final String PAYPAL_INFO_BLOCK_ERROR = "PayPal info block is not displayed!";
	private static final String CREDITCARD_INFO_BLOCK_ERROR = "Creditcard info block is not displayed!";
	private static final String SOFORT_INFO_BLOCK_ERROR = "Sofort info block is not displayed!";
	private static final String KLARNA_INFO_BLOCK_ERROR = "Klarna info block is not displayed!";
	private static final String VORKRASSE_INFO_BLOCK_ERROR = "Vorkrasse info block is not displayed!";
	private static final String KLARNA_SEX_RB_SYNC_ERROR = "Klarna sex radiobuttons are not in sync!";

	
	@Test
	public void paymentBlockTest()
	{
		
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPageBlock2 cartPage = new CheckoutPageBlock2(driver);
		
//		cartPage.checkPayPal();
//		cartPage.waitForPayPalInfo();
//		softAssert.assertTrue(!cartPage.getPayPalInfo().isDisplayed(), PAYPAL_INFO_BLOCK_ERROR);
		
		cartPage.checkCreditCardBlock();
		cartPage.waitForElement(cartPage.getCreditCardInfo());
		softAssert.assertTrue(cartPage.getCreditCardInfo().isDisplayed(), CREDITCARD_INFO_BLOCK_ERROR);
		
		cartPage.checkSofort();
		cartPage.waitForSofortInfo();
		softAssert.assertTrue(cartPage.getSofortInfo().isDisplayed(), SOFORT_INFO_BLOCK_ERROR);
		
		cartPage.checkKlarna();
		cartPage.waitForKlarnaInfo();
		softAssert.assertTrue(cartPage.getKlarnaInfo().isDisplayed(), KLARNA_INFO_BLOCK_ERROR);
		
		cartPage.checkVorkrasse();
		cartPage.waitForVorkrasseInfo();
		softAssert.assertTrue(cartPage.getVorkrasseInfo().isDisplayed(), VORKRASSE_INFO_BLOCK_ERROR);
		
		softAssert.assertAll();
	}
	
	@Test
	public void klarnaSexSyncTest()
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
		checkoutPage.herrRadioButtonClick();
		
		CheckoutPageBlock2 checkoutBlock2 = new CheckoutPageBlock2(driver);
		checkoutBlock2.checkKlarna();
		checkoutBlock2.waitForKlarnaInfo();
		
		softAssert.assertEquals(checkoutBlock2.getKlarnaMaleRB().getAttribute("checked"),
				"true", KLARNA_SEX_RB_SYNC_ERROR);
		softAssert.assertEquals(checkoutBlock2.getKlarnaFemaleRB().getAttribute("checked"),
				null, KLARNA_SEX_RB_SYNC_ERROR);
		
		checkoutPage.frauRadioButtonClick();
		softAssert.assertEquals(checkoutBlock2.getKlarnaMaleRB().getAttribute("checked"),
				null, KLARNA_SEX_RB_SYNC_ERROR);
		softAssert.assertEquals(checkoutBlock2.getKlarnaFemaleRB().getAttribute("checked"),
				"true", KLARNA_SEX_RB_SYNC_ERROR);
		
		softAssert.assertAll();
	}
	
	@Test(enabled = false)// disabled because telephone field is no longer present
	public void klarnaPhoneNumberTest()
	{
		String str = "123456789";
		
		driver.get(System.getProperty("campaignURL1"));
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
