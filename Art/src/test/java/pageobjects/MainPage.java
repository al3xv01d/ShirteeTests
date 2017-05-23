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

	@FindBy(xpath="//*[@id='header']/div/a/img[1]")
	private WebElement shirteeLogo;
	
	//@FindBy(xpath="//*[@id='top']/body/div[1]/div/div[1]/div/div/div[2]/a")
	//*[@id="top"]/body/div[4]/div/div[1]/div/div/div[2]/a
	@FindBy(xpath="//*[@id='top']/body/div[4]/div/div[1]/div/div/div[2]/a")
	private WebElement loginLink;
	
	@FindBy(id="header-login-form-email")
	private WebElement loginEmail;
	
	@FindBy(id="header-login-form-password")
	private WebElement loginPassword;
	
	//@FindBy(xpath="//*[@id='header-login-form']/div/div/div[3]/div/button")
	//*[@id="header-login-form"]/div/div/div[3]/div/button
	@FindBy(xpath="//*[@id='header-login-form']/div/div/div[3]/div/button")
	private WebElement submitButton;
	


	public MainPage(WebDriver driver)
	{
		super(driver);
		waitForElement(loginLink);
		Assert.assertTrue(shirteeLogo.isDisplayed());
	}
	
	public void performLogin(String eMail, String passWord)
	{
		loginLink.click();
		waitForElement(loginEmail);
		sendKeys(loginEmail, eMail);
		sendKeys(loginPassword, passWord);
		submitButton.click();
	}
	
}
