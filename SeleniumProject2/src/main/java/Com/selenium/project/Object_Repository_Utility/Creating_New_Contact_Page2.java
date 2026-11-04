package Com.selenium.project.Object_Repository_Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Creating_New_Contact_Page2 
{
WebDriver driver;

public Creating_New_Contact_Page2(WebDriver driver) 
{
this.driver = driver;
PageFactory.initElements(driver, this);
}

@FindBy(xpath = "//input[@name='lastname']")
private WebElement lastName;

@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[2]")
private WebElement saveBtn;

@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
private WebElement selectOrgIcon;

@FindBy(name="support_start_date")
private WebElement startDate;

@FindBy(name="support_end_date")
private WebElement endDate;

@FindBy(name="search_text")
private WebElement searchEdt;

@FindBy(name="search")
private WebElement searchBtn;



public WebElement getSearchEdt() {
	return searchEdt;
}

public void setSearchEdt(WebElement searchEdt) {
	this.searchEdt = searchEdt;
}

public WebElement getSearchBtn() {
	return searchBtn;
}

public void setSearchBtn(WebElement searchBtn) {
	this.searchBtn = searchBtn;
}

public WebDriver getDriver() {
	return driver;
}

public WebElement getLastName() {
	return lastName;
}

public WebElement getSaveBtn() {
	return saveBtn;
}

public WebElement getSelectOrgIcon() {
	return selectOrgIcon;
}

public WebElement getStartDate() {
	return startDate;
}

public WebElement getEndDate() {
	return endDate;
}


// bussiness library
public void creatingNewContact(String lastName)
{
this.lastName.sendKeys(lastName);
saveBtn.click();
}
public void createContactWithOrg(String lastName, String orgName) 
{
    this.lastName.sendKeys(lastName);
    selectOrgIcon.click();

    // switch to child window
    String parent = driver.getWindowHandle();
    for (String win : driver.getWindowHandles()) 
    {
        if (!win.equals(parent)) 
        {
            driver.switchTo().window(win);
            break;
        }
    }

    // search org
    searchEdt.sendKeys(orgName);
    searchBtn.click();

    driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

    // switch back
    driver.switchTo().window(parent);

    saveBtn.click();
}

public void creatingNewContactWithSupportDate(String lastName, String startDate, String endDate) 
{
this.lastName.sendKeys(lastName);
this.startDate.clear();
this.startDate.sendKeys(startDate);
this.endDate.clear();
this.endDate.sendKeys(endDate);
saveBtn.click();

}
}

