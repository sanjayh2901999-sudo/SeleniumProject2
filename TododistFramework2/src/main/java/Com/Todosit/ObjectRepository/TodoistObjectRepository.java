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
 * 
 * @author SANJAY
 * POM class for Todoist
 */
public class TodoistObjectRepository
{
    WebDriver driver;
    WebDriverWait wait;
    public TodoistObjectRepository(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(.,'Add task')]")
    private WebElement addTaskButton;

    @FindBy(xpath = "//div[@aria-label='Task name']")
    private WebElement taskNameTextField;

    @FindBy(xpath = "//div[@aria-label='Description']")
    private WebElement descriptionTextField;

    @FindBy(xpath = "//button[contains(.,'Date') or @aria-label='Set due date']")
    private WebElement dueDateButton;

    @FindBy(xpath = "//button[contains(.,'Weekend')]")
    private WebElement weekendOption;

    @FindBy(xpath = "//div[text()='Priority']")
    private WebElement priorityButton;

    @FindBy(xpath = "//span[text()='Priority 1']")
    private WebElement priorityOneOption;

    @FindBy(xpath = "//button/span[text()='Add task']")
    private WebElement saveTaskButton;
    
    @FindBy(xpath = "//div[contains(text(),'Task added')]")
    private WebElement toastMessage;
  

    @FindBy(xpath = "//*[normalize-space()='Tomorrow']")
    private WebElement tomorrowOption;

    @FindBy(xpath = "//*[normalize-space()='Priority 2']")
    private WebElement priorityTwoOption;

    @FindBy(xpath = "//*[normalize-space()='Priority 3']")
    private WebElement priorityThreeOption;

    @FindBy(xpath = "//*[normalize-space()='Priority 4']")
    private WebElement priorityFourOption;
    
    /**
	 * @return the addTaskButton
	 */
	public WebElement getAddTaskButton() 
	{
		return addTaskButton;
	}

	/**
	 * @return the taskNameTextField
	 */
	public WebElement getTaskNameTextField()
	{
		return taskNameTextField;
	}

	/**
	 * @return the descriptionTextField
	 */
	public WebElement getDescriptionTextField() 
	{
		return descriptionTextField;
	}

	/**
	 * @return the dueDateButton
	 */
	public WebElement getDueDateButton()
	{
		return dueDateButton;
	}

	/**
	 * @return the weekendOption
	 */
	public WebElement getWeekendOption()
	{
		return weekendOption;
	}

	/**
	 * @return the priorityButton
	 */
	public WebElement getPriorityButton()
	{
		return priorityButton;
	}

	/**
	 * @return the priorityOneOption
	 */
	public WebElement getPriorityOneOption() 
	{
		return priorityOneOption;
	}

	/**
	 * @return the saveTaskButton
	 */
	public WebElement getSaveTaskButton()
	{
		return saveTaskButton;
	}

    /***
     * 
     * @param taskName
     * @param description
     * BUSINESS LIBRARIES
     */

	public void createTaskWithWeekendAndP1(String taskName, String description)
	{
	    Reporter.log("Clicking Add Task button", true);
	    wait.until(ExpectedConditions.elementToBeClickable(addTaskButton)).click();

	    Reporter.log("Entering task name with weekend date and P1 priority", true);

	    String taskNameWithDateAndPriority = taskName + " this weekend p1";

	    wait.until(ExpectedConditions.visibilityOf(taskNameTextField))
	            .sendKeys(taskNameWithDateAndPriority);

	    Reporter.log("Entering description", true);
	    descriptionTextField.sendKeys(description);

	    Reporter.log("Task name, weekend date, and P1 priority entered successfully", true);
	}
	public void createTask(String taskName, String description)
	{
	    Reporter.log("Clicking Add Task button", true);

	    WebElement addTaskBtn = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("(//*[normalize-space()='Add task'])[last()]")));

	    addTaskBtn.click();

	    Reporter.log("Entering task name: " + taskName, true);

	    WebElement taskNameField = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath(
	                        "//div[@role='textbox' and contains(@aria-label,'Task name')]"
	                      + " | //div[@contenteditable='true' and contains(@aria-label,'Task name')]"
	                      + " | //div[contains(@class,'public-DraftStyleDefault-block')]")));

	    taskNameField.click();
	    taskNameField.sendKeys(taskName);

	    Reporter.log("Entering description", true);

	    WebElement descriptionField = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath(
	                        "//div[@role='textbox' and contains(@aria-label,'Description')]"
	                      + " | //div[@contenteditable='true' and contains(@aria-label,'Description')]")));

	    descriptionField.click();
	    descriptionField.sendKeys(description);

	    Reporter.log("Task name and description entered successfully", true);
	}
	
	
    public void selectWeekend()
    {
        Reporter.log("Selecting Weekend due date", true);

        WebElement dueDateBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//button[@data-testid='due-date-control'])[last()]")));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", dueDateBtn);
        js.executeScript("arguments[0].click();", dueDateBtn);

        WebElement weekendOption = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[normalize-space()='This weekend' or normalize-space()='Weekend']")));

        js.executeScript("arguments[0].click();", weekendOption);

        Reporter.log("Weekend due date selected successfully", true);
    }

    public void selectPriorityP1()
    {
        Reporter.log("Selecting Priority P1", true);

        WebElement priorityBtn = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("(//*[normalize-space()='Priority' or @aria-label='Set priority'])[last()]")));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].scrollIntoView({block:'center'});", priorityBtn);
        js.executeScript("arguments[0].click();", priorityBtn);

        WebElement priorityOne = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//*[normalize-space()='Priority 1' or normalize-space()='P1']")));

        js.executeScript("arguments[0].click();", priorityOne);

        Reporter.log("Priority P1 selected successfully", true);
    }

    public void saveTask()
    {
        Reporter.log("Saving task", true);

        By addTaskButtonsLocator = By.xpath(
                "//button[.//span[normalize-space()='Add task'] or normalize-space()='Add task']");

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(addTaskButtonsLocator));

        java.util.List<WebElement> addTaskButtons =
                driver.findElements(addTaskButtonsLocator);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        for(int i = addTaskButtons.size() - 1; i >= 0; i--)
        {
            WebElement button = addTaskButtons.get(i);

            try
            {
                if(button.isDisplayed() && button.isEnabled())
                {
                    js.executeScript("arguments[0].scrollIntoView({block:'center'});", button);
                    js.executeScript("arguments[0].click();", button);

                    Reporter.log("Task saved successfully", true);
                    return;
                }
            }
            catch(Exception e)
            {
                Reporter.log("Skipping non-clickable Add Task button", true);
            }
        }

        throw new RuntimeException("No visible/enabled Add Task button found");
    }
    public String getToastMessage()
    {
        Reporter.log("Capturing toaster message", true);

        WebElement toast = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(normalize-space(),'Task added')]")));

        return toast.getText();
    }
    
	/**
	 * @param toastMessage the toastMessage to set
	 */
	public void setToastMessage(WebElement toastMessage)
	{
		this.toastMessage = toastMessage;
	}

	public boolean isTaskPresentInProject(String taskName)
    {
        Reporter.log("Verifying task inside project: " + taskName, true);

        WebElement task = driver.findElement(
                By.xpath("//div[contains(text(),'" + taskName + "')]"));

        return task.isDisplayed();
    }
	public boolean isTaskDisplayedInsideProject(String taskName)
	{
	    Reporter.log("Checking task inside project list: " + taskName, true);

	    By taskLocator = By.xpath("//*[contains(text(),'" + taskName + "')]");

	    for(int i = 1; i <= 3; i++)
	    {
	        try
	        {
	            WebElement task = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(taskLocator)
	            );

	            if(task.isDisplayed())
	            {
	                Reporter.log("Task found inside project list: " + taskName, true);
	                return true;
	            }
	        }
	        catch(org.openqa.selenium.StaleElementReferenceException e)
	        {
	            Reporter.log("Stale element found. Retrying task verification. Attempt: " + i, true);
	        }
	        catch(Exception e)
	        {
	            Reporter.log("Task not displayed inside project list: " + e.getMessage(), true);
	            return false;
	        }
	    }

	    Reporter.log("Task not displayed after retrying", true);
	    return false;
	}
	public void selectPriority(String priority)
	{
	    Reporter.log("Selecting priority: " + priority, true);

	    wait.until(ExpectedConditions.elementToBeClickable(priorityButton)).click();

	    if(priority.equalsIgnoreCase("P1"))
	    {
	        wait.until(ExpectedConditions.elementToBeClickable(priorityOneOption)).click();
	    }
	    else if(priority.equalsIgnoreCase("P2"))
	    {
	        wait.until(ExpectedConditions.elementToBeClickable(priorityTwoOption)).click();
	    }
	    else if(priority.equalsIgnoreCase("P3"))
	    {
	        wait.until(ExpectedConditions.elementToBeClickable(priorityThreeOption)).click();
	    }
	    else if(priority.equalsIgnoreCase("P4"))
	    {
	        wait.until(ExpectedConditions.elementToBeClickable(priorityFourOption)).click();
	    }
	    else
	    {
	        throw new RuntimeException("Invalid priority: " + priority);
	    }
	}
	public void selectTomorrow()
	{
	    Reporter.log("Selecting Tomorrow due date", true);

	    wait.until(ExpectedConditions.elementToBeClickable(dueDateButton)).click();

	    wait.until(ExpectedConditions.elementToBeClickable(tomorrowOption)).click();

	    Reporter.log("Tomorrow due date selected successfully", true);
	}
	public void waitForToastMessageToDisappear()
	{
	    Reporter.log("Waiting for toaster message to disappear", true);

	    try
	    {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                By.xpath("//*[contains(text(),'Task added')]")));

	        Reporter.log("Toaster message disappeared successfully", true);
	    }
	    catch(Exception e)
	    {
	        Reporter.log("Toaster message did not disappear within timeout: " + e.getMessage(), true);
	    }
	}
	public void createTaskWithEmptyName(String description)
	{
	    Reporter.log("Clicking Add Task button", true);

	    wait.until(ExpectedConditions.elementToBeClickable(addTaskButton)).click();

	    Reporter.log("Leaving task name empty", true);

	    Reporter.log("Entering description only", true);

	    wait.until(ExpectedConditions.visibilityOf(descriptionTextField))
	            .sendKeys(description);

	    Reporter.log("Description entered successfully", true);
	}
	public boolean isAddTaskButtonDisabled()
	{
	    Reporter.log("Checking Add Task button disabled status", true);

	    try
	    {
	        WebElement addTaskBtn = wait.until(
	                ExpectedConditions.presenceOfElementLocated(
	                By.xpath("(//button[.//span[normalize-space()='Add task'] or normalize-space()='Add task'])[last()]")));

	        String ariaDisabled = addTaskBtn.getAttribute("aria-disabled");
	        String disabled = addTaskBtn.getAttribute("disabled");

	        Reporter.log("aria-disabled value: " + ariaDisabled, true);
	        Reporter.log("disabled value: " + disabled, true);

	        return "true".equalsIgnoreCase(ariaDisabled) || disabled != null || !addTaskBtn.isEnabled();
	    }
	    catch(Exception e)
	    {
	        Reporter.log("Unable to check Add Task button disabled status: " + e.getMessage(), true);
	        return false;
	    }
	}
	public boolean isEmptyTaskCreated()
	{
	    Reporter.log("Checking whether empty task was created", true);

	    try
	    {
	        WebElement emptyTask = driver.findElement(
	                By.xpath("//div[normalize-space()='']"));

	        return emptyTask.isDisplayed();
	    }
	    catch(Exception e)
	    {
	        Reporter.log("No empty task found in project list", true);
	        return false;
	    }
	}
	public void cancelTaskCreation()
	{
	    Reporter.log("Cancelling task creation form", true);

	    try
	    {
	        WebElement cancelButton = wait.until(
	                ExpectedConditions.elementToBeClickable(
	                        By.xpath("(//button[normalize-space()='Cancel'])[last()]")));

	        cancelButton.click();

	        Reporter.log("Cancel button clicked", true);

	        // If Todoist asks for discard confirmation, click Discard
	        try
	        {
	            WebElement discardButton = wait.until(
	                    ExpectedConditions.elementToBeClickable(
	                            By.xpath("//*[normalize-space()='Discard']")
	                    )
	            );

	            discardButton.click();

	            Reporter.log("Discard unsaved changes popup handled", true);
	        }
	        catch(Exception e)
	        {
	            Reporter.log("Discard popup not displayed", true);
	        }

	        // Wait until overlay/dialog disappears
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                By.xpath("//div[@data-focus-lock-disabled='false']")));

	        Reporter.log("Task creation form closed successfully", true);
	    }
	    catch(Exception e)
	    {
	        Reporter.log("Unable to close task creation form: " + e.getMessage(), true);
	    }
	}
	public void openTask(String taskName)
	{
	    Reporter.log("Opening task: " + taskName, true);

	    By taskLocator = By.xpath("//*[contains(normalize-space(),'" + taskName + "')]");

	    for(int i = 1; i <= 3; i++)
	    {
	        try
	        {
	            WebElement task = wait.until(
	                    ExpectedConditions.refreshed(
	                            ExpectedConditions.elementToBeClickable(taskLocator)));

	            JavascriptExecutor js = (JavascriptExecutor) driver;

	            js.executeScript("arguments[0].scrollIntoView({block:'center'});", task);

	            js.executeScript("arguments[0].click();", task);

	            Reporter.log("Task opened successfully: " + taskName, true);

	            return;
	        }
	        catch(org.openqa.selenium.StaleElementReferenceException e)
	        {
	            Reporter.log("Stale element while opening task. Retrying attempt: " + i, true);
	        }
	        catch(Exception e)
	        {
	            Reporter.log("Unable to open task in attempt " + i + ": " + e.getMessage(), true);
	        }
	    }

	    throw new RuntimeException("Unable to open task after retries: " + taskName);
	}
	

	public void updateTaskName(String originalTaskName, String updatedTaskName)
	{
	    Reporter.log("Updating task name from: " + originalTaskName
	            + " to: " + updatedTaskName, true);

	    By taskTitleLocator = By.xpath("(//*[contains(normalize-space(),'" + originalTaskName + "')])[last()]");

	    WebElement taskTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(taskTitleLocator));

	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    js.executeScript("arguments[0].scrollIntoView({block:'center'});", taskTitle);

	    Actions act = new Actions(driver);

	    act.doubleClick(taskTitle).perform();

	    Reporter.log("Task title double clicked for editing", true);

	    act.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(updatedTaskName).sendKeys(Keys.ENTER).perform();

	    Reporter.log("Updated task name entered and saved", true);

	    wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//*[contains(normalize-space(),'" + updatedTaskName + "')]")));

	    Reporter.log("Updated task name verified in UI", true);
	}
	public void closeTaskDetailsPanel()
	{
	    Reporter.log("Closing task details panel", true);

	    try
	    {
	        Actions act = new Actions(driver);

	        act.sendKeys(Keys.ESCAPE).perform();

	        Reporter.log("ESC key pressed to close task details panel", true);

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        wait.until(ExpectedConditions.invisibilityOfElementLocated(
	                By.xpath("//div[@data-focus-lock-disabled='false']")));

	        Reporter.log("Task details panel closed successfully", true);
	    }
	    catch(Exception e)
	    {
	        Reporter.log("Task details panel close wait skipped/failed: "
	                + e.getMessage(), true);
	    }
	}
	    public void closeActiveTodoistPanel()
	    {
	        Reporter.log("Closing active Todoist panel/form if present", true);

	        try
	        {
	            Actions act = new Actions(driver);

	            act.sendKeys(Keys.ESCAPE).perform();
	            act.sendKeys(Keys.ESCAPE).perform();

	            Reporter.log("ESC key pressed to close active Todoist panel", true);

	            try
	            {
	                WebElement discardButton = new WebDriverWait(driver, Duration.ofSeconds(3))
	                        .until(ExpectedConditions.elementToBeClickable(
	                                By.xpath("//*[normalize-space()='Discard']")));

	                discardButton.click();

	                Reporter.log("Discard popup handled", true);
	            }
	            catch(Exception e)
	            {
	                Reporter.log("Discard popup not displayed", true);
	            }

	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	            wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//*[normalize-space()='Search']")));

	            Reporter.log("Todoist panel closed and Search is clickable", true);
	        }
	        catch(Exception e)
	        {
	            Reporter.log("Unable to close active Todoist panel: " + e.getMessage(), true);
	        }
	    }
	}

	

