package base;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.testng.annotations.*;

import pageobjects.MainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class FunctionalTest {

protected static WebDriver driver;
	
	@BeforeClass
	public static void setUp(){
		System.setProperty("webdriver.chrome.driver",
				"Resources/chromedriver");
		driver = new ChromeDriver();
	}
	
	@BeforeMethod
	public void setEnv(){
		driver.get(System.getProperty("mainPageURL"));
		MainPage mainPage = new MainPage(driver);
		driver.manage().window().maximize();

		mainPage.switchToDELocale();
	}
	
	@AfterMethod
	public void cleanUp(){
		driver.manage().deleteAllCookies();
		
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}	
	
}
