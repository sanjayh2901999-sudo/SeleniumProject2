package Com.Todoist.GenericUtility;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
/**
 * @author SANJAY
 * Utility class object
 */
public class UtilityClassObject 
{
	
private static ThreadLocal<ExtentTest>test=new ThreadLocal<ExtentTest>();
private static ThreadLocal<WebDriver>driver=new ThreadLocal<WebDriver>();


/**
 * 
 * @return
 * getters and setters
 */

public static ExtentTest getTest()
{
	return test.get();
}

public static void setTest(ExtentTest actTest)
{
	 test.set(actTest);
}


public static WebDriver getDriver()
{
	return driver.get();
}

public static void setDriver(WebDriver actDriver)
{
	 driver.set(actDriver);
}
}
