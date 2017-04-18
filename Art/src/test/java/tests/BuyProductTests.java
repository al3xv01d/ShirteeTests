package tests;

//import org.junit.Test;
import org.testng.annotations.Test;

import base.FunctionalTest;
import pageobjects.CartPage;
import pageobjects.DashboardBestellungen;
import pageobjects.DashboardPage;
import pageobjects.MainPage;
import pageobjects.OrderSuccessPage;
import pageobjects.ProductPage;
//import static org.junit.Assert.assertEquals;
import org.testng.Assert;

public class BuyProductTests extends FunctionalTest{
	

	@Test(invocationCount = 1)
	public void orderNumTest()
	{
		driver.get("https://www.shirtee.de");
		driver.manage().window().maximize();
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin();
		
		
		driver.get("https://www.shirtee.de/testurlll");
		ProductPage productPage = new ProductPage(driver);
		productPage.getSize();
		productPage.buy();
		productPage.waitForPopup();
		productPage.gotoCart();
		
		CartPage cartPage = new CartPage(driver);
		cartPage.checkVorkrasse();
		cartPage.waitForVorkrasseInfo();
		cartPage.submitBtn();
		
		driver.get("https://www.shirtee.de/checkout/onepage/success/");
		OrderSuccessPage orderSuccessPage = new OrderSuccessPage(driver);
		String  orderFromSuccessPage = orderSuccessPage.getOrderNum();
		
		driver.get("https://www.shirtee.de/customdashboard/order/history/");
		
		DashboardBestellungen bestellungen = new DashboardBestellungen(driver);
		String orderFromBestellungen = bestellungen.getOrderNum();
		
		Assert.assertEquals("Bestellung" + " " + "#" + orderFromSuccessPage, orderFromBestellungen);
		
	}

}
