package tests;

import org.testng.annotations.Test;
import Util.CompareImage;
import Util.WebElementExtender;

import static org.junit.Assert.assertEquals;

import java.io.File;
import base.FunctionalTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageobjects.DesignerPageStep_1;

public class DesignerStep1Tests extends FunctionalTest{

	private String actualFonts[] = {"Resources/img/Fonts/Actual/Agent Orange.png",
							"Resources/img/Fonts/Actual/florence.png",
							"Resources/img/Fonts/Actual/canterbury.png",
							"Resources/img/Fonts/Actual/LDFComicSans.png",
							"Resources/img/Fonts/Actual/LeArchitect.png"
							};
	
	private String expectedFonts[] = {"Resources/img/Fonts/Expected/Agent Orange.png",
								"Resources/img/Fonts/Expected/florence.png",
								"Resources/img/Fonts/Expected/canterbury.png",
								"Resources/img/Fonts/Expected/LDFComicSans.png",
								"Resources/img/Fonts/Expected/LeArchitect.png"};
	
	private String selects[] = {"Agent Orange", "Florence", "Canterbury", "LDFComicSans", "LeArchitect"};
	
	//TODO: Store methods in array and implement through for loop
	@Test
	public void colorsTests()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		//Initial Color Test
		WebElement image = driver.findElement(By.id("pd_container"));
		try {
			
		FileUtils.copyFile(
		WebElementExtender.captureElementPicture(image),
		new File("Resources/img/Colors/Actual/initial.png"));
		} catch (Exception e) {
		e.printStackTrace();
		}
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/initial.png", 
				"Resources/img/Colors/Expected/initial.png"),
				Util.CompareImage.Result.Matched);
		
		//Black color test
		step1.chooseBlackColor();
		try {
			
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image),
			new File("Resources/img/Colors/Actual/black.png"));
			} catch (Exception e) {
			e.printStackTrace();
			}
			
			Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/black.png", 
					"Resources/img/Colors/Expected/black.png"),
					Util.CompareImage.Result.Matched);
			
		//Blue color test	
		step1.chooseBlueColor();
		try {
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image),
			new File("Resources/img/Colors/Actual/blue.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/blue.png", 
				"Resources/img/Colors/Expected/blue.png"),
				Util.CompareImage.Result.Matched);
		
		//Green Color Test
		step1.chooseGreenColor();
		try {
			FileUtils.copyFile(
					WebElementExtender.captureElementPicture(image),
					new File("Resources/img/Colors/Actual/green.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/green.png", 
				"Resources/img/Colors/Expected/green.png"),
				Util.CompareImage.Result.Matched);	
		
		//Red color Test
		step1.chooseRedColor();
		
		try {
			FileUtils.copyFile(
					WebElementExtender.captureElementPicture(image),
					new File("Resources/img/Colors/Actual/red.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/red.png", 
				"Resources/img/Colors/Expected/red.png"),
				Util.CompareImage.Result.Matched);
		
		//Yellow color test
		step1.chooseYellowColor();
		try {
			FileUtils.copyFile(
					WebElementExtender.captureElementPicture(image),
					new File("Resources/img/Colors/Actual/yellow.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/yellow.png", 
				"Resources/img/Colors/Expected/yellow.png"),
				Util.CompareImage.Result.Matched);
	}
	
	
	@Test
	public void fontTests()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		WebElement image = driver.findElement(By.id("pd_container"));
		step1.sendKeysDesign();
		//step1.selectFont("Florence");
		int size = selects.length;
		
		for(int i=0; i < size; i++)
		{
			step1.selectFont(selects[i]);
			try {
				
				FileUtils.copyFile(
				WebElementExtender.captureElementPicture(image),
				new File(actualFonts[i]));
				} catch (Exception e) {
				e.printStackTrace();
				}
			
			Assert.assertEquals(Util.CompareImage.CompareImage(actualFonts[i], expectedFonts[i]),
				Util.CompareImage.Result.Matched);
		}
		
		
	}
	
	@Test 
	public void noDesignTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		step1.continueClick();
		
		Assert.assertEquals(true, step1.returnCanvasError());

	}
	
	@Test
	public void imageTooBigTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.goToImgTab();
		step1.waitForImgLink();
		step1.sikuliBrowseAll(pageobjects.DesignerPageStep_1.Image.BIG);
		step1.waitForErrorPopup();

		
		Assert.assertTrue(step1.returnImgTooBigError());
	}
}
