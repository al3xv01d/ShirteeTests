package pageobjects;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends PageObject{
	
	@FindBy(id="billing_firstname")
	private WebElement vorName;
	
	@FindBy(id="p_method_banktransfer")
	private WebElement vorkasseRadioButton;
	
	@FindBy(id="submit-btn")
	private WebElement submitButton;
	
	@FindBy(xpath="//*[@id='payment_form_banktransfer']/li/div")
	private WebElement vorkrasseInfo;


	public CartPage(WebDriver driver)
	{
		super(driver);
		assertTrue(vorName.isDisplayed());
	}

	public void checkVorkrasse()
	{
		vorkasseRadioButton.click();
	}
	
	public void waitForVorkrasseInfo()
	{
		waitForElement(vorkrasseInfo);
	}
	
	
	public void submitBtn()
	{
		submitButton.click();
	}
	
	
}
