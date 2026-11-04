package Com.selenium.project.Object_Repository_Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Organisation_Page_Object_Repository 
{
	
// for object initialisation make sure constructor is available
	
	WebDriver driver;// global variable ,  // Rule 3: Object initialisation
	//provide the constructor
	public Organisation_Page_Object_Repository(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
@FindBy(xpath="//img[@title='Create Organization...']")
private WebElement createNewOrgBtn;

@FindBy(name="search_text")
private WebElement searchEdt;

@FindBy(name="search_field")
private WebElement search_drop_down;

@FindBy(name="submit")
private WebElement searchBtn;

@FindBy(name="search")
private WebElement searchBtn1;

public WebElement getSearchBtn1() {
	return searchBtn1;
}


public WebElement getSearchBtn() 
{
	return searchBtn;
}


public WebElement getSearchEdt()
{
	return searchEdt;
}


public WebElement getSearch_drop_down()
{
	return search_drop_down;
}



public WebElement getCreateNewOrgBtn()
{
	return createNewOrgBtn;
}


}

