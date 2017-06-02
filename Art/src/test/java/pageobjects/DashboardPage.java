package pageobjects;


import java.math.BigDecimal;
import java.text.NumberFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DashboardPage extends PageObject{
	
	
	@FindBy(xpath="//*[@id='top']/body/div[1]/div[2]/div[1]/div/div[2]/div/div[1]/div/div/span[1]")
	private WebElement ubersicht;
	
	//Header Product/Profits
	@FindBy(xpath="//*[@id='top']/body/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/span[2]")
	//*[@id="top"]/body/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/span[2]
	private WebElement verkaufteProducte; //format = x/y
	
	@FindBy(xpath="//*[@id='top']/body/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[2]/span[2]/span")
	private WebElement gesamtgewinn;
	//*[@id="top"]/body/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[2]/span[2]/span
	
	@FindBy(xpath="//*[@id='top']/body/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[3]/span[2]/span")
	private WebElement gewinnVerfugbar;
	
	@FindBy(xpath="//*[@id='top']/body/div[3]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[4]/span[2]/span")
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
	
	
	public DashboardPage(WebDriver driver)
	{
		super(driver);
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
	
	public int[] getVerkaufteProducteOld()
	{
		String[] str = getVerkaufteProducte().getText().split("/");
		
		int[] verk = {0,0};
		verk[0] = Integer.parseInt(str[0]);
		verk[1] = Integer.parseInt(str[1]);
		
		return verk;
	}
	
	
	public BigDecimal getGesamtgewinnOld()
	{
		String s = getGesamtgewinn().getText();
		BigDecimal d = formatStringProfits(s);
		
		return d;
	}
	
	public BigDecimal getGewinnVerfugbarOld()
	{
		String s = getGewinnVerfugbar().getText();
		BigDecimal d = formatStringProfits(s);
		
		return d;
	}

	public BigDecimal getGewinnAusstehendOld()
	{
		String s = getGewinnAusstehend().getText();
		BigDecimal d = formatStringProfits(s);
		
		return d;
	}
	
	public int getVerkaufeSoldOld()
	{
		String s = getVerkaufeSoldItems().getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkaufeProductionOld()
	{
		String s =  getVerkaufeProductionItems().getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkHeuteSoldOld()
	{
		String s = getVerkHeuteSoldItems().getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkHeuteProductionOld()
	{
		String s = getVerkHeuteProductionItems().getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkGesternSoldOld()
	{
		String s = getVerkGesternSoldItems().getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkGesternProductionOld()
	{
		String s = getVerkGesternProductionItems().getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public BigDecimal getAktuellerGewinnOld()
	{
		String s = getAktuellerGewinn().getText();
		BigDecimal d = formatStringProfits(s);
		
		return d;
	}
	
	public BigDecimal formatStringProfits(String str)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		
		String[] newStr = str.split(" ");
		newStr[0] = newStr[0].replaceAll(",", ".");
		
		BigDecimal a = new BigDecimal(newStr[0]);
		nf.format(a);
		return a;
	}



	
}
