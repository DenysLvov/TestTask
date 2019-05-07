/*
 * BaseTest Class
 * 
 * Shares Web driver in test classes     
 */

	package tests;

    import java.util.concurrent.TimeUnit;
    import org.apache.log4j.PropertyConfigurator;
    import org.openqa.selenium.Dimension;
    import org.openqa.selenium.WebDriver;
    import org.testng.annotations.AfterSuite;
    import org.testng.annotations.BeforeSuite;
    import utils.DataProviderClass;
    import utils.DriverContainer;

    public class BaseTest {
    
    DataProviderClass dataProviderHelper;
    public WebDriver driver;

/**
 * Setup driver    
 */
    @BeforeSuite (alwaysRun = true)
    public void setUpSuite(){
        this.driver = DriverContainer.getInstance().getDriver();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        PropertyConfigurator.configure("Log4j.properties");
        }
	
    @AfterSuite	public void tearDown(){
        DriverContainer.stopDriver(this.driver);
        }
    }
