/*
 * 	DriverContainer class
 * 
 *  Class shares web driver
 */

	package utils;
	
	import org.openqa.selenium.WebDriver;
	import utils.BrowserWebDriver;

	public class DriverContainer {
	
	private static DriverContainer driverContainer = new DriverContainer();
	private BrowserWebDriver webDriverTest;
	
	private DriverContainer(){
		webDriverTest = new BrowserWebDriver();
	}
		
	public static DriverContainer getInstance(){
		return driverContainer;
	}
		
	public WebDriver getDriver() {
		return webDriverTest.getDriver();
	}

	public static void stopDriver(WebDriver driver){
		if(driver!=null){
			driver.quit();
			driver=null;
		}
	}
	}