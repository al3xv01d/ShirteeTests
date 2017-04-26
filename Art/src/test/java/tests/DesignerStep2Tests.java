package tests;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import base.FunctionalTest;
import pageobjects.DesignerPageStep_1;
import pageobjects.DesignerPageStep_2;

public class DesignerStep2Tests extends FunctionalTest{

	private String profits[] = {"16,45 €", "13,45 €", "13,95 €", "12,95 €", "11,95 €", "11,45 €",
			"11,45 €", "9,45 €", "9,45 €", "12,95 €", "11,95 €", "7,95 €", "6,25 €", "12,45 €",
			"10,95 €", "20,95 €", "4,95 €", "10,00 €", "10,90 €", "8,45 €", "9,95 €", "9,95 €",
			"9,95 €", "9,45 € ", "9,95 €", "9,95 €", "9,95 €", "9,95 €"};
	
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

		step2.chooseProductFromSelect(0);
		Assert.assertTrue(step2.isElementPresent(By.xpath("//*[@id='1337']")));
		
		step2.removeAdditionalProduct();
		Assert.assertTrue(!step2.isElementPresent(By.xpath("//*[@id='1337']")));
	}
	
	@Test
	public void productProfitTest()
	{
		driver.get("https://www.shirtee.de/designer/?id=1140");
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		
		Assert.assertEquals(step2.getProfitForFirstProduct(), "13,95 €");
//		
//		step2.inputPriceForFirstProduct("25");
//		step2.profitClick();
//		step2.waitForProfitToChange("16,00 €");
//		Assert.assertEquals(step2.getProfitForFirstProduct(), "16,00 €");
//		
//		step2.inputPriceForFirstProduct("0");
//		step2.profitClick();
//		step2.waitForProfitToChange("0,01 €");
//		Assert.assertEquals(step2.getProfitForFirstProduct(), "0,01 €");
//		
//		step2.chooseProductFromSelect(0);
//		Assert.assertEquals(step2.getProfitForSecondProduct(), "16,45 €");
		
		int size = step2.getProductListSize();
		
		for (int i=0; i<size; i++)
		{
			//step2.productSelectSpanClick();
			step2.chooseProductFromSelect(i);
			//step2.waitForProfitToChange(profits[i]);
			Assert.assertEquals(step2.getProfitForSecondProduct(), profits[i]);
			step2.removeAdditionalProduct();
		}
		
		
	}
	
}
