package com.shop.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.util.TestUtil;
import com.shop.qa.base.TestBase;

public class HomePage extends TestBase{

	
	//************** OR 
	@FindBy(xpath = "//div[@class='sc-hd-row sc-hd-main']//div[@class='ui-searchbar-main']//input[@name='SearchText' and @class='ui-searchbar-keyword']")
	WebElement searchinput;
	
	@FindBy(xpath = "//div[@class='sc-hd-row sc-hd-main']//input[@class='ui-searchbar-submit']")
	WebElement searchbtn;
	
	@FindBy(xpath = "") // Women
	WebElement category;
	
	public  final String title = driver.getTitle();
	
	// ****************** Initilization of PageFactory 
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
		
	//**** Methods *** 
	//1 . Return Title of Landing page
	public void ValidateHomePageTitle()
	{
		
				
		Assert.assertEquals(title, prop.getProperty("title"), "Title Not Matching ");
			
		
	}
		
	//2. 
	public SearchResultPage searchItem(String itemname) throws InterruptedException
	{
		if(itemname.trim().length()>=1) {
			if(searchinput.isEnabled()) {
				
				Actions performAct = new Actions(driver);
				try {
					performAct.sendKeys(searchinput, itemname).build().perform();
					System.out.println("Error NOT Occured while enetreing the text for Search Item");
					log.info("Error NOT Occured while enetreing the text for Search Item");
				}
				catch(Exception e) {
					e.printStackTrace();
					System.out.println("Error Occured while enetreing the text for Search Item"+e);
					log.info("Error Occured while enetreing the text for Search Item"+e);
				}
				
	
				//searchinput.sendKeys(itemname);
				clickon(driver, searchbtn, TestUtil.EXPLICIT_TIMEOUT);
				
			
			}
			else {
				log.info("Failed to found SearchItem Object");
				
			}
		}
			
		return new SearchResultPage();
		// return new SearchResultPage(); // object of next page // page chain
	}
	
	
	public void shopByCategory(String categoryname) {
		
	}
	
	
}
