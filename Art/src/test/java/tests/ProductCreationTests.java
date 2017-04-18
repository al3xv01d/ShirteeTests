package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
//import org.junit.Test;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.FunctionalTest;
import pageobjects.DesignerPageStep_1;
import pageobjects.DesignerPageStep_2;
import pageobjects.DesignerPageStep_3;
import pageobjects.MainPage;



public class ProductCreationTests extends FunctionalTest{
	
	//TODO: Assert that campaign is created
	@Test(invocationCount = 1)
	public void testCreateCampaignWithImage()
	{
		driver.get("https://www.shirtee.de");
		driver.manage().window().maximize();
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin();
		
		driver.get("https://www.shirtee.de/designer/?id=1140");
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		step1.goToImgTab();
		step1.waitForImgLink();
		step1.browseForImage();
		step1.waitForPictureToLoad();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		step2.chooseAllProducts();
		step2.continueClick();
		
		DesignerPageStep_3 step3 = new DesignerPageStep_3(driver);
		step3.waitForTitle();
		step3.sendInfoOn3rdStep();
		step3.submitCampain();
				
	}
	
	//TODO: Assert that campaign is created
	@Test(invocationCount = 1)
	public void testCreateCampaignWithText()
	{
		driver.get("https://www.shirtee.de");
		driver.manage().window().maximize();
		MainPage mainPage = new MainPage(driver);
		mainPage.performLogin();
		
		driver.get("https://www.shirtee.de/designer/?id=1140");
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		step2.chooseAllProducts();
		step2.continueClick();
		
		
		DesignerPageStep_3 step3 = new DesignerPageStep_3(driver);
		step3.waitForTitle();
		step3.sendInfoOn3rdStep();
		step3.submitCampain();
				
	}
	
	@Test 
	public void testNoDesign()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		step1.continueClick();
		
		assertEquals(true, step1.returnCanvasError());

	}

}
