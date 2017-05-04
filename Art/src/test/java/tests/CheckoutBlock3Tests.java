package tests;

import java.math.BigDecimal;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.FunctionalTest;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock3;
import pageobjects.ProductPage;

public class CheckoutBlock3Tests extends FunctionalTest{

	String price = "30,00 €";
	String charges = "2,52 €";
	String shipping = "4,50 €";
	
	@Test
	public void oneProductpricesTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get("https://www.shirtee.de/testautocampaign2");
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CheckoutPageBlock3 cartBlock3 = new CheckoutPageBlock3(driver);
		
		softAssert.assertEquals(cartBlock3.getPrice(), price);
		softAssert.assertEquals(cartBlock3.getSum(), price);
		softAssert.assertEquals(cartBlock3.getTotal(), price);
		
		softAssert.assertAll();
	}
	
}
