package TodoistSmoke.Test;

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
 * Test Scenario : To check that the user can create a task.
 * Automation flow 
1. Launch browser
2. Open Todoist login page
3. Enter valid email
4. Enter valid password
5. Click Login
6. Verify user is navigated to dashboard (Inbox/Home page)
7. Click on Todoist project / My Projects
8. Click on Add Task button
9. Enter task name (Todoist)
10. Enter task description (Discuss automation smoke status)
11. Select due date (Tomorrow)
12. Select priority (P1)
13. Click on Save/Add Task button
14. Capture success/toast message
15. Verify task is created successfully
16. Close browser
 * 
 * 
 */
public class TaskCreationSmokeTest extends Base_Class
{
    @Test
    public void verifyUserCanCreateNewTaskWithWeekendDateAndP1Priority() throws Exception
    {
        Reporter.log("===== TASK CREATION SMOKE TEST STARTED =====", true);

        // Step 1: Read task data from Excel
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

        // Step 3: Open Todoist Project
        Reporter.log("Opening Todoist project", true);

        MyProjectObjectRepository mpor =new MyProjectObjectRepository(driver);

        mpor.clickOnTodoistProject();

        Reporter.log("Todoist project opened successfully", true);

        // Step 4: Create task with This Weekend date and P1 priority
        Reporter.log("Creating task with This Weekend date and P1 priority", true);

        TodoistObjectRepository tor =new TodoistObjectRepository(driver);

        tor.createTaskWithWeekendAndP1(taskName, description);
       
        Reporter.log("Task details entered successfully", true);

        // Step 5: Save task
        Reporter.log("Clicking Save/Add Task button", true);
        tor.saveTask();

        Reporter.log("Save/Add Task button clicked successfully", true);

        // Step 6: Capture toaster message
        Reporter.log("Capturing success toaster message", true);

        String actualMessage = tor.getToastMessage();

        Reporter.log("Actual Toaster Message: " + actualMessage, true);

        // Step 7: Validate task creation
        Reporter.log("Validating task creation", true);

        Assert.assertTrue(actualMessage.contains("Task added"),
        		"Task creation failed. Expected toaster message was not displayed");

        Reporter.log("Task created successfully with This Weekend date and P1 priority", true);

        Reporter.log("===== TASK CREATION SMOKE TEST PASSED =====", true);
    }
}