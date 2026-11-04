package Com.Todosit.ObjectRepository;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
/**
 * @author SANJAY
 * POM class for Home Page
 * 
 */
public class HomePage
{
	WebDriver driver;
	public HomePage(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }
	
    @FindBy(xpath = "//button[@aria-label='Settings']")
    private WebElement settingsButton;

    @FindBy(xpath = "//*[normalize-space()='Log out' or normalize-space()='Logout']")
    private WebElement logoutButton;
    
    @FindBy(xpath = "//span[text()='My Projects']")
    private WebElement myProjectsLink;

    @FindBy(xpath = "//*[normalize-space()='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//span[text()='Inbox']")
    private WebElement inboxButton;

    
  

    /**
	 * @return the settingsButton
	 */
	public WebElement getSettingsButton()
	{
		return settingsButton;
	}

	/**
	 * @return the logoutButton
	 */
	public WebElement getLogoutButton()
	{
		return logoutButton;
	}

	/**
	 * @return the myProjectsLink
	 */
	public WebElement getMyProjectsLink() 
	{
		return myProjectsLink;
	}

	/**
	 * @return the searchButton
	 */
	public WebElement getSearchButton()
	{
		return searchButton;
	}

	/**
	 * @return the inboxButton
	 */
	public WebElement getInboxButton() 
	{
		return inboxButton;
	}

	/**
	 * 
	 * BUSINESS LIBRARIES
	 * 
	 */
	public boolean isInboxDisplayed()
    {
        Reporter.log("Checking Inbox is displayed", true);
        return inboxButton.isDisplayed();
    }

    

    public void ClickOnMyProjects()
    {
        Reporter.log("Clicking My Projects", true);
        myProjectsLink.click();
    }

    public WebElement getSearch()
    {
        return searchButton;
    }
    public void clickSearch()
    {
        Reporter.log("Clicking Search", true);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public void clickInbox()
    {
        Reporter.log("Clicking Inbox", true);
        inboxButton.click();
    }

    public void logout()
    {
        Reporter.log("Clicking Settings", true);

        handleCdnFallbackErrorIfPresent();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

                   WebElement settingsBtn = wait.until
(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Settings']")));

        settingsBtn.click();

        Reporter.log("Clicking Logout", true);

        WebElement logoutBtn = wait.until
(ExpectedConditions.elementToBeClickable(By.xpath("//*[normalize-space()='Log out' or normalize-space()='Logout']")));

        logoutBtn.click();

        Reporter.log("Waiting until user redirects to login page", true);

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("auth/login"),
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='email']"))
        ));

        Reporter.log("Logout completed and login page displayed", true);
    }
    public void handleCdnFallbackErrorIfPresent()
    {
        Reporter.log("Checking CDN fallback overlay", true);

        try
        {
            if(driver.getCurrentUrl().contains("cdn_fallback"))
            {
                Reporter.log("CDN fallback URL detected. Refreshing clean dashboard URL", true);

                String cleanUrl = driver.getCurrentUrl().split("\\?")[0];

                driver.get(cleanUrl);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

                wait.until(webDriver ->
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                                .equals("complete"));

                Reporter.log("Clean dashboard URL loaded successfully", true);
            }
        }
        catch(Exception e)
        {
            Reporter.log("CDN fallback handling skipped: " + e.getMessage(), true);
        }
    }
    public boolean verifyDashboardDisplayed()
    {
        Reporter.log("Checking Inbox/Dashboard is displayed", true);

        try
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            wait.until(ExpectedConditions.visibilityOf(inboxButton));

            return inboxButton.isDisplayed();
        }
        catch(Exception e)
        {
            Reporter.log("Dashboard/InBox not displayed: " + e.getMessage(), true);
            return false;
        }
    }
    public void clickSettings()
    {
        Reporter.log("Clicking Settings/Profile icon", true);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(settingsButton)).click();
    }
    public void clickLogout()
    {
        Reporter.log("Clicking Logout button", true);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
    public void openSearchUsingKeyboardShortcut()
    {
        Reporter.log("Opening Search using keyboard shortcut", true);

        Actions act = new Actions(driver);

        act.sendKeys(Keys.ESCAPE).perform();
        act.sendKeys(Keys.ESCAPE).perform();

        Reporter.log("ESC pressed to close active Todoist panel", true);

        act.keyDown(Keys.CONTROL).sendKeys("k").keyUp(Keys.CONTROL).perform();

        Reporter.log("CTRL + K pressed to open Search", true);
    }
    public boolean isDashboardVisibleWithoutWait()
    {
        Reporter.log("Checking dashboard visibility without wait", true);

        try
        {
            return driver.findElements(
                    By.xpath("//*[normalize-space()='Inbox' or normalize-space()='Today']")).size() > 0;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
