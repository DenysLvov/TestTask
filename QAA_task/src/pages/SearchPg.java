/*
 * SearchPg class
 * 
 * Class describes Search Page
 */
	package pages;
	
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import utils.DriverContainer;

	public class SearchPg {
		
	private By searchFieldLocator = By.xpath("//input[@title='Search']");
	private WebDriver driver;
	
	public SearchPg(){
		this.driver = DriverContainer.getInstance().getDriver();
	}
	
	public ResultsPg searchForWord(String word){
		driver.findElement(searchFieldLocator).sendKeys(word); 
		driver.findElement(searchFieldLocator).sendKeys(Keys.ENTER);
		return new ResultsPg();
	}
	
}
