package pageobjects;

import static org.junit.Assert.assertTrue;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends PageObject{
	
	@FindBy(id="submit-btn")
	private WebElement submitButton;
	
	//1st Block
	@FindBy(id="billing_firstname")
	private WebElement vorNameInputField;
	
	@FindBy(id="billing_lastname")
	private WebElement nachNameInputField;
	
	@FindBy(id="billing_email")
	private WebElement eMailInputField;
	
	@FindBy(id="billing_street1")
	private WebElement addressInputField;
	
	@FindBy(id="billing_postcode")
	private WebElement postCodeInputField;

	@FindBy(id="billing_city")
	private WebElement cityInputField;
	
	@FindBy(id="billing_telephone")
	private WebElement phoneInputField;
	
	// 1st block Validation Messages
	//Empty field messages
	@FindBy(id="advice-required-entry-billing_firstname")
	private WebElement vorNameIsEmptyMessage;
	
	@FindBy(id="advice-required-entry-billing_lastname")
	private WebElement nachNameIsEmptyMessage;
	
	@FindBy(id="advice-required-entry-billing_email")
	private WebElement eMailIsEmptyMessage;
	
	@FindBy(id="advice-required-entry-billing_street1")
	private WebElement addressIsEmptyMessage;
	
	@FindBy(id="advice-required-entry-billing_postcode")
	private WebElement postcodeIsEmptyMessage;
	
	@FindBy(id="advice-required-entry-billing_city")
	private WebElement cityIsEmptyMessage;
	
	@FindBy(id="advice-validate-one-required-by-name-p_method_paypal_express")
	private WebElement paymentOptionRequiredMsg;
	
	//incorrect input messages 
	@FindBy(id="advice-validate-name-billing_firstname")
	private WebElement vorNameIsIncorrectMessage;
	
	@FindBy(id="advice-validate-name-billing_lastname")
	private WebElement nachNameIsIncorrectMessage;
	
	@FindBy(id="advice-validate-email-billing_email")
	private WebElement eMailIsIncorrectMessage;
	
	@FindBy(id="advice-validate-address-billing_street1")
	private WebElement addressIsIncorrectMessage;
	
	@FindBy(id="advice-billing_postcode-billing_postcode")
	private WebElement postcodeIsIncorrectMessage;
	
	@FindBy(id="advice-billing_city-billing_city")
	private WebElement cityIsIncorrectMessage;
	
	@FindBy(id="advice-billing_telephone-billing_telephone")
	private WebElement phoneIsIncorrectMessage;
	
	//Packstation Block
	@FindBy(xpath = "//*[@id='billing_tabs_packstation_tabbed']/a")
	private WebElement packstationTab;
	
	@FindBy(xpath = "//*[@id='billing_tabs_packstation_tabbed_contents']/li[5]/div/a")
	private WebElement zumPackstationLink;
	
	@FindBy(id = "billing_dhl_pack")
	private WebElement postNumberInput;
	
	@FindBy(id = "advice-required-entry-billing_dhl_pack")
	private WebElement postNumberEmptyInputMsg;
	
	@FindBy(id = "billing_pack")
	private WebElement packstationNumberInput;
	
	@FindBy(id = "advice-required-entry-billing_pack")
	private WebElement packstationNumberEmptyInputMsg;
	
	//RadioButtons
	@FindBy(xpath = "//*[@id='billing_select-company-gender']/div[3]/label")
	private WebElement firmaRadioButton;
	
	@FindBy(xpath = "//*[@id='billing_select-company-gender']/div[1]/label")
	private WebElement herrRadioButton;
	
	@FindBy(xpath = "//div[@class='breadcrumbs-checkout-back']/a")
	private WebElement backLink;

	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		assertTrue(vorNameInputField.isDisplayed());
	}

	//Getters
	public WebElement getBackLink(){
		return backLink;
	}
	
	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	public WebElement getVorNameIsEmptyMessage() {
		return vorNameIsEmptyMessage;
	}

	public WebElement getNachNameIsEmptyMessage() {
		return nachNameIsEmptyMessage;
	}

	public WebElement geteMailIsEmptyMessage() {
		return eMailIsEmptyMessage;
	}

	public WebElement getAddressIsEmptyMessage() {
		return addressIsEmptyMessage;
	}

	public WebElement getPostcodeIsEmptyMessage() {
		return postcodeIsEmptyMessage;
	}

	public WebElement getCityIsEmptyMessage() {
		return cityIsEmptyMessage;
	}
	
	//Incorrect Message Getters
	public WebElement getVorNameIsIncorrectMessage() {
		return vorNameIsIncorrectMessage;
	}

	public WebElement getNachNameIsIncorrectMessage() {
		return nachNameIsIncorrectMessage;
	}

	public WebElement geteMailIsIncorrectMessage() {
		return eMailIsIncorrectMessage;
	}

	public WebElement getAddressIsIncorrectMessage() {
		return addressIsIncorrectMessage;
	}

	public WebElement getPostcodeIsIncorrectMessage() {
		return postcodeIsIncorrectMessage;
	}

	public WebElement getCityIsIncorrectMessage() {
		return cityIsIncorrectMessage;
	}

	public WebElement getPhoneIsIncorrectMessage() {
		return phoneIsIncorrectMessage;
	}
	
	public WebElement getPackstationTab() {
		return packstationTab;
	}
	

	public WebElement getPaymentOptionRequiredMsg() {
		return paymentOptionRequiredMsg;
	}


	public WebElement getPostNumberEmptyInputMsg() {
		return postNumberEmptyInputMsg;
	}

	public WebElement getPackstationNumberEmptyInputMsg() {
		return packstationNumberEmptyInputMsg;
	}

	public void  herrRadioButtonClick()
	{
		herrRadioButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(absenceOfElementLocated(By.xpath("//div[@class = 'loadinfo']")));
	}
	
	public void firmaRadioButtonClick()
	{
		firmaRadioButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(absenceOfElementLocated(By.xpath("//div[@class = 'loadinfo']")));
	}
	
	public void packstationClick()
	{
		getPackstationTab().click();
	}
	
	public String getPackstationLink()
	{
		String str = zumPackstationLink.getAttribute("href");
		return str;
	}
	
	public void sendKeysVorname(String str)
	{
		vorNameInputField.clear();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Clipboard clipboard = toolkit.getSystemClipboard();
	    StringSelection strSelection = new StringSelection(str);
	    clipboard.setContents(strSelection, null);
	    
	    vorNameInputField.sendKeys(Keys.CONTROL + "v");
	    vorNameInputField.sendKeys(Keys.CLEAR);
	}
	
	public void sendKeysNachname(String str)
	{
		nachNameInputField.clear();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Clipboard clipboard = toolkit.getSystemClipboard();
	    StringSelection strSelection = new StringSelection(str);
	    clipboard.setContents(strSelection, null);
		
	    nachNameInputField.sendKeys(Keys.CONTROL + "v");
	    nachNameInputField.sendKeys(Keys.CLEAR);
	}
	
	public void sendKeysEMail(String str)
	{
		eMailInputField.clear();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Clipboard clipboard = toolkit.getSystemClipboard();
	    StringSelection strSelection = new StringSelection(str);
	    clipboard.setContents(strSelection, null);
		
	    eMailInputField.sendKeys(Keys.CONTROL + "v");
	    eMailInputField.sendKeys(Keys.CLEAR);
	}
	
	public void sendKeysAddress(String str)
	{
		addressInputField.clear();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Clipboard clipboard = toolkit.getSystemClipboard();
	    StringSelection strSelection = new StringSelection(str);
	    clipboard.setContents(strSelection, null);
		
	    addressInputField.sendKeys(Keys.CONTROL + "v");
	    addressInputField.sendKeys(Keys.CLEAR);
	}
	
	public void sendPostcode(String str)
	{
		postCodeInputField.clear();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Clipboard clipboard = toolkit.getSystemClipboard();
	    StringSelection strSelection = new StringSelection(str);
	    clipboard.setContents(strSelection, null);
		
	    postCodeInputField.sendKeys(Keys.CONTROL + "v");
	    postCodeInputField.sendKeys(Keys.CLEAR);
	}
	
	public void sendCity(String str)
	{
		cityInputField.clear();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Clipboard clipboard = toolkit.getSystemClipboard();
	    StringSelection strSelection = new StringSelection(str);
	    clipboard.setContents(strSelection, null);
		
	    cityInputField.sendKeys(Keys.CONTROL + "v");
	    cityInputField.sendKeys(Keys.CLEAR);
	}
	
	public void sendPhoneNumber(String str)
	{
		phoneInputField.clear();
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Clipboard clipboard = toolkit.getSystemClipboard();
	    StringSelection strSelection = new StringSelection(str);
	    clipboard.setContents(strSelection, null);
		
	    phoneInputField.sendKeys(Keys.CONTROL + "v");
	    phoneInputField.sendKeys(Keys.CLEAR);
	}
	
	public void submitOrder()
	{
		getSubmitButton().click();
	}

	
}
