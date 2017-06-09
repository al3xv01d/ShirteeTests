package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
	
	private static final String CAMPAIGN_TITLE_ERROR = "Campaign title is not as expected!";
	private static final String PRICE_ERROR = "Price amount is not as expected!";
	private static final String SUMM_ERROR = "Summe amount is not as expected!";
	private static final String TOTAL_ERROR = "Total amount is not as expected!";
	private static final String SHIPPING_KLARNA_ERROR = "Shipping amount is not as expected!";
	private static final String CHARGES_ERROR = "Charges amount is not as expected!";
	private static final String QUANTITY_ERROR = "Price after changing product quantity is not as expected!";
	
	private static final String BACKTOSHOP_EXIST_ERROR = "'Back to shop' link is not present!";
	private static final String BACKTOSHOP_LINK_ERROR = "'Back to shop' link URL is not as expected!";
	
	private static final String DELETE_PRODUCT_ERROR = "Deleted product is still present!";


	
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
		
//		softAssert.assertTrue(cartBlock3.getCampaignTitle().getAttribute("href").
//				contains(System.getProperty("campaignURL1")));
		softAssert.assertEquals(cartBlock3.getCampaignTitle().getText(), CAMPAIGN_TITLE, CAMPAIGN_TITLE_ERROR);
		
		softAssert.assertEquals(cartBlock3.getPrice(), price, PRICE_ERROR);
		softAssert.assertEquals(cartBlock3.getSum(), price, SUMM_ERROR);
		softAssert.assertEquals(cartBlock3.getTotal(), price, TOTAL_ERROR);
		
		CheckoutPageBlock2 cartBlock2 = new CheckoutPageBlock2(driver);

		cartBlock2.checkKlarna();
		cartBlock2.waitForKlarnaInfo();
		softAssert.assertEquals(cartBlock3.getShippingKlarna(), SHIPPING, SHIPPING_KLARNA_ERROR);
		softAssert.assertEquals(cartBlock3.getCharges(), CHARGES, CHARGES_ERROR);
		
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
		softAssert.assertEquals(cartBlock3.getTotal(), "64,50 €", QUANTITY_ERROR);
		cartBlock3.decreaseQuantity();

		softAssert.assertEquals(cartBlock3.getTotal(), "34,50 €", QUANTITY_ERROR);
		
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

		softAssert.assertEquals(cartBlock3.getTotal(), SUM_FOR_2_ITEMS, TOTAL_ERROR);
		
		CheckoutPageBlock2 cartBlock2 = new CheckoutPageBlock2(driver);
		cartBlock2.checkKlarna();
		cartBlock2.waitForKlarnaInfo();
		softAssert.assertEquals(cartBlock3.getShippingKlarna(), SHIPPING, SHIPPING_KLARNA_ERROR);
		softAssert.assertEquals(cartBlock3.getCharges(), CHARGES, CHARGES_ERROR);
		
		softAssert.assertAll();
	}
	
	@Test(enabled = true)
	public void breadcrumbsTest()
	{

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
		Assert.assertTrue(checkoutPage.isElementPresent(By.linkText("Zurück zum Shop")), BACKTOSHOP_EXIST_ERROR);

		softAssert.assertTrue(checkoutPage.getBackLink().getAttribute("href").
				contains(System.getProperty("campaignURL2")), BACKTOSHOP_LINK_ERROR);
		
		driver.get(System.getProperty("shopURL"));
		
		shopPage.campaignItem2Click();

		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		Assert.assertTrue(checkoutPage.isElementPresent(By.linkText("Zurück zum Shop")), BACKTOSHOP_EXIST_ERROR);

		softAssert.assertTrue(checkoutPage.getBackLink().getAttribute("href").
				contains(System.getProperty("campaignURL1")), BACKTOSHOP_LINK_ERROR);
		
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

		softAssert.assertEquals(cartBlock3.getTotal(), "34,50 €", TOTAL_ERROR);
		softAssert.assertTrue(!cartBlock3.isElementPresent(
				By.xpath("//*[@id='checkout-review-table']/tbody/tr[2]/td[2]/h3")), DELETE_PRODUCT_ERROR);
		
		softAssert.assertAll();
	}
	
	
}
