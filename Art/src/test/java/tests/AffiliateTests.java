package tests;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Util.DatabaseHelper;
import Util.ParseHelper;
import Util.ReadDataFromFile;
import base.FunctionalTest;
import pageobjects.AdminInvoicePage;
import pageobjects.AdminMainPage;
import pageobjects.AdminOrderPage;
import pageobjects.AdminOrdersPage;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock2;
import pageobjects.MainPage;
import pageobjects.ProductPage;
import pageobjects.WalletMainPage;

public class AffiliateTests extends FunctionalTest{

	private String sharerMail1;
	private String sharerMail2;
	private String sharerPass;
	
	private String joinerMail1;
	private String joinerMail2;
	private String joinerPass;
	
	private String currentSharerMail;
	private String currentSharerPass;
	
	private String currentJoinerMail;
	private String currentJoinerPass;
	
	private String sharerLvlProfit;
	private String joinerLvlProfit;
	
	private static final String SHARER_PROFIT_ERROR = "Sharer profit is not as expected!";
	private static final String JOINER_PROFIT_ERROR = "Joiner profit is not as expected!";
	private static final String ORDER_NAME_ERROR = "Order accountee name is not as expected!";

	
	public  AffiliateTests() {
		
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		
		 sharerMail1 = data.getPropertie("sharerMail1");
		 sharerMail2 = data.getPropertie("sharerMail2");
		 currentSharerPass = data.getPropertie("sharerPass");
		
		 joinerMail1 = data.getPropertie("joiner1Mail");
		 joinerMail2 = data.getPropertie("joiner2Mail");
		 currentJoinerPass = data.getPropertie("joinerPass");
 
	}
	
	@DataProvider
	public Object[][] affiliateTestData()
	{
		return new Object[][] {
			new Object[] {"Sharer1", "Joiner1", 1, 1, System.getProperty("JoinerCmpURL1")},
			new Object[] {"Sharer2", "Joiner2", 2, 2, System.getProperty("JoinerCmpURL2")},
			
		};
	}
	
	
	public void makePurchase(String...campaignURL )
	{
		for (String str : campaignURL )
		{
			
			driver.get(str);
			ProductPage productPage = new ProductPage(driver);
		
			productPage.getSize();
			productPage.buy();
			productPage.waitForPopup();
			productPage.gotoCart();
		
		}
		
		CheckoutPage cartPage1 = new CheckoutPage(driver);
		
		CheckoutPageBlock2 cartPage2 = new CheckoutPageBlock2(driver);
		cartPage2.checkVorkrasse();
		cartPage2.waitForVorkrasseInfo();
		cartPage1.submitOrder();
		Assert.assertTrue(cartPage1.getSubmitButton().isDisplayed());
		
		driver.get(System.getProperty("successPageURL"));
	}
	
	@Test(dataProvider = "affiliateTestData", enabled=false)
	public void affiliateProfitsTest(String sharer, String joiner, int sharerLvl, int joinerLvl, String joinerURL) throws SQLException
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		DatabaseHelper databaseHelper = new DatabaseHelper("/home/dglazov/data.properties");
		
		String joinURL = joinerURL;

		String customerName = null;
		//currentJoinerPass = data.getPropertie("joinerPass");
		String adminUser = data.getPropertie("adminUser");
		String adminPassword = data.getPropertie("adminPassword");
		
		SoftAssert softAssert = new SoftAssert();
		
		sharerLvlProfit = databaseHelper.getAffiliateByType(Util.DatabaseHelper.AffiliateType.SharerFirstLvl);
		joinerLvlProfit = databaseHelper.getAffiliateByType(Util.DatabaseHelper.AffiliateType.JoinerFirstLvl);
		
		switch (sharer) {
		case "Sharer1":
			currentSharerMail = data.getPropertie("sharerMail1");			
			break;
			
		case "Sharer2":
			currentSharerMail = data.getPropertie("sharerMail2");
		default:
			break;
		}
		
		switch (joiner) {
		case "Joiner1":
			currentJoinerMail = data.getPropertie("joiner1Mail");
			customerName = "Herr joiner2 joiner2";
			break;
			
		case "Joiner2":
			currentJoinerMail = data.getPropertie("joiner2Mail");
			customerName = "Herr joiner3 joiner3";
		default:
			break;
		}
		
		if (sharerLvl == 2) {
			sharerLvlProfit = databaseHelper.getAffiliateByType(Util.DatabaseHelper.AffiliateType.SharerSecondLvl);
		}
		
		if (joinerLvl == 2) {
			joinerLvlProfit = databaseHelper.getAffiliateByType(Util.DatabaseHelper.AffiliateType.JoinerSecondLvl);
		}
		
		BigDecimal orderProfit = new BigDecimal("13.95");
		BigDecimal joinerProfit = new BigDecimal(joinerLvlProfit);
		BigDecimal sharerProfit = new BigDecimal(sharerLvlProfit);
		
		ParseHelper parseHelper = new ParseHelper();
		
		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);

		//Check Joiner Saldo
		mainPage.performLogin(currentJoinerMail, currentJoinerPass);
		driver.get(System.getProperty("walletUrl"));
		WalletMainPage walletMainPage = new WalletMainPage(driver);
		
		BigDecimal initialJoinerSaldo = parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo());
		System.out.println(initialJoinerSaldo);
		
		makePurchase(joinURL);

		//Check Sharer Saldo
		driver.get(System.getProperty("mainPageURL"));
		
		mainPage.logOut();
		mainPage.performLogin(currentSharerMail, currentSharerPass);
		driver.get(System.getProperty("walletUrl"));
		
		BigDecimal initialSharerSaldo = parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo());
		System.out.println(initialSharerSaldo);
		
		//Make purchase
		driver.get(System.getProperty("ffadminURL"));
		
		AdminMainPage adminPage = new AdminMainPage(driver);
		adminPage.waitforButton();
		adminPage.sendCredentials(adminUser, adminPassword);
		adminPage.submit();
		
		driver.get(System.getProperty("ffadminOrdersURL"));
		AdminOrdersPage adminOrdersPage = new AdminOrdersPage(driver);
		
		adminOrdersPage.orderRowClick();
		AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
		Assert.assertEquals(adminOrderPage.getCustomerName(), customerName, ORDER_NAME_ERROR);
		adminOrderPage.invoiceButtonClick();
		
		AdminInvoicePage invoicePage = new  AdminInvoicePage(driver);
		invoicePage.checkShipmentCheckbox();
		invoicePage.submitOrder();
		
		driver.get(System.getProperty("walletUrl"));
		
		//Sharer profits check
		System.out.println(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()));
		
		//System.out.println(initialSharerSaldo.add(orderProfit));
		softAssert.assertEquals(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()),
				initialSharerSaldo.add(sharerProfit), SHARER_PROFIT_ERROR);
		
		driver.get(System.getProperty("mainPageURL"));
		
		mainPage.logOut();
		mainPage.performLogin(currentJoinerMail, currentJoinerPass);
		driver.get(System.getProperty("walletUrl"));
		
		softAssert.assertEquals(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()),
				initialJoinerSaldo.add(orderProfit).add(joinerProfit), JOINER_PROFIT_ERROR);
		
		softAssert.assertAll();
	}
	
	@Test(enabled=false)
	public void refundTest() throws SQLException
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		String adminUser = data.getPropertie("adminUser");
		String adminPassword = data.getPropertie("adminPassword");
		
		currentJoinerMail = data.getPropertie("joiner1Mail");
		currentSharerMail = data.getPropertie("sharerMail1");
		String customerName = "Herr joiner2 joiner2";
		
		SoftAssert softAssert = new SoftAssert();

		ParseHelper parseHelper = new ParseHelper();

		MainPage mainPage = new MainPage(driver);

		//Check Joiner Saldo
		mainPage.performLogin(currentJoinerMail, currentJoinerPass);
		driver.get(System.getProperty("walletUrl"));
		WalletMainPage walletMainPage = new WalletMainPage(driver);
		
		BigDecimal initialJoinerSaldo = parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo());
		System.out.println(initialJoinerSaldo);
		
		makePurchase(System.getProperty("JoinerCmpURL1"));

		//Check Sharer Saldo
		driver.get(System.getProperty("mainPageURL"));
		
		mainPage.logOut();
		
		mainPage.performLogin(currentSharerMail, currentSharerPass);
		driver.get(System.getProperty("walletUrl"));
		
		BigDecimal initialSharerSaldo = parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo());
		System.out.println(initialSharerSaldo);
		
		//Make purchase
		driver.get(System.getProperty("ffadminURL"));
		
		AdminMainPage adminPage = new AdminMainPage(driver);
		adminPage.waitforButton();
		adminPage.sendCredentials(adminUser, adminPassword);
		adminPage.submit();
		
		driver.get(System.getProperty("ffadminOrdersURL"));
		AdminOrdersPage adminOrdersPage = new AdminOrdersPage(driver);
		
		adminOrdersPage.orderRowClick();
		AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
		Assert.assertEquals(adminOrderPage.getCustomerName(), customerName, ORDER_NAME_ERROR);
		adminOrderPage.invoiceButtonClick();
		
		AdminInvoicePage invoicePage = new  AdminInvoicePage(driver);
		invoicePage.checkShipmentCheckbox();
		invoicePage.submitOrder();
		
		adminOrderPage.makeRefund();
		
		driver.get(System.getProperty("walletUrl"));
		
		//Sharer profits check
		System.out.println(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()));
		
		//System.out.println(initialSharerSaldo.add(orderProfit));
		softAssert.assertEquals(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()),
				initialSharerSaldo, SHARER_PROFIT_ERROR);
		
		driver.get(System.getProperty("mainPageURL"));
		
		mainPage.logOut();
		mainPage.performLogin(currentJoinerMail, currentJoinerPass);
		driver.get(System.getProperty("walletUrl"));
		System.out.println(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()));
		softAssert.assertEquals(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()),
				initialJoinerSaldo, JOINER_PROFIT_ERROR);
		
		softAssert.assertAll();
	}
}
