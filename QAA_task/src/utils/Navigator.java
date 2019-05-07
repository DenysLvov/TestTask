/*
 * Navigator class
 * 
 * Contains methods navigate on web page	
 */
	package utils;
	
	import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.WebDriver;

	public class Navigator {
	private WebDriver driver;
	private Logger log = LogManager.getLogger("Navigator log: ");
	
	public Navigator() {
		this.driver = DriverContainer.getInstance().getDriver();
		PropertyConfigurator.configure("Log4j.properties");
	}
	
/**
 * Is element present on the page	
 * 
 */
	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			log.error("Element wasn't found "+locator);
	        return false;
		}
	}
	
	public static void delay(long delay) {
		try { 
			Thread.sleep(delay); 
		} catch (Exception e){ 
		throw new RuntimeException(e);}
		}
	}
