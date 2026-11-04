package Listener_Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import Com.selenium.project.Base_CLass.Base_CLass;

public class Listener_Utility_Class implements ITestListener, ISuiteListener
{
	public ExtentReports spark;
	public ExtentReports report;
	public ExtentTest test=null;

	@Override
	public void onStart(ISuite suite) 
	{
		Reporter.log("Report Configuration");
		String timeStamp = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());

		ExtentSparkReporter spark =
			    new ExtentSparkReporter("./Extent_Reports/Create_Contact_Test_" + timeStamp + ".html");
		
		spark.config().setDocumentTitle("Contact_Module");
		spark.config().setReportName("Create_Contact_Test");
		spark.config().setTheme(Theme.STANDARD);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Windows", "11");
		report.setSystemInfo("Browser", "chrome");
		report.setSystemInfo("Browser Versio", "121.10.22.50");

	}

	@Override
	public void onFinish(ISuite suite)
	{
		Reporter.log("Reprt Backup");
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) 
	{
		Reporter.log("======" + result.getMethod().getMethodName() + "=========");
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO,"Test Script Started");

	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		Reporter.log("=====" + result.getMethod().getMethodName() + "=====END=====");
		test.log(Status.PASS,"Sucessfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		
		String test_name = result.getMethod().getMethodName();

	    TakesScreenshot ts = (TakesScreenshot) Base_CLass.driver;
	    String file_path = ts.getScreenshotAs(OutputType.BASE64);

	    String time = new Date().toString().replace(" ","_").replace(":","_");

	    test.log(Status.FAIL, test_name + " ==> FAILED");
	    test.log(Status.FAIL, result.getThrowable());

	    test.addScreenCaptureFromBase64String(file_path, test_name + "_" + time);
	    test.log(Status.FAIL,result.getMethod().getMethodName()+"===> FAILED<=====");

	
		
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	
	
	{
		
		test.log(Status.SKIP,"Test script got skipped ");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
       
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) 
	{
            
	}

}
