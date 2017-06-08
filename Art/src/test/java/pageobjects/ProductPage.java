package pageobjects;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends PageObject{

	private boolean pageAvailability = true;
	
	@FindBy(id = "addition_size")
	private WebElement sizeSelect;
	
	@FindBy(xpath = "//*[@id='product_addtocart_form']/div[3]/div/button")
	private WebElement confirmButton;
	
	@FindBy(xpath = "//*[@id='product-add-complete-popup']/div[2]/div[2]/div[2]")
	private WebElement popup;
	
	@FindBy(id = "go_to_checkout")
	private WebElement goToCheckout;
	
	@FindBy(xpath = "//*[@class='regular-price']/span")
	private WebElement price;
	
	@FindAll({@FindBy(xpath="//*[@class='regular-price']/span")})
	private List<WebElement> priceList;
	
	@FindBy(xpath = "//img[@alt = 'Error 404']")
	private WebElement error404Img;
	
	@FindBy(id = "apply-promo-popup")
	private WebElement promoPopUp;
	
	@FindBy(xpath = "//button[@class = 'close-popup button']")
	private WebElement promoPopUpCloseButton;
	
	public ProductPage(WebDriver driver)
	{
		super(driver);
	}

	public WebElement getPopUp()
	{
		return promoPopUp;
	}
	
	public void closePopUp()
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(promoPopUpCloseButton));
		promoPopUpCloseButton.click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("apply-promo-popup")));
	}
	
	public boolean checkPageAvailability()
	{
		if (priceList.isEmpty())
		{
			setPageAvailability(false);
		}
		
		return getPageAvailability();
	}
	
	public String getPrice()
	{
		return priceList.get(0).getText();
	}
	
	public void getSize()
	{
		Select size = new Select(sizeSelect);
		size.selectByVisibleText("M");
	}
	
	public void waitForPopup(){
		waitForElement(goToCheckout);
	}
	
	public void buy()
	{
		waitForElement(confirmButton);
		confirmButton.click();
	}
	
	public void gotoCart(){
		goToCheckout.click();
	}

	public WebElement getError404Img() {
		return error404Img;
	}

	public boolean getPageAvailability() {
		return pageAvailability;
	}

	public void setPageAvailability(boolean pageAvailability) {
		this.pageAvailability = pageAvailability;
	}


	
}
