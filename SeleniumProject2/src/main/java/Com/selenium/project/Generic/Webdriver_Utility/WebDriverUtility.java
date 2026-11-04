package Com.selenium.project.Generic.Webdriver_Utility;

import java.time.Duration;


import java.util.Iterator;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility 
{
public void waitForPageToLoad(WebDriver driver)
{
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
}
public void waitForElementPresent(WebDriver driver, WebElement element)
{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.visibilityOf(element));
	
}
public void switchToTabOnURl(WebDriver driver,String partialURl)
{
	  // switch to child window
    Set<String> set = driver.getWindowHandles();
    Iterator<String> it = set.iterator();
    while (it.hasNext()) 
    {
  	  // capture the window id 
  	  String WindowId = it.next();
	driver.switchTo().window(WindowId);
	String actUrl=driver.getCurrentUrl();
	if (actUrl.contains(partialURl)) 
	{
		break;
	}
		
	}
}
public void switchToTabOnTitle(WebDriver driver,String partialTitle)
{
	  // switch to child window
    Set<String> set = driver.getWindowHandles();
    Iterator<String> it = set.iterator();
    while (it.hasNext()) 
    {
  	  // capture the window id 
  	  String WindowId = it.next();
	driver.switchTo().window(WindowId);
	String actUrl=driver.getTitle();
	if (actUrl.contains(partialTitle)) 
	{
		break;
	}
		
	}
}
// interview Question : Have you ever used overloaded method you can explain this
public void switchToFrame(WebDriver driver ,int index)
{
	driver.switchTo().frame(index);
	
}
public void switchToFrame(WebDriver driver ,String nameID)
{
	driver.switchTo().frame(nameID);
	
}
public void switchToFrame(WebDriver driver ,WebElement elemenet)
{
	driver.switchTo().frame(elemenet);
	
}
public void switchToAlertAndAccept(WebDriver driver)
{
	driver.switchTo().alert().accept();
}
public void switchToAlertAndCancel(WebDriver driver)
{
	driver.switchTo().alert().dismiss();
}
public void Select(WebElement element, String text)
{
	Select sel= new Select(element);
	sel.selectByVisibleText(text);
	
}
public void Select(WebElement element, int index)
{
	Select sel= new Select(element);
	sel.selectByIndex(index);
	
}
public void MouseMoveOnElement(WebDriver driver ,WebElement element)
{
Actions act = new Actions(driver)	;
act.moveToElement(element).build().perform();
}
public void DoubleClick(WebDriver driver ,WebElement element)
{
Actions act = new Actions(driver)	;
act.doubleClick(element).build().perform();
}
public void switchToWindow(WebDriver driver, String expectedurl) 
{
	Set<String> windows = driver.getWindowHandles();
	Iterator<String> it = windows.iterator();
	while(it.hasNext())
	{
		String windowId = it.next();
		driver.switchTo().window(windowId);
String actualUrl = driver.getCurrentUrl();
if(actualUrl.contains(expectedurl))
{
	break;
}
	}
}
public void waitForElementToBeClickable(WebDriver driver, WebElement contactLink)
{
	Actions act = new Actions(driver);
	act.moveToElement(contactLink).click().perform();
	
}
public void toSelectFromDropDown(WebElement wb,int indix)
{
	Select sel=new Select(wb);
	sel.selectByIndex(indix);	
}
public void toSelectFromDropDown(WebElement wb,String value)
{
	Select sel=new Select(wb);
	sel.selectByValue(value);
}
public void toSelectFromDropDown(String visibletext,WebElement wb)
{
	Select sel=new Select(wb);
	sel.selectByVisibleText(visibletext);
}
}

