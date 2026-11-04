package Com.selenium.project.Object_Repository_Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Creating_New_Organization_Page 
{
	WebDriver driver;

	public Creating_New_Organization_Page(WebDriver driver) // parameterized constructor
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@name='accountname']")
	private WebElement org_name_edit;

	@FindBy(name = "ship_street")
	private WebElement shipping_address_edit;

	@FindBy(name = "button")
	private WebElement save_btn;

	@FindBy(name = "industry")
	private WebElement Industry_dropdown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropdown;
	
	@FindBy(xpath = "//td[@id='mouseArea_Type']")
	private WebElement Type;
	
	@FindBy(xpath="//input[@id='phone' and @name='phone']")
	private WebElement phone;
	
	
	public WebElement getphone()
	{
		return phone;
	}
	public WebElement gettype()
	{
		return typeDropdown;
	}
	public WebElement getIndustry_dropdown() 
	{
		return Industry_dropdown;
	}
  public WebElement getType() 
	{
		return Type;
	}


	public WebElement getOrg_name_edit()
	{
		return org_name_edit;
	}

	public WebElement getShipping_address_edit() 
	{
		return shipping_address_edit;
	}

	public WebElement getSave_btn() 
	{
		return save_btn;
	}
// bussiness libraries 

	public void createOrg(String orgName, String address) 
	{
		org_name_edit.sendKeys(orgName);
		shipping_address_edit.sendKeys(address);
		save_btn.click();

	}

	public void createOrgWithIndustry(String orgName, String industry, String address) 
	{
		org_name_edit.sendKeys(orgName);
		Select s = new Select(Industry_dropdown);
		s.selectByVisibleText(industry);
		shipping_address_edit.sendKeys(address);
		save_btn.click();
	}
	
	

	

}
