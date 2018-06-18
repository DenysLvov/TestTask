package utils;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListProcessor {
	
	private List<WebElement> webElemList = new ArrayList<>();
	private	WebDriver driver;
	
	public ListProcessor(WebDriver driver){
			this.driver=driver;
	}
/*
* Adds elements (by locator)to the list	
*/
	public List<WebElement> addWebElemInList(WebDriver driver, By locator){
		webElemList.clear();									
		webElemList. addAll(driver.findElements(locator));		//Generates list with links
		return webElemList;
		}
	
	
}