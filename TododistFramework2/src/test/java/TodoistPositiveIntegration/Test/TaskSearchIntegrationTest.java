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
 * Test Scenario:To check that when a user creates a task he is able to check them in the search text field.
 * Automation Flow ( +ve test case)
1. Launch browser
2. Login to application
3. Navigate to project
4. Create new task
5. Copy generated task name
6. Click Search
7. Enter task name in search field
8. Press Enter
9. Verify created task appears in search results
10. Logout
11. Close browser
 * 
 * 
 */
public class TaskSearchIntegrationTest extends Base_Class
{
    @Test
    public void verifyNewlyCreatedTaskIsSearchable() throws Exception
    {
        Reporter.log("===== TASK + SEARCH POSITIVE INTEGRATION TEST STARTED =====", true);

        // Step 1: Read test data from Excel
        Reporter.log("Reading task data from Excel", true);

        ExcelUtility eUtil = new ExcelUtility();

        String taskName = eUtil.readDataFromExcel("TaskCreation", 1, 1)
                + System.currentTimeMillis();

        String description = eUtil.readDataFromExcel("TaskCreation", 1, 2);

        Reporter.log("Generated Task Name: " + taskName, true);
        Reporter.log("Description: " + description, true);

        // Step 2: Verify dashboard after login
        Reporter.log("Verifying user is navigated to dashboard", true);

        HomePage hp = new HomePage(driver);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard verified successfully", true);

        // Step 3: Navigate to required project
        Reporter.log("Opening required Todoist project", true);

        MyProjectObjectRepository mpor =new MyProjectObjectRepository(driver);

        mpor.clickOnTodoistProject();

        Reporter.log("Todoist project opened successfully", true);

        // Step 4: Create new task
        Reporter.log("Creating new task", true);

        TodoistObjectRepository tor =new TodoistObjectRepository(driver);

        tor.createTaskWithWeekendAndP1(taskName, description);

        Reporter.log("Task details entered successfully", true);

        // Step 5: Save task
        Reporter.log("Clicking Add Task button", true);

        tor.saveTask();

        Reporter.log("Task save action completed", true);

        // Step 6: Capture toaster message
        Reporter.log("Capturing toaster message", true);

        String actualMessage = tor.getToastMessage();

        Reporter.log("Actual Toaster Message: " + actualMessage, true);

        // Step 7: Validate task creation
        Reporter.log("Validating task creation", true);

        Assert.assertTrue(actualMessage.contains("Task added"),
                "Task creation failed. Expected toaster message was not displayed");

        Reporter.log("Task created successfully", true);

        // Step 8: Click Search
        Reporter.log("Clicking Search option", true);

        hp.clickSearch();

        Reporter.log("Search option clicked successfully", true);

        // Step 9: Search created task
        Reporter.log("Searching newly created task: " + taskName, true);

        SearchPage sp = new SearchPage(driver);

        sp.searchTask(taskName);

        Reporter.log("Task name entered in search field and Enter key pressed", true);

        // Step 10: Verify task appears in search results
        Reporter.log("Verifying created task appears in search results", true);

        boolean searchStatus = sp.isTaskPresent(taskName);

        Assert.assertTrue(searchStatus,"Created task is not displayed in search results");

        Reporter.log("Created task is displayed in search results", true);

        Reporter.log("===== TASK + SEARCH POSITIVE INTEGRATION TEST PASSED =====", true);
    }
}