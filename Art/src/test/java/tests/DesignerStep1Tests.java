package tests;

import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import Util.CompareImage;
import Util.WebElementExtender;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import base.FunctionalTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	private String fontSelects[] = {"Agent Orange", "Florence", "Canterbury", "LDFComicSans", "LeArchitect"};
	
	//TODO: Store methods in array and implement through for loop
	@Test
	public void colorsTest()
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
	public void fontTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		WebElement image = driver.findElement(By.id("pd_container"));
		step1.sendKeysDesign();
		//step1.selectFont("Florence");
		int size = fontSelects.length;
		
		for(int i=0; i < size; i++)
		{
			step1.selectFont(fontSelects[i]);
			try {
				
				FileUtils.copyFile(
				WebElementExtender.captureElementPicture(image),
				new File("Resources/img/Colors/Actual/initial.png"));
				} catch (Exception e) {
				e.printStackTrace();
				}
			
			Assert.assertEquals(Util.CompareImage.CompareImage(actualFonts[i], expectedFonts[i]),
				Util.CompareImage.Result.Matched);
		}
		
		
	}
	
	
	@Test
	public void fontColorTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
			
		step1.sendKeysDesign();
		WebElement image = driver.findElement(By.id("pd_container"));
		
		try {
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image),
			new File("Resources/img/FontColors/Actual/BlackFont.png"));
			} catch (Exception e) {
			e.printStackTrace();
			}
		

		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/FontColors/Actual/BlackFont.png", 
				"Resources/img/FontColors/Expected/BlackFont.png"),
				Util.CompareImage.Result.Matched);
		
		//TEST RED COLOR
		step1.textColorPickerClick();
		step1.waitForColorPickerToShow();
		
		//execute through javascript, because otherwise element is not visible
		((JavascriptExecutor)driver).executeScript("arguments[0].checked = true;", step1.returnRedFontColorElm());
		step1.textColorRedClick();
		
		try {
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image),
			new File("Resources/img/FontColors/Actual/RedFont.png"));
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/FontColors/Actual/RedFont.png", 
				"Resources/img/FontColors/Expected/RedFont.png"),
				Util.CompareImage.Result.Matched);
		
	}
	
	@Test
	public void pictureTabTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.goToImgTab();
		step1.sikuliBrowseAll(pageobjects.DesignerPageStep_1.Image.FIRST);
		WebElement image = driver.findElement(By.id("pd_container"));
		step1.waitForPictureToLoad();
		
		try {
			
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image),
			new File("Resources/img/ShirtWithImage/Actual/shirtWithImage.png"));
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/ShirtWithImage/Actual/shirtWithImage.png",
				"Resources/img/ShirtWithImage/Expected/shirtWithImage.png"),
				Util.CompareImage.Result.Matched);
		
		Assert.assertTrue(step1.isElementPresent(By.xpath("//*[@id='uploadedImages']/img")));
		Assert.assertTrue(step1.isElementPresent(By.xpath("//*[@id='remove-img-btn']")));
		
		step1.removeImgs();
		//Assert.assertFalse(step1.returnRemovePreviewButton().isDisplayed());
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//step1.waitForPictureToLoad();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertFalse(step1.isElementPresent(By.xpath("//*[@id='uploadedImages']/img"))); 
		Assert.assertTrue(step1.isElementPresent(By.xpath("//*[@id='remove-img-btn']"
				+ "[contains(@style, 'display: none')]")));
		//Assert.assertFalse(step1.returnUploadedImagePreview().isDisplayed()); //FAILS
		//TODO: Ask about popup that appears after remove 
		
		
	}
	
	@Test
	public void productBlockTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);

		
		//Hoodie Test
		step1.selectCategory("Hoodies & Sweatshirts");
		step1.waitForProductBlock();
		step1.returnUnisexHoodiePage();
		
		WebElement image = driver.findElement(By.id("pd_container"));
		
		try {
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image),
			new File("Resources/img/ProductImages/Actual/UnisexHoodie.png"));
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/ProductImages/Actual/UnisexHoodie.png",
				"Resources/img/ProductImages/Expected/UnisexHoodie.png"),
				Util.CompareImage.Result.Matched);
		
		//Herren Tank-top Test
		step1.selectCategory("Tank-Tops");
		step1.waitForProductBlock();
		step1.returnHerrenTankTopPage();
		
		WebElement image2 = driver.findElement(By.id("pd_container"));
		
		try {
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image2),
			new File("Resources/img/ProductImages/Actual/HerrenTankTop.png"));
			} catch (Exception e) {
			e.printStackTrace();
			}

		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/ProductImages/Actual/HerrenTankTop.png",
				"Resources/img/ProductImages/Expected/HerrenTankTop.png"),
				Util.CompareImage.Result.Matched);
		
		//Poster Test
		step1.selectCategory("Poster");
		step1.waitForProductBlock();
		step1.returnPosterPage();;
		
		WebElement image3 = driver.findElement(By.id("pd_container"));
		
		try {
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image3),
			new File("Resources/img/ProductImages/Actual/PosterTest.png"));
			} catch (Exception e) {
			e.printStackTrace();
			}
		Assert.assertEquals(Util.CompareImage.CompareImage("Resources/img/ProductImages/Actual/PosterTest.png",
				"Resources/img/ProductImages/Expected/PosterTest.png"),
				Util.CompareImage.Result.Matched);
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
