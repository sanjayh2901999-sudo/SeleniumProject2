package TodoistSmoke.Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Com.Todosit.ObjectRepository.HomePage;
/**
 * @author SANJAY
 * Test Scenario : To check that the user can login with a valid username and password.
 * Automation Steps:
1. Launch browser
2. Open Todoist login page
3. Enter valid email
4. Enter valid password
5. Click Login
6. Verify dashboard (Inbox/Home) is displayed
 * 
 */
public class ValidLoginSmokeTest extends Todoist_BaseClass.Base_Class
{
    @Test
    public void verifyUserLoginWithValidCredentials()
    {
        Reporter.log("===== Execute Valid Login Smoke Test =====", true);

        // Step 1: Verify dashboard after login
        Reporter.log("Verifying user navigated to dashboard", true);

        HomePage hp = new HomePage(driver);

        boolean status = hp.isInboxDisplayed();

        Assert.assertTrue(status,"Login failed: Inbox/Dashboard is not displayed");

        Reporter.log("User login successful and Inbox/Dashboard is displayed", true);

        Reporter.log("===== Valid Login Smoke Test Passed =====", true);
    }
}

