package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.FunctionalTest;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock3;
import pageobjects.ProductPage;

public class PromoTests extends FunctionalTest{

	private static final String PROMO_CODE = "testauto";
	private static final String DISCOUNT_VALUE = "-10,00 €";
	
	@Test
	public void promoURLTest()
	{
		SoftAssert softAssert = new SoftAssert();
		driver.get(System.getProperty("campaignURL1") + "?gs=" + PROMO_CODE);
		ProductPage productPage = new ProductPage(driver);
		softAssert.assertTrue(productPage.getPopUp().isDisplayed());
		
		productPage.closePopUp();
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		
		driver.get(System.getProperty("checkoutURL"));
		CheckoutPageBlock3 checkoutPage = new CheckoutPageBlock3(driver);
		softAssert.assertEquals(checkoutPage.getDiscountValue().getText(), DISCOUNT_VALUE);
		softAssert.assertEquals(checkoutPage.getDiscountCode().getText(), PROMO_CODE);
		
		softAssert.assertAll();
	}
	
}
