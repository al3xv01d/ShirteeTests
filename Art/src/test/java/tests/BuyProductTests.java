package tests;

//import org.junit.Test;
import org.testng.annotations.Test;

import Util.ReadDataFromFile;
import base.FunctionalTest;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock2;
import pageobjects.DashboardBestellungen;
import pageobjects.DashboardPage;
import pageobjects.MainPage;
import pageobjects.OrderSuccessPage;
import pageobjects.ProductPage;
//import static org.junit.Assert.assertEquals;
import org.testng.Assert;

public class BuyProductTests extends FunctionalTest{
	

	@Test()
	public void orderNumTest()
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		
		String eMail = data.getPropertie("eMail");
		String userPassword = data.getPropertie("userPassword");
		String adminUser = data.getPropertie("adminUser");
		String adminPassword = data.getPropertie("adminPassword");

		MainPage mainPage = new MainPage(driver);
		//mainPage.performLogin();
		mainPage.performLogin(eMail, userPassword);
		
		driver.get("https://www.shirtee.de/testurlll");
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
		
		driver.get("https://www.shirtee.de/checkout/onepage/success/");
		OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);
		String  orderFromSuccessPage = orderSuccessPage.getOrderNum();
		
		driver.get("https://www.shirtee.de/customdashboard/order/history/");
		
		DashboardBestellungen bestellungen = new DashboardBestellungen(driver);
		String orderFromBestellungen = bestellungen.getOrderNum();
		
		Assert.assertEquals("Bestellung" + " " + "#" + orderFromSuccessPage, orderFromBestellungen);
		
	}

}
