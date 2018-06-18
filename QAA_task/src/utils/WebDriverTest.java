package utils;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverTest {

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	public WebDriverTest() {
	 // Optional, if not specified, WebDriver will search your path for chromedriver.
		System.setProperty("webdriver.chrome.driver", "\\PROJECTS\\MayaFS\\Auto_test\\Java_code\\Maya_Kiosk\\chromedriver.exe");
		System.setProperty("webdriver.chrome.logfile", "\\PROJECTS\\MayaFS\\Auto_test\\Java_code\\Maya_Kiosk\\log\\chromedriver.log");
		System.setProperty("webdriver.chrome.verboseLogging", "false");
		DesiredCapabilities capability = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		
		capability.setBrowserName("chrome");
		capability.setPlatform(Platform.WINDOWS);
		options.addArguments("disable-infobars");														//removes  notification bar
		options.addArguments("--lang=en-GB");															//set browser lang. to ENG														
		capability.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(capability);                           								//for local PC    
		//driver = new RemoteWebDriver(new URL("http://192.168.21.128:4444/wd/hub"), capability);   	//For remote PC
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	
	public void stop() {
		  driver.quit();
		    String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
		}

	public WebDriver getDriver() {
		return driver;
	}
}
	

