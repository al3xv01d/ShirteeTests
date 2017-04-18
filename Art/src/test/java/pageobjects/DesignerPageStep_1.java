package pageobjects;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DesignerPageStep_1 extends PageObject{

	
	@FindBy(id="add_text_textarea")
	private WebElement textArea;
	
	@FindBy(id = "pd_gt_product")
	private WebElement continueButton;
	
	@FindBy(id="pd_add_image")
	private WebElement imgTab;
	
	@FindBy(partialLinkText="Computer durchsuchen")
	private WebElement imgLink;
	
	
	@FindBy(xpath="//*[@id='pd_sides']/ul/li[2]/img")
	private WebElement backSide;
	
	@FindBy(xpath="//*[@id='uploadedImages']/img")
	private WebElement img;
	
	@FindBy(id="remove-img-btn")
	private WebElement removeImgBtn;
	
	@FindBy(xpath="//*[@id='canvas-error']/div")
	private WebElement canvasError;
	
	@FindBy(xpath="//*[@id='product_designer_image_upload-loading']/div[3]")
	private WebElement loadingPopup;
	
	private String filePath = "c:\\1.png";
	
	public DesignerPageStep_1(WebDriver driver)
	{
		super(driver);
		assertTrue(textArea.isDisplayed());
	}

	
	public void sendKeysDesign()
	{
		sendKeys(textArea, "12345");
	}
	
	public void goToImgTab()
	{
		imgTab.click();
	}

	public void backSideClick()
	{
		backSide.click();
	}
	
	public void chooseImg()
	{
		img.click();
	}
	
	public void removeImgs(){
		
		if (removeImgBtn.isDisplayed()){
		removeImgBtn.click();
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(imgLink));
		}
		
	}
	
	public void waitForImgLink()
	{
		waitForElement(imgLink);
	}
	
	public void browseForImage()
	{
		imgLink.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			Robot r = new Robot();
			
			for (char c : filePath.toCharArray())
			{
				if (c == ':')
				{
					r.keyPress(KeyEvent.VK_SHIFT);
					r.keyPress(KeyEvent.getExtendedKeyCodeForChar(';'));
					r.keyRelease(KeyEvent.getExtendedKeyCodeForChar(';'));
					r.keyRelease(KeyEvent.VK_SHIFT);
					
					continue;
				}
				
				r.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
				r.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
				
			}
			
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void waitForPictureToLoad()
	{
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean returnCanvasError(){
		return canvasError.isDisplayed();
	}
	
	public  void continueClick()
	{
		continueButton.click();

	}
	
	
	
}
