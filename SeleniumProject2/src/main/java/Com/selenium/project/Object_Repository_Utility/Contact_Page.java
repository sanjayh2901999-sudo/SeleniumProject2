package Com.selenium.project.Object_Repository_Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact_Page 
{

    public Contact_Page(WebDriver driver)
    {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@title='Create Contact...']")
    private WebElement createContact;
    
    @FindBy(className = "dvHeaderText")
    private WebElement headerMsg;
    
    @FindBy(id="dtlview_LastName")
    private WebElement lastName;
    
    public WebElement getLastName()
    {
        return lastName;
    }
    
    public WebElement getHeaderMsg()
    {
        return headerMsg;
    }

    public WebElement getCreateContact() 
    {
        return createContact;
    }
    
}