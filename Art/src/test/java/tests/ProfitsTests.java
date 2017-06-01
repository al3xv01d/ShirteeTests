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
import pageobjects.WalletDebitPage;
import pageobjects.WalletMainPage;

import java.math.BigDecimal;
import java.security.cert.CertificateRevokedException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.testng.annotations.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Util.ParseHelper;
import Util.ReadDataFromFile;

public class ProfitsTests extends FunctionalTest{

	private String eMail;
	private String userPassword;
	
	private String adminUser;
	private String adminPassword;
	
	private String zeroEmail;
	private String zeroPassword;
	
	private int initVerkaufteProducteSold;
	private int initVerkaufteProducteProduction;
	private BigDecimal initGesamtGewinn;
	private BigDecimal initGewinnVerfugbar;
	private BigDecimal initGewinnAusstehend;
	
	private int initVerkaufeSold;
	private int initVerkaufeProduction;
	
	private int initVerkHeuteSold;
	private int initVerkHeuteProduction;
	
	private int initVerkGesternSold;
	private int initVerkGesternProduction;
	
	private BigDecimal initAktuellerGewinn;
	
	private BigDecimal orderProfit;
	private String customerName = "Herr Test Account";
	
	public ProfitsTests()
	{
		ReadDataFromFile data = new ReadDataFromFile("/home/dglazov/data.properties");
		
		eMail = data.getPropertie("eMail");
		userPassword = data.getPropertie("userPassword");
		adminUser = data.getPropertie("adminUser");
		adminPassword = data.getPropertie("adminPassword");
		
		zeroEmail = data.getPropertie("zeroEmail");
		zeroPassword = data.getPropertie("zeroPassword");
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
		
		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		//driver.get(System.getProperty("dashboardURL"));
		DashboardPage dashboardMainPage = new DashboardPage(driver);

		dashboardMainPage.waitForDataToShow();
		
		ParseHelper parseHelper = new ParseHelper();
		
		//verkaufteProducteSold = dashboardMainPage.getVerkaufteProducteOld()[0];
		initVerkaufteProducteSold = parseHelper.stringToIntArrayBySplit(dashboardMainPage.
				getVerkaufteProducte().getText(),"/")[0];
		//verkaufteProducteProduction = dashboardMainPage.getVerkaufteProducteOld()[1];
		initVerkaufteProducteProduction = parseHelper.stringToIntArrayBySplit(dashboardMainPage.
				getVerkaufteProducte().getText(), "/")[1];
		
		initGesamtGewinn = parseHelper.profitStringToBigDecimal(dashboardMainPage.getGesamtgewinn().getText());
		initGewinnVerfugbar = parseHelper.profitStringToBigDecimal(dashboardMainPage.getGewinnVerfugbar().getText());
		initGewinnAusstehend = parseHelper.profitStringToBigDecimal(dashboardMainPage.getGewinnAusstehend().getText());
		
		initVerkaufeSold = parseHelper.stringToInt(dashboardMainPage.getVerkaufeSoldItems().getText());
		initVerkaufeProduction = parseHelper.stringToInt(dashboardMainPage.getVerkaufeProductionItems().getText());
		
		initVerkHeuteSold = parseHelper.stringToInt(dashboardMainPage.getVerkHeuteSoldItems().getText());
		initVerkHeuteProduction = parseHelper.stringToInt(dashboardMainPage.getVerkHeuteProductionItems().getText());
		
		initVerkGesternSold = parseHelper.stringToInt(dashboardMainPage.getVerkGesternSoldItems().getText());
		initVerkGesternProduction = parseHelper.stringToInt(dashboardMainPage.getVerkGesternProductionItems().getText());
		
		initAktuellerGewinn = parseHelper.profitStringToBigDecimal(dashboardMainPage.getAktuellerGewinn().getText());
		
		System.out.println(dashboardMainPage.getGesamtgewinnOld());
		System.out.println(dashboardMainPage.getGewinnVerfugbarOld());
		System.out.println(dashboardMainPage.getGewinnAusstehendOld());
		
		System.out.println("/////////////////////////////////");
		System.out.println(dashboardMainPage.getVerkaufeSoldOld());
		System.out.println(dashboardMainPage.getVerkaufeProductionOld());
		System.out.println(dashboardMainPage.getVerkHeuteSoldOld());
		System.out.println(dashboardMainPage.getVerkHeuteProductionOld());
		System.out.println(dashboardMainPage.getVerkGesternSoldOld());
		System.out.println(dashboardMainPage.getVerkGesternProductionOld());
		System.out.println(dashboardMainPage.getAktuellerGewinnOld());
		
		
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
		System.out.println(dashboardMainPage.getGesamtgewinnOld());
		System.out.println(dashboardMainPage.getGewinnVerfugbarOld());
		System.out.println(dashboardMainPage.getGewinnAusstehendOld());
		
		System.out.println("/////////////////////////////////");
		System.out.println(dashboardMainPage.getVerkaufeSoldOld());
		System.out.println(dashboardMainPage.getVerkaufeProductionOld());
		System.out.println(dashboardMainPage.getVerkHeuteSoldOld());
		System.out.println(dashboardMainPage.getVerkHeuteProductionOld());
		System.out.println(dashboardMainPage.getVerkGesternSoldOld());
		System.out.println(dashboardMainPage.getVerkGesternProductionOld());
		System.out.println(dashboardMainPage.getAktuellerGewinnOld());
		
		
		softAssertion.assertEquals(dashboardMainPage.getVerkaufteProducteOld()[0], initVerkaufteProducteSold + 1);
		softAssertion.assertEquals(dashboardMainPage.getVerkaufteProducteOld()[1], initVerkaufteProducteProduction);
		softAssertion.assertEquals(dashboardMainPage.getGesamtgewinnOld(), initGesamtGewinn.add(orderProfit));
		softAssertion.assertEquals(dashboardMainPage.getGewinnVerfugbarOld(), initGewinnVerfugbar.add(orderProfit));
		softAssertion.assertEquals(dashboardMainPage.getGewinnAusstehendOld(), initGewinnAusstehend);
		
		softAssertion.assertEquals(dashboardMainPage.getVerkaufeSoldOld(), initVerkaufeSold + 1);
		softAssertion.assertEquals(dashboardMainPage.getVerkaufeProductionOld(), initVerkaufeProduction);
		
		//TODO: VerkHeute assert
		//VerkHeute assert - doesn't work now. Blocked by a bug
		
		softAssertion.assertEquals(dashboardMainPage.getVerkGesternSoldOld(), initVerkGesternSold);
		softAssertion.assertEquals(dashboardMainPage.getVerkGesternProductionOld(), initVerkGesternProduction);
		
		softAssertion.assertEquals(dashboardMainPage.getAktuellerGewinnOld(), initAktuellerGewinn.add(orderProfit));
		
		softAssertion.assertAll();
		
	}
	
	@Test(enabled=false)
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
	

	@Test(enabled=false)
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
	
	@Test(enabled=false)
	public void twoProductsInOrderTest() //Only profit for 1st product should count
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
	
	@Test(enabled=false)
	public void zeroSaldoRefundTest()
	{
		driver.get(System.getProperty("mainPageURL"));
		driver.manage().window().maximize();
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(zeroEmail, zeroPassword);
		driver.get(System.getProperty("walletUrl"));
		
		WalletMainPage walletMainPage = new WalletMainPage(driver);
		
		WalletDebitPage debitPage = walletMainPage.goToDebitPage();
		debitPage.requestRefundBtnClick();

		if (debitPage.isAlertPresent()) {
			Alert alert = driver.switchTo().alert();
			
			Assert.assertEquals(alert.getText(), "On your balance sheet zero");
			alert.dismiss();
		}
		
		else { Assert.fail("No alert was displayed!"); }
		
	}
	
}
