package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class WalletMainPage extends PageObject{

	@FindBy(xpath = "//table[@class='new-account-table']/tbody/tr/td/span")
	private WebElement saldo;
	
	@FindBy(partialLinkText="Lastschrift")
	private WebElement debitPageLink;
	
	
	public WalletMainPage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getSaldo()
	{
		String str = saldo.getText();
		return	str;
	}

	public WalletDebitPage goToDebitPage()
	{
		debitPageLink.click();
		return new WalletDebitPage(driver);
	}
	
}
