package pageobjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class DashboardPromoPage extends PageObject{

	private static final String PROMO_CODE_ERR_MSG_NOT_DISPL = "Promo code error message is not displayed! \n";
	private static final String PROFIT_ERR_MSG_NOT_DISPL = "Profit error message is not displayed! \n";
	private static final String PROMO_ERR_POPUP_NOT_DISPL = "Promo error popup is not displayed! \n";
	
	@FindBy(id = "promo_code")
	private WebElement promoCodeInputField;
	
	@FindBy(id = "discount_amount")
	private WebElement discountAmountInputField;
	
	@FindBy(id = "add_code")
	private WebElement addCodeButton;
	
	@FindBy(xpath = "//input[@id='discount_amount']/../div")
	private WebElement emtyProfitMsg;
	
	@FindBy(xpath = "//input[@id='promo_code']/../div")
	private WebElement promoCodeValidationMsg;
	
	@FindBy(id = "create-promo-error")
	private WebElement promoErrorPopup;
	
	@FindAll({@FindBy(xpath="//tbody[@id='coupons_list']/tr")})
	private List<WebElement> promoTableRows;
	
	@FindBy(xpath = "//tbody[@id='coupons_list']/tr[2]/td[1]")
	private WebElement createdPromoCode;
	
	@FindBy(xpath = "//tbody[@id='coupons_list']/tr[2]/td[3]")
	private WebElement createdPromoDiscount;
	
	@FindBy(xpath="//tbody[@id='coupons_list']/tr[2]/td[5]/button[1]")
	private WebElement deleteCreatedPromoButton;
	
	public DashboardPromoPage(WebDriver driver){
		super(driver);
	}
	
	public List<WebElement> getPromoTableRows() {
		return promoTableRows;
	}	

	public WebElement getCreatedPromoCode() {
		return createdPromoCode;
	}

	public WebElement getCreatedPromoDiscount() {
		return createdPromoDiscount;
	}

	public WebElement getDeleteCreatedPromoButton() {
		return deleteCreatedPromoButton;
	}
	
	public WebElement getPromoErrorPopup() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(promoErrorPopup));
		} catch (TimeoutException e) {
			Assert.fail(PROMO_ERR_POPUP_NOT_DISPL);
		}
		return promoErrorPopup;
	}
	
	public WebElement getEmtyProfitMsg() {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(emtyProfitMsg));
		} catch (TimeoutException e) {
			Assert.fail(PROFIT_ERR_MSG_NOT_DISPL);
		}
		return emtyProfitMsg;
	}

	public WebElement getPromoCodeValidationMsg() {
		
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOf(promoCodeValidationMsg));
		} catch (TimeoutException e) {
			Assert.fail(PROMO_CODE_ERR_MSG_NOT_DISPL);
		}
		return promoCodeValidationMsg;
	}
	
	public void deleteSecondPromo(){
		
		try {
			deleteCreatedPromoButton.click();
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(new ExpectedCondition<Boolean>() {
			    public Boolean apply(WebDriver driver) {
			        int tableSize = promoTableRows.size();
			        if(tableSize == 1) 
			            return true;
			        else
			            return false;
			    }
			});
		} catch (TimeoutException e) {
			Assert.fail("Timeout exception while deleting campaign! \n" + e);
		}
		catch (NoSuchElementException e) {
			Assert.fail("Delete button not found! \n" + e);
		}
	}
	
	public void addCode()
	{
		addCodeButton.click();
	}
	
	public void inputCode(String str){
		try {
			promoCodeInputField.clear();
			promoCodeInputField.sendKeys(str);
		} catch (NoSuchElementException e) {
			Assert.fail("Promo-code input field not found! \n"  + e);
		}
		
	}
	
	public void inputDiscountAmount(String amount){
		try {
			discountAmountInputField.clear();
			discountAmountInputField.sendKeys(amount);
		} catch (NoSuchElementException e) {
			Assert.fail("Discount amount input field not found! \n" + e);
		}

	}
}
