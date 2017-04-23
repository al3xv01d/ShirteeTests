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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;

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
	
	@FindBy(id="pd_container")
	private WebElement productImgContainer;
	
	@FindBy(xpath="//*[@id='canvas-error']/div")
	private WebElement canvasError;
	
	//Colors
	@FindBy(xpath="//*[@id='right-color-group']/span[2]")
	private WebElement yellowColor;
	
	@FindBy(xpath="//*[@id='right-color-group']/span[4]")
	private WebElement redColor;
	
	@FindBy(xpath="//*[@id='right-color-group']/span[6]")
	private WebElement greenColor;
	
	@FindBy(xpath="//*[@id='right-color-group']/span[13]")
	private WebElement blueColor;
	
	@FindBy(xpath="//*[@id='right-color-group']/span[18]")
	private WebElement blackColor;
	
	//Fonts
	@FindBy(id="font-selector")
	private WebElement fontSelect;
	
	//Error popup - upload image too big
	@FindBy(xpath = "//*[@id='product_designer_image_upload-loading']/div[2]")
	private WebElement imgTooBigErrorPopup;
	
	public enum Image {FIRST, SECOND, BIG};

	//Constructor
	public DesignerPageStep_1(WebDriver driver)
	{
		super(driver);
		assertTrue(textArea.isDisplayed());
	}

	public void waitForErrorPopup()
	{
		waitForElement(imgTooBigErrorPopup);
	}
	
	
	//Font select
	public void selectFont(String font)
	{
		Select sel = new Select(fontSelect);
		sel.selectByVisibleText(font);
	}
	
	//Color block
	public void chooseYellowColor()
	{
		yellowColor.click();
	}
	
	public void chooseRedColor()
	{
		redColor.click();
	}
	
	public void chooseGreenColor()
	{
		greenColor.click();
	}
	
	public void chooseBlueColor()
	{
		blueColor.click();
	}
	
	public void chooseBlackColor()
	{
		blackColor.click();
	}
	//Color block end
	
	public void sendKeysDesign()
	{
		sendKeys(textArea, "test");
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
	
	public WebElement returnProductImageContainer()
	{
		return productImgContainer;
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
	
	
	public void sikuliBrowseAll(Image img)
	{
		imgLink.click();

		Screen s = new Screen();
		
		try
		{
			
			switch (img) {
			case FIRST: 
				s.wait("Resources/img/folder.png");
				s.click("Resources/img/folder.png");
				s.wait("Resources/img/image.png");
				s.click("Resources/img/image.png");
				s.click("Resources/img/open-button.png");
				break;
				
			case SECOND:
				s.wait("Resources/img/folder.png");
				s.click("Resources/img/folder.png");
				s.wait("Resources/img/2ndimage.png");
				s.click("Resources/img/2ndimage.png");
				s.click("Resources/img/open-button.png");
				break;
				
			case BIG:
				s.wait("Resources/img/folder.png");
				s.click("Resources/img/folder.png");
				s.wait("Resources/img/big.png");
				s.click("Resources/img/big.png");
				s.click("Resources/img/open-button.png");
				break;
				
			default: {throw new IllegalArgumentException("Can't yet handle " + img);}
				
			}
			
		} catch (FindFailed e) {
			e.printStackTrace();
		}
	}
	
	
	public void waitForPictureToLoad()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='product_designer_image_upload-loading']"
								+ "[contains(@style, 'display: none')]")));

	}
	
	public boolean returnCanvasError(){
		return canvasError.isDisplayed();
	}
	
	public boolean returnImgTooBigError(){
		return imgTooBigErrorPopup.isDisplayed();
	}
	
	public  void continueClick()
	{
		continueButton.click();

	}
	
	
	
}
