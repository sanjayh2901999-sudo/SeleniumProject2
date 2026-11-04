package Todoist_BaseClass;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import Com.Todoist.GenericUtility.FileUtility;
import Com.Todoist.GenericUtility.WebdriverUtility;
import Com.Todosit.ObjectRepository.HomePage;
import Com.Todosit.ObjectRepository.LoginPageObjectRepository;

/**
 * @author SANJAY
 * Base class consist of all the configuration annotations 
 */
@Listeners(TaskManagement.Todoist.ListenerUtility.ListenerImplementation_Reports.class)
public class Base_Class
{
    public WebDriver driver;

    public FileUtility fUtil;
    public WebdriverUtility wUtil;

    String BROWSER;
    String URL;
    String USERNAME;
    String PASSWORD;
/**
 * Used to for Report configuration 
 */
    @BeforeSuite
    public void configureReport()
    {
        Reporter.log("=== Report Configuration ===", true);
    }
    /**
     * @throws Exception
     * used to launch the browser
     */

    @BeforeClass
    public void launchBrowser() throws Exception
    {
        Reporter.log("=== Launch Browser ===", true);

        fUtil = new FileUtility();
        wUtil = new WebdriverUtility();

        BROWSER = fUtil.readDataFromPropertyFile("browser");
        URL = fUtil.readDataFromPropertyFile("url");
        USERNAME = fUtil.readDataFromPropertyFile("validUsername");
        PASSWORD = fUtil.readDataFromPropertyFile("validPassword");

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
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();

        driver.manage().timeouts()
              .implicitlyWait(Duration.ofSeconds(10));

        driver.get(URL);

        Reporter.log("Application Opened", true);
    }
    /**
     * @throws Exception
     * Used to login to the application
     */

    @BeforeMethod
    public void loginToApplication() throws Exception
    {
        Reporter.log("=== Login Started ===", true);

        LoginPageObjectRepository lp =
                new LoginPageObjectRepository(driver);

        lp.loginToApplication(USERNAME, PASSWORD);

        Reporter.log("Login Successful", true);
    }
/**
 * Used to Logout from the application
 */
    @AfterMethod
    public void logoutFromApplication()
    {
        Reporter.log("=== Logout Started ===", true);

        try
        {
            LoginPageObjectRepository login =
                    new LoginPageObjectRepository(driver);

            if(driver.getCurrentUrl().contains("auth/login")
                    || login.verifyLoginPageDisplayed())
            {
                Reporter.log("User already logged out", true);
                return;
            }

            HomePage hp = new HomePage(driver);

            hp.logout();

            Reporter.log("Logout Successful", true);
        }
        catch(Exception e)
        {
            Reporter.log("Logout failed : " + e.getMessage(), true);
        }
    }

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