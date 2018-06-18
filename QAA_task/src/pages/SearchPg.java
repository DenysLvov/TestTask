package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPg {
	
	
	private By searchFieldId = By.id("lst-ib");
	private WebDriver driver;
	
	public SearchPg(WebDriver driver){
		this.driver = driver;
	}
	
	public ResultsPg searchForWord(WebDriver driver, String word){
		driver.findElement(searchFieldId).sendKeys(word); 
    	driver.findElement(searchFieldId).sendKeys(Keys.ENTER);
    	return new ResultsPg(driver);
	}
	
}
