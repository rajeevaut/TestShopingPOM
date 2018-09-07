package com.shop.qa.pages;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.util.TestUtil;
import com.shop.qa.base.TestBase;

public class SearchResultPage extends TestBase{

	@FindBy(xpath="//select[@class='sort-by']")
	WebElement sortby;
	
	//*********** Initilization of PageFactory
	public SearchResultPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void VerifySearchResultPage(){
		WebDriverWait wait = new WebDriverWait(driver,TestUtil.EXPLICIT_TIMEOUT);
		try {
			
			wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(sortby));
			log.info("Search Result Page Displayed with relavant items");
		}catch(Exception e) {
			log.info("Exception Occurs"+ e.getMessage());
			e.toString();
		}
	}
	
	
}
