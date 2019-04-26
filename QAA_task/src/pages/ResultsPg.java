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

	private static Logger log = LogManager.getLogger("ResultPage log: ");
	private WebDriver driver;
	private By linkLocator = By.xpath("//a//h3");
	private By nextButton = By.xpath("//a/span[text()='Next']");
	private By domainLinkLocator = By.xpath("//div//cite[@class]");
	
	private Navigator navigator;
	
	public ResultsPg(WebDriver driver) {
		this.driver = driver;
		navigator = new Navigator(driver);
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	public WebPage openLink(WebDriver driver, String linkToOpen) {
		int n = Integer.valueOf(linkToOpen);
		new ListProcessor(driver).addWebElemInList(driver, linkLocator).get(n).click();		//generates list with links and opens by linkToOpen
		return new WebPage(driver);
	}
	
/*
 * Returns TRUE if expected element (searchDomain) was found on the web page
 */	
	public boolean findDomain(String pageCounter, String searchDomain) {
		
		int numPg = Integer.valueOf(pageCounter);
				
		List<WebElement> list;
		boolean isFound=false;
		StringProcessor sproc =  new StringProcessor();
		sproc.setRegExp(searchDomain);
		int pageNum=0;
		while(pageNum <numPg){
			list = new ListProcessor(driver).addWebElemInList(driver, domainLinkLocator);
			for(WebElement w:list){															//Search word in the list
				if(sproc.searchWordInStrng(searchDomain, w.getText())){
					log.info("Link was found on "+pageNum+" page.");
					log.info("Link is "+w.getText());
					log.info("Expected link is "+searchDomain);
					return isFound = true;													//! should return this value
					}
				}	//end of for each
				if(navigator.isElementPresent(nextButton)){									//If [Next] button is available - click it 			  
					pageNum++;																//Switch to the next Page
					driver.findElement(nextButton).click();
					}//end of if
					else{log.info("Link was found - "+isFound);
						return isFound;}													//returns false if nothing was found and NEXT is n/a
		    	}
		log.error("Link was not found. Processed "+pageNum+" pages.");
		return isFound; 																	//returns false if nothing was found
		}
}

