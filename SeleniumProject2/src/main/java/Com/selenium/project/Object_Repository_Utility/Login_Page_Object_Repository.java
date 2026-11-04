package Com.selenium.project.Object_Repository_Utility;



import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Com.selenium.project.Generic.Webdriver_Utility.WebDriverUtility;


/**
 * @author SANJAY
 * 
 * Contains login Page Elements & Bussiness Libraries like login()
 * 
 */

public class Login_Page_Object_Repository extends WebDriverUtility // Rule 1:create a seperate class
{
	WebDriver driver;// global variable ,  // Rule 3: Object initialisation
	//provide the constructor
	public Login_Page_Object_Repository(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

//Rule 2: Object creation identify all the element by using @findBy
	@FindBy(name="user_name")
	private WebElement usernameEtd;

	
	@FindBy(name="user_password")
	private WebElement passwordEtd;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	
	public WebElement getUsernameEtd()  //Rule 4: Object Encapsulation
	                                    // Rule 5: Object Utilisation
	{
		return usernameEtd;
	}

	public WebElement getPasswordEtd() 
	{
		return passwordEtd;
	}

	public WebElement getLoginBtn() 
	{
		return loginBtn;
	}
	
	/**
	 * Login to application based on username, password, url arguments 
	 * @param url
	 * @param username
	 * @param password
	 */
	
	
	// rule 5 : you can utilise via getters or else you can provide action business method
	public void loginToApp(String url, String username, String password)
	{
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEtd.sendKeys(username);
		passwordEtd.sendKeys(password);
		loginBtn.click();
	}
	
}
