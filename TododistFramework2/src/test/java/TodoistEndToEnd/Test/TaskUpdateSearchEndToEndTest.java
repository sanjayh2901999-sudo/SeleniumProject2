package TodoistEndToEnd.Test;

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
 * 
 * Test Scenario:To check that when a user updates a task name, the updated value is reflected across the dashboard and search results.

Automation Flow (E2E Test Case)
1. Launch browser
2. Open Todoist login page
3. Enter valid email
4. Enter valid password
5. Click Login
6. Verify dashboard loads successfully
7. Create a new task
8. Capture created task name
9. Open created task
10. Click Edit task
11. Update task name
12. Save updated task
13. Capture update success toaster message
14. Verify update confirmation message displayed
15. Click search box
16. Search updated task name
17. Verify updated task appears in search result
18. Verify old task name is not displayed
19. Logout from application
20. Close browser
 * 
 * 
 */
public class TaskUpdateSearchEndToEndTest extends Base_Class
{
    @Test
    public void verifyUpdatedTaskNameReflectsInSearch() throws Exception
    {
        Reporter.log("===== TASK UPDATE + SEARCH E2E TEST STARTED =====", true);

        // Step 1: Read test data from Excel
        Reporter.log("Reading test data from Excel", true);

        ExcelUtility eUtil = new ExcelUtility();

        String originalTaskName = eUtil.readDataFromExcel("TaskCreation", 1, 1)+ System.currentTimeMillis();

        String description = eUtil.readDataFromExcel("TaskCreation", 1, 2);

        String updatedTaskName = eUtil.readDataFromExcel("TaskUpdate", 1, 3)+ System.currentTimeMillis();

        Reporter.log("Original Task Name: " + originalTaskName, true);
        Reporter.log("Description: " + description, true);
        Reporter.log("Updated Task Name: " + updatedTaskName, true);

        // Step 2: Verify dashboard
        Reporter.log("Verifying dashboard after login", true);

        HomePage hp = new HomePage(driver);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard verified successfully", true);

        // Step 3: Open existing Todoist project
        Reporter.log("Opening existing Todoist project", true);

        MyProjectObjectRepository mpor =new MyProjectObjectRepository(driver);

        mpor.clickOnTodoistProject();

        Reporter.log("Todoist project opened successfully", true);

        // Step 4: Create new task
        Reporter.log("Creating new task", true);

        TodoistObjectRepository tor =new TodoistObjectRepository(driver);

        tor.createTask(originalTaskName, description);

        Reporter.log("Task name and description entered successfully", true);

        // Step 5: Save created task
        Reporter.log("Clicking Add Task button", true);

        tor.saveTask();

        Reporter.log("Task save action completed", true);

        // Step 6: Capture creation toaster message
        Reporter.log("Capturing task creation toaster message", true);

        String creationMessage = tor.getToastMessage();

        Reporter.log("Task Creation Toaster Message: " + creationMessage, true);

        Assert.assertTrue(creationMessage.contains("Task added"),"Task creation failed. Expected toaster message was not displayed");

        Reporter.log("Task creation verified successfully", true);

        // Step 7: Open Search for original task
        Reporter.log("Opening Search for original task", true);

        hp.openSearchUsingKeyboardShortcut();

        Reporter.log("Search opened successfully using keyboard shortcut", true);

        SearchPage sp = new SearchPage(driver);

        Reporter.log("Searching original task: " + originalTaskName, true);

        sp.searchTask(originalTaskName);

        Reporter.log("Original task name entered in search field", true);

        // Step 8: Verify original task appears in search result
        Reporter.log("Verifying original task appears in search result", true);

        boolean originalTaskSearchStatus =sp.isTaskPresent(originalTaskName);

        Assert.assertTrue(originalTaskSearchStatus,"Created task is not displayed in search result");

        Reporter.log("Original task displayed in search result", true);

        // Step 9: Open original task from search result
        Reporter.log("Opening original task from search result", true);

        sp.openTaskFromSearchResult(originalTaskName);

        Reporter.log("Original task opened successfully", true);

        // Step 10: Update task name
        Reporter.log("Updating task name", true);

        tor = new TodoistObjectRepository(driver);

        tor.updateTaskName(originalTaskName, updatedTaskName);

        Reporter.log("Task name updated successfully", true);

        // Step 11: Refresh page to close active Todoist panel
        Reporter.log("Refreshing page after task update", true);

        driver.navigate().refresh();

        wUtil.waitForPageToLoad(driver);

        Reporter.log("Page refreshed successfully after task update", true);

        // Step 12: Verify dashboard after refresh
        Reporter.log("Verifying dashboard after refresh", true);

        hp = new HomePage(driver);

        boolean dashboardAfterUpdate = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardAfterUpdate,"Dashboard is not displayed after refreshing page");

        Reporter.log("Dashboard verified after refresh", true);

        // Step 13: Open Search again for updated task
        Reporter.log("Opening Search again for updated task", true);

        hp.openSearchUsingKeyboardShortcut();

        Reporter.log("Search opened successfully using keyboard shortcut", true);

        // Step 14: Search updated task
        sp = new SearchPage(driver);

        Reporter.log("Searching updated task: " + updatedTaskName, true);

        sp.searchTask(updatedTaskName);

        Reporter.log("Updated task name entered in search field", true);

        // Step 15: Verify updated task appears in search result
        Reporter.log("Verifying updated task appears in search result", true);

        boolean updatedTaskSearchStatus =sp.isTaskPresent(updatedTaskName);

        Assert.assertTrue(updatedTaskSearchStatus,"Updated task is not displayed in search result");

        Reporter.log("Updated task displayed in search result", true);

        // Step 16: Verify old task name is not displayed
        Reporter.log("Verifying old task name is not displayed in search result", true);

        boolean oldTaskSearchStatus =sp.isTaskPresentWithoutWait(originalTaskName);

        Assert.assertFalse(oldTaskSearchStatus,"Old task name is still displayed in search result");

        Reporter.log("Old task name is not displayed in search result", true);

        Reporter.log("===== TASK UPDATE + SEARCH E2E TEST PASSED =====", true);
    }
}