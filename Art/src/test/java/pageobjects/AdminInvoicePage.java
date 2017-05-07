package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminInvoicePage extends PageObject{

	@FindBy(id = "invoice_do_shipment")
	private WebElement shipmentCheckbox;
	
	@FindBy(xpath = "//button[contains(@title,'Rechnung erstellen')]")
	private WebElement submitOrderButton;
	
	public AdminInvoicePage(WebDriver driver)
	{
		super(driver);
	}
	
	public void checkShipmentCheckbox()
	{
		if (!shipmentCheckbox.isSelected())
		{
			shipmentCheckbox.click();
		}
	}
	
	public void submitOrder()
	{
		submitOrderButton.click();
	}
	
}
