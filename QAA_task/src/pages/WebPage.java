/*
 * WebPage class
 * 
 * Class describes web page opened from link
 */

	package pages;
	
	import org.apache.log4j.LogManager;
	import org.apache.log4j.Logger;
	import org.apache.log4j.PropertyConfigurator;
	import org.openqa.selenium.WebDriver;

	import utils.DriverContainer;

	public class WebPage {

	private WebDriver driver;
	private static Logger log = LogManager.getLogger("WebPage log: "); 

	public WebPage() {
		this.driver = DriverContainer.getInstance().getDriver();
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	public String getTitle() {
		log.info("Title is: "+driver.getTitle());
		return driver.getTitle();
	}
	}
