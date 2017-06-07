package pageobjects;

import java.rmi.UnexpectedException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class DashboardShopsPage extends PageObject{

	@FindBy(xpath="//table[@class='new-account-table table-shops-list']/tbody/tr[2]/td[1]")
	private WebElement shopName;
	
	@FindBy(xpath="//table[@class='new-account-table table-shops-list']/tbody/tr[2]/td[2]")
	private WebElement shopURL;
	
	@FindAll({@FindBy(xpath="//input[@name='switch-shop']")})
	private List<WebElement> shopSwitchers;
	
	public DashboardShopsPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void enableShop()
	{
		if (shopSwitchers.get(1).getAttribute("checked") == null) {
			shopSwitchers.get(1).click();
		}
		else {System.out.println("unexpected exception!");}
	}
}
