package Com.selenium.project.Object_Repository_Utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home_Page_Object_Repository {

    WebDriver driver;

    public Home_Page_Object_Repository(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Organizations")
    private WebElement orgLink;

    @FindBy(linkText = "Contacts")
    private WebElement contactLink;

    @FindBy(linkText = "Campaigns")
    private WebElement campaignLink;

    @FindBy(xpath = "//a[normalize-space()='More']")
    private WebElement moreLink;

    @FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
    private WebElement adminImg;

    @FindBy(xpath="//a[text()='Sign Out']")
    private WebElement signOutBtn;

    // getters

    public WebElement getOrgLink() 
    {
        return orgLink;
    }

    public WebElement getContactLink() {
        return contactLink;
    }

    // business methods

    public void clickOnOrganizations() {
        orgLink.click();
    }

    public void clickOnContacts() {
        contactLink.click();
    }

    public void navigateToCampaignPage() {
        Actions act = new Actions(driver);
        act.moveToElement(moreLink).perform();
        campaignLink.click();
    }

    public void logout()
    {
        try 
        {
            Actions act = new Actions(driver);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Step 1: Hover first (VERY IMPORTANT)
            act.moveToElement(adminImg).perform();

            // Step 2: Wait for Sign Out after hover
            wait.until(ExpectedConditions.elementToBeClickable(signOutBtn));

            // Step 3: Click
            signOutBtn.click();

        } 
        catch (Exception e) 
        {
            System.out.println("Logout skipped due to session issue: " + e.getMessage());
        }
    }

	
}