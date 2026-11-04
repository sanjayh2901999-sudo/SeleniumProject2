package Com.Todoist.GenericUtility;

import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverUtility 
{
	/**
	 * This method is used to maximise the window
	 * @param driver
	 */
public void maximiseWindow(WebDriver driver)
{
	driver.manage().window().maximize();
}
/**
 * This method is used to wait until page gets loaded
 * @param driver
 * @param sec
 */
public void waitForPageLoad(WebDriver driver,int sec)
{
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(sec));
}

public WebDriverWait webDriverWaitObj(WebDriver driver,int sec)
{
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(sec));
	return wait;
	
}
public void waitUntilElementToBeVisible(WebDriver driver,int sec,WebElement element)
{
	webDriverWaitObj(driver,sec).until(ExpectedConditions.visibilityOf(element));
}
public void waitUntilElementToBeClickable(WebDriver driver,int sec,WebElement element)
{
	webDriverWaitObj(driver, sec).until(ExpectedConditions.elementToBeClickable(element));
}
public void waitUntilTogetTitle(WebDriver driver,int sec,String title)
{
	webDriverWaitObj(driver, sec).until(ExpectedConditions.titleContains(title));
}
public void waitUntilAlertToBePresent(WebDriver driver,int sec)
{
	webDriverWaitObj(driver, sec).until(ExpectedConditions.alertIsPresent());
}
public Select dropdownObject(WebElement element)
{
	Select	sel=new Select(element);
	return sel;
}
public void handleDropdown(WebElement element,String text)
{
	dropdownObject(element).selectByVisibleText(text);
}
public void handleDropdown(WebElement element,int index)
{
	dropdownObject(element).selectByIndex(index);
	
}
public void handleDropdown(String value,WebElement element)
{
	Select sel=new Select(element);
	sel.selectByValue(value);
}
public void mousehover(WebDriver driver,WebElement element)
{
	Actions act = new Actions(driver);
	act.moveToElement(element).build().perform();
}
public Actions performActions(WebDriver driver)
{
	Actions act = new Actions(driver);
	return act;
}
public void dragAndDrop(WebDriver driver,WebElement src,WebElement dst)
{
	performActions(driver).dragAndDrop(src, dst);
}
public void dragAndDrop(WebDriver driver, WebElement element, int x, int y)
{
	performActions(driver).dragAndDropBy(element,x,y);
}
public void doubleClick(WebDriver driver)
{
	performActions(driver).doubleClick().build().perform();
}
public void doubleClick(WebDriver driver,WebElement element)
{
	performActions(driver).doubleClick(element).build().perform();
}
public void rightClick(WebDriver driver)
{
	performActions(driver).contextClick().build().perform();
}
public void enterKeyPress(WebDriver driver)
{
	performActions(driver).sendKeys(Keys.ENTER).build().perform();
}
/**
 * 
 * This method will Press key
 * @param driver
 */
public void switchToWindow(WebDriver driver,String expWind)
{
	Set<String>window=driver.getWindowHandles();
	Iterator<String>it=window.iterator();
	while (it.hasNext()) 
	{
		String win = it.next();

		String currentTitle = driver.switchTo().window(win).getTitle();
		if (currentTitle.contains(expWind)) 
		{
			break;
		}
	}
}
public Robot robotObj() throws AWTException
{
	Robot rb = new Robot();
	return rb;
	
}
public void enterKey() throws AWTException
{
	robotObj().keyPress(KeyEvent.VK_ENTER);
}
public void enterRelease() throws AWTException
{
	robotObj().keyRelease(KeyEvent.VK_ENTER);
}
/**
 * This method will the frame based on index 
 * @param driver
 * @param index
 */
public void switchToFrame(WebDriver driver,int index)
{
	driver.switchTo().frame(index);
}
/**
 * This method will switch the frame based on nameOrID
 * @param driver
 * @param nameOrID
 */
public void switchToFrame(WebDriver driver,String nameOrID)
{
	driver.switchTo().frame(nameOrID);
}
public void switchToFrame(WebDriver driver,WebElement address)
{
	driver.switchTo().frame(address);
}
/**
 * This method is used to accept alert popup
 * @param driver
 */
public void acceptAlert(WebDriver driver)
{
	driver.switchTo().alert().accept();
}
/**
 * This method is used to cancel alert popup
 * @param driver
 */
public void cancelAlert(WebDriver driver)
{
	driver.switchTo().alert().dismiss();
}
/**
 * This method will take screenshot and store it in folder called as screenshot
 * @param driver
 * @param screenShotName
 * @return
 * @throws IOException
 */
public static String getScreenShot(WebDriver driver, String screenShotName) throws IOException
{
	JavaUtility java_utility = new JavaUtility();
	TakesScreenshot ts = (TakesScreenshot)driver;
	File src = ts.getScreenshotAs(OutputType.FILE);
	String path=("./screenshot/"+screenShotName+""+java_utility.getSystemDateYYYYDDMM()+".png");
	File dst = new File(path);
	String scrpath = dst.getAbsolutePath();
	FileUtils.copyFile(src, dst);
	return scrpath;
	
}
/**
 * This method will perform random scroll
 * @param driver
 */
public void scrollBarAction(WebDriver driver)
{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,800)","");
}
/**
 * This method will scroll until specified element is found 
 * @param driver
 * @param element
 */
public void scrollAction(WebDriver driver,WebElement element)
{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	int y=element.getLocation().getY();
	js.executeScript("window.scrollBy(0,"+y+")",element);
}
/**
 * scroll Down till the bottom of the page 
 * @param driver
 */
public void scrollDownTillBottomOfThePage(WebDriver driver)
{
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}
/**
 * scroll till element to be visible
 * @param driver
 * @param element
 */
public void scrollTillElementToBeVisible(WebDriver driver,WebElement element)
{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("arguments[0].scrollIntoView()",element);
}

public void clickOnElement(WebDriver driver,WebElement element)
{
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].click()",element);
}

public void JavaScriptUsingSendKeys(WebDriver driver , WebElement element, String expData)
{
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].innerText=arguments[1]", element, expData);
}

public void scrollUpTillElementToBeVissible(WebDriver driver, WebElement element)
{
	JavascriptExecutor js = (JavascriptExecutor)driver;
	Point loc = element.getLocation();
	int x= loc.getX();
	int y= loc.getY();
	js.executeScript("window.scrollBy("+x+","+y+")");
}
public void customType(WebElement element, String text) throws Exception
{
    for (char ch : text.toCharArray())
    {
        element.sendKeys(String.valueOf(ch));
        Thread.sleep(100);
    }
}
public void waitForPageToLoad(WebDriver driver)
{
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
}
public void waitForOverlaysToDisappear(WebDriver driver)
{
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    wait.until(ExpectedConditions.invisibilityOfElementLocated(
        By.xpath("//div[contains(@data-focus-lock,'true')]")));
}
}



