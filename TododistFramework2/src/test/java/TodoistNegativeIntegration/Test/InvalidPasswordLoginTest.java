package TodoistNegativeIntegration.Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Com.Todosit.ObjectRepository.LoginPageObjectRepository;
import Todoist_BaseClass.Negative_Base_Class;
/**
 * @author SANJAY
 * Test Scenario: To check that when a user tries to login. Login fails with invalid password.
 * Automation Flow (-ve test case)
1. Launch browser
2. Open login page
3. Enter valid email
4. Enter invalid password
5. Click Login
6. Capture error message
7. Verify login failure message
8. Verify user remains on login page
9. Close browser

 * 
 * 
 */
public class InvalidPasswordLoginTest extends Negative_Base_Class
{
    @Test
    public void verifyLoginFailsWithInvalidPassword() throws Exception
    {
        Reporter.log("===== INVALID PASSWORD LOGIN NEGATIVE TEST STARTED =====", true);

        // Step 1: Read valid username from property file
        Reporter.log("Reading valid username from property file", true);

        String username = fUtil.readDataFromPropertyFile("validUsername");

        Reporter.log("Username fetched from property file", true);

        // Step 2: Read invalid password from property file
        Reporter.log("Reading invalid password from property file", true);

        String invalidPassword = fUtil.readDataFromPropertyFile("invalidPassword");

        Reporter.log("Invalid password fetched from property file", true);

        // Step 3: Login with valid username and invalid password
        Reporter.log("Entering valid username and invalid password", true);

        LoginPageObjectRepository login =new LoginPageObjectRepository(driver);

        login.loginToApplication(username, invalidPassword);

        Reporter.log("Login button clicked with invalid password", true);

        // Step 4: Capture error message
        Reporter.log("Capturing login error message", true);

        String actualErrorMessage = login.getInvalidLoginErrorMessage();

        Reporter.log("Actual Error Message: " + actualErrorMessage, true);

        // Step 5: Validate error message
        Reporter.log("Validating invalid login error message", true);

        Assert.assertTrue(actualErrorMessage.length() > 0,
                "Error message is not displayed for invalid password");

        Reporter.log("Invalid password error message displayed successfully", true);

        Reporter.log("===== INVALID PASSWORD LOGIN NEGATIVE TEST PASSED =====", true);
    }
}