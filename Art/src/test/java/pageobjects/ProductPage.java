package pageobjects;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends PageObject{

	
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
	
	public ProductPage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getPrice()
	{
		return price.getText();
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
		confirmButton.click();
	}
	
	public void gotoCart(){
		goToCheckout.click();
	}
	
	
}
