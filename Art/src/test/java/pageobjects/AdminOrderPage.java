package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminOrderPage extends PageObject{

	@FindBy(xpath="//*[@id='sales_order_view_tabs_order_info_content']"
			+ "/div[1]/div[3]/div/div[2]/div/table/tbody/tr[1]/td[2]/a/strong")
	private WebElement customerName;
	
	@FindBy(xpath = "//button[contains(@title,'Rechnung')]")
	private WebElement invoiceButton;
	
	@FindBy(xpath = "//button[contains(@title,'Stornieren')]")
	private WebElement cancelButton;
	
	@FindBy(xpath = "//button[contains(@title,'Rechnungskorrektur')]")
	private WebElement creditMemoBtn;
	
	@FindBy(xpath = "//button[contains(@title,'Erstattung (offline)')]")
	private WebElement submitCreditMemoBtn;
	
	public AdminOrderPage(WebDriver driver)
	{
		super(driver);
	}
	
	public String getCustomerName()
	{
		return customerName.getText();
	}
	
	public void invoiceButtonClick()
	{
		invoiceButton.click();
	}
	
	public void cancelButtonClick()
	{
		cancelButton.click();
	}
	
	public void makeRefund()
	{
		creditMemoBtn.click();
		waitForElement(submitCreditMemoBtn);
		submitCreditMemoBtn.click();
	}
	
}
