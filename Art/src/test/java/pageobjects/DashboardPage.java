package pageobjects;


import java.math.BigDecimal;
import java.text.NumberFormat;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends PageObject{
	
	
	@FindBy(xpath="//*[@id='top']/body/div[1]/div[2]/div[1]/div/div[2]/div/div[1]/div/div/span[1]")
	private WebElement ubersicht;
	
	//Header Product/Profits
	@FindBy(xpath="//*[@id='top']/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[1]/span[2]")
	private WebElement verkaufteProducte; //format = x/y
	
	@FindBy(xpath="//*[@id='top']/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[2]/span[2]/span")
	private WebElement gesamtgewinn;
	
	@FindBy(xpath="//*[@id='top']/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[3]/span[2]/span")
	private WebElement gewinnVerfugbar;
	
	@FindBy(xpath="//*[@id='top']/body/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div/div[4]/span[2]/span")
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
	
	
	public DashboardPage(WebDriver driver)
	{
		super(driver);
	}
	
	public int[] getVerkaufteProducte()
	{
		String[] str = verkaufteProducte.getText().split("/");
		
		int[] verk = {0,0};
		verk[0] = Integer.parseInt(str[0]);
		verk[1] = Integer.parseInt(str[1]);
		
		return verk;
	}
	
	public BigDecimal getGesamtgewinn()
	{
		String s = gesamtgewinn.getText();
		BigDecimal d = formatStringProfits(s);
		
		return d;
	}
	
	public BigDecimal getGewinnVerfugbar()
	{
		String s = gewinnVerfugbar.getText();
		BigDecimal d = formatStringProfits(s);
		
		return d;
	}

	public BigDecimal getGewinnAusstehend()
	{
		String s = gewinnAusstehend.getText();
		BigDecimal d = formatStringProfits(s);
		
		return d;
	}
	
	public int getVerkaufeSold()
	{
		String s = verkaufeSoldItems.getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkaufeProduction()
	{
		String s =  verkaufeProductionItems.getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkHeuteSold()
	{
		String s = verkHeuteSoldItems.getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkHeuteProduction()
	{
		String s = verkHeuteProductionItems.getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkGesternSold()
	{
		String s = verkGesternSoldItems.getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public int getVerkGesternProduction()
	{
		String s = verkGesternProductionItems.getText();
		int i = Integer.parseInt(s);
		return i;
	}
	
	public BigDecimal getAktuellerGewinn()
	{
		String s = aktuellerGewinn.getText();
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
		//double d = Double.parseDouble(newStr[0]);
		nf.format(a);
		return a;
	}
	
}
