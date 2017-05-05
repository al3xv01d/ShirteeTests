package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPageBlock3 extends PageObject{
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[3]/span/span")
	private WebElement price;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[5]/span/span")
	private WebElement summe;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[4]/strong[2]")
	private WebElement incQuantityButton;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[4]/strong[1]")
	private WebElement decQuantityButton;
	
	@FindBy(xpath="//tr[@class='last']/td[@class='a-right last']/strong[2]/span")
	private WebElement total;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[2]/td[2]/span")
	private WebElement shipping;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[3]/td[2]/span")
	private WebElement shippingKlarna;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[2]/td[2]/span")
	private WebElement charges;
	
	
	public CheckoutPageBlock3(WebDriver driver)
	{
		super(driver);
	}

	public void increaseQuantity()
	{
		incQuantityButton.click();
	}
	
	public void decreaseQuantity()
	{
		decQuantityButton.click();
	}
	
	public void waitForCalculationToFinish()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(absenceOfElementLocated(By.xpath("//div[@class = 'loadinfo']")));
	}
	
	public String getCharges()
	{
		return charges.getText();
	}
	
	public String getShipping()
	{
		return shipping.getText();
	}
	
	
	public String getShippingKlarna()
	{
		return shippingKlarna.getText();
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
