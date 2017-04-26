package pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DesignerPageStep_2 extends PageObject{
	
	@FindBy(xpath="//*[@id='calculation']/div[2]/div/div[2]/span")
	private WebElement productSelectSpan;
	
	@FindBy(id = "pd_gt_product")
	private WebElement continueButton;
	
	@FindAll({@FindBy(xpath="//*[@id='custom-list']/li")})
	private List<WebElement> productList;
	
	//Color Picker
	@FindBy(id = "calculation-colors-link")
	private WebElement colorPicker;

	@FindBy(xpath="//span[@title='Gold']")
	private WebElement colorGold;
	
	@FindBy(xpath="//*[@id='calculation-selected-colors']/span[2]")
	private WebElement addedColor;
	
	//Product Block
	@FindBy(xpath="//*[@id='assignto-1337']/td[3]/span")
	private WebElement removeAdditionalProductButton;
	
	@FindBy(xpath="//*[@id='profit']/span[1]")
	private WebElement profitFirstProduct;
	
	//@FindBy(xpath="//*[@id='1337']/td/strong/span[1]")
	@FindBy(className="price profit")
	private WebElement profitSecondProduct;
	
	@FindBy(xpath="//*[@id='sales_price']")
	private WebElement priceFirstProduct;
	
	
	public DesignerPageStep_2(WebDriver driver)
	{
		super(driver);
	}
	
	public void chooseProductFromSelect(int i)
	{
		productSelectSpan.click();
		productList.get(i).click();
	}
	
	public int getProductListSize()
	{
		int i = productList.size();
			return i;
	}
	
	public void productSelectSpanClick()
	{
		productSelectSpan.click();
	}
	
	public void removeAdditionalProduct()
	{
		removeAdditionalProductButton.click();
	}
	
	public String getProfitForFirstProduct()
	{
		return profitFirstProduct.getText();
	}
	
	public String getProfitForSecondProduct()
	{
		return profitSecondProduct.getText();
	}
	
	
	public String getPriceForFirstProduct() 
	{
		return priceFirstProduct.getText();//WHERE?
	}
	
	public void inputPriceForFirstProduct(String str)
	{
		priceFirstProduct.clear();
		priceFirstProduct.sendKeys(str);
	}
	
	public void profitClick()
	{
		profitFirstProduct.click();
	}
	
	public void waitForProfitToChange(String str)
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.
				textToBePresentInElementLocated(By.xpath("//*[@id='profit']/span[1]"),
						str));
	}
	
	public void colorPickerClick()
	{
		colorPicker.click();
	}
	
	public void addedColorClick()
	{
		addedColor.click();
	}
	
	public void waitforPicker()
	{
		waitForElement(colorPicker);
	}
	
	public void chooseGoldColor()
	{
		colorGold.click();
	}
	
	
	public void chooseAllProducts()
	{

		int size = productList.size();
		
		for (int i=0; i<size; i++)
		{
			productSelectSpan.click();
			productList.get(i).click();
		}
	}

	
	public void waitForContinueButton()
	{
		waitForElement(continueButton);
	}
	
	public void continueClick()
	{
		continueButton.click();
	}

}
