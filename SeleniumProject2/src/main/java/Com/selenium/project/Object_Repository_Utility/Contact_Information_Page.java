package Com.selenium.project.Object_Repository_Utility;


import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact_Information_Page 
{
	
	public Contact_Information_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerInf;
	
	@FindBy(xpath = "//td[@id='mouseArea_Last Name']")
	private WebElement lastName;
	
	@FindBy(xpath = "//td[@id='mouseArea_Support Start Date']")
	private WebElement startDate;
	
	@FindBy(xpath = "//td[@id='mouseArea_Support End Date']")
	private WebElement endDate;
	
	@FindBy(xpath = "//td[@id='mouseArea_Organization Name']")
	private WebElement orgName;

	public WebElement getHeaderInf() {
		return headerInf;
	}

	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getStartDate() {
		return startDate;
	}

	public WebElement getEndDate() {
		return endDate;
	}

	public WebElement getOrgName() {
		return orgName;
	}
	
	
	
	
	
	
} 

