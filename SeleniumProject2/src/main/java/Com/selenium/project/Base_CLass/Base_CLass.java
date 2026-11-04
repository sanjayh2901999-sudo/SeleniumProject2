
package Com.selenium.project.Base_CLass;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Com.selenium.project.Generic.DataBase_Utility.Data_Base_Utility;
import Com.selenium.project.Generic.FileUtility.Excel_Utility;
import Com.selenium.project.Generic.FileUtility.File_Utility;
import Com.selenium.project.Generic.Webdriver_Utility.JavaUtility;
import Com.selenium.project.Object_Repository_Utility.Home_Page_Object_Repository;
import Com.selenium.project.Object_Repository_Utility.Login_Page_Object_Repository;

public class Base_CLass
{


	public Data_Base_Utility dblib = new Data_Base_Utility();
	public File_Utility flib = new File_Utility();
	public Excel_Utility elib = new Excel_Utility();
	public JavaUtility jlib = new JavaUtility();
	
	public static WebDriver driver = null; // declare it as the global variable

	@BeforeSuite
	public void configBS() throws SQLException
	{
		System.out.println("====connect to DB , Report Config====");
		dblib.getDbConnection();
	}

	@BeforeClass
	public void configBC() throws Exception 
	{
		System.out.println("====launch the BROWSER===");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		String BROWSER = flib.getDataFromPropertiesFile("browser");

		if (BROWSER.equals("chrome"))
		{
			driver = new ChromeDriver(options); // initialisation
		} 
		else if (BROWSER.equals("firefox")) 
		{
			driver = new FirefoxDriver();
		} 
		else if (BROWSER.equals("edge"))
		{
			driver = new EdgeDriver();
		} 
		else 
		{
			driver = new ChromeDriver();
		}
	}

	@BeforeMethod
	public void configBM() throws Exception 
	{
		System.out.println("==login==");
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");

		Login_Page_Object_Repository lplib = new Login_Page_Object_Repository(driver);
		lplib.loginToApp(URL, USERNAME, PASSWORD);

	}

	@AfterMethod(alwaysRun = true)
	public void configAM() {
	    System.out.println("==logout==");

	    try 
	    {
	        if (driver != null) 
	        {
	            Home_Page_Object_Repository hp = new Home_Page_Object_Repository(driver);
	            hp.logout();
	        }
	    } 
	    catch (Exception e)
	    {
	        System.out.println("Logout skipped due to session issue: " + e.getMessage());
	    }
	}

	@AfterClass
	public void configAC() {
		System.out.println("===close the BROWSER===");
		driver.quit();

	}

	@AfterSuite
	public void configAS() throws SQLException {
		System.out.println("===close DB,,Report back up=====");
		dblib.closeDbConnection();
	}
}

