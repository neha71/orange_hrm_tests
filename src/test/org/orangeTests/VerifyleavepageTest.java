package test.org.orangeTests;

import java.time.Duration;

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

import pageFactoryClasses.LeavePageFactory;
import pageFactoryClasses.LoginPageFactory;

public class VerifyleavepageTest {
	private WebDriver driver;
	private String baseUrl;
	LeavePageFactory leavePage;
	LoginPageFactory loginPage;
	private ExtentReports extent;
	
	private static final Logger log = LogManager.getLogger(VerifyloginpageTest.class.getName());

	@Parameters("BrowserType")
	@BeforeClass
	public void setup(String sBrowserType) {
		extent = ExtentManager.fetchExtentReport();
		driver = DriverManager.fetchDriver(sBrowserType);

		baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.get(baseUrl);

		// Login to reach the admin page
		loginPage = new LoginPageFactory(driver);
		loginPage.enterUserNameCode("Admin");
		loginPage.enterPasswordInputCode("admin123");
		loginPage.clickLoginButton();
		
		// Create an object for Leave Page and click leave tab
		leavePage = new LeavePageFactory(driver);
	}

	@BeforeMethod
	public void setupBeforeEachTest() throws InterruptedException {
		Thread.sleep(1000);
	}
	
	@Test(priority = 4)
	public void searchLeaveWithDefaultDates() throws InterruptedException {
		leavePage.clickLeaveTab();
		leavePage.selectLeaveStatus();
		Thread.sleep(1000);
		leavePage.clickSearchButton();
		
		Thread.sleep(1000);
		Assert.assertTrue(leavePage.isSearchSuccessful());
	}
	
	@Test(priority = 5)
	public void searchLeaveWithCustomDates() throws InterruptedException {
		leavePage.clickLeaveTab();
		leavePage.selectLeaveStatus();
		leavePage.setDates();
		Thread.sleep(1000);
		leavePage.clickSearchButton();
		
		Thread.sleep(1000);
		Assert.assertTrue(leavePage.isSearchSuccessful());
	}
	
	@Test(priority = 6)
	public void searchLeaveWithCustomDatesAndEmployee() throws InterruptedException {
		leavePage.clickLeaveTab();
		leavePage.selectLeaveStatus();
		leavePage.setDates();
		leavePage.enterEmpName("a");
		Thread.sleep(1000);
		leavePage.clickSearchButton();
		
		Thread.sleep(1000);
		Assert.assertTrue(leavePage.isSearchSuccessful());
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
		loginPage.logout();
	}	
}
