    package pageFactoryClasses;

    import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	public class LoginPageFactory {
		
	       WebDriver driver;

	    // Elements present in this Page
	       
	     @FindBy(xpath="//input[@name=\"username\"]")
	     private WebElement usernameInput;

	     @FindBy(xpath="//input[@type=\"password\"]")
	     private WebElement passwordInput;

	     @FindBy(xpath="//button[@type='submit']")
	     private WebElement loginButton;
	   
	     
	     @FindBy(partialLinkText="Dashboard")
	     private WebElement dashboard;
	     
	     @FindBy(xpath="//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]")
		  private WebElement errormsg;
	     
	        
	    // Constructor to initialize this Class and the Elements
	     public LoginPageFactory(WebDriver driver) {
	         this.driver = driver;
	         PageFactory.initElements(driver, this);
	         }
	     
	  
	  // Methods/Actions for the elements
	      
	      public void enterUserNameCode(String userNameCode)
	      {
	    	  usernameInput.clear();
	    	  usernameInput.sendKeys(userNameCode);
	      }

	      public void enterPasswordInputCode(String passNameCode)
	      {
	    	  passwordInput.clear();
	    	  passwordInput.sendKeys(passNameCode);
	      }

	      public void clickLoginButton() {
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
		     
	      
	      
	      
	      
	      
	      
	      
	      
	   /*   
	      
	      
	     
	     public void clickUserInputField() {
	    	 usernameInput.click();
	     }

	     public void clickPasswordInputField() {
	    	 passwordInput.click();
	     }
	     
	     
	     public void enterUserNameCode(String userNameCode) {
	         clickUserInputField();
	         usernameInput.sendKeys(userNameCode);
	     }
	      
	    public void enterPasswordInputCode(String userInputCode) {
	    	 clickPasswordInputField();
	    	 passwordInput.sendKeys(userInputCode);
	     }

	     
	     public void clickLoginButton() {
	    	 loginButton.click();
	     }
	     
	}
	     
	     
	     
	     
	     
	     
	     
	     
	     
	     
	   









	/*
	    
	     
	     
	     public void clickUsernameInput() {
	    	 usernameInput.sendKeys(returningDate);
	     }

	     public void clickDepartField() {
	    	 passwordInput.click();
	     }

	     public void clickArriveField() {
	    	 loginButton.click();
	     }

	     public void enterDepartAirportCode(String departAirportCode) {
	         clickDepartField();
	         departField.sendKeys(departAirportCode);
	     }

	     public void enterArriveAirportCode(String arriveAirportCode) {
	         clickArriveField();
	         arriveField.sendKeys(arriveAirportCode);
	     }

	     public void enterDepartDate(String departureDate) {
	         departDate.clear();
	         departDate.sendKeys(departureDate);
	     }

	     public void enterReturnDate(String returningDate) {
	         returnDate.clear();
	         returnDate.sendKeys(returningDate);
	     }

	     public void clickSubmitButton() {
	         submitButton.click();
	     }

	     
	     
	     

	}*/



