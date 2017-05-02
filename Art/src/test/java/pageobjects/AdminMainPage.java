package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminMainPage extends PageObject{

	@FindBy(id = "username")
	private WebElement userName;
	
	@FindBy(id = "login")
	private WebElement password;
	
	@FindBy(xpath = "//*[@id='loginForm']/div/div[5]/input")
	private WebElement confirmButton;
	
	public AdminMainPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void sendCredentials(String userName, String password)
	{
		this.userName.sendKeys(userName);
		this.password.sendKeys(password);
	}
	
	
	public void submit()
	{
		confirmButton.click();
	}
	
}
