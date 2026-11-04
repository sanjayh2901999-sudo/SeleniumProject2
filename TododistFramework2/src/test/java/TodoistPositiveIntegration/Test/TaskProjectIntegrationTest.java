package TodoistPositiveIntegration.Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Com.Todoist.GenericUtility.ExcelUtility;
import Com.Todosit.ObjectRepository.HomePage;
import Com.Todosit.ObjectRepository.MyProjectObjectRepository;
import Com.Todosit.ObjectRepository.TodoistObjectRepository;
import Todoist_BaseClass.Base_Class;
/**
 * @author SANJAY
 * Test Scenario: To check that when a user creates a task inside the project it appears in the project.
 * Automation Flow (+ve test case)
1. Launch browser
2. Open Todoist application
3. Login with valid credentials
4. Click on required project
5. Click “Add Task”
6. Enter task name
7. Enter description
8. Select due date
9. Select priority
10. Click “Add Task”
11. Capture toaster message
12. Verify task appears inside project task list
13. Logout from application
14. Close browser
 * 
 * 
 * 
 */
public class TaskProjectIntegrationTest extends Base_Class
{
    @Test
    public void verifyTaskCreatedInsideProjectAppearsInProjectList() throws Exception
    {
        Reporter.log("===== TASK + PROJECT POSITIVE INTEGRATION TEST STARTED =====", true);

        // Step 1: Read test data from Excel
        Reporter.log("Reading task data from Excel", true);

        ExcelUtility eUtil = new ExcelUtility();

        String taskName = eUtil.readDataFromExcel("TaskCreation", 1, 1)
                + System.currentTimeMillis();

        String description = eUtil.readDataFromExcel("TaskCreation", 1, 2);

        Reporter.log("Task Name: " + taskName, true);
        Reporter.log("Description: " + description, true);

        // Step 2: Verify dashboard after login
        Reporter.log("Verifying user is navigated to dashboard", true);

        HomePage hp = new HomePage(driver);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard verified successfully", true);

        // Step 3: Open required Todoist project
        Reporter.log("Opening required Todoist project", true);

        MyProjectObjectRepository mpor =new MyProjectObjectRepository(driver);

        mpor.clickOnTodoistProject();

        Reporter.log("Todoist project opened successfully", true);

        // Step 4: Create task inside selected project
        Reporter.log("Creating task inside selected project", true);

        TodoistObjectRepository tor =new TodoistObjectRepository(driver);

        tor.createTaskWithWeekendAndP1(taskName, description);

        Reporter.log("Task name, description, due date, and priority entered successfully", true);

        // Step 5: Save task
        Reporter.log("Clicking Add Task button", true);

        tor.saveTask();

        Reporter.log("Task save action completed", true);

        // Step 6: Capture toaster message
        Reporter.log("Capturing toaster message", true);

        String actualMessage = tor.getToastMessage();

        Reporter.log("Actual Toaster Message: " + actualMessage, true);

        // Step 7: Validate toaster message
        Reporter.log("Validating task creation toaster message", true);

    Assert.assertTrue(actualMessage.contains("Task added"),
    "Task creation failed. Expected toaster message was not displayed");

        Reporter.log("Task creation toaster message validated successfully", true);

        // Step 8: Verify task appears inside project task list
        Reporter.log("Verifying created task appears inside selected project list", true);

        boolean taskStatus = tor.isTaskDisplayedInsideProject(taskName);

        Assert.assertTrue(taskStatus,
          "Created task is not displayed inside selected project list");

        Reporter.log("Created task is displayed inside selected project list", true);

        Reporter.log("===== TASK + PROJECT POSITIVE INTEGRATION TEST PASSED =====", true);
    }
}