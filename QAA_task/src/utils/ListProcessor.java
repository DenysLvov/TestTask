/*
 * ListProcessor class.
 * 
 * Generates list of WebElements
 */
	package utils;

	import java.util.ArrayList;
	import java.util.List;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

	public class ListProcessor {
	
	private List<WebElement> webElemList;
	private	WebDriver driver;
	
	public ListProcessor(WebDriver driver){
			this.driver=driver;
	}
/*
* Adds elements (by locator)to the list	
*/
	public List<WebElement> listWebElmnts(By locator){
		webElemList = new ArrayList<>();									
		webElemList.addAll(driver.findElements(locator));		
		return webElemList;
	}
	}