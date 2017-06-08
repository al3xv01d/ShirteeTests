package pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShopPage extends PageObject{

	
	//@FindBy(xpath = "//a[@href = 'http://dev.shirtee.de/testautocampaign1']")
	@FindBy(xpath = "//a[@href = 'http://dev.shirtee.de/testautocampaign1']")
	private WebElement campaignItem1;
	
	@FindBy(xpath = "//a[@href = 'http://dev.shirtee.de/testautocampaign2']")
	private WebElement campaignItem2;
	
	@FindBy(xpath = "//div[@class = 'custom-shop-desc-title']")
	private WebElement shopTitle;
	
	@FindBy(xpath = "//div[@class = 'custom-shop-desc']/div[2]")
	private WebElement shopDescription;
	
	public ShopPage(WebDriver driver)
	{
		super(driver);
	}
	
	public WebElement getShopTitle() {
		return shopTitle;
	}

	public WebElement getShopDescription() {
		return shopDescription;
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
