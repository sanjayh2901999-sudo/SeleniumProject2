package TodoistNegativeIntegration.Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Com.Todosit.ObjectRepository.HomePage;
import Com.Todosit.ObjectRepository.LoginPageObjectRepository;
import Todoist_BaseClass.Base_Class;
/**
 * @author SANJAY
 * Test Scenario: Verify users cannot access the dashboard after logout via URL.
 * 
 * Automation Flow (-ve test case) 
1.Launch browser
2. Login to application
3. Logout from application
4. Copy dashboard URL
5. Paste dashboard URL manually
6. Press Enter
7. Verify redirection to login page
8. Verify dashboard inaccessible
9. Close browser
* 
 *  
 *  
 */
public class DashboardAccessAfterLogoutTest extends Base_Class
{
    @Test
    public void verifyDashboardInaccessibleAfterLogoutViaURL()
    {
        Reporter.log("===== DASHBOARD ACCESS AFTER LOGOUT NEGATIVE TEST STARTED =====", true);

        HomePage hp = new HomePage(driver);

        Reporter.log("Verifying dashboard is displayed after login", true);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard verified successfully", true);

        Reporter.log("Capturing dashboard URL before logout", true);

        String dashboardUrl = driver.getCurrentUrl();

        Reporter.log("Captured Dashboard URL: " + dashboardUrl, true);

        Assert.assertTrue(dashboardUrl.contains("/app/"),"Captured URL is not dashboard URL");

        Reporter.log("Logging out from application", true);

        hp.logout();

        Reporter.log("Verifying login page after logout", true);

        LoginPageObjectRepository login =new LoginPageObjectRepository(driver);

        boolean loginPageAfterLogout = login.verifyLoginPageDisplayed();

        Assert.assertTrue(loginPageAfterLogout,"Login page is not displayed after logout");

        Reporter.log("Login page displayed successfully after logout", true);

        Reporter.log("Trying to access old dashboard URL after logout", true);

        driver.get(dashboardUrl);

        Reporter.log("Current URL after accessing dashboard URL: "+ driver.getCurrentUrl(), true);

        Reporter.log("Verifying dashboard is inaccessible after logout", true);

        login = new LoginPageObjectRepository(driver);

        boolean loginPageAfterDashboardAccess =login.verifyLoginPageDisplayed();

        Assert.assertTrue(loginPageAfterDashboardAccess,"Dashboard is accessible after logout. User was not redirected to login page");

        Reporter.log("Dashboard is inaccessible after logout", true);

        Reporter.log("===== DASHBOARD ACCESS AFTER LOGOUT NEGATIVE TEST PASSED =====", true);
    }
}