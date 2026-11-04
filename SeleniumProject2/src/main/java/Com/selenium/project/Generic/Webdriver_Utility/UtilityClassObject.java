package Com.selenium.project.Generic.Webdriver_Utility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject 
{
private static ThreadLocal<ExtentTest>test=new ThreadLocal<ExtentTest>();
private static ThreadLocal<WebDriver>driver=new ThreadLocal<WebDriver>();


// generate getters and setters

public static ExtentTest getTest()
{
	return test.get();
}
public static void setTest(ExtentTest actTest)
{
	 test.set(actTest);
}

//generate getters and setters

public static WebDriver getdriver()
{
	return driver.get();
}

public static void setDriver(WebDriver actDriver)
{
	 driver.set(actDriver);
}
}
