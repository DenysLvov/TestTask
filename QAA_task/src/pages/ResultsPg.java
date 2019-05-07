/*
 *  ResultsPg class.
 *  
 *  Class describes Page with results of search
 */
	package pages;

	import java.util.List;
	import org.apache.log4j.LogManager;
	import org.apache.log4j.Logger;
	import org.apache.log4j.PropertyConfigurator;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import utils.DriverContainer;
	import utils.ListProcessor;
	import utils.Navigator;
	import utils.StringProcessor;

	public class ResultsPg {

	private Logger log = LogManager.getLogger("ResultPage log: ");
	private WebDriver driver;
	private By linkLocator = By.xpath("//a//h3");
	private By nextButton = By.xpath("//a/span[text()='Next']");
	private By domainLinkLocator = By.xpath("//div//cite[@class]");
	
	private Navigator navigator;
	
	public ResultsPg() {
		this.driver = DriverContainer.getInstance().getDriver();
		navigator = new Navigator(driver);
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	public WebPage openLink(String linkToOpen) {
		int n = Integer.valueOf(linkToOpen);
		new ListProcessor(driver).listWebElmnts(linkLocator).get(n).click();		//generates list with links and opens by linkToOpen
		return new WebPage();
	}
	
/**
 * Returns true if expected element (expectDomain) was found on the web page
 */	
	public boolean findDomain(String pageCounter, String expectDomain) {
		int numPg = Integer.valueOf(pageCounter);
				
		List<WebElement> elemntList;
		boolean isFound=false;
		StringProcessor strngproc =  new StringProcessor();
		strngproc.setRegExp(expectDomain);
		int pageNum=0;
		
		while(pageNum < numPg){
			elemntList = new ListProcessor(driver).listWebElmnts(domainLinkLocator);
			for(WebElement elmnt:elemntList){														//Search word in the list
				
				if(strngproc.searchWordInStrng(expectDomain, elmnt.getText())){
					log.info("Link was found on "+pageNum+" page.");
					log.info("Link is "+elmnt.getText());
					log.info("Expected link is "+expectDomain);
					return isFound = true;													
					}
				}	
			
				if(navigator.isElementPresent(nextButton)){									//If [Next] button is available - click it 			  
					pageNum++;																//Switch to the next Page
					driver.findElement(nextButton).click();
				}
					else{log.info("Link was found - "+isFound);
						return isFound;														//returns false if nothing was found and NEXT is n/a
					}																		
		    	}
		
		log.error("Link was not found. Processed "+pageNum+" pages.");
		return isFound; 																	//returns false if nothing was found
	}
	}

