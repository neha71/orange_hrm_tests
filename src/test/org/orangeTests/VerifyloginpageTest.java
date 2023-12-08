package test.org.orangeTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageFactoryClasses.LoginPageFactory;

import java.time.Duration;

public class VerifyloginpageTest {
	private WebDriver driver;
	private String baseUrl;
	LoginPageFactory loginPage;
	private ExtentReports extent;
	private static final Logger log = LogManager.getLogger(VerifyloginpageTest.class.getName());

	@Parameters("BrowserType")
	@BeforeClass
	public void setup(String sBrowserType) {
		extent = ExtentManager.fetchExtentReport();
		driver = DriverManager.fetchDriver(sBrowserType);

		baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

		// Create an object for the Page Class
		loginPage = new LoginPageFactory(driver);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
	}

	@BeforeMethod
	public void setupBeforeEachTest() throws InterruptedException {
		driver.get(baseUrl);
	}

	// To verify login is successful
	@Test(priority = 7)
	public void validCredentials() {
		loginPage.enterUserNameCode("Admin");
		loginPage.enterPasswordInputCode("admin123");
		loginPage.clickLoginButton();
		boolean actual_adminText = loginPage.findDashBorad();
		
		Assert.assertTrue(actual_adminText);
		loginPage.logout();
	}

	// To verify that error message occur when username is wrong
	@Test(priority = 8)
	public void invalidUsername() {
		loginPage.enterUserNameCode("ad");
		loginPage.enterPasswordInputCode("admin123");
		loginPage.clickLoginButton();
		String actualError = loginPage.findErrorMsg();
		String expectedError = "Invalid credentials";
		
		Assert.assertEquals(actualError, expectedError);
	}

	// To verify that error message occur when password is wrong
	@Test(priority = 9)
	public void invalidPassword() {
		loginPage.enterUserNameCode("admin");
		loginPage.enterPasswordInputCode("ad123");
		loginPage.clickLoginButton();
		String actualError = loginPage.findErrorMsg();
		String expectedError = "Invalid credentials";
		
		Assert.assertEquals(actualError, expectedError);
	}
	
	@AfterMethod
	public void logExtentReport(ITestResult result) {
		String testName = result.getTestClass() + "." + result.getName();
		ExtentTest extTest = extent.createTest(testName);
		if (result.isSuccess()) {
			extTest.log(Status.PASS, "Passed!");
			log.info(testName + " PASSED");
		} else {
			extTest.log(Status.FAIL, "FAILED!");
			log.error(testName + " FAILED");
		}
	}
	
	@AfterTest
	public void flushExtentReport() {
		extent.flush();
	}

	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(1000);
		driver.quit();
	}
}