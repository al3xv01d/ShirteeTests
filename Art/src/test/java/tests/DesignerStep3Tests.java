package tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.By;
import org.testng.Assert;

import base.FunctionalTest;
import pageobjects.DesignerPageStep_1;
import pageobjects.DesignerPageStep_2;
import pageobjects.DesignerPageStep_3;

public class DesignerStep3Tests extends FunctionalTest{

	@Test
	public void titleAndUrlTest()
	{
		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		step2.continueClick();
		
		DesignerPageStep_3 step3 = new DesignerPageStep_3(driver);
		step3.waitForTitle();
		step3.waitForSubmitButton();
		step3.submitCampaignClick();
		
		//Title tests
		
		softAssert.assertEquals(step3.getTitleNoInputValidationText(),"Dies ist ein Pflichtfeld.");
		softAssert.assertEquals(step3.getTitleLengthValidationText(), "");
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='title-lenght-error']"
				+ "[contains(@style, 'display: none')]")));
		
		step3.sendKeysToTitle("titleqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
		
		step3.clearTitle();
		
		step3.sendKeysToTitle("titleqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"
				+ "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");

		step3.submitCampaignClick();
		step3.waitForTitleLengthValidationToShow();
		step3.waitForTitleNoInputValidationToDisappear();
		
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='title-lenght-error']"
				+ "[contains(@style, 'display: block')]")));
		softAssert.assertEquals(step3.getTitleLengthValidationText(),"Max title length is 40 symbols");
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='advice-required-entry-sales_name']"
				+ "[contains(@style, 'display: none')]")));

		
		//URL Tests
		//No input test
		softAssert.assertEquals(step3.getUrlNoInputValidationText(),"Dies ist ein Pflichtfeld.");
		step3.sendKeysToUrl("123test");
		step3.submitCampaignClick();
		
		//Wrong input test 1
		step3.waitForURLNoInputValidationToDisappear();
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='advice-required-entry-sales_url']"
				+ "[contains(@style, 'display: none')]")));
		softAssert.assertEquals(step3.getUrlWrongInputValidationText(),"Deine URL darf nur Kleinbuchstaben, "
				+ "Zahlen und Bindestriche enthalten.");
		
		//Wrong input test 2
		step3.sendKeysToUrl("Test");
		step3.submitCampaignClick();
		step3.waitForURLNoInputValidationToDisappear();
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='advice-required-entry-sales_url']"
				+ "[contains(@style, 'display: none')]")));
		softAssert.assertEquals(step3.getUrlWrongInputValidationText(),"Deine URL darf nur Kleinbuchstaben, "
				+ "Zahlen und Bindestriche enthalten.");
		
		//Existing URL Test
		step3.sendKeysToUrl("test");
		step3.submitCampaignClick();
		step3.waitForURLWrongInputValidationToDisappear();
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='advice-validate-code-pd-sales_url']"
				+ "[contains(@style, 'display: none')]")));
		softAssert.assertEquals(step3.getExistingURLValidationText(),"Is unavailable");
		
		//Valid URL
		step3.sendKeysToUrl("test134658");
		step3.submitCampaignClick();
		step3.waitForURLExistingInputValidationToDisappear();
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='advice-pd-url-sales_url']"
				+ "[contains(@style, 'display: none')]")));
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='advice-validate-code-pd-sales_url']"
				+ "[contains(@style, 'display: none')]")));
		softAssert.assertTrue(step3.isElementPresent(By.xpath("//*[@id='advice-required-entry-sales_url']"
				+ "[contains(@style, 'display: none')]")));
		
		softAssert.assertAll();
	}
	
	@Test
	public void tagsTest()
	{

		SoftAssert softAssert = new SoftAssert();
		
		driver.get(System.getProperty("designerURL"));
		driver.manage().window().maximize();
		DesignerPageStep_1 step1 = new DesignerPageStep_1(driver);
		
		step1.sendKeysDesign();
		step1.continueClick();
		
		DesignerPageStep_2 step2 = new DesignerPageStep_2(driver);
		step2.waitForContinueButton();
		step2.continueClick();
		
		DesignerPageStep_3 step3 = new DesignerPageStep_3(driver);
		step3.waitForTitle();
		step3.sendTag("test");
		step3.submitCampaignClick();
		softAssert.assertEquals(step3.getNameOfAddedTag(),"test");
		
		step3.removeTagClick();
		softAssert.assertTrue(!step3.isElementPresent(By.id("tag-id-test")));
		
		for (int i = 0; i < 15; i++) {
			
			step3.sendTag("test");
			step3.URLClick();
		}

		softAssert.assertEquals(step3.returnTagsListSize(),10);
		
		softAssert.assertAll();
		
	}
	
}
