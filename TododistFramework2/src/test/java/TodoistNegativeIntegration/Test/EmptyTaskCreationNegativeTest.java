package TodoistNegativeIntegration.Test;

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
 * Test Scenario:To check that when a user creates a task in a project  fails with an empty name.
 * 
 * Automation Flow (-ve test case)
1. Launch browser
2. Login to application
3. Open project
4. Click Add Task
5. Leave task name empty
6. Enter description only
7. Click Add Task
8. Capture validation message
9. Verify task is not created
10. Logout
11. Close browser
 * 
 * 
 */
public class EmptyTaskCreationNegativeTest extends Base_Class
{
    @Test
    public void verifyTaskCreationFailsWithEmptyTaskName() throws Exception
    {
        Reporter.log("===== EMPTY TASK CREATION NEGATIVE TEST STARTED =====", true);

        // Step 1: Read description from Excel
        Reporter.log("Reading description from Excel", true);

        ExcelUtility eUtil = new ExcelUtility();

        String description = eUtil.readDataFromExcel("TaskCreation", 1, 2);

        Reporter.log("Description: " + description, true);

        // Step 2: Verify dashboard after login
        Reporter.log("Verifying dashboard after login", true);

        HomePage hp = new HomePage(driver);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard verified successfully", true);

        // Step 3: Open Todoist project
        Reporter.log("Opening Todoist project", true);

        MyProjectObjectRepository mpor =new MyProjectObjectRepository(driver);

        mpor.clickOnTodoistProject();

        Reporter.log("Todoist project opened successfully", true);

        // Step 4: Click Add Task and enter description only
        Reporter.log("Creating task with empty task name and description only", true);

        TodoistObjectRepository tor =new TodoistObjectRepository(driver);

        tor.createTaskWithEmptyName(description);

        Reporter.log("Description entered, task name left empty", true);

        // Step 5: Verify task cannot be saved
        Reporter.log("Verifying Add Task button is disabled for empty task name", true);

        boolean addTaskDisabled = tor.isAddTaskButtonDisabled();

        Assert.assertTrue(addTaskDisabled,"Add Task button is enabled even when task name is empty");

        Reporter.log("Add Task button is disabled as expected", true);

        // Step 6: Verify task is not created
        Reporter.log("Verifying task is not created with empty task name", true);

        boolean emptyTaskCreated = tor.isEmptyTaskCreated();

        Assert.assertFalse(emptyTaskCreated,"Task was created even though task name was empty");

   Assert.assertFalse(emptyTaskCreated,"Task was created even though task name was empty");

        Reporter.log("Task was not created with empty task name", true);

        // Close task form before logout
        Reporter.log("Closing task creation form", true);
        tor.cancelTaskCreation();

        Reporter.log("===== EMPTY TASK CREATION NEGATIVE TEST PASSED =====", true);
}
}
