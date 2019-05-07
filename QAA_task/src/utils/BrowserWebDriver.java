/*
 * BrowserWebDriver class
 * 
 * Creates, setup and closes driver
 */

	package utils;

	import org.openqa.selenium.Platform;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.remote.DesiredCapabilities;

	public class BrowserWebDriver {

	private WebDriver driver;
		
	public BrowserWebDriver() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.logfile", System.getProperty("user.dir")+"\\log\\chromedriver.log");
		System.setProperty("webdriver.chrome.verboseLogging", "false");
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WINDOWS);
		options.addArguments("disable-infobars");									//removes notification bar
		options.addArguments("--lang=en-GB");										//set browser lang. to ENG														
		capability.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capability);                           	    
	}
	
	public void stop() {
		driver.quit();
	}

	public WebDriver getDriver() {
		return driver;
	}
	}
	

