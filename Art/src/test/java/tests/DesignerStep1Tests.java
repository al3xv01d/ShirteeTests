package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
	
	private String basePrice = "2,50 €";
	private String frontPrice = "9,00 €";
	private String backPrice = "9,00 €";
	private String frontAndBackPrice = "15,50 €";
	private String categories[] = {"Herren-Shirts", "Hoodies & Sweatshirts", "Tank-Tops", "V-Neck-Shirts",
			"Damen-Shirts", "Taschen", "Kinder-Shirts", "Übergrößen", "Jacken", "Ac­ces­soires", "Poster", "Küchentextilien"};
	
	private String[][] basePriceArray = new String [][] {{"2,50 €", "4,50 €", "4,50 €"},//Herren-Shirts
														{"12,00 €", "9,00 €"},//Hoodies & Sweatshirts
														{"4,00 €", "4,00 €"},//Tank-Tops
														{"4,00 €", "4,00 €"},//V-Neck-Shirts
														{"2,50 €","4,50 €","4,50 €" },//Damen-Shirts
														{"2,50 €", "1,20 €"},//Taschen
														{"2,50 €"},//Kinder-Shirts
														{"6,00 €"},//Übergrößen
														{"22,50 €"},//Jacken
														{"3,50 €", "13,45 €", "7,50 €"},//Ac­ces­soires
														{"0,00 €", "3,50 €", "9,50 €"},//Poster
														{"8,50 €"}};//Küchentextilien
	
	private String[][] frontPriceArray = new String [][] {{"9,00 €", "11,00 €", "11,00 €"},//Herren-Shirts
															{"18,50 €", "15,50 €"},//Hoodies & Sweatshirts
															{"10,50 €", "10,50 €"},//Tank-Tops
															{"10,50 €", "10,50 €"},//V-Neck-Shirts
															{"9,00 €","11,00 €","11,00 €" },//Damen-Shirts
															{"9,00 €", "7,70 €"},//Taschen
															{"9,00 €"},//Kinder-Shirts
															{"12,50 €"},//Übergrößen
															{"29,00 €"},//Jacken
															{"10,00 €", "19,95 €", "14,00 €"},//Ac­ces­soires
															{"6,50 €", "10,00 €", "16,00 €"},//Poster
															{"15,00 €"}};//Küchentextilien
	
	private String backPriceArray[] = {"9,00 €"};
	
	private String frontAndBackPriceArray[] = {"15,50 €"};
	//TODO: Store methods in array and implement through for loop
	@Test
	public void colorsTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
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
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/initial.png", 
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
			
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/black.png", 
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
		
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/blue.png", 
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
			
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/green.png", 
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
		
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/red.png", 
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
		
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/Colors/Actual/yellow.png", 
				"Resources/img/Colors/Expected/yellow.png"),
				Util.CompareImage.Result.Matched);
		
		softAssert.assertAll();
	}
	
	
	@Test
	public void fontTest()
	{
		driver.get(System.getProperty("designerURL"));
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
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
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
		

		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/FontColors/Actual/BlackFont.png", 
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
		
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/FontColors/Actual/RedFont.png", 
				"Resources/img/FontColors/Expected/RedFont.png"),
				Util.CompareImage.Result.Matched);
		
	}
	
	@Test()
	public void pictureTabTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
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
		
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/ShirtWithImage/Actual/shirtWithImage.png",
				"Resources/img/ShirtWithImage/Expected/shirtWithImage.png"),
				Util.CompareImage.Result.Matched);
		
		softAssert.assertTrue(step1.isElementPresent(By.xpath("//*[@id='uploadedImages']/img")));
		softAssert.assertTrue(step1.isElementPresent(By.xpath("//*[@id='remove-img-btn']")));
		
		step1.removeImgs();
		
		step1.waitForDeletePicturePopupToDisappear();
		
		softAssert.assertFalse(step1.isElementPresent(By.xpath("//*[@id='uploadedImages']/img"))); 
		softAssert.assertTrue(step1.isElementPresent(By.xpath("//*[@id='remove-img-btn']"
				+ "[contains(@style, 'display: none')]")));
		//Assert.assertFalse(step1.returnUploadedImagePreview().isDisplayed()); //FAILS
		
		softAssert.assertAll();
	}
	
	@Test
	public void productBlockTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
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
		
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/ProductImages/Actual/UnisexHoodie.png",
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

		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/ProductImages/Actual/HerrenTankTop.png",
				"Resources/img/ProductImages/Expected/HerrenTankTop.png"),
				Util.CompareImage.Result.Matched);
		
		//Poster Test
		step1.selectCategory("Poster");
		step1.waitForProductBlock();
		step1.returnPosterPage();
		
		WebElement image3 = driver.findElement(By.id("pd_container"));
		
		try {
			FileUtils.copyFile(
			WebElementExtender.captureElementPicture(image3),
			new File("Resources/img/ProductImages/Actual/PosterTest.png"));
			} catch (Exception e) {
			e.printStackTrace();
			}
		softAssert.assertEquals(Util.CompareImage.CompareImage("Resources/img/ProductImages/Actual/PosterTest.png",
				"Resources/img/ProductImages/Expected/PosterTest.png"),
				Util.CompareImage.Result.Matched);
		
		softAssert.assertAll();
	}
	
	@Test
	public void pricePerProductTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		softAssert.assertEquals(step1.getBasePrice(), basePrice);
		
		step1.sendKeysDesign();
		softAssert.assertEquals(step1.getFrontPrice(), frontPrice);
		
		step1.deleteDesignClick();
		softAssert.assertEquals(step1.getBasePrice(), basePrice);
		
		step1.backSideClick();
		step1.sendKeysDesign();
		softAssert.assertEquals(step1.getBackPrice(), backPrice);
		
		step1.frontSideClick();
		step1.sendKeysDesign();
		softAssert.assertEquals(step1.getFrontAndBackPrice(), frontAndBackPrice);
		
		softAssert.assertAll();
		
//		
//		for (int i = 2; i < categories.length; i++) {
//			step1.selectCategory(categories[i]);
//			step1.waitForProductBlock();
//			
//			for (int j = 0; j < step1.getCarouselSize(); j++) {
//				
//				if (j == 3) {
//					break;
//				}
//				step1.carouselElement(j).click();
//				Assert.assertEquals(step1.getBasePrice(), basePriceArray[i][j])	;
//				
//				//TODO:Add asserts for front, back and front+back prices
//				step1.sendKeysDesign();
//				Assert.assertEquals(step1.getFrontPrice(), frontPriceArray[i][j]);
//				step1.deleteDesignClick();
//				}
//				
//			}
			
		}
		
	
	@Test 
	public void noDesignTest()
	{
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);

		
		step1.continueClick();
		
		Assert.assertEquals(true, step1.returnCanvasError());


	}
	
	@Test
	public void imageTooBigTest()
	{
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);

		
		step1.goToImgTab();
		step1.waitForImgLink();
		step1.sikuliBrowseAll(pageobjects.DesignerPageStep_1.Image.BIG);
		step1.waitForErrorPopup();

		Assert.assertTrue(step1.returnImgTooBigError());
	}
}
