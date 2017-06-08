package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DashboardCreateShopPage extends PageObject{

	@FindBy(id = "url_key")
	private WebElement urlInputField;
	
	@FindBy(id = "submit_button")
	private WebElement submitButton;
	
	@FindBy(className = "fr-element")
	private WebElement descriptionInputField;
	
	@FindBy(id = "sales_name")
	private WebElement shopNameInputField;
	
	@FindBy(id = "advice-required-entry-sales_name")
	private WebElement noTitleMsg;
	
	@FindBy(id = "advice-required-entry-url_key")
	private WebElement noURLMsg;
	
	private final static String SHOP_LOADER_LOCATOR_ID = "new_shop-loading";
	
	public DashboardCreateShopPage(WebDriver driver)
	{
		super(driver);
	}
	
	public WebElement getSubmitButton() {
		return submitButton;
	}
	
	public String getNoTitleMsg()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(noTitleMsg));
		} catch (TimeoutException e) {
			Assert.fail("No title msg timeout exception!:" + e);
		}
		
		return noTitleMsg.getText();
	}
	
	public String getNoUrlMsg()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOf(noURLMsg));
		} catch (TimeoutException e) {
			Assert.fail("No URL msg timeout exception!:" + e);
		}
		
		return noTitleMsg.getText();
	}
	
	public void waitForAlert()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (TimeoutException e) {
			Assert.fail("Alert timeout exception!:" + e);
		}
		
	}
	
	public void waitForShopCreation()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(SHOP_LOADER_LOCATOR_ID)));
		} catch (TimeoutException e) {
			Assert.fail("PopUp timeout exception!:" + e);
		}
	}
	
	public void submit()
	{
		getSubmitButton().click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(new ExpectedCondition<Boolean>() {
			    public Boolean apply(WebDriver driver) {
			        String enabled = getSubmitButton().getAttribute("disabled");
			        if(enabled == null) 
			            return true;
			        else
			            return false;
			    }
			});
		} catch (TimeoutException e) {
			Assert.fail("Button status change timeout!:" + e);
		}
		catch (UnhandledAlertException e){
			Assert.fail("Unhandled alert appeared!:" + e);
		}
	}
	
	public void enterDescription(String str)
	{
		descriptionInputField.clear();
		descriptionInputField.sendKeys(str);
	}
	
	public void enterShopTitle(String str)
	{
		shopNameInputField.clear();
		shopNameInputField.sendKeys(str);
	}
	
	public void enterShopUrl(String str)
	{
		urlInputField.clear();
		urlInputField.sendKeys(str);
	}
	
}
