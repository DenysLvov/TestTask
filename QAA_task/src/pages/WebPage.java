package pages;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class WebPage {

	private WebDriver driver;
	private static Logger log = LogManager.getLogger("WebPage log: "); 

	public WebPage(WebDriver driver) {
		this.driver = driver;
		PropertyConfigurator.configure("Log4j.properties");
	}
	
	public String getTitle() {
		log.info("Title: "+driver.getTitle());
		return driver.getTitle();
	}
	
}
