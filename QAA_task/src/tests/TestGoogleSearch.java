	package tests;

	import static org.testng.Assert.assertTrue;
	import org.apache.log4j.LogManager;
	import org.apache.log4j.Logger;
	import org.testng.annotations.BeforeMethod;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import pages.ResultsPg;
	import pages.SearchPg;
	import pages.WebPage;
	import utils.DataProviderClass;
	import utils.StringProcessor;

	public class TestGoogleSearch extends BaseTest {
	
	private Logger log = LogManager.getLogger("Test log: ");
	
	@BeforeMethod
    public void openGooglePg(){
		driver.get("http://google.com/");
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
    
/**
 * Opens Google. Searches for “automation”. 
 * Opens the first link on search results page and verifies that title contains searched word
 */
    @Test (enabled=true, priority=1, dataProvider="test1" )
    
    public void testSearchForWord(String wordToSearch, String linkToOpen){
    	log.info("Test testSearchForWord");
    	
    	StringProcessor sproc =  new StringProcessor();
    	SearchPg searchPg= new SearchPg();
    	sproc.setRegExp(wordToSearch);
    	ResultsPg resPg;
    	WebPage webPage;
		
		resPg = searchPg.searchForWord(wordToSearch);
		webPage = resPg.openLink(linkToOpen);
		assertTrue(sproc.searchWordInStrng(wordToSearch, webPage.getTitle()), "Is word '" +wordToSearch+"' found in title");
	}
    
/**
 * Opens Google. Searches for “automation”. Verifies that there is expected domain (“testautomationday.com”) on search results
 * pages (page: 1-5).	
 */
    @Test (enabled=true, priority=10, dataProvider="test2")
    
    	public void testSearchForLink(String wordToSearch, String numOfPagesToSearch, String searchDomain){
    	log.info("Test testSearchForLink");
    	
    	SearchPg searchPg= new SearchPg();
    	ResultsPg resPg;

    	resPg = searchPg.searchForWord(wordToSearch);
    	assertTrue (resPg.findDomain(numOfPagesToSearch, searchDomain));    
    }
	    	
}


