package tests;

import base.FunctionalTest;
import pageobjects.AdminInvoicePage;
import pageobjects.AdminMainPage;
import pageobjects.AdminOrderPage;
import pageobjects.AdminOrdersPage;
import pageobjects.CheckoutPage;
import pageobjects.CheckoutPageBlock2;
import pageobjects.DashboardPage;
import pageobjects.MainPage;
import pageobjects.ProductPage;
import pageobjects.WalletMainPage;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Util.ParseHelper;
import Util.ReadDataFromFile;

public class ProfitsTests extends FunctionalTest{

	private String eMail;
	private String userPassword;
	
	private String adminUser;
	private String adminPassword;
	
	private int verkaufteProducteSold;
	private int verkaufteProducteProduction;
	private BigDecimal gesamtGewinn;
	private BigDecimal gewinnVerfugbar;
	private BigDecimal gewinnAusstehend;
	
	private int verkaufeSold;
	private int verkaufeProduction;
	
	private int verkHeuteSold;
	private int verkHeuteProduction;
	
	private int verkGesternSold;
	private int verkGesternProduction;
	
	private BigDecimal aktuellerGewinn;
	
	private BigDecimal orderProfit;
	private String customerName = "Herr Test Account";
	
	public ProfitsTests()
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		
		eMail = data.getPropertie("eMail");
		userPassword = data.getPropertie("userPassword");
		adminUser = data.getPropertie("adminUser");
		adminPassword = data.getPropertie("adminPassword");
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
	
	
	@Test
	public void simpleProfitTest()
	{
		SoftAssert softAssertion = new SoftAssert();
		orderProfit = new BigDecimal("21");
		
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		
		eMail = data.getPropertie("eMail");
		userPassword = data.getPropertie("userPassword");
		adminUser = data.getPropertie("adminUser");
		adminPassword = data.getPropertie("adminPassword");
		
		
		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		//driver.get(System.getProperty("dashboardURL"));
		DashboardPage dashboardMainPage = new DashboardPage(driver);

		dashboardMainPage.waitForDataToShow();
		
		ParseHelper parseHelper = new ParseHelper();

		String sg = dashboardMainPage.test();
		BigDecimal bd = parseHelper.profitStringToBigDecimal(sg);
		
		System.out.println(bd);
		
		verkaufteProducteSold = dashboardMainPage.getVerkaufteProducte()[0];
		verkaufteProducteProduction = dashboardMainPage.getVerkaufteProducte()[1];
		
		gesamtGewinn = dashboardMainPage.getGesamtgewinn();
		gewinnVerfugbar = dashboardMainPage.getGewinnVerfugbar();
		gewinnAusstehend = dashboardMainPage.getGewinnAusstehend();
		
		verkaufeSold = dashboardMainPage.getVerkaufeSold();
		verkaufeProduction = dashboardMainPage.getVerkaufeProduction();
		
		verkHeuteSold = dashboardMainPage.getVerkHeuteSold();
		verkHeuteProduction = dashboardMainPage.getVerkHeuteProduction();
		
		verkGesternSold = dashboardMainPage.getVerkGesternSold();
		verkGesternProduction = dashboardMainPage.getVerkGesternProduction();
		
		aktuellerGewinn = dashboardMainPage.getAktuellerGewinn();
		
		
		System.out.println(dashboardMainPage.getGesamtgewinn());
		System.out.println(dashboardMainPage.getGewinnVerfugbar());
		System.out.println(dashboardMainPage.getGewinnAusstehend());
		
		System.out.println("/////////////////////////////////");
		System.out.println(dashboardMainPage.getVerkaufeSold());
		System.out.println(dashboardMainPage.getVerkaufeProduction());
		System.out.println(dashboardMainPage.getVerkHeuteSold());
		System.out.println(dashboardMainPage.getVerkHeuteProduction());
		System.out.println(dashboardMainPage.getVerkGesternSold());
		System.out.println(dashboardMainPage.getVerkGesternProduction());
		System.out.println(dashboardMainPage.getAktuellerGewinn());
		
		
		makePurchase("campaignURL1");

		driver.get(System.getProperty("ffadminURL"));
		driver.manage().window().maximize();
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
		
		driver.get(System.getProperty("dashboardURL"));
		
		dashboardMainPage.waitForDataToShow();
		
		System.out.println("New Profits");
		System.out.println(dashboardMainPage.getGesamtgewinn());
		System.out.println(dashboardMainPage.getGewinnVerfugbar());
		System.out.println(dashboardMainPage.getGewinnAusstehend());
		
		System.out.println("/////////////////////////////////");
		System.out.println(dashboardMainPage.getVerkaufeSold());
		System.out.println(dashboardMainPage.getVerkaufeProduction());
		System.out.println(dashboardMainPage.getVerkHeuteSold());
		System.out.println(dashboardMainPage.getVerkHeuteProduction());
		System.out.println(dashboardMainPage.getVerkGesternSold());
		System.out.println(dashboardMainPage.getVerkGesternProduction());
		System.out.println(dashboardMainPage.getAktuellerGewinn());
		
		
		softAssertion.assertEquals(dashboardMainPage.getVerkaufteProducte()[0], verkaufteProducteSold + 1);
		softAssertion.assertEquals(dashboardMainPage.getVerkaufteProducte()[1], verkaufteProducteProduction);
		softAssertion.assertEquals(dashboardMainPage.getGesamtgewinn(), gesamtGewinn.add(orderProfit));
		softAssertion.assertEquals(dashboardMainPage.getGewinnVerfugbar(), gewinnVerfugbar.add(orderProfit));
		softAssertion.assertEquals(dashboardMainPage.getGewinnAusstehend(), gewinnAusstehend);
		
		softAssertion.assertEquals(dashboardMainPage.getVerkaufeSold(), verkaufeSold + 1);
		softAssertion.assertEquals(dashboardMainPage.getVerkaufeProduction(), verkaufeProduction);
		
		//TODO: VerkHeute assert
		//VerkHeute assert - doesn't work now. Blocked by a bug
		
		softAssertion.assertEquals(dashboardMainPage.getVerkGesternSold(), verkGesternSold);
		softAssertion.assertEquals(dashboardMainPage.getVerkGesternProduction(), verkGesternProduction);
		
		softAssertion.assertEquals(dashboardMainPage.getAktuellerGewinn(), aktuellerGewinn.add(orderProfit));
		
		softAssertion.assertAll();
		
	}
	
	@Test
	public void walletSaldoTest()
	{
		orderProfit = new BigDecimal("21");

		ParseHelper parseHelper = new ParseHelper();
		
		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
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
	}
	
	@Test
	public void orderCancelTest()
	{
		ParseHelper parseHelper = new ParseHelper();
		
		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
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
		adminOrderPage.cancelButtonClick();
		
		if (adminOrderPage.isAlertPresent())
		{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		
		driver.get(System.getProperty("walletUrl"));

		Assert.assertEquals(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()),
				initialSaldo);
	}
	
	@Test
	public void twoProductsInOrderTest()
	{
		orderProfit = new BigDecimal("21");
		
		ParseHelper parseHelper = new ParseHelper();
		
		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		driver.get(System.getProperty("walletUrl"));
		
		WalletMainPage walletMainPage = new WalletMainPage(driver);
		
		BigDecimal initialSaldo = parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo());
		System.out.println(initialSaldo);
		
		makePurchase("campaignURL1", "campaignURL3");


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
		
	}
	
}
