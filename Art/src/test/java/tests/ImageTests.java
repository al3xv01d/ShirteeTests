package tests;
import Util.CompareImage;
import Util.WebElementExtender;

import java.io.File;
import base.FunctionalTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.Assert;
import pageobjects.DesignerPageStep_1;


public class ImageTests extends FunctionalTest {

	@Test
	public void takeScreenshotTest()
	{
		//CompareImage cmp = new CompareImage();
				
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		//WebElement elm = step1.returnProductImageContainer();
			
		//step1.chooseYellowColor();
		step1.sendKeysDesign();
		step1.selectFont("LDFComicSans");
		WebElement image = driver.findElement(By.id("pd_container"));
		try {
			
		FileUtils.copyFile(
		WebElementExtender.captureElementPicture(image),
		new File("Resources/img/Fonts/Expected/LDFComicSans.png"));
		} catch (Exception e) {
		e.printStackTrace();
		}
		
//		Assert.assertEquals(Util.CompareImage.CompareImage("/home/dglazov/base.png", 
//				"Resources/img/Expected/new.png"),
//				Util.CompareImage.Result.Matched);
	}
	
}
		

	
