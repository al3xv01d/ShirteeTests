package pageobjects;

import java.rmi.UnexpectedException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DashboardShopsPage extends PageObject{

	@FindBy(xpath="//table[@class='new-account-table table-shops-list']/tbody/tr[2]/td[1]")
	private WebElement shopName;
	
	@FindBy(xpath="//table[@class='new-account-table table-shops-list']/tbody/tr[2]/td[2]")
	private WebElement shopURL;
	
	@FindAll({@FindBy(xpath="//td[@class = 'td-for-buttons']/div/a[1]")})
	private List<WebElement> shopDeleteBtns;
	
	@FindAll({@FindBy(xpath="//div[@class='b-label-checkbox']")})
	private List<WebElement> shopSwitchers;

	private static final By CHECKBOX_LOADER = By.xpath("//div[@class='b-label-checkbox-loader'][2]");
	
	public DashboardShopsPage(WebDriver driver)
	{
		super(driver);
	}
	
	public WebElement getShopName() {
		return shopName;
	}

	public WebElement getShopURL() {
		return shopURL;
	}
	
	public void deleteShop()
	{
		shopDeleteBtns.get(1).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		try {
			wait.until(ExpectedConditions.alertIsPresent());
		} catch (TimeoutException e) {
			Assert.fail("Alert timeout exception!" + e);
		}
		
		Alert alert = driver.switchTo().alert();
		alert.accept();		
	}
	
	public void enableShop()
	{
		shopSwitchers.get(1).click();
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(CHECKBOX_LOADER));
		} catch (TimeoutException e) {
			Assert.fail("Shop status change timeout exception!:" + e);
		}
		
	}




}
