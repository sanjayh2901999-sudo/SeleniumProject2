package Com.Todosit.ObjectRepository;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
/**
 * @author SANJAY
 * POM class for Search Page 
 */
public class SearchPage
{
    WebDriver driver;
    WebDriverWait wait;

    public SearchPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void searchTask(String taskName)
    {
        Reporter.log("Searching task: " + taskName, true);

        WebElement searchTextField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@placeholder,'Search or type a command')]")
                )
        );

        searchTextField.click();

        Actions act = new Actions(driver);

        act.sendKeys(taskName).perform();

        Reporter.log("Task name entered in search field using Actions class", true);

        act.sendKeys(Keys.ENTER).perform();

        Reporter.log("Enter key pressed", true);
    }
    public boolean isTaskPresent(String taskName)
    {
        Reporter.log("Checking task in search result: " + taskName, true);

        try
        {
            WebElement searchedTask = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'" + taskName + "')]")
                    )
            );

            return searchedTask.isDisplayed();
        }
        catch(Exception e)
        {
            Reporter.log("Task not found in search result: " + e.getMessage(), true);
            return false;
        }
    }
    public boolean isTaskPresentWithoutWait(String taskName)
    {
        Reporter.log("Checking old task name without wait: " + taskName, true);

        try
        {
            return driver.findElements(
                    By.xpath("//*[contains(normalize-space(),'" + taskName + "')]")
            ).size() > 0;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public void openTaskFromSearchResult(String taskName)
	{
	    Reporter.log("Opening task from search result: " + taskName, true);

	    By taskLocator = By.xpath("//*[contains(normalize-space(),'" + taskName + "')]");

	    for(int i = 1; i <= 3; i++)
	    {
	        try
	        {
	            WebElement task = wait.until(
	                    ExpectedConditions.elementToBeClickable(taskLocator)
	            );

	            task.click();

	            Reporter.log("Task opened from search result: " + taskName, true);
	            return;
	        }
	        catch(org.openqa.selenium.StaleElementReferenceException e)
	        {
	            Reporter.log("Stale element while opening search result. Retrying: " + i, true);
	        }
	        catch(Exception e)
	        {
	            Reporter.log("Unable to open task from search result in attempt "
	                    + i + ": " + e.getMessage(), true);
	        }
	    }

	    throw new RuntimeException("Unable to open task from search result: " + taskName);
	}
}