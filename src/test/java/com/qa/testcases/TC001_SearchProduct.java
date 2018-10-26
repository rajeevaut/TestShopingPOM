package com.qa.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.customlistener.WebEventListener;
import com.qa.util.ExcelDataProvider;
import com.qa.util.TestUtil;

import com.shop.qa.base.TestBase;
import com.shop.qa.pages.HomePage;
import com.shop.qa.pages.SearchResultPage;

//@Listeners(com.qa.customlistener.Listener.class)
public class TC001_SearchProduct extends TestBase{

	HomePage homepage;  // homepage 
	SearchResultPage searchresultpage;
	String searchtext;

	public TC001_SearchProduct() {
		// call superclass constructor by super keyword 
		super();
		
	}
	
	/*@BeforeClass
	public void setup() {
		initilisation();   /// we can keep read config , select browser in all 
		log.info("Script--"+this.getClass().getName().toUpperCase() );
	}*/
	
	
	//@Test(retryAnalyzer = com.qa.customlistener.RetryAnalyzer.class)
	
	/*@DataProvider(name="SearchProvider")
	public Object[][] getScriptData(){
		Object data[][] = ExcelDataProvider.getTestData(null);
		return (data);
	}*/
	
	
	// pass same count of data as per parameter based on excelsheet
	//@Test(dataProvider="SearchProvider")
	
	@Test
	public  void SearchProduct() throws InterruptedException{
		
		//try {
		
		
			homepage = new HomePage(); 
			searchresultpage = new SearchResultPage();
			homepage.ValidateHomePageTitle();
			Thread.sleep(2000);
		//	System.out.println("-----------"+SearchProduct);
			homepage.searchItem("Pen");
			searchresultpage.VerifySearchResultPage();
			searchresultpage.TotalProduct();
			
			
			
		/*}
		catch (AssertionError e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}*/
		
	}
	

	
	//@BeforeTest -- launchapp
	//@AfterMethod() --
	//@BeforeMethod
	//@afterClass  -- these 4 we can declair in Test Base to make it more generic
	
}
 