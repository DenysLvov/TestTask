package pages;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.ListProcessor;
import utils.Navigator;
import utils.StringProcessor;


public class ResultsPg {

	private static final Logger LOGGER = LogManager.getLogger("ResultPage log: ");
	private WebDriver driver;
	private By nextButton = By.cssSelector("#pnnext > span:nth-child(2)");
	private Navigator navigator;
	
	public ResultsPg(WebDriver driver) {
		this.driver = driver;
		navigator = new Navigator(driver);
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	public WebPage openLink(WebDriver driver, int linkToOpen, By locator) {
		new ListProcessor(driver).addWebElemInList(driver, locator).get(linkToOpen).click();		//generates list with links and opens by linkToOpen
		return new WebPage(driver);
	}
	
/*
 * Returns TRUE if expected element (searchDomain) was found on the web page
 */	
	public boolean findElemOnPage(int pageCounter, String searchDomain, By locator) {
		List<WebElement> list;
		boolean isFound=false;
		StringProcessor sproc =  new StringProcessor();
		sproc.setRegExp(".*("+searchDomain+").*");
		int pageNum=0;
		while(pageNum <pageCounter){
			list = new ListProcessor(driver).addWebElemInList(driver, locator);
			for(WebElement w:list){															//Search word in the list
				if(sproc.searchWordInStrng(searchDomain, w.getText())){
					LOGGER.info("Link was found on "+pageNum+" page.");
					LOGGER.info("Link is "+w.getText());
					LOGGER.info("Expected link is "+searchDomain);
					return isFound = true;													//! should return this value
					}
				}	//end of for each
				if(navigator.isElementPresent(nextButton)){									//If [Next] button is available - click it 			  
					pageNum++;																//Switch to the next Page
					driver.findElement(nextButton).click();
					}//end of if
					else{LOGGER.info("Link was found - "+isFound);
						return isFound;}													//returns false if nothing was found and NEXT is n/a
		    	}
		LOGGER.error("Link was not found. Processed "+pageNum+" pages.");
		return isFound; 																	//returns false if nothing was found
		}
}

