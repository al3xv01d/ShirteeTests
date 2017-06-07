package pageobjects;

import static org.junit.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
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
	
	@FindBy(xpath="//div[@class='upload-image-drop-zone-text-3']/a")
	private WebElement imgLink;
	
	@FindBy(xpath="//*[@id='pd_sides']/ul/li[2]/img")
	private WebElement backSide;
	
	@FindBy(xpath="//*[@id='pd_sides']/ul/li[1]/img")
	private WebElement frontSide;
	
	@FindBy(xpath="//*[@id='uploadedImages']/img")
	private WebElement uploadedImagePreview;
	
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
	
	//Font Colors
	@FindBy(className="new-text-color-toggle")
	private WebElement textColorPicker;
	
	//@FindBy(xpath="//*[@id='add_text_colors_panel']/span[3]")
	@FindBy(xpath="//span[@colorname='Monza']")
	private WebElement fontRedColor;
	
	@FindBy(xpath="//*[@id='add_text_colors_panel']/span[8]")
	private WebElement fontBlueColor;
	//*[@id="add_text_colors_panel"]/span[8]

	//Product Block
	@FindBy(id="category_selector")
	private WebElement categorySelect;
	
	@FindBy(xpath="//a[@title='Herren Tank-Top']")
	private WebElement productHerrenTankTop;
	
	@FindBy(xpath="//a[@title='Unisex  Hoodie']")
	private WebElement productUnisexHoodie;
	
	@FindBy(xpath="//a[@title='DIN A3 Poster (hochformat)']")
	private WebElement productPoster;
	
	@FindAll({@FindBy(xpath="//*[@id='carousel-content']/li/a")})
	private List<WebElement> productsCarouselItems;
	
	@FindBy(xpath="//*[@id='navigation_listing']/div/a[2]")
	private WebElement carouselControl;
	
	//Error popup - upload image too big
	@FindBy(xpath = "//*[@id='product_designer_image_upload-loading']/div[3]")
	private WebElement imgTooBigErrorPopup;
	
	//Price per product
	@FindBy(xpath="//*[@id='base_price']/span")
	private WebElement basePrice;
	
	@FindBy(xpath="//*[@id='front_price']/span")
	private WebElement frontPrice;
	
	@FindBy(xpath="//*[@id='back_price']/span")
	private WebElement backPrice;
	
	@FindBy(xpath="//*[@id='front_back_price']/span")
	private WebElement frontAndBackPrice;
	
	@FindBy(id="pd_delete")
	private WebElement deleteDesignButton;
	
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
	
	//Price per product block
	public String getBasePrice()
	{
		return basePrice.getText();
	}
	
	public String getFrontPrice()
	{
		return frontPrice.getText();
	}
	
	public String getBackPrice()
	{
		return backPrice.getText();
	}
	
	public String getFrontAndBackPrice()
	{
		return frontAndBackPrice.getText();
	}

	public void deleteDesignClick()
	{
		deleteDesignButton.click();
	}
	
	//product block
	public void returnHerrenTankTopPage()
	{
		productHerrenTankTop.click();
	}
	
	public void returnUnisexHoodiePage()
	{
		productUnisexHoodie.click();
	}
	
	public void returnPosterPage()
	{
		productPoster.click();
	}
	
	public int getCarouselSize()
	{
		int i = productsCarouselItems.size();
		return i;
	}
	
	public WebElement carouselElement(int i)
	{
		return productsCarouselItems.get(i);
	}
	
	public void carouselControlClick()
	{
		carouselControl.click();
	}
	
	//Font select
	public void selectFont(String font)
	{
		Select sel = new Select(fontSelect);
		sel.selectByVisibleText(font);
	}
	
	public void selectCategory(String category)
	{
		Select sel = new Select(categorySelect);
		sel.selectByVisibleText(category);
	}
	
	public void waitForColorPickerToShow()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='add_text_colors_panel']"
								+ "[contains(@style, 'display: block')]")));

	}
	
	public void waitForDeletePicturePopupToDisappear()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='designer-load-info']"
								+ "[contains(@style, 'display: none')]")));
	}
	
	public void waitForProductBlock()
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='designer-load-info']"
								+ "[contains(@style, 'display: none')]")));

	}
	
	public void waitForProductToShowInCarousel()
	{
		WebDriverWait wait = new WebDriverWait(driver, 7);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='navigation_listing']/div/a[2]"
								+ "[contains(@class, 'carousel-control')]")));

	}
	
	public WebElement returnRedFontColorElm()
	{
		return fontRedColor;
	}
	
	public WebElement returnBlueFontColorElm()
	{
		return fontBlueColor;
	}
	
	//Text color
	public void textColorPickerClick()
	{
		textColorPicker.click();
	}
	
	public void textColorRedClick()
	{
		fontRedColor.click();
	}
	
	public void textColorBlueClick()
	{
		fontBlueColor.click();
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
	
	public void frontSideClick()
	{
		frontSide.click();
	}
	
	public void chooseImg()
	{
		uploadedImagePreview.click();
	}
	
	public WebElement returnUploadedImagePreview()
	{
		return uploadedImagePreview;
	}
	

	
	public WebElement returnProductImageContainer()
	{
		return getProductImgContainer();
	}
	
	public void removeImgs(){
		removeImgBtn.click();
	}
	
	public void waitForImgLink()
	{
		waitForElement(imgLink);
	}
	
	
	public void sikuliBrowseAll(Image img)
	{
		imgLink.click();

		
		//Added parameter "1" for multi-screen support
		//Currently selects primary screen
		//Removing parameter is required for single-monitor setup
		
		Screen s = new Screen(1);
		
		
		try
		{
			
			switch (img) {
			case FIRST: 
				s.wait("Resources/img/folder.png");
				s.click("Resources/img/folder.png");
				s.wait("Resources/img/1stimage.png");
				s.click("Resources/img/1stimage.png");
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

	public WebElement getProductImgContainer() {
		return productImgContainer;
	}
	
	
}
