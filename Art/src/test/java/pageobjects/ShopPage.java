package pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopPage extends PageObject{

	
	@FindBy(xpath = "//a[@href = 'https://dev.shirtee.de/testautocampaign1']")
	private WebElement campaignItem1;
	
	@FindBy(xpath = "//a[@href = 'https://dev.shirtee.de/testautocampaign2']")
	private WebElement campaignItem2;
	
	public ShopPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void campaignItem1Click()
	{
		campaignItem1.click();
	}
	
	public void campaignItem2Click()
	{
		campaignItem2.click();
	}
}
