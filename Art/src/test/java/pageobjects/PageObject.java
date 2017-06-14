package pageobjects;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class PageObject {
	
	protected WebDriver driver;
	
	public PageObject(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		if (isAlertPresent())
		{
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
	}
	
	public void waitForElement(WebElement element)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			Assert.fail("Time-out exception waiting for element visibility \n" + e);
		}
		
	}
	
	public void sendKeys(WebElement element, String text)
	{
		element.clear();
		element.sendKeys(text);
	}
	
	public boolean isElementPresent(By locatorKey) {
	    try {
	        driver.findElement(locatorKey);
	        return true;
	    } catch (org.openqa.selenium.NoSuchElementException e) {
	        return false;
	    }
	}
	
	public static ExpectedCondition<Boolean> absenceOfElementLocated(
		      final By locator) {
		    return new ExpectedCondition<Boolean>() {
		      @Override
		      public Boolean apply(WebDriver driver) {
		        try {
		          driver.findElement(locator);
		          return false;
		        } catch (NoSuchElementException e) {
		          return true;
		        } catch (StaleElementReferenceException e) {
		          return true;
		        }
		      }

		      @Override
		      public String toString() {
		        return "element to not being present: " + locator;
		      }
		    };
		  }
	
	public  boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   // isAlertPresent()
	
	
	
	public void waitForLoad(WebDriver driver) {
		
		ExpectedCondition<Boolean> pageLoadCondition = new
		    ExpectedCondition<Boolean>() {
		        public Boolean apply(WebDriver driver) {
		            return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
		        }
		    };
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
		}
	
}
