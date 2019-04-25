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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.DataProviderClass;
import pages.ResultsPg;
import pages.SearchPg;
import pages.WebPage;
import utils.StringProcessor;
import utils.WebDriverTest;

public class TestSet {

	WebDriverTest wb = new WebDriverTest();
	DataProviderClass dataProviderHelper;
	private static Logger log = LogManager.getLogger("Test set log: ");
			
			
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
	log.info("End of test");
	// Add close browser
	}
		    
	@DataProvider (name="test1")
	public Object[][] searchWordInTitle() {
	dataProviderHelper =new DataProviderClass();  
	return dataProviderHelper.readTestData("//test1_data.txt");  
	}
	
	@DataProvider (name="test2")
	public Object[][] searchWordInDomain() {
	dataProviderHelper =new DataProviderClass();  
	return dataProviderHelper.readTestData("//test2_data.txt");  
	}
	
	/*
	 *  Open Google. Search for “automation”. Open the first link
	 *	on search results page. Verify that title contains searched word
	 */
	
	@Test (enabled=false, dataProvider="test1")
	public void testSearchForWord(String wordToSearch, String linkToOpen){
		log.info("Test testSearchForWord");
	SearchPg searchPg= new SearchPg(wb.getDriver());
	ResultsPg resPg;
	WebPage webPage;
	StringProcessor sproc =  new StringProcessor();
	sproc.setRegExp(wordToSearch);
		    			    	   	
	//------------Test---------------
	resPg = searchPg.searchForWord(wb.getDriver(), wordToSearch);
	webPage = resPg.openLink(wb.getDriver(), linkToOpen);
	assertTrue(sproc.searchWordInStrng(wordToSearch, webPage.getTitle()), "Is word '" +wordToSearch+"' found in title");
	}
	
	/*
	 * Open Google. Search for “automation”. Verify that there is expected domain (“testautomationday.com”)
	 * on search results pages (page: 1-5)
	 */
	
	@Test  (enabled=true, dataProvider="test2")
	public void testSearchForLink(String wordToSearch, String numOfPagesToSearch, String searchDomain){
		log.info("Test testSearchForLink");
	SearchPg searchPg= new SearchPg(wb.getDriver());
	ResultsPg resPg;
	
	//------Test data------------
		
	By expDomainLocator = By.xpath("//div//cite[@class]");
	
	//--------------Test----------
	resPg = searchPg.searchForWord(wb.getDriver(), wordToSearch);
	assertTrue (resPg.findElemOnPage(numOfPagesToSearch, searchDomain, expDomainLocator));    
   	}
	    	
}


