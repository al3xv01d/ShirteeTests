package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardCreateShopPage extends PageObject{

	@FindBy(id = "url_key")
	private WebElement urlInputField;
	
	@FindBy(id = "submit_button")
	private WebElement submitButton;
	
	@FindBy(className = "fr-element")
	private WebElement descriptionInputField;
	
	@FindBy(id = "sales_name")
	private WebElement shopNameInputField;
	
	private final static String SHOP_LOADER_LOCATOR = "//*[@id='new_shop-loading']"
			+ "[contains(@style, 'display: none')]";
	
	public DashboardCreateShopPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void waitForAlert()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (TimeoutException e) {
			// TODO: handle exception
		}
		
	}
	
	public void waitForShopCreate()
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			wait.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath(SHOP_LOADER_LOCATOR)));
		} catch (TimeoutException e) {
			// TODO: handle exception
		}
	}
	
	public void submit()
	{
		submitButton.click();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 25);
			wait.until(ExpectedConditions.
					presenceOfElementLocated(By.xpath("//div[@class='shop-edit-actions']/button[@disabled = null]")));
		} catch (TimeoutException e) {
			// TODO: handle exception
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
