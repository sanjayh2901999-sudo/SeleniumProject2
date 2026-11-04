package TodoistEndToEnd.Test;

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
 * Test Scenario:To check that when a user creates a task in a project, the task is linked and visible under that project .
 * Automation Flow (E2E Test Case)
1. Launch browser
2. Open Todoist login page
3. Enter valid email
4. Enter valid password
5. Click Login
6. Verify dashboard is displayed
7. Click on “My Projects”
8. Select existing project / create new project
9. Click “Add Task”
10. Enter task name
11. Enter task description
12. Click “Add Task” button
13. Capture toaster success message
14. Verify “Task added” message displayed
15.Verify created task appears inside selected project
16. Logout from application
17. Close browser"
 * 
 */ 

public class TaskCreationInsideProjectEndToEndTest extends Base_Class
{
    @Test
    public void verifyTaskIsLinkedToSelectedProject() throws Exception
    {
        Reporter.log("===== TASK CREATION INSIDE EXISTING PROJECT E2E TEST STARTED =====", true);

        // Step 1: Read task data from Excel
        Reporter.log("Reading task data from Excel", true);

        ExcelUtility eUtil = new ExcelUtility();

        String taskName = eUtil.readDataFromExcel("TaskCreation", 1, 1)+ System.currentTimeMillis();

        String description = eUtil.readDataFromExcel("TaskCreation", 1, 2);

        Reporter.log("Generated Task Name: " + taskName, true);
        Reporter.log("Description: " + description, true);

        // Step 2: Verify dashboard after login
        Reporter.log("Verifying dashboard after login", true);

        HomePage hp = new HomePage(driver);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard verified successfully", true);

        // Step 3: Click existing Todoist project
        Reporter.log("Opening existing Todoist project", true);

        MyProjectObjectRepository mpor =new MyProjectObjectRepository(driver);

        mpor.clickOnTodoistProject();

        Reporter.log("Existing Todoist project opened successfully", true);

        // Step 4: Create task inside existing project
        Reporter.log("Creating task inside existing project", true);

        TodoistObjectRepository tor =new TodoistObjectRepository(driver);

        tor.createTask(taskName, description);

        Reporter.log("Task name and description entered successfully", true);

        // Step 5: Click Add Task button
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

        Reporter.log("Task added toaster message verified successfully", true);

        // Step 8: Verify task appears inside selected project
        Reporter.log("Verifying created task appears inside existing project", true);

        boolean taskStatus = tor.isTaskDisplayedInsideProject(taskName);

        Assert.assertTrue(taskStatus,"Created task is not displayed inside existing project");

        Reporter.log("Created task is displayed inside existing Todoist project", true);

        Reporter.log("===== TASK CREATION INSIDE EXISTING PROJECT E2E TEST PASSED =====", true);
    }
}