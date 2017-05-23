package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WalletDebitPage extends PageObject{

	@FindBy(xpath = "//*[@id='top']/body/div[3]/div[2]/div/div/div[2]/div[1]/div/button")
	private WebElement requestRefundBtn;
	
	public WalletDebitPage(WebDriver driver)
	{
		super(driver);
	}
	
}
