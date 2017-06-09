package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CheckoutPageBlock3 extends PageObject{
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr[1]/td/div/div[4]/div[1]/span/span")
	//*[@id="checkout-review-table"]/tbody/tr[1]/td/div/div[4]/div[1]/span/span
	private WebElement price;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td/div/div[4]/div[2]/span/span")
	private WebElement summe;
	
	//@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[4]/strong[2]")
	@FindBy(xpath="//div[@class='checkout-product-qty']/strong[2]")
	private WebElement incQuantityButton;
	//*[@id="checkout-review-table"]/tbody/tr/td/div/div[3]/strong[2]
	
	//@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td[4]/strong[1]")
	@FindBy(xpath="//div[@class='checkout-product-qty']/strong[1]")
	private WebElement decQuantityButton;
	
	@FindBy(xpath="//tr[@class='last']/td[@class='a-right last']/strong/span")
	private WebElement total;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[2]/td[2]/span")
	private WebElement shipping;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[3]/td[2]/span")
	private WebElement shippingKlarna;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tfoot/tr[2]/td[2]/span")
	private WebElement charges;
	
	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr[2]/td/div/div[1]/a")
	//*[@id="checkout-review-table"]/tbody/tr[2]/td/div/div[1]/a
	private WebElement deleteSecondItemIcon;

	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr[2]/td[2]/h3")
	private WebElement secondItemProductName;
	
	@FindAll({@FindBy(xpath="//*[@id='carousel-content']/li/a")})
	private List<WebElement> crossSellProducts;

	@FindBy(xpath="//*[@id='checkout-review-table']/tbody/tr/td/div/div[2]/div[2]/h3/a")
	private WebElement campaignTitle;
	
	@FindBy(xpath = DISCOUND_VALUE_LOCATOR)
	private WebElement discountValue;
	
	@FindBy(xpath = DISCOUND_CODE_LOCATOR)
	private WebElement discountCode;
	
	@FindBy(id="coupon_code")
	private WebElement inputCodeField;
	
	@FindBy(xpath="//button[@class='nglc-button']")
	private WebElement inputCodeButton;
	
	@FindBy(xpath="//a[@title='remove']")
	private WebElement removeDiscountButton;
	
	private static final String DISCOUND_CODE_LOCATOR = "//div[@class='discount-applied-code']";
	private static final String DISCOUND_VALUE_LOCATOR = "//span[@class='discount-value']/span";

	
	public CheckoutPageBlock3(WebDriver driver)
	{
		super(driver);
	}

	public WebElement getSecondItemProductName() {
		return secondItemProductName;
	}
	
	public WebElement getCampaignTitle(){
		return campaignTitle;
	}
	
	public WebElement getDiscountValue(){
		return discountValue;
	}
	
	public WebElement getDiscountCode(){
		return discountCode;
	}
	
	public boolean isDiscountCodePresent()
	{
		if (!isElementPresent(By.xpath(DISCOUND_CODE_LOCATOR))) {
			return false;
		}
		return true;
	}
	
	
	public boolean isDiscountValuePresent()
	{
		if (!isElementPresent(By.xpath(DISCOUND_VALUE_LOCATOR))) {
			return false;
		}
		return true;
	}
	
	public void inputDiscountCode(String code){
		inputCodeField.sendKeys(code);
		inputCodeButton.click();
		waitForCalculationToFinish();
	}
	
	public void removeDiscount(){
		removeDiscountButton.click();
		waitForCalculationToFinish();
	}
	
	public void increaseQuantity()
	{
		incQuantityButton.click();
		waitForCalculationToFinish();
	}
	
	public void decreaseQuantity()
	{
		decQuantityButton.click();
		waitForCalculationToFinish();
	}
	
	public void deleteSecondItemClick()
	{
		deleteSecondItemIcon.click();
		waitForCalculationToFinish();
	}
	
	public void waitForCalculationToFinish()
	{
		try {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(absenceOfElementLocated(By.xpath("//div[@class = 'loadinfo']")));
		}
		catch (TimeoutException e) {
			Assert.fail("Checkout calculation timeoute exception");
		}
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
