package com.shop.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.qa.customlistener.WebEventListener;
import com.qa.util.TestUtil;


public class TestBase {

	// Defined prop in gloabal label to use the entire class 
	public static WebDriver driver; // accessible every where - public
	public static Properties prop;
	
	// these 2 declaration to implement WebEventListener
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	
	 //Declare ThreadLocal Driver (ThreadLocalMap) for ThreadSafe Tests
    final ThreadLocal<RemoteWebDriver> dr = new ThreadLocal<RemoteWebDriver>();

	/**
	 * This Method will load the config file  
	 * 
	 * 
	 */

	public void loadConfig() {
		prop = new Properties();
		// Constructor
		try {
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(ip);
			log.info("Config File has been loaded");
			
		}
		catch(FileNotFoundException e ){
			log.info("Exceptions Occurs--"+e.getMessage());
			e.printStackTrace();
		}
		catch(IOException e){
			log.info("Exceptions Occurs--"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/*public static void main(String[] args ) {
		
		System.out.println(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\config\\config.properties");
		
	}*/
	
	
	/**
	 * 
	 * This method will is responsible for browser selection and navigation of application URL
	 * @throws IOException 
	 * 
	 */

	// **** browser for grid managed thrpugh parameter - test ng concept ***//
	@Parameters("myBrowser")
	@BeforeClass
	public void initilisation(String myBrowser ) throws IOException {
		
		loadConfig();
		log.info("======= Execution Started ===========");
		String browsername = prop.getProperty("browser");
		String url=prop.getProperty("url");
		String executiontype = prop.getProperty("executiontype");
		
		//**** Log4J Propertyconfiguration ** set path for available log4J.properties file **//
			String log4jConfPath = "log4j.properties";
			PropertyConfigurator.configure(log4jConfPath);
		//**** END OF LOG4J Configuration		
		if(executiontype.equalsIgnoreCase("S"))	{
			
			if(browsername.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Drivers\\chromedriver.exe");
				//driver = new ChromeDriver();
				
				//**** Notification handling by chrome option ****//
				   // Create object of HashMap Class
				Map<String, Object> prefs = new HashMap<String, Object>();
		              
		                // Set the notification setting it will override the default setting
				prefs.put("profile.default_content_setting_values.notifications", 2);
				 
		                // Create object of ChromeOption class
				ChromeOptions options = new ChromeOptions();
		 
		                // Set the experimental option
				options.setExperimentalOption("prefs", prefs);
		 
		                // pass the options object in Chrome driver
		 
				 driver = new ChromeDriver(options);
				 log.info("Initilisation of --"+ prop.getProperty("browser") + "-- Browser");
				
			}
			else if (browsername.equalsIgnoreCase("FF")) {
				System.setProperty("WebDriver.gecko.driver", System.getProperty("user.dir")+ "\\Drivers\\geckodriver.exe");
				driver = new FirefoxDriver();
				 log.info("Failed to Initilise --"+ prop.getProperty("browser") + "-- Browser");
			}

			
			/***** Event Listener  Implementation 
			*
			*
			******/
			// Initializing EventFiringWebDriver using Firefox WebDriver instance
			e_driver = new EventFiringWebDriver(driver);

			// Now create object of EventListerHandler to register it with EventFiringWebDriver
			eventListener = new WebEventListener();
			//register eventlistener driver
			e_driver.register(eventListener);
			//Assign eventlistenr driver to driver 
			driver = e_driver;
			
			/// ************************ End of Event Listener********
					
		
			driver.manage().deleteAllCookies();
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
			driver.get(url);
			log.info("navigating to :-" + url);
			
		}
		
		if(executiontype.equalsIgnoreCase("P")) {
			 
		    
		    RemoteWebDriver driver1 = null;

			if (myBrowser.equals("chrome")) {
				
				
				//System.out.println(System.getProperty("user.dir"));
				//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				//System.out.println(" Executing on CHROME");	
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.WINDOWS);
				String Node = "http://192.168.43.208:5001/wd/hub";
				driver1 = new RemoteWebDriver(new URL(Node), cap);
				//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// Launch website
				 log.info("Initilisation of Browser --"+ myBrowser + "-- Browser");
			
				
			} else if (myBrowser.equals("firefox")) {
				//System.out.println(System.getProperty("user.dir"));
				//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				//System.out.println(" Executing on FireFox");
				String Node = "http://192.168.43.208:5002/wd/hub";
				DesiredCapabilities cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				cap.setPlatform(Platform.WINDOWS);
				driver1 = new RemoteWebDriver(new URL(Node), cap);
				log.info("Initilisation of Browser --"+ myBrowser + "-- Browser");
				
			} else if (myBrowser.equalsIgnoreCase("ie")) {
				//System.out.println(" Executing on IE");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("ie");
				cap.setPlatform(Platform.WINDOWS);
				String Node = "http://192.168.43.208:5003/wd/hub";
				driver1 = new RemoteWebDriver(new URL(Node), cap);
			//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				log.info("Initilisation of Browser --"+ myBrowser + "-- Browser");
				
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
			
			setDriver(driver1);
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			
		}
		
				
		// Fetch pageload time out variable value from Util Class
		// no need to import , all are public variable accessible here 
		// control then mouse over to reach in class
		
	}
	
	
	
	
	
	public WebDriver getDriver() {
		return dr.get();
	}
	
	public void setDriver(RemoteWebDriver driver1) {
		dr.set(driver1);
	}
	
	/*@AfterClass(alwaysRun = true)
	public void endTest() {
		tearDown();
	}*/

	
	/**
	 * This Method will close the browser 
	 * 
	 * 
	 * 
	 *
	 */
	
	@AfterClass
	public void tearDown() {
		try {
			driver.quit();
			log.info("Browser is closed");
		}catch(Exception Ex) {
			log.info("Exception Occurs "+Ex.getMessage());
			Ex.toString();
		}
		
		//extent.endTest(test);
		//extent.flush();
	}
	
	/**
	 * 06/09/2018
	 * 
	 * This Method will check the element until its not clickable in given timeperiod
	 * 
	 * @param driver
	 * @param locator
	 * @param timeout
	 * 
	 */
		
	
	public void clickon(WebDriver driver,WebElement locator,int timeout  ) {
		//Explicit Wait Implementation 
		try {
			new WebDriverWait(driver,timeout).
			ignoring(StaleElementReferenceException.class).
			until(ExpectedConditions.elementToBeClickable(locator)).click();
			locator.clear();
		}catch(Exception e) {
			log.info("Exception Occurs "+e.getMessage());
			e.toString();
		}
			
	}
}

