package tests;

import org.testng.Assert;
import org.hamcrest.core.AnyOf;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;

import base.FunctionalTest;
import pageobjects.DesignerPageStep_1;
import pageobjects.DesignerPageStep_2;

public class DesignerStep2Tests extends FunctionalTest{

	private String profits[] = {"16,45 €", "13,45 €", "13,95 €", "12,95 €", "11,95 €", "11,45 €",
			"11,45 €", "9,45 €", "9,45 €", "12,95 €", "11,95 €", "7,95 €", "6,25 €", "12,45 €",
			"10,95 €", "20,95 €", "4,95 €", "10,00 €", "10,90 €", "8,45 €", "9,95 €", "9,95 €",
			"9,95 €", "9,45 €", "9,95 €", "9,95 €", "9,95 €", "9,95 €"};
	
	@Test
	public void colorPickerTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitforPicker();
		step2.colorPickerClick();
		step2.chooseGoldColor();
		
		softAssert.assertTrue(step2.isElementPresent(By.xpath("//*[@id='calculation-selected-colors']/span[2]")));
		step2.addedColorClick();
		
		softAssert.assertTrue(!step2.isElementPresent(By.xpath("//*[@id='calculation-selected-colors']/span[2]")));
		
		softAssert.assertAll();
	}
	
	@Test
	public void productBlockTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		
		softAssert.assertTrue(step2.isElementPresent(By.xpath("//*[@id='calculation-items']/tr[1]")));

		step2.chooseProductFromSelect(0);
		softAssert.assertTrue(step2.isElementPresent(By.xpath("//*[@id='1337']")));
		
		step2.removeAdditionalProduct();
		softAssert.assertTrue(!step2.isElementPresent(By.xpath("//*[@id='1337']")));
		
		softAssert.assertAll();
	}
	
	@Test
	public void productProfitTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		
		softAssert.assertEquals(step2.getProfitForFirstProduct(), "13,95 €");
		
		step2.inputPriceForFirstProduct("25");
		step2.profitClick();
		step2.waitForProfitToChange("16,00 €");
		softAssert.assertEquals(step2.getProfitForFirstProduct(), "16,00 €");
		
		step2.inputPriceForFirstProduct("0");
		step2.profitClick();
		step2.waitForProfitToChange("0,01 €");
		softAssert.assertEquals(step2.getProfitForFirstProduct(), "0,01 €");
		
		step2.chooseProductFromSelect(0);
		softAssert.assertEquals(step2.getProfitForSecondProduct(), "16,45 €");
		step2.removeAdditionalProduct();
		
		int size = step2.getProductListSize();

		step2.chooseAllProducts();
		for (int i = 0; i < size; i++) {
			softAssert.assertEquals(step2.getProductProfit(i), profits[i]);
			
		}
		
		softAssert.assertAll();
	}
	
	@Test
	public void potentialProfitTest()
	{
		
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		step2.chooseProductFromSelect(0);
		softAssert.assertEquals(step2.getPotentialProfit(), "279,00 € - 329,00 €" );
		
		
		step2.sendKeysItemsPotentialProfit("5");
		softAssert.assertEquals(step2.getPotentialProfit(), "69,75 € - 82,25 €");
		
		step2.sendKeysItemsPotentialProfit("test");
		softAssert.assertEquals(step2.getPotentialProfit(), "13,95 € - 16,45 €");
		
		step2.sendKeysItemsPotentialProfit("");
		softAssert.assertEquals(step2.getPotentialProfit(), "13,95 € - 16,45 €");
		
		softAssert.assertAll();
	}
	
}
