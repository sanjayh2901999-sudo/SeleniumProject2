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
 * POM Class for My Projejct 
 */
public class MyProjectObjectRepository
{
    WebDriver driver;
    WebDriverWait wait;
    public MyProjectObjectRepository(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[normalize-space()='Todoist']")
    private WebElement todoistProject;

    /**
     * BUSINESS LIBRARIES
     * 
     */

    public void clickOnTodoistProject()
    {
        Reporter.log("Clicking Todoist Project", true);
        wait.until(ExpectedConditions.elementToBeClickable(todoistProject)).click();
    }
    public void clickOnProject(String projectName)
    {
        Reporter.log("Clicking project: " + projectName, true);

        WebElement project = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[normalize-space()='" + projectName + "']")
                )
        );

        project.click();

        Reporter.log("Project clicked successfully: " + projectName, true);
    }
}