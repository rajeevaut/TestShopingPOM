package com.qa.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.shop.qa.base.TestBase;
import com.shop.qa.pages.HomePage;
import com.shop.qa.pages.SearchResultPage;

public class TC001_SearchProduct extends TestBase{

	HomePage homepage;  // homepage 
	SearchResultPage searchresultpage;
	
	public TC001_SearchProduct() {
		// call superclass constructor by super keyword 
		super();
		
	}
	
	@BeforeClass
	public void setup() {
		initilisation();   /// we can keep read config , select browser in all 
		log.info("Script--"+this.getClass().getName().toUpperCase() );
	}
	
	
	//@Test(retryAnalyzer = com.qa.customlistener.RetryAnalyzer.class)
	
	@Test
	public void SearchProduct() throws InterruptedException {
		try {
			homepage = new HomePage(); 
			searchresultpage = new SearchResultPage();
			
			homepage.ValidateHomePageTitle();
			Thread.sleep(2000);
			homepage.searchItem("Gift personalized metal ball pens with custom logo");
			searchresultpage.VerifySearchResultPage();
			log.info("Serached for Item--Gift personalized metal ball pens with custom logo");
			
			
		}catch(Exception e) {
			
			
		}
		
	}
	
	//@BeforeTest -- launchapp
	//@AfterMethod() --
	//@BeforeMethod
	//@afterClass  -- these 4 we can declair in Test Base to make it more generic
	
}
 