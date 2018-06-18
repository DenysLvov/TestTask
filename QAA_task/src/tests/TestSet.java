package tests;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.ResultsPg;
import pages.SearchPg;
import pages.WebPage;
import utils.StringProcessor;
import utils.WebDriverTest;

public class TestSet {

			WebDriverTest wb = new WebDriverTest();
			private static final Logger LOGGER = LogManager.getLogger("Test set log: ");
			
			
		    @BeforeClass
		    public  void startDriver(){
		    	PropertyConfigurator.configure("Log4j.properties");
		    }
		    
		    @AfterClass
		    public void tearDown(){
		        wb.getDriver().close();
		    }
		        
		    @BeforeMethod
		    public void startTest(){
		    wb.getDriver().manage().window().setSize(new Dimension(1920, 1080));
		    wb.getDriver().get("http://google.com/");
		    }
		    
		    @AfterMethod
		    public void afterMethod() {
		    LOGGER.info("End of test");
		    // Add close browser
		    } 
		    
		    @Test   (enabled=false)
		    public void testSearchForWord(){
		    	LOGGER.info("Test testSearchForWord");
		    	SearchPg searchPg= new SearchPg(wb.getDriver());
		    	ResultsPg resPg;
		    	WebPage webPage;
		    	StringProcessor sproc =  new StringProcessor();
		    			    	
		    	//----------Test data-------------
		    	String wordToSearch = "automation";
		    	int linkToOpen=1;
		    	By searchWordlinkLocator = By.xpath("//h3//a[contains(.,'"+wordToSearch+"')]");
		    	sproc.setRegExp(".*("+wordToSearch+").*");
		    			    	   	
		    	//------------Test---------------
		    	resPg = searchPg.searchForWord(wb.getDriver(), wordToSearch);
		    	webPage = resPg.openLink(wb.getDriver(), linkToOpen, searchWordlinkLocator);
		    	assertTrue(sproc.searchWordInStrng(wordToSearch, webPage.getTitle()));		//returns true if <SearchWord> is found in Title
		    }
		    
		    @Test  (enabled=true)
		    public void testSearchForLink(){
		    	LOGGER.info("Test testSearchForLink");
		    	SearchPg searchPg= new SearchPg(wb.getDriver());
		    	ResultsPg resPg;
		    	
		    	//------Test data------------
		    	String wordToSearch = "automation";
		    	int numOfPagesToSearch = 10;									//Number of pages where search is made
		    	String searchDomain ="testautomationday.com";
		    	By expDomainLocator = By.xpath("//div//cite[@class]");
		    	
		    		    	
		    	//--------------Test----------
		    	resPg = searchPg.searchForWord(wb.getDriver(), wordToSearch);
		    	assertTrue (resPg.findElemOnPage(numOfPagesToSearch, searchDomain, expDomainLocator));    
		    	
		    	}
		     	
		    	
}


