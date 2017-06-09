package pageobjects;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DashboardPage extends PageObject{
	
	//Header Product/Profits
	@FindBy(xpath="//div[@class='new-account-stat sold-products']/span[2]")
	private WebElement verkaufteProducte; //format = x/y
	
	@FindBy(xpath="//div[@class='new-account-stat ttl-profit']/span[2]/span")
	private WebElement gesamtgewinn;
	
	@FindBy(xpath="//div[@class='new-account-stat balance'][1]/span[2]/span")
	private WebElement gewinnVerfugbar;
	
	@FindBy(xpath="//div[@class='new-account-stat balance'][2]/span[2]/span")
	private WebElement gewinnAusstehend;
	
	//Campaign data
	//Verkaufe
	@FindBy(xpath="//*[@id='campaign_items_content']/tr[1]/td[4]/div/span[1]")
	private WebElement verkaufeSoldItems;
	
	@FindBy(xpath="//*[@id='campaign_items_content']/tr[1]/td[4]/div/span[2]")
	private WebElement verkaufeProductionItems;
	
	//Verk.heute
	@FindBy(xpath="//*[@id='campaign_items_content']/tr[1]/td[5]/div/span[1]")
	private WebElement verkHeuteSoldItems;
	
	@FindBy(xpath="//*[@id='campaign_items_content']/tr[1]/td[5]/div/span[2]")
	private WebElement verkHeuteProductionItems;
	
	//Verk.gestern
	@FindBy(xpath="//*[@id='campaign_items_content']/tr[1]/td[6]/div/span[1]")
	private WebElement verkGesternSoldItems;
	
	@FindBy(xpath="//*[@id='campaign_items_content']/tr[1]/td[6]/div/span[2]")
	private WebElement verkGesternProductionItems;
	
	//Actueller Gewinn
	@FindBy(xpath="//*[@id='campaign_items_content']/tr[1]/td[7]/div/span/span")
	private WebElement aktuellerGewinn;
	
	@FindBy(xpath = "//a[@href='https://dev.shirtee.de/testautocampaign2']")
	private WebElement campaignLink;
	
	@FindBy(xpath = "//input[@type='checkbox'][1]")
	private WebElement firstCampaignStatusCheckbox;
	
	@FindBy(xpath = "//div[@class='b-label-checkbox'][1]")
	private WebElement firstCampaignStatusSwitcher;
	
	//pagination field input
	@FindBy(id = "page_direct")
	private WebElement pageNumberInput;
	
	@FindBy(xpath = "//p[@class = 'amount amount--has-pages']")
	private WebElement pagesRange;
	
	//loading popup
	@FindBy(id = "sort_campaigns-loading")
	private WebElement sortingCampaignPopup;
	
	@FindBy(xpath = "//div[@class='b-dashboard-search-in']/input")
	private WebElement searchInputField;
	
	@FindAll({@FindBy(xpath="//div[@id='search-result']/div")})
	private List<WebElement> searchResults;

	public DashboardPage(WebDriver driver)
	{
		super(driver);
	}
	
	public void searchCampaign(String name)
	{
		searchInputField.sendKeys(name);
	}
	
	public List<WebElement> getSearchResults(){
		return searchResults;
	}
	
	public void waitForCampaignLoading()
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//div[@id = 'sort_campaigns-loading']"
				+ "[contains(@style, 'display: none')]")));
	}
	
	public String getPageRange()
	{
		return pagesRange.getText();
	}
	
	public void switchCampaignStatus()
	{
		getFirstCampaignStatusSwitcher().click();	
	}

	public WebElement getFirstCampaignStatusSwitcher() {
		return firstCampaignStatusSwitcher;
	}
	
	public WebElement getVerkaufteProducte() {
		return verkaufteProducte;
	}

	public WebElement getGesamtgewinn() {
		return gesamtgewinn;
	}

	public WebElement getGewinnVerfugbar() {
		return gewinnVerfugbar;
	}

	public WebElement getGewinnAusstehend() {
		return gewinnAusstehend;
	}

	public WebElement getVerkaufeSoldItems() {
		return verkaufeSoldItems;
	}

	public WebElement getVerkaufeProductionItems() {
		return verkaufeProductionItems;
	}

	public WebElement getVerkHeuteSoldItems() {
		return verkHeuteSoldItems;
	}

	public WebElement getVerkHeuteProductionItems() {
		return verkHeuteProductionItems;
	}

	public WebElement getVerkGesternSoldItems() {
		return verkGesternSoldItems;
	}

	public WebElement getVerkGesternProductionItems() {
		return verkGesternProductionItems;
	}

	public WebElement getAktuellerGewinn() {
		return aktuellerGewinn;
	}
	
	public void waitForDataToShow()
	{
		waitForElement(getVerkaufteProducte());
	}
	
	public String getCampaignStatus()
	{
		return firstCampaignStatusCheckbox.getAttribute("checked");
	}

	public void campaignLinkClick()
	{
		campaignLink.click();
	}
	
	public void sendPageNumber(String str)
	{
		pageNumberInput.clear();
		pageNumberInput.sendKeys(str);
		pageNumberInput.sendKeys(Keys.ENTER);
	}


}
