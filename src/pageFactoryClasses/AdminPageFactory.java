   package pageFactoryClasses;


    import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
	
	public class AdminPageFactory {
		
		 WebDriver driver;

		    // Elements present in this Page
		
		
		 @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a")
	      private WebElement adminTab;
	     
	     @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")
	     private WebElement addButton;

	     @FindBy(xpath="//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]")
	     private WebElement selectUserRole;
	     
	     
	     @FindBy(xpath="//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]")
	     private WebElement selectStatus;
	     
	    
	     @FindBy(xpath="//input[@placeholder='Type for hints...']")
	     private WebElement enterEmpName;
	     
	     @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")
	     private WebElement enterUserName;
	     
	     
	     @FindBy(xpath="//input[@type='password']")
	     private WebElement enterPassword;
	     
	     @FindBy(xpath="//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]")
	     private WebElement saveButton;
	     
	     
	     
	     
	  // Constructor to initialize this Class and the Elements
	     public AdminPageFactory(WebDriver driver) {
	         this.driver = driver;
	         PageFactory.initElements(driver, this);
	         }
	     
	     
	  // Methods/Actions for the elements
	      
	     public void clickAdminTab()
	     {
	    	 adminTab.click();
	     }

	     public void clickAddEmployee()
	     {
	    	 addButton.click();
	     }
	     
	     
	     public void clickSelectUserRole()
	     {
	    	 selectUserRole.click();
	     }

	     public void SelectStatus()
	     {
	    	 selectStatus.click();
	     }
 
	     
	     
	     
	     
	     
	}
	
	
	
	
	
	
	
	

	  /*   public void clickLoginButton() {
		    	 loginButton.click();
		     }
		     

	     public String findErrorMsg() {
	  
	          String actual_error=errormsg.getText();
	          return actual_error;

	      }
	      
	      

	      public boolean findDashBorad()
	      {
	          boolean dashboard_display=dashboard.isDisplayed();
	          return dashboard_display;

	      }
	      }
		    
	     */
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     

	
	
	
	
	


