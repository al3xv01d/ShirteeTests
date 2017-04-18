package pageobjects;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DesignerPageStep_3 extends PageObject{

	
	@FindBy(id="sales_name")
	private WebElement campaignTitle;
	
	@FindBy(id="sales_url")
	private WebElement campaignURL;
	
	@FindBy(id = "pd_sales")
	private WebElement submitButton;
	
	@FindBy(xpath = "//*[@id='result_block_save_campaign']/div")
	private WebElement resultMsg;
	
	private String str;
	
	public DesignerPageStep_3(WebDriver driver)
	{
		super(driver);
	}
	

	public void waitForTitle()
	{
		waitForElement(campaignTitle);
	}
	
	public void sendInfoOn3rdStep()
	{
		sendKeys(campaignTitle,"title");
		str = new String("x") + RandomStringUtils.randomAlphanumeric(8).toLowerCase(); // Is it unique every time?
		sendKeys(campaignURL, str);
	}
	
	public DashboardPage submitCampain()
	{
		submitButton.click();
		waitForElement(resultMsg);
		return new DashboardPage(driver);
	}
}
