package pageobjects;

import static org.junit.Assert.assertTrue;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
		assertTrue(vorNameInputField.isDisplayed());
	}

	//Getters
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
