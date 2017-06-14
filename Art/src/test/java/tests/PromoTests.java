package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Util.ReadDataFromFile;
import base.FunctionalTest;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock3;
import pageobjects.DashboardPromoPage;
import pageobjects.MainPage;
import pageobjects.ProductPage;

public class PromoTests extends FunctionalTest{

	private static final String PROMO_CODE = "testauto";
	private static final String DISCOUNT_VALUE = "-10,00 €";
	private static final String DOUBLE_DISCOUNT_VALUE = "-20,00 €";

	private static final String TOTAL_VALUE_W_PROMO = "24,50 €";
	private static final String DOUBLE_TOTAL_VALUE_W_PROMO = "44,50 €";
	private static final String TOTAL_VALUE_WO_PROMO = "34,50 €";
	
	private static final String PROMO_CODE_ERROR = "Promo code is not as expected!";
	private static final String DISCOUND_VALUE_ERROR = "Discound value is not as expected!";
	private static final String TOTAL_VALUE_W_PROMO_ERROR = "Total value with promo is not as expected!";
	private static final String TOTAL_VALUE_WO_PROMO_ERROR = "Total value without promo is not as expected!";
	private static final String PROMO_CODE_PRESENT_ERROR = "Promo code should not be present!";
	private static final String PROMO_VALUE_PRESENT_ERROR = "Promo value should not be present!";
	
	private static final String PROMO_VALUE_AFTER_INC_ERROR =
			"Promo value after products inc is not as expected!";
	private static final String PROMO_VALUE_AFTER_DEC_ERROR =
			"Promo value after products dec is not as expected!";
	private static final String TOTAL_VALUE_AFTER_INC_ERROR =
			"Total value after products inc is not as expected!";
	private static final String TOTAL_VALUE_AFTER_DEC_ERROR =
			"Total value after products dec is not as expected!";
	
	private static final String EMPTY_INPUT = "";
	private static final String PROMO_CREATED_ERROR = "Promo is not supposed to be created!";
	
	@Test
	public void createPromoTest(){
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		String eMail = data.getPropertie("eMail");
		String userPassword = data.getPropertie("userPassword");
		SoftAssert softAssert = new SoftAssert();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("dashboardPromoUrl"));
		
		DashboardPromoPage promoPage = new DashboardPromoPage(driver);
		promoPage.inputCode("newcode2");
		promoPage.inputDiscountAmount("5");
		promoPage.addCode();
		promoPage.waitForElement(promoPage.getDeleteCreatedPromoButton());
		Assert.assertTrue(promoPage.getPromoTableRows().size() == 2);
		softAssert.assertEquals(promoPage.getCreatedPromoCode().getText(), "newcode2");
		softAssert.assertEquals(promoPage.getCreatedPromoDiscount().getText(), "5 €");
		promoPage.deleteSecondPromo();
		Assert.assertTrue(promoPage.getPromoTableRows().size() == 1);
		softAssert.assertAll();
	}
	
	@Test
	public void noInputValidationTest()
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		String eMail = data.getPropertie("eMail");
		String userPassword = data.getPropertie("userPassword");
		SoftAssert softAssert = new SoftAssert();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("dashboardPromoUrl"));
		
		DashboardPromoPage promoPage = new DashboardPromoPage(driver);
		promoPage.inputCode(EMPTY_INPUT);
		promoPage.inputDiscountAmount(EMPTY_INPUT);
		promoPage.addCode();
		
		softAssert.assertTrue(promoPage.getPromoCodeValidationMsg().isDisplayed());
		softAssert.assertTrue(promoPage.getEmtyProfitMsg().isDisplayed());

		softAssert.assertAll();
	}
	
	@Test
	public void sameCodeValidationTest(){
		SoftAssert softAssert = new SoftAssert();
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		String eMail = data.getPropertie("eMail");
		String userPassword = data.getPropertie("userPassword");
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("dashboardPromoUrl"));
		
		DashboardPromoPage promoPage = new DashboardPromoPage(driver);
		
		promoPage.inputCode(PROMO_CODE);
		promoPage.inputDiscountAmount("5");
		promoPage.addCode();
		softAssert.assertTrue(promoPage.getPromoErrorPopup().isDisplayed());
		softAssert.assertTrue(promoPage.getPromoTableRows().size() == 1, PROMO_CREATED_ERROR);
		softAssert.assertAll();
	}
	
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
		softAssert.assertEquals(checkoutPage.getDiscountValue().getText(),
				DISCOUNT_VALUE, DISCOUND_VALUE_ERROR);
		softAssert.assertEquals(checkoutPage.getDiscountCode().getText(), PROMO_CODE, PROMO_CODE_ERROR);
		
		softAssert.assertAll();
	}
	
	@Test
	public void promoCodeInputTest()
	{
		SoftAssert softAssert = new SoftAssert();
		driver.get(System.getProperty("campaignURL1"));
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		
		driver.get(System.getProperty("checkoutURL"));
		CheckoutPageBlock3 checkoutPage = new CheckoutPageBlock3(driver);
		checkoutPage.inputDiscountCode(PROMO_CODE);
		
		softAssert.assertEquals(checkoutPage.getDiscountValue().getText(),
				DISCOUNT_VALUE, DISCOUND_VALUE_ERROR);
		softAssert.assertEquals(checkoutPage.getTotal(), TOTAL_VALUE_W_PROMO, TOTAL_VALUE_W_PROMO_ERROR);
		
		checkoutPage.removeDiscount();
		softAssert.assertFalse(checkoutPage.isDiscountCodePresent(), PROMO_CODE_PRESENT_ERROR);
		softAssert.assertFalse(checkoutPage.isDiscountValuePresent(), PROMO_VALUE_PRESENT_ERROR);
		softAssert.assertEquals(checkoutPage.getTotal(), TOTAL_VALUE_WO_PROMO, TOTAL_VALUE_WO_PROMO_ERROR);
		
		softAssert.assertAll();
	}
	
	@Test
	public void promoValueCalculationTest()
	{
		SoftAssert softAssert = new SoftAssert();
		driver.get(System.getProperty("campaignURL1"));
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		
		driver.get(System.getProperty("checkoutURL"));
		CheckoutPageBlock3 checkoutPage = new CheckoutPageBlock3(driver);
		checkoutPage.inputDiscountCode(PROMO_CODE);
		checkoutPage.increaseQuantity();
		
		softAssert.assertEquals(checkoutPage.getDiscountValue().getText(), 
				DOUBLE_DISCOUNT_VALUE, PROMO_VALUE_AFTER_INC_ERROR);
		softAssert.assertEquals(checkoutPage.getTotal(), 
				DOUBLE_TOTAL_VALUE_W_PROMO, TOTAL_VALUE_AFTER_INC_ERROR);
		
		checkoutPage.decreaseQuantity();
		softAssert.assertEquals(checkoutPage.getDiscountValue().getText(), 
				DISCOUNT_VALUE, PROMO_VALUE_AFTER_DEC_ERROR);
		softAssert.assertEquals(checkoutPage.getTotal(), 
				TOTAL_VALUE_W_PROMO, TOTAL_VALUE_AFTER_DEC_ERROR);

		softAssert.assertAll();
	}
	
}
