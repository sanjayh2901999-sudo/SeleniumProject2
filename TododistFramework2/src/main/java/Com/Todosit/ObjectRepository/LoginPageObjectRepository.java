package Com.Todosit.ObjectRepository;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
/**
 * @author SANJAY
 * POM class for Login Page 
 * 
 */
public class LoginPageObjectRepository
{
	WebDriver driver;
	WebDriverWait wait;
	 public LoginPageObjectRepository(WebDriver driver)
	    {
	        PageFactory.initElements(driver, this);
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    }
	@FindBy(xpath = "//input[@type='email']")
    private WebElement emailTextField;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

   

    /**
	 * @return the emailTextField
	 */
	public WebElement getEmailTextField() 
	{
		return emailTextField;
	}

     /**
	 * @return the passwordTextField
	 */
	public WebElement getPasswordTextField() 
	{
		return passwordTextField;
	}
   /**
	 * @return the loginButton
	 */
	public WebElement getLoginButton()
	{
		return loginButton;
	}

/**
 * 
 * @param username
 * @param password
 * BUSSINESS LIBRARIES
 */

	public void loginToApplication(String username, String password)
    {
        Reporter.log("Entering username", true);
        emailTextField.sendKeys(username);

        Reporter.log("Entering password", true);
        passwordTextField.sendKeys(password);

        Reporter.log("Clicking Login button", true);
        loginButton.click();
    }
	public boolean verifyLoginPageDisplayed()
	{
	    Reporter.log("Verifying Login page is displayed", true);

	    try
	    {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        wait.until(ExpectedConditions.visibilityOf(emailTextField));

	        return emailTextField.isDisplayed();
	    }
	    catch(Exception e)
	    {
	        Reporter.log("Login page not displayed: " + e.getMessage(), true);
	        return false;
	    }
	    
	}
	public String getInvalidLoginErrorMessage()
    {
        Reporter.log("Getting invalid login error message", true);

        WebElement errorMessage = wait.until(
        ExpectedConditions.visibilityOfElementLocated
        (By.xpath("//*[contains(text(),'Wrong email or password') or contains(text(),'Invalid') "
		+ "or contains(text(),'incorrect') or contains(text(),'password')]")));

        return errorMessage.getText();
    }
	
	}

