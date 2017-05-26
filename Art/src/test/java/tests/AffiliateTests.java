package tests;

import java.math.BigDecimal;
import java.sql.SQLException;

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
	
	public  AffiliateTests() {
		
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properites");
		
		 sharerMail1 = data.getPropertie("sharerMail1");
		 sharerMail2 = data.getPropertie("sharerMail2");
		 sharerPass = data.getPropertie("sharerPass");
		
		 joinerMail1 = data.getPropertie("joiner.joiner2@mail.ru");
		 joinerMail2 = data.getPropertie("joiner3.joiner3@mail.ru");
		 joinerPass = data.getPropertie("renioj555");
 
	}
	
	@DataProvider
	public Object[][] affiliateTestData()
	{
		return new Object[][] {
			new Object[] {"Sharer1", "Joiner1", 1 , 2},
			new Object[] {"Sharer2", "Joiner2", 1, 2},
			
		};
	}
	
//	public String getSharerMail(String sharer)
//	{
//		String mail;
//		
//		switch (sharer) {
//		case "Sharer1":
//			currentSharerMail = data.getPropertie("sharerMail1");
//			break;
//			
//		case "Sharer2":
//			currentSharerMail = data.getPropertie("sharerMail2");
//
//		default:
//			break;
//		}
//	}
	
	public void getSharerLvl(int sharerLvl)
	{
		switch (sharerLvl) {
		case 1:
			
			break;

		default:
			break;
		}
	}
	
	public void makePurchase(String...campaignURL )
	{
		for (String str : campaignURL )
		{
			
			driver.get(System.getProperty(str));
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
	
	@Test(dataProvider = "affiliateTestData")
	public void affiliateProfitsTest(String sharer, String joiner, int sharerLvl, int joinerLvl) throws SQLException
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properites");
		DatabaseHelper databaseHelper = new DatabaseHelper("/home/dglazov/data.properties");
		
		String customerName = null;
		
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
			currentJoinerMail = data.getPropertie("joiner.joiner2@mail.ru");
			customerName = "Herr Joiner2 Joiner2";
			break;

		case "Joiner2":
			currentJoinerMail = data.getPropertie("joiner3.joiner3@mail.ru");
			customerName = "Herr Joiner3 Joiner3";
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
		
		ParseHelper parseHelper = new ParseHelper();
		
		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(currentJoinerMail, currentJoinerPass);
		
		driver.get(System.getProperty("walletUrl"));
		
		WalletMainPage walletMainPage = new WalletMainPage(driver);
		
		BigDecimal initialSaldo = parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo());
		System.out.println(initialSaldo);
		
		makePurchase("campaignURL1");

		driver.get(System.getProperty("ffadminURL"));
		
		AdminMainPage adminPage = new AdminMainPage(driver);
		adminPage.waitforButton();
		adminPage.sendCredentials(adminUser, adminPassword);
		adminPage.submit();
		
		driver.get(System.getProperty("ffadminOrdersURL"));
		AdminOrdersPage adminOrdersPage = new AdminOrdersPage(driver);
		
		adminOrdersPage.orderRowClick();
		AdminOrderPage adminOrderPage = new AdminOrderPage(driver);
		Assert.assertEquals(adminOrderPage.getCustomerName(), customerName);
		adminOrderPage.invoiceButtonClick();
		
		AdminInvoicePage invoicePage = new  AdminInvoicePage(driver);
		invoicePage.checkShipmentCheckbox();
		invoicePage.submitOrder();
		
		driver.get(System.getProperty("walletUrl"));
		System.out.println(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()));
		
		System.out.println(initialSaldo.add(orderProfit));
		Assert.assertEquals(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()),
				initialSaldo.add(orderProfit));
		
		softAssert.assertAll();
	}
	
	@Test
	public void affiliateChangeTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		
		
		
		softAssert.assertAll();
	}
}
