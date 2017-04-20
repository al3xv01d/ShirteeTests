package base;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
import org.testng.annotations.*;
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
	
	@AfterMethod
	public void cleanUp(){
		driver.manage().deleteAllCookies();
	}
	
	@AfterClass
	public static void tearDown(){
		driver.quit();
	}	
	
}
