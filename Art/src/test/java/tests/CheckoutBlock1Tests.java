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

	private String devCmpUrl = "https://dev.shirtee.de/shir2-3";
	private String liveCmpUrl = "https://www.shirtee.de/testautocampaign2";
	
	private String liveCheckoutURL = "https://www.shirtee.de/checkout/onepage/";
	private String devCheckoutURL  = "https://dev.shirtee.de/checkout/onepage/";
	
	private String devSuccessURL = "https://dev.shirtee.de/checkout/onepage/success/";
	private String liveSuccessURL = "https://www.shirtee.de/checkout/onepage/success/";
	
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
		
		driver.get(devCmpUrl);
		
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
		
		softAssert.assertTrue(cartPage.getVorNameIsEmptyMessage().isDisplayed());
		softAssert.assertTrue(cartPage.getNachNameIsEmptyMessage().isDisplayed());
		softAssert.assertTrue(cartPage.geteMailIsEmptyMessage().isDisplayed());
		softAssert.assertTrue(cartPage.getAddressIsEmptyMessage().isDisplayed());
		softAssert.assertTrue(cartPage.getPostcodeIsEmptyMessage().isDisplayed());
		softAssert.assertTrue(cartPage.getCityIsEmptyMessage().isDisplayed());
		
		//if order succeed - success page is displayed
		//if not - redirect to cart page
		//driver.get("https://www.shirtee.de/checkout/onepage/success/");
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();

	}
	
	@Test(dataProvider = "vornameTestData")
	public void vornameTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city){
		
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(devCmpUrl);
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(devCheckoutURL);
		
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
		
		driver.get(devSuccessURL);
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "nachnameTestData")
	public void nachnameTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(devCmpUrl);
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(devCheckoutURL);
		
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
		
		driver.get(devSuccessURL);
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "emailTestData")
	public void eMailTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		//driver.get("https://www.shirtee.de/testautocampaign2");
		driver.get(devCmpUrl);
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(devCheckoutURL);
		
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
		
		driver.get(devSuccessURL);
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "addressTestData")
	public void addressTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(devCmpUrl);
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get(devCheckoutURL);
		
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
		
		driver.get(devSuccessURL);
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "postcodeTestData")
	public void postcodeTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(devCmpUrl);
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		driver.get(devCheckoutURL);
		
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
		
		driver.get(devSuccessURL);
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
	@Test(dataProvider = "cityTestData")
	public void cityTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(devCmpUrl);
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();
		
		driver.get(devCheckoutURL);
		
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
		
		driver.get(devSuccessURL);
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}

	@Test(dataProvider = "phoneTestData", enabled = false)//disabled because field is set off
	public void phoneTest(String vorname, String nachname, String eMail, String address, 
			String postalCode, String city, String phoneNum)
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get("https://www.shirtee.de/testautocampaign2");
		driver.manage().window().maximize();
		
		ProductPage productPage = new ProductPage(driver);
		
		productPage.getSize();
		productPage.buy();

		driver.get("https://www.shirtee.de/checkout/onepage/");
		
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

		
		driver.get("https://www.shirtee.de/checkout/onepage/success/");
		softAssert.assertTrue(cartPage.getSubmitButton().isDisplayed());
		
		softAssert.assertAll();
	}
	
}
