package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.FunctionalTest;
//import pageobjects.DesignerPage;
import pageobjects.MainPage;

/**
 * @author DGlazov
 *	Stub for Login tests
 */
public class LoginTest extends FunctionalTest
   
{
	
	@Test
	public void loginTest()
	{
		driver.get("https://www.shirtee.de");
		driver.manage().window().maximize();
		MainPage mainPage = new MainPage(driver);

		
	}
	
	
}
