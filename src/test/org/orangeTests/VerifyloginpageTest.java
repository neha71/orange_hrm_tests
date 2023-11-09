package test.org.orangeTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageFactoryClasses.LoginPageFactory;
import java.time.Duration;

  
    
public class VerifyloginpageTest {
	private WebDriver driver;
	private String baseUrl;
	LoginPageFactory loginPage;
    private static final Logger log = LogManager.getLogger(VerifyloginpageTest.class.getName());

	@BeforeMethod
	public void setup() {
		driver = new ChromeDriver();
		baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	
	    // Create an object for the Page Class
	    loginPage = new LoginPageFactory(driver);
	        
	    driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
        driver.get(baseUrl);
    }  
  
    //To verify login is successful 
    @Test(priority=1)
    public void verify1() throws InterruptedException {
    	loginPage.enterUserNameCode("Admin");
    	loginPage.enterPasswordInputCode("admin123");
        loginPage.clickLoginButton();
        boolean actual_adminText=loginPage.findDashBorad();
        Assert.assertTrue(actual_adminText);
        log.info("Got the stuff done");
        log.debug("Got debug stuff done");
     }
    
    //To verify that error message occur when username is wrong     
    @Test(priority=2)	    
    public void verify2() throws InterruptedException {
    	loginPage.enterUserNameCode("ad");
    	loginPage.enterPasswordInputCode("admin123");
        loginPage.clickLoginButton();
        String actualError=loginPage.findErrorMsg();
        String expectedError="Invalid credentials";
        Assert.assertEquals(actualError,expectedError);     
	}

   //To verify that error message occur when password is wrong 
   @Test(priority=3)
   public void verify3() throws InterruptedException {
    	loginPage.enterUserNameCode("admin");
    	loginPage.enterPasswordInputCode("ad123");
        loginPage.clickLoginButton();
        String actualError=loginPage.findErrorMsg();
        String expectedError="Invalid credentials";
        Assert.assertEquals(actualError,expectedError);    
    }
   
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }		
}