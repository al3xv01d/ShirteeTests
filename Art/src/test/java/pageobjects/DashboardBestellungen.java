package pageobjects;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardBestellungen extends PageObject{

	@FindBy(xpath="//*[@id='userhistory']/div[2]/div/table/tbody/tr[1]/td[1]/span")
	private WebElement orderNumber;
	
	public DashboardBestellungen(WebDriver driver)
	{
		super(driver);
	}
	
	
	public String getOrderNum()
	{
		return orderNumber.getText();
	}
	
	
}
