package utils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class Navigator {
	private WebDriver driver;
	
	public Navigator(WebDriver driver) {
		this.driver = driver;
	}
	
	//Is Element Present	
	public boolean isElementPresent(By locator) {
		 try {
			 driver.findElement(locator);
	            return true;
	        } catch (NoSuchElementException e) {
	        	System.err.println("No such element "+locator);
	        	return false;
	        }
	}
	
	//Timeouts	
		public static void delay(long delay) {
			try { 
				Thread.sleep(delay); 
				} catch (Exception e) 
				{ 
					System.out.println("exception in Navigator.delay()");
					throw new RuntimeException(e);}
				}
}
