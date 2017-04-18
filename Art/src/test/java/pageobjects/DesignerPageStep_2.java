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

	public DesignerPageStep_2(WebDriver driver)
	{
		super(driver);
	}
	
	public void clickProductSelect()
	{
		productSelectSpan.click();
	}
	
	public void chooseAllProducts()
	{

		int size = productList.size();
		
		for (int i=0; i<size; i++)
		{
			clickProductSelect();
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
