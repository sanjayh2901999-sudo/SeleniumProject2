package Todoist_BaseClass;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import Com.Todoist.GenericUtility.FileUtility;
import Com.Todoist.GenericUtility.UtilityClassObject;
/**
 * @author SANJAY
 * This is the Base class for Negative Integration Testing consist of all the configuration annotations
 */
public class Negative_Base_Class
{
    public WebDriver driver;
    public FileUtility fUtil;
    public String BROWSER;
    public String URL;
/**
 * Used for Report Configuration
 */
    @BeforeSuite
    public void configureReport()
    {
        Reporter.log("=== Report Configuration ===", true);
    }
    /**
     * @throws Exception
     * Used to Launch the Browser
     */
    @BeforeClass
    public void launchBrowser() throws Exception
    {
        Reporter.log("=== Launch Browser ===", true);

        fUtil = new FileUtility();

        BROWSER = fUtil.readDataFromPropertyFile("browser");
        URL = fUtil.readDataFromPropertyFile("url");

        if(BROWSER.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
        }
        else if(BROWSER.equalsIgnoreCase("edge"))
        {
            driver = new EdgeDriver();
        }
        else
        {
            Reporter.log("Invalid browser value in property file. Launching Chrome by default", true);
            driver = new ChromeDriver();
        }

        UtilityClassObject.setDriver(driver);

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        driver.get(URL);

        Reporter.log("Application opened: " + URL, true);
    }
/**
 * Used to Launch the Browser
 */
    @AfterClass
    public void closeBrowser()
    {
        Reporter.log("=== Close Browser ===", true);

        if(driver != null)
        {
            driver.quit();
        }
    }
/**
 * Used for Report Backup
 */
    @AfterSuite
    public void backupReport()
    {
        Reporter.log("=== Report Backup ===", true);
    }
}