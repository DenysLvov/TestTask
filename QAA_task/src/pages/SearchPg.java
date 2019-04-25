package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchPg {
	
	
	private By searchFieldlocator = By.xpath("//input[@title='Search']");
	private WebDriver driver;
	
	public SearchPg(WebDriver driver){
		this.driver = driver;
	}
	
	public ResultsPg searchForWord(WebDriver driver, String word){
		driver.findElement(searchFieldlocator).sendKeys(word); 
    	driver.findElement(searchFieldlocator).sendKeys(Keys.ENTER);
    	return new ResultsPg(driver);
	}
	
}
