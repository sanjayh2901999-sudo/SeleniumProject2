package TaskManagement.Todoist.ListenerUtility;

import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Com.Todoist.GenericUtility.UtilityClassObject;

/**
 * @author SANJAY
 * Listner implementation class 
 */
public class ListenerImplementation_Reports implements ITestListener, ISuiteListener
{
    public ExtentReports report;

    public static ExtentTest test;

    @Override
    public void onStart(ISuite suite)
    {
        System.out.println("=== Report Configuration ===");

        String time = new Date().toString().replace(" ", "_").replace(":", "_");

        ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_" + time + ".html");

        spark.config().setDocumentTitle("Todoist Test Suite Results");

        spark.config().setReportName("Todoist Report");

        spark.config().setTheme(Theme.DARK);

        report = new ExtentReports();

        report.attachReporter(spark);

        report.setSystemInfo("OS", "Windows 11");

        report.setSystemInfo("Browser", "Chrome");
    }

    @Override
    public void onFinish(ISuite suite)
    {
        System.out.println("=== Report Backup ===");

        report.flush();
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        test = report.createTest(result.getMethod().getMethodName());

        UtilityClassObject.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        test.log(Status.PASS,result.getMethod().getMethodName()+ " ==> COMPLETED");
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        String testName =
                result.getMethod().getMethodName();

        test.log(Status.FAIL,
                testName + " ==> FAILED");

        if(result.getThrowable()!=null)
        {
            test.log(Status.FAIL,
                    result.getThrowable());
        }

        try
        {
            TakesScreenshot ts =
                    (TakesScreenshot) UtilityClassObject.getDriver();

            String filePath =
                    ts.getScreenshotAs(OutputType.BASE64);

            String time =
                    new Date().toString()
                    .replace(" ", "_")
                    .replace(":", "_");

            test.addScreenCaptureFromBase64String(
                    filePath,
                    testName + "_" + time);
        }

        catch(Exception e)
        {
            System.out.println(
                    "Unable to capture screenshot");
        }
    }
    @Override
    public void onTestSkipped(ITestResult result)
    {
        test.log(Status.SKIP,
        result.getMethod().getMethodName()+" ==> SKIPPED");
    }
}