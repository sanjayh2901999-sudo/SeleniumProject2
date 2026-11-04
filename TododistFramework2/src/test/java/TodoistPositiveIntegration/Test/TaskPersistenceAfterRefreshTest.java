package TodoistPositiveIntegration.Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Com.Todoist.GenericUtility.ExcelUtility;
import Com.Todosit.ObjectRepository.HomePage;
import Com.Todosit.ObjectRepository.MyProjectObjectRepository;
import Com.Todosit.ObjectRepository.SearchPage;
import Com.Todosit.ObjectRepository.TodoistObjectRepository;
import Todoist_BaseClass.Base_Class;
/**
 * @author SANJAY
 * Test Scenario : Verify task is present after page refresh.
 * Automation Flow (+ve test case)
1. Launch browser
2. Login to Todoist
3. Navigate to project
4. Create task
5. Verify task created
6. Refresh browser page
7. Re-open project
8. Verify same task still exists
9. Logout
10. Close browser
 * 
 * 
 */
public class TaskPersistenceAfterRefreshTest extends Base_Class
{
    @Test
    public void verifyTaskPersistsAfterPageRefresh() throws Exception
    {
        Reporter.log("===== TASK PERSISTENCE AFTER REFRESH TEST STARTED =====", true);

        Reporter.log("Reading task data from Excel", true);

        ExcelUtility eUtil = new ExcelUtility();

        String taskName = eUtil.readDataFromExcel("TaskCreation", 1, 1)+ System.currentTimeMillis();

        String description = eUtil.readDataFromExcel("TaskCreation", 1, 2);

        Reporter.log("Generated Task Name: " + taskName, true);
        Reporter.log("Description: " + description, true);

        Reporter.log("Verifying dashboard after login", true);

        HomePage hp = new HomePage(driver);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard verified successfully", true);

        Reporter.log("Opening Todoist project", true);

        MyProjectObjectRepository mpor =
                new MyProjectObjectRepository(driver);

        mpor.clickOnTodoistProject();

        Reporter.log("Todoist project opened successfully", true);

        Reporter.log("Creating task inside Todoist project", true);

        TodoistObjectRepository tor =new TodoistObjectRepository(driver);

        tor.createTask(taskName, description);

        Reporter.log("Task name and description entered successfully", true);

        Reporter.log("Clicking Add Task button", true);

        tor.saveTask();

        Reporter.log("Task save action completed", true);

        Reporter.log("Capturing toaster message", true);

        String actualMessage = tor.getToastMessage();

        Reporter.log("Actual Toaster Message: " + actualMessage, true);

        Assert.assertTrue(actualMessage.contains("Task added"),"Task creation failed. Expected toaster message was not displayed");

        Reporter.log("Task creation verified successfully", true);

        Reporter.log("Waiting for task creation sync before refresh", true);

        tor.waitForToastMessageToDisappear();

        Reporter.log("Refreshing browser page", true);

        driver.navigate().refresh();

        Reporter.log("Browser page refreshed successfully", true);

        Reporter.log("Waiting for page to load after refresh", true);

        wUtil.waitForPageToLoad(driver);

        Reporter.log("Verifying dashboard after refresh", true);

        hp = new HomePage(driver);

        boolean dashboardAfterRefresh = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardAfterRefresh,"Dashboard/InBox is not displayed after refresh");

        Reporter.log("Dashboard verified successfully after refresh", true);

        Reporter.log("Clicking Search option", true);

        hp.clickSearch();

        Reporter.log("Search option clicked successfully", true);

        Reporter.log("Searching same task after refresh: " + taskName, true);

        SearchPage sp = new SearchPage(driver);

        sp.searchTask(taskName);

        Reporter.log("Task name entered in search field", true);

        Reporter.log("Verifying same task appears after refresh", true);

        boolean taskStatus = sp.isTaskPresent(taskName);

        Assert.assertTrue(taskStatus,"Task is not available after page refresh");

        Reporter.log("Task persisted successfully after page refresh", true);

        Reporter.log("===== TASK PERSISTENCE AFTER REFRESH TEST PASSED =====", true);
    }
}