package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WalletDebitPage extends PageObject{
	
	@FindBy(className = "button")
	private WebElement requestRefundBtn;
	
	public WalletDebitPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void requestRefundBtnClick()
	{
		requestRefundBtn.click();
	}
}
