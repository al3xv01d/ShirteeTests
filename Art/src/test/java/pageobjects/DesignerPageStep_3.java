package pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DesignerPageStep_3 extends PageObject{

	
	@FindBy(id="sales_name")
	private WebElement campaignTitle;
	
	@FindBy(id="sales_url")
	private WebElement campaignURL;
	
	@FindBy(id = "pd_sales")
	private WebElement submitButton;
	
	@FindBy(xpath = "//*[@id='result_block_save_campaign']/div")
	private WebElement resultMsg;
	
	@FindBy(id = "advice-required-entry-sales_name")
	private WebElement titleNoInputValidation;
	
	@FindBy(id = "title-lenght-error")
	private WebElement titleLengthValidation;
	
	@FindBy(id = "advice-required-entry-sales_url")
	private WebElement urlNoInputValidation;
	
	@FindBy(id = "advice-validate-code-pd-sales_url")
	private WebElement urlWrongInput;
	
	@FindBy(id = "advice-pd-url-sales_url")
	private WebElement urlExistingInput;
	
	//Tags
	@FindBy(id = "tags-input")
	private WebElement tagsInputField;
	
	@FindBy(id = "tag-id-test")
	private WebElement addedTag;
	
	@FindBy(xpath = "//*[@id='tag-id-test']/span")
	private WebElement tagRemoveIcon;
	
	@FindAll({@FindBy(xpath="//*[@id='selected-tags']/span")})
	private List<WebElement> tagsList;
	
	private String str;
	
	public DesignerPageStep_3(WebDriver driver)
	{
		super(driver);
	}
	
	//Tags
	public void sendTag(String str)
	{
		sendKeys(tagsInputField, str);
	}

	public String getNameOfAddedTag()
	{
		return addedTag.getText();
	}
	
	public void removeTagClick()
	{
		tagRemoveIcon.click();
	}
	
	public int returnTagsListSize()
	{
		int i = tagsList.size();
		return i;
	}
	
	//Waits
	public void waitForTitle()
	{
		waitForElement(campaignTitle);
	}
	
	public void waitForSubmitButton()
	{
		waitForElement(submitButton);
	}
	
	public void waitForTitleLengthValidationToShow()// Title length
	{
		WebDriverWait wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='title-lenght-error']"
								+ "[contains(@style, 'display: block')]")));
	}
	
	public void waitForTitleNoInputValidationToDisappear() //Title no input
	{
		WebDriverWait wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='advice-required-entry-sales_name']"
								+ "[contains(@style, 'display: none')]")));
	}
	
	public void waitForURLNoInputValidationToDisappear() //URL no input
	{
		WebDriverWait wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='advice-required-entry-sales_url']"
								+ "[contains(@style, 'display: none')]")));
	}
	
	public void waitForURLWrongInputValidationToDisappear() //URL wrong input
	{
		WebDriverWait wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='advice-validate-code-pd-sales_url']"
								+ "[contains(@style, 'display: none')]")));
	}
	
	public void waitForURLExistingInputValidationToDisappear() //URL existing input
	{
		WebDriverWait wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.
				presenceOfElementLocated(By.
						xpath("//*[@id='advice-pd-url-sales_url']"
								+ "[contains(@style, 'display: none')]")));
	}
	
	public void sendInfoOn3rdStep()
	{
		sendKeys(campaignTitle,"title");
		str = new String("x") + RandomStringUtils.randomAlphanumeric(8).toLowerCase(); // Is it unique every time?
		sendKeys(campaignURL, str);
	}
	
	public String getTitleNoInputValidationText()
	{
		return titleNoInputValidation.getText();
	}
	
	public String getTitleLengthValidationText()
	{
		return titleLengthValidation.getText();
	}
	
	public String getUrlNoInputValidationText()
	{
		return urlNoInputValidation.getText();
	}
	
	public String getUrlWrongInputValidationText()
	{
		return urlWrongInput.getText();
	}
	
	public String getExistingURLValidationText()
	{
		return urlExistingInput.getText();
	}
	
	public void sendKeysToTitle(String str)
	{
		sendKeys(campaignTitle, str);
	}
	
	public void sendKeysToUrl(String str)
	{
		sendKeys(campaignURL, str);
	}
	
	public void URLClick()
	{
		campaignURL.click();
	}
	
	public void clearTitle()
	{
		campaignTitle.clear();
	}
	
	public void submitCampaignClick()
	{
		submitButton.click();
	}
	
	public DashboardPage submitCampain()
	{
		submitButton.click();
		waitForElement(resultMsg);
		return new DashboardPage(driver);
	}
}
