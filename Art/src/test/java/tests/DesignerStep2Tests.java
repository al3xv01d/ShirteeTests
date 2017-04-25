package tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import base.FunctionalTest;
import pageobjects.DesignerPageStep_1;
import pageobjects.DesignerPageStep_2;

public class DesignerStep2Tests extends FunctionalTest{

	@Test
	public void colorPickerTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitforPicker();
		step2.colorPickerClick();
		step2.chooseGoldColor();
		
		Assert.assertTrue(step2.isElementPresent(By.xpath("//*[@id='calculation-selected-colors']/span[2]")));
		step2.addedColorClick();
		
		Assert.assertTrue(!step2.isElementPresent(By.xpath("//*[@id='calculation-selected-colors']/span[2]")));
	}
	
	@Test
	public void productBlockTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		
		Assert.assertTrue(step2.isElementPresent(By.xpath("//*[@id='calculation-items']/tr[1]")));

		step2.chooseProductFromSelect();
		Assert.assertTrue(step2.isElementPresent(By.xpath("//*[@id='1337']")));
		
		step2.removeAdditionalProduct();
		Assert.assertTrue(!step2.isElementPresent(By.xpath("//*[@id='1337']")));
	}
	
}
