package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPageBlock2 extends PageObject{

	//@FindBy(id="p_method_paypal_express")
	@FindBy(xpath="//*[@id='p_method_paypal_express']/parent::dt")
	private WebElement payPalRadioButton;
	
	@FindBy(id="payment_form_paypal_express")
	private WebElement payPalInfo;
	
	@FindBy(xpath="//*[@id='p_method_paymentnetwork_pnsofortueberweisung']/parent::dt")
	private WebElement sofortRadioButton;
	
	@FindBy(id="payment_form_paymentnetwork_pnsofortueberweisung")
	private WebElement sofortInfo;
	
	@FindBy(xpath="//*[@id='p_method_vaimo_klarna_invoice']/parent::dt")
	private WebElement klarnaRadioButton;
	
	@FindBy(id="vaimo_klarna_invoice_input_fields")
	private WebElement klarnaInfo;
	
	@FindBy(xpath="//*[@id='p_method_banktransfer']/parent::dt")
	private WebElement vorkasseRadioButton;
	
	@FindBy(xpath="//*[@id='payment_form_banktransfer']/li/div")
	private WebElement vorkrasseInfo;
	
	@FindBy(id="vaimo_klarna_invoice_phonenumber")
	private WebElement klarnaPhoneNumberBlock;
	
	//Payment block getters
	public WebElement getPayPalInfo() {
			return payPalInfo;
		}

	public WebElement getSofortInfo() {
			return sofortInfo;
		}

	public WebElement getKlarnaInfo() {
			return klarnaInfo;
		}

	public WebElement getVorkrasseInfo() {
			return vorkrasseInfo;
		}
		

	public CheckoutPageBlock2(WebDriver driver)
	{
		super(driver);
	}
	
	//2nd block checkboxes click methods
	public void checkVorkrasse()
			{
				vorkasseRadioButton.click();
			}
			
	public void checkPayPal()
			{
				getPayPalRadioButton().click();
			}
			
	public void checkSofort()
			{
				sofortRadioButton.click();
			}
			
	public void checkKlarna()
			{
				klarnaRadioButton.click();
			}
	
	public void waitForVorkrasseInfo()
	{
		waitForElement(getVorkrasseInfo());
	}
	
	public void waitForPayPalInfo()
	{
		waitForElement(getPayPalInfo());
	}
	
	public void waitForSofortInfo()
	{
		waitForElement(getSofortInfo());
	}
	
	public void waitForKlarnaInfo()
	{
		waitForElement(getKlarnaInfo());
	}
	
	public String getKlarnaPhoneNumber()
	{
		return klarnaPhoneNumberBlock.getAttribute("value");
	}

	public WebElement getPayPalRadioButton() {
		return payPalRadioButton;
	}

	
}
