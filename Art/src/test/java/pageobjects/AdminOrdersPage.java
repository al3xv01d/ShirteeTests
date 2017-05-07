package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminOrdersPage extends PageObject{

	@FindBy(xpath="//*[@id='sales_order_grid_table']/tbody/tr[1]/td[5]")
	private WebElement firstOrderRow;
	

	public AdminOrdersPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void orderRowClick()
	{
		firstOrderRow.click();
	}
	

	
}
