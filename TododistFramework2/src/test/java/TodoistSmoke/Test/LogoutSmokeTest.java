package TodoistSmoke.Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Com.Todosit.ObjectRepository.HomePage;
import Com.Todosit.ObjectRepository.LoginPageObjectRepository;
import Todoist_BaseClass.Base_Class;
/**
 * @author SANJAY
 * Test Scenario: To check that the user can logout successfully.
 * "Automation Flow
1. Launch browser
2. Open Todoist login page
3. Enter valid email
4. Enter valid password
5. Click Login
6. Verify user is navigated to dashboard / Inbox page
7. Click on profile icon / settings icon
8. Click on Logout option
9. Verify user is redirected to login page
10. Verify logout is successful
11. Close browser
 * 
 * 
 * 
 */
public class LogoutSmokeTest extends Base_Class
{
    @Test
    public void verifyUserCanLogoutSuccessfully() throws InterruptedException
    {
        Reporter.log("===== LOGOUT SMOKE TEST STARTED =====", true);

        // Step 1: Verify dashboard after login
        Reporter.log("Verifying user is navigated to dashboard", true);

        HomePage hp = new HomePage(driver);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(
                dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard verified successfully", true);

        // Step 2: Click profile/settings icon
        Reporter.log("Clicking profile/settings icon", true);
        hp.clickSettings();

        Reporter.log("Profile/settings menu opened successfully", true);

        // Step 3: Click Logout
        Reporter.log("Clicking Logout option", true);
        hp.clickLogout();

        Reporter.log("Logout option clicked successfully", true);

        // Step 4: Verify login page is displayed
        Reporter.log("Verifying user is redirected to login page", true);

        LoginPageObjectRepository login = new LoginPageObjectRepository(driver);

        boolean loginPageStatus = login.verifyLoginPageDisplayed();

        Assert.assertTrue(loginPageStatus,"Logout failed: Login page is not displayed");

        Reporter.log("User logged out successfully", true);

        Reporter.log("===== LOGOUT SMOKE TEST PASSED =====", true);
    }
}