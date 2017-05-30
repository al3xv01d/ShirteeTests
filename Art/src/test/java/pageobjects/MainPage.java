package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import static org.junit.Assert.assertTrue;
import org.testng.Assert;

import java.io.Console;
import java.util.Objects;

public class MainPage extends PageObject{

	//@FindBy(xpath="//*[@id='header']/div/a/img[1]")
	@FindBy(xpath="//a[@class='logo']")
	private WebElement shirteeLogo;
	
	@FindBy(xpath="//div[@class='bhi-link-right']/a")
	private WebElement loginLink;
	
	@FindBy(id="header-login-form-email")
	private WebElement loginEmail;
	
	@FindBy(xpath="//*[@id='top']/body/div[3]/div/div[1]/div/div/div[2]/a[2]")
	private WebElement logOutLink;
	
	@FindBy(id="header-login-form-password")
	private WebElement loginPassword;
	
	@FindBy(className="btn-proceed-checkout")
	private WebElement submitButton;
	


	public MainPage(WebDriver driver)
	{
		super(driver);

		Assert.assertTrue(shirteeLogo.isDisplayed());
	}
	
	public void logOut()
	{
		waitForElement(logOutLink);
		logOutLink.click();
	}
	
	public void performLogin(String eMail, String passWord)
	{
		waitForElement(loginLink);
		loginLink.click();
		waitForElement(loginEmail);
		sendKeys(loginEmail, eMail);
		sendKeys(loginPassword, passWord);
		submitButton.click();
	}
	
}
