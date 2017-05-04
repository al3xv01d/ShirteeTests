package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPageBlock3 extends PageObject{
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[3]/span/span")
	private WebElement price;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[5]/span/span")
	private WebElement summe;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[4]/strong[2]")
	private WebElement incQuantityButton;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[4]/strong[1]")
	private WebElement decQuantityButton;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[5]/td/strong[2]/span")
	//*[@id="checkout-review-table"]/tfoot/tr[5]/td/strong[2]/span
	private WebElement total;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[2]/td[2]/span")
	//*[@id="checkout-review-table"]/tfoot/tr[3]/td[2]/span
	private WebElement shipping;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[2]/td[2]/span")
	private WebElement charges;
	
	
	public CheckoutPageBlock3(WebDriver driver)
	{
		super(driver);
	}

	public String getCharges()
	{
		return charges.getText();
	}
	
	public String getShipping()
	{
		return shipping.getText();
	}
	
	public String getPrice()
	{
		return price.getText();
	}
	
	public String getSum()
	{
		return summe.getText();
	}
	
	public String getTotal()
	{
		return total.getText();
	}
	
}
