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
	
	private static final BigDecimal ORDERPROFIT = new BigDecimal("21");
	private static final  String CUSTOMERNAME = "Herr Test Account";
	
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
		
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin(eMail, userPassword);
		
		//driver.get(System.getProperty("dashboardURL"));
		DashboardPage dashboardMainPage = new DashboardPage(driver);

		dashboardMainPage.waitForDataToShow();
		
		ParseHelper parseHelper = new ParseHelper();

		initVerkaufteProducteSold = parseHelper.stringToIntArrayBySplit(dashboardMainPage.
				getVerkaufteProducte().getText(),"/")[0];
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
		Assert.assertEquals(adminOrderPage.getCustomerName(), CUSTOMERNAME);
		adminOrderPage.invoiceButtonClick();
		
		AdminInvoicePage invoicePage = new  AdminInvoicePage(driver);
		invoicePage.checkShipmentCheckbox();
		invoicePage.submitOrder();
		
		driver.get(System.getProperty("dashboardURL"));
		
		dashboardMainPage.waitForDataToShow();
		
		softAssertion.assertEquals(parseHelper.stringToIntArrayBySplit(
				dashboardMainPage.getVerkaufteProducte().getText(), "/")[0],initVerkaufteProducteSold + 1);
		softAssertion.assertEquals(parseHelper.stringToIntArrayBySplit(
				dashboardMainPage.getVerkaufteProducte().getText(), "/")[1],initVerkaufteProducteProduction);
		softAssertion.assertEquals(parseHelper.profitStringToBigDecimal(
				dashboardMainPage.getGesamtgewinn().getText()), initGesamtGewinn.add(ORDERPROFIT));
		softAssertion.assertEquals(parseHelper.profitStringToBigDecimal(
				dashboardMainPage.getGewinnVerfugbar().getText()), initGewinnVerfugbar.add(ORDERPROFIT));
		softAssertion.assertEquals(parseHelper.profitStringToBigDecimal(
				dashboardMainPage.getGewinnAusstehend().getText()), initGewinnAusstehend);

		softAssertion.assertEquals(parseHelper.stringToInt(
				dashboardMainPage.getVerkaufeSoldItems().getText()),  initVerkaufeSold + 1);
		softAssertion.assertEquals(parseHelper.stringToInt(
				dashboardMainPage.getVerkaufeProductionItems().getText()),  initVerkaufeProduction);
		
		//TODO: VerkHeute assert
		//VerkHeute assert - doesn't work now. Blocked by a bug
	
		softAssertion.assertEquals(parseHelper.stringToInt(
				dashboardMainPage.getVerkGesternSoldItems().getText()),  initVerkGesternSold);
		softAssertion.assertEquals(parseHelper.stringToInt(
				dashboardMainPage.getVerkGesternProductionItems().getText()),  initVerkGesternProduction);
		softAssertion.assertEquals(parseHelper.profitStringToBigDecimal(
				dashboardMainPage.getAktuellerGewinn().getText()),  initAktuellerGewinn.add(ORDERPROFIT));
		
		softAssertion.assertAll();
		
	}
	
	@Test(enabled=true)
	public void walletSaldoTest()
	{
		ParseHelper parseHelper = new ParseHelper();
		

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
		Assert.assertEquals(adminOrderPage.getCustomerName(), CUSTOMERNAME);
		adminOrderPage.invoiceButtonClick();
		
		AdminInvoicePage invoicePage = new  AdminInvoicePage(driver);
		invoicePage.checkShipmentCheckbox();
		invoicePage.submitOrder();
		
		driver.get(System.getProperty("walletUrl"));
		System.out.println(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()));
		
		System.out.println(initialSaldo.add(ORDERPROFIT));
		Assert.assertEquals(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()),
				initialSaldo.add(ORDERPROFIT));
	}
	

	@Test(enabled=true)
	public void orderCancelTest()
	{
		ParseHelper parseHelper = new ParseHelper();
		
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
		Assert.assertEquals(adminOrderPage.getCustomerName(), CUSTOMERNAME);
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
	
	@Test(enabled=true)
	public void twoProductsInOrderTest() //Only profit for 1st product should count
	{
	
		ParseHelper parseHelper = new ParseHelper();

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
		Assert.assertEquals(adminOrderPage.getCustomerName(), CUSTOMERNAME);
		adminOrderPage.invoiceButtonClick();
		
		AdminInvoicePage invoicePage = new  AdminInvoicePage(driver);
		invoicePage.checkShipmentCheckbox();
		invoicePage.submitOrder();
		
		driver.get(System.getProperty("walletUrl"));
		System.out.println(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()));
		
		System.out.println(initialSaldo.add(ORDERPROFIT));
		Assert.assertEquals(parseHelper.profitStringToBigDecimal(walletMainPage.getSaldo()),
				initialSaldo.add(ORDERPROFIT));
		
	}
	
	@Test(enabled=true)
	public void zeroSaldoRefundTest()
	{
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
