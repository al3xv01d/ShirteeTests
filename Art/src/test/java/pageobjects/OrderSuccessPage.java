package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class OrderSuccessPage extends PageObject{
	
	@FindBy(xpath="//*[@id='top']/body/div[1]/div/div[3]/div/div/div/div[3]/a")
	private WebElement orderNumber;
	
	public OrderSuccessPage(WebDriver driver)
	{
		super(driver);
	}
	
	
	public String getOrderNum()
	{
		return orderNumber.getText();
	}

}
