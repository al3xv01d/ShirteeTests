package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.*;

import base.FunctionalTest;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock2;
import pageobjects.ProductPage;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CheckoutBlock1Tests extends FunctionalTest{

	
	private static final String VOR_NAME_IS_EMPTY_MSG = "'VorName is empty' msg is not displayed!";
	private static final String NACH_NAME_IS_EMPTY_MSG = "'NachName is empty' msg is not displayed!";
	private static final String EMAIL_IS_EMPTY_MSG = "'Email is empty' msg is not displayed!";
	private static final String ADDRESS_IS_EMPTY_MSG = "'Address is empty' msg is not displayed!";
	private static final String POSTCODE_IS_EMPTY_MSG = "'Postcode is empty' msg is not displayed!";
	private static final String CITY_IS_EMPTY_MSG = "'City is empty' msg is not displayed!";
	
	
	@DataProvider
	public Object[][] vornameTestData()
	{
		return new Object[][] {
			new Object[] {"asd()","NÄchname","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"asd,,!@","NachnÜame","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"as,d","NacÖhname","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"as+d","Nacßhname","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"as_d","Nächname","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"as*d","Nachnöame","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"as§d","Nacühname","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"as;:d","ßhname","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"asролd","ßhname","email@mail.com", "address 11", "123456", "City"},
		};
	}
	
	@DataProvider
	public Object[][] nachnameTestData()
	{
		return new Object[][] {
			new Object[] {"VoÄrname","()Nchname","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"VorÜname","NachnÜame!@","email@mail.com", "addrÜess 11", "123Ü456", "CiÜty"},
			new Object[] {"VorÖname",",NacÖhn+ame","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"Vorßname","Nac_ßhname","emÜail@mail.com", "address 11", "123456", "City"},
			new Object[] {"Väorname","Nächna*me","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"Voröname","Nac§hnöame","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"Voürname","Nacühname/","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"Vornßame","ßhname;","email@mail.com", "address 11", "123456", "City"},
			new Object[] {"Vornßame","прhname","email@mail.com:", "address 11", "123456", "City"},
		};
	}
	
	@DataProvider
	public Object[][] emailTestData()
	{
		return new Object[][] {
			new Object[] {"VoÄrname","Nchname","ema!(il@mail.com", "address 11", "123456", "City"},
			new Object[] {"VorÜname","NachnÜame","em)ail@mail.com", "address 11", "123456", "City"},
			new Object[] {"VorÖname","NacÖhname","' 'email@mail.com", "address 11", "123456", "City"},
			new Object[] {"Vorßname","Nacßhname","ema%il@mail.com", "address 11", "123456", "City"},
			new Object[] {"Väorname","Nächname","em=ail@mail.com", "address 11", "123456", "City"},
			new Object[] {"Voröname","Nachnöame","email@mai,;l.com", "address 11", "123456", "City"},
			new Object[] {"Voürname","Nacühname","emai l@mail.com", "address 11", "123456", "City"},
			new Object[] {"Vornßame","ßhname","emfail@ma:il.com:", "address 11", "123456", "City"},
			new Object[] {"Vornßame","ßhname","emfail@maолil.com:", "address 11", "123456", "City"},
		};
	}
	
	@DataProvider
	public Object[][] addressTestData()
	{
		return new Object[][] {
			new Object[] {"VoÄrname","Nchname","email@mail.com", "()address 11", "123456", "City"},
			new Object[] {"VorÜname","NachnÜame","email@mail.com", "address 11=", "123456", "City"},
			new Object[] {"VorÖname","NacÖhname","email@mail.com", "+address 11", "123456", "City"},
			new Object[] {"Vorßname","Nacßhname","email@mail.com", "address 11_", "123456", "City"},
			new Object[] {"Väorname","Nächname","email@mail.com", ";address 11", "123456", "City"},
			new Object[] {"Voröname","Nachnöame","email@mail.com", "$address 11", "123456", "City"},
			new Object[] {"Voürname","Nacühname","email@mail.com", "address 11:", "123456", "City"},
			new Object[] {"Vornßame","ßhname","email@mail.com", "#address 11", "123456", "City"},
			new Object[] {"Vornßame","ßhname","email@mail.com", "оролaddress 11", "123456", "City"}
		};
	}
	
	@DataProvider
	public Object[][] postcodeTestData()
	{
		return new Object[][] {
			new Object[] {"VoÄrname","Nchname","email@mail.com", "address 11", "asd", "City"},
			new Object[] {"VorÜname","NachnÜame","email@mail.com", "address 11", ":123456", "City"},
			new Object[] {"VorÖname","NacÖhname","email@mail.com", "address 11", "123456;", "City"},
			new Object[] {"Vorßname","Nacßhname","email@mail.com", "address 11", "12 3456", "City"},
			new Object[] {"Väorname","Nächname","email@mail.com", "address 11", "/123456", "City"},
			new Object[] {"Voröname","Nachnöame","email@mail.com", "address 11", "123456*", "City"},
			new Object[] {"Voürname","Nacühname","email@mail.com", "address 11", "()123456", "City"},
			new Object[] {"Vornßame","ßhname","email@mail.com", "address 11", "123456%", "City"},
			new Object[] {"Vornßame","ßhname","email@mail.com", "address 11", "@123456", "City"}
		};
	}
	
	@DataProvider
	public Object[][] cityTestData()
	{
		return new Object[][] {
			new Object[] {"VoÄrname","Nchname","email@mail.com", "address 11", "123456", "City!"},
			new Object[] {"VorÜname","NachnÜame","email@mail.com", "address 11", "123456", "Cit(y"},
			new Object[] {"VorÖname","NacÖhname","email@mail.com", "address 11", "123456", "&City"},
			new Object[] {"Vorßname","Nacßhname","email@mail.com", "address 11", "123456", "%City"},
			new Object[] {"Väorname","Nächname","email@mail.com", "address 11", "123456", "City="},
			new Object[] {"Voröname","Nachnöame","email@mail.com", "address 11", "123456", ",City;"},
			new Object[] {"Voürname","Nacühname","email@mail.com", "address 11", "123456", "прCity"},
			new Object[] {"Vornßame","ßhname","email@mail.com", "address 11", "123456", "City@"},
			new Object[] {"Vornßame","ßhname","email@mail.com", "address 11", "123456", ":City"}
		};
	}
	
	@DataProvider
	public Object[][] phoneTestData()
	{
		return new Object[][] {
			new Object[] {"VoÄrname","Nchname","email@mail.com", "address 11", "123456", "City", "7894562!"},
			new Object[] {"VorÜname","NachnÜame","email@mail.com", "address 11", "123456", "City", "78 94562"},
			new Object[] {"VorÖname","NacÖhname","email@mail.com", "address 11", "123456", "City", "()7894562"},
			new Object[] {"Vorßname","Nacßhname","email@mail.com", "address 11", "123456", "City", ",7894562"},
			new Object[] {"Väorname","Nächname","email@mail.com", "address 11", "123456", "City", "7894562@"},
			new Object[] {"Voröname","Nachnöame","email@mail.com", "address 11", "123456", "City", "ß7894562"},
			new Object[] {"Voürname","Nacühname","email@mail.com", "address 11", "123456", "City", "78смп94562"},
			new Object[] {"Vornßame","ßhname","email@mail.com", "address 11", "123456", "City", "78945.62"},
			new Object[] {"Vornßame","ßhname","email@mail.com", "address 11", "123456", "City", ";7894562"}
		};
	}
	 

	@Test
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
		
		CheckoutPage cartPage = new CheckoutPage(driver);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		cartPage.submitOrder();
		
		softAssert.assertTrue(cartPage.getVorNameIsEmptyMessage().isDisplayed(), VOR_NAME_IS_EMPTY_MSG);
		softAssert.assertTrue(cartPage.getNachNameIsEmptyMessage().isDisplayed(), NACH_NAME_IS_EMPTY_MSG);
		softAssert.assertTrue(cartPage.geteMailIsEmptyMessage().isDisplayed(), EMAIL_IS_EMPTY_MSG);
		softAssert.assertTrue(cartPage.getAddressIsEmptyMessage().isDisplayed(), ADDRESS_IS_EMPTY_MSG);
		softAssert.assertTrue(cartPage.getPostcodeIsEmptyMessage().isDisplayed(), POSTCODE_IS_EMPTY_MSG);
		softAssert.assertTrue(cartPage.getCityIsEmptyMessage().isDisplayed(), CITY_IS_EMPTY_MSG);
		
		//if order succeed - success page is displayed
		//if not - redirect to cart page
		//driver.get("https://www.shirtee.de/checkout/onepage/success/");
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();

	}
	
	@Test(dataProvider = "vornameTestData", enabled = false)
	public void vornameTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city){
		
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(System.getProperty("checkoutURL"));
		
		CheckoutPage cartPage = new CheckoutPage(driver);
	    cartPage.sendKeysVorname(vorname);
	    
		cartPage.sendKeysNachname(nachname);
		cartPage.sendKeysEMail(eMail);
		cartPage.sendKeysAddress(address);
		cartPage.sendPostcode(postalCode);
		cartPage.sendCity(city);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		
		cartPage.submitOrder();
		
		softAssert.assertTrue(cartPage.getVorNameIsIncorrectMessage().isDisplayed());
		softAssert.assertTrue(!cartPage.isElementPresent(By.id("advice-validate-name-billing_lastname")));
		
		driver.get(System.getProperty("successPageURL"));
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "nachnameTestData", enabled = false)
	public void nachnameTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(System.getProperty("checkoutURL"));
		
		CheckoutPage cartPage = new CheckoutPage(driver);
	    cartPage.sendKeysVorname(vorname);
	    
		cartPage.sendKeysNachname(nachname);
		cartPage.sendKeysEMail(eMail);
		cartPage.sendKeysAddress(address);
		cartPage.sendPostcode(postalCode);
		cartPage.sendCity(city);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		
		cartPage.submitOrder();
		softAssert.assertTrue(cartPage.getNachNameIsIncorrectMessage().isDisplayed());
		softAssert.assertTrue(!cartPage.isElementPresent(By.id("advice-validate-name-billing_firstname")));
		
		driver.get(System.getProperty("successPageURL"));
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "emailTestData", enabled = false)
	public void eMailTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		//driver.get("https://www.shirtee.de/testautocampaign2");
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(System.getProperty("checkoutURL"));
		
		CheckoutPage cartPage = new CheckoutPage(driver);
	    cartPage.sendKeysVorname(vorname);
	    
		cartPage.sendKeysNachname(nachname);
		cartPage.sendKeysEMail(eMail);
		cartPage.sendKeysAddress(address);
		cartPage.sendPostcode(postalCode);
		cartPage.sendCity(city);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		
		cartPage.submitOrder();
		softAssert.assertTrue(cartPage.geteMailIsIncorrectMessage().isDisplayed());
		//softAssert.assertTrue(!cartPage.isElementPresent(By.id("advice-validate-name-billing_firstname")));
		
		driver.get(System.getProperty("successPageURL"));
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "addressTestData", enabled = false)
	public void addressTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(System.getProperty("checkoutURL"));
		
		CheckoutPage cartPage = new CheckoutPage(driver);
	    cartPage.sendKeysVorname(vorname);
	    
		cartPage.sendKeysNachname(nachname);
		cartPage.sendKeysEMail(eMail);
		cartPage.sendKeysAddress(address);
		cartPage.sendPostcode(postalCode);
		cartPage.sendCity(city);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		
		cartPage.submitOrder();
		softAssert.assertTrue(cartPage.getAddressIsIncorrectMessage().isDisplayed());
		//softAssert.assertTrue(!cartPage.isElementPresent(By.id("advice-validate-name-billing_firstname")));
		
		driver.get(System.getProperty("successPageURL"));
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "postcodeTestData", enabled = false)
	public void postcodeTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		driver.get(System.getProperty("checkoutURL"));
		
		CheckoutPage cartPage = new CheckoutPage(driver);
	    cartPage.sendKeysVorname(vorname);
	    
		cartPage.sendKeysNachname(nachname);
		cartPage.sendKeysEMail(eMail);
		cartPage.sendKeysAddress(address);
		cartPage.sendPostcode(postalCode);
		cartPage.sendCity(city);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		
		cartPage.submitOrder();
		softAssert.assertTrue(cartPage.getPostcodeIsIncorrectMessage().isDisplayed());
		
		driver.get(System.getProperty("successPageURL"));
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "cityTestData", enabled = false)
	public void cityTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		
		driver.get(System.getProperty("checkoutURL"));
		
		CheckoutPage cartPage = new CheckoutPage(driver);
	    cartPage.sendKeysVorname(vorname);
	    
		cartPage.sendKeysNachname(nachname);
		cartPage.sendKeysEMail(eMail);
		cartPage.sendKeysAddress(address);
		cartPage.sendPostcode(postalCode);
		cartPage.sendCity(city);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		
		cartPage.submitOrder();
		softAssert.assertTrue(cartPage.getCityIsIncorrectMessage().isDisplayed());
		//softAssert.assertTrue(!cartPage.isElementPresent(By.id("advice-validate-name-billing_firstname")));
		
		driver.get(System.getProperty("successPageURL"));
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}

	@Test(dataProvider = "phoneTestData", enabled = false)//disabled because field is disabled
	public void phoneTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city, String phoneNum)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("campaignURL1"));
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(System.getProperty("checkoutURL"));
		
		CheckoutPage cartPage = new CheckoutPage(driver);
	    cartPage.sendKeysVorname(vorname);
	    
		cartPage.sendKeysNachname(nachname);
		cartPage.sendKeysEMail(eMail);
		cartPage.sendKeysAddress(address);
		cartPage.sendPostcode(postalCode);
		cartPage.sendCity(city);
		cartPage.sendPhoneNumber(phoneNum);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		
		cartPage.submitOrder();
		softAssert.assertTrue(cartPage.getPhoneIsIncorrectMessage().isDisplayed());

		
		driver.get(System.getProperty("successPageURL"));
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
}
