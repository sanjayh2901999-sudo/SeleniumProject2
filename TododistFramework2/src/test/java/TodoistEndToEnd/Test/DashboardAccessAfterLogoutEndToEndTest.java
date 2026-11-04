package TodoistEndToEnd.Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Com.Todosit.ObjectRepository.HomePage;
import Com.Todosit.ObjectRepository.LoginPageObjectRepository;
import Todoist_BaseClass.Base_Class;
/**
 * @author SANJAY
 * Test Scenario:To check that when a user logs out and tries to access the dashboard using URL, access is restricted.
 * Automation Flow (Security E2E Test Case)
1. Launch browser
2. Open Todoist login page
3. Enter valid email
4. Enter valid password
5. Click Login
6. Verify dashboard displayed successfully
7. Capture dashboard URL
8. Click profile icon
9. Click Logout
10. Verify user redirected to login page
11. Manually enter dashboard URL in browser
12. Press Enter
13. Capture redirected URL
14. Verify application redirects to login page
15. Verify dashboard is not accessible after logout
16. Close browser"
 * 
 * 
 */
public class DashboardAccessAfterLogoutEndToEndTest extends Base_Class
{
    @Test
    public void verifyDashboardAccessIsRestrictedAfterLogout()
    {
        Reporter.log("===== LOGOUT + SECURITY E2E TEST STARTED =====", true);

        Reporter.log("Verifying dashboard is displayed after login", true);

        HomePage hp = new HomePage(driver);

        boolean dashboardStatus = hp.verifyDashboardDisplayed();

        Assert.assertTrue(dashboardStatus,"Dashboard/InBox is not displayed after login");

        Reporter.log("Dashboard displayed successfully", true);

        Reporter.log("Capturing dashboard URL", true);

        String dashboardUrl = driver.getCurrentUrl();

        Reporter.log("Captured Dashboard URL: " + dashboardUrl, true);

        Assert.assertTrue(dashboardUrl.contains("/app/"),"Captured URL is not a Todoist dashboard URL");

        Reporter.log("Logging out from application", true);

        hp.logout();

        Reporter.log("Logout completed successfully", true);

        Reporter.log("Verifying user is redirected to login page after logout", true);

        LoginPageObjectRepository login =new LoginPageObjectRepository(driver);

        boolean loginPageAfterLogout = login.verifyLoginPageDisplayed();

        Assert.assertTrue(loginPageAfterLogout,"Login page is not displayed after logout");

        Reporter.log("Login page displayed successfully after logout", true);

        Reporter.log("Entering captured dashboard URL after logout", true);

        driver.get(dashboardUrl);

        Reporter.log("Dashboard URL entered after logout", true);

        String redirectedUrl = driver.getCurrentUrl();

        Reporter.log("Current URL after entering dashboard URL: "+ redirectedUrl, true);

        Reporter.log("Verifying application restricts dashboard access after logout", true);

        login = new LoginPageObjectRepository(driver);

        boolean loginPageAfterDashboardAccess =login.verifyLoginPageDisplayed();

        Assert.assertTrue(loginPageAfterDashboardAccess,
        		"Dashboard is accessible after logout. Login page is not displayed");

        Reporter.log("Login page displayed after trying to access dashboard URL", true);

        Reporter.log("Dashboard access is restricted after logout", true);

        Reporter.log("===== LOGOUT + SECURITY E2E TEST PASSED =====", true);
    }
}