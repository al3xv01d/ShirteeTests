package pageobjects;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends PageObject{
	
	@FindBy(xpath="//*[@id='top']/body/div[1]/div[2]/div[1]/div/div[2]/div/div[1]/div/div/span[1]")
	private WebElement ubersicht;
	
	public DashboardPage(WebDriver driver)
	{
		super(driver);
		driver.get("https://www.shirtee.de/dashboard/index/index/");
		assertTrue(ubersicht.isDisplayed());
	}

}
