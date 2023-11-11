package test.org.orangeTests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pageFactoryClasses.LeavePageFactory;
import pageFactoryClasses.LoginPageFactory;

public class VerifyleavepageTest {
	private WebDriver driver;
	private String baseUrl;
	LeavePageFactory leavePage;
	LoginPageFactory loginPage;
	private ExtentReports extent;
	
	private static final Logger log = LogManager.getLogger(VerifyloginpageTest.class.getName());

	@BeforeClass
	public void setup() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/sparkReport.html");
		extent.attachReporter(spark);
	}

	@Parameters("BrowserType")
	@BeforeMethod
	public void setupBeforeEachTest(String sBrowserType) {
		if (sBrowserType.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (sBrowserType.equals("firefox")) {
			driver = new FirefoxDriver();
		}

		baseUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.get(baseUrl);

		// Login to reach the admin page
		loginPage = new LoginPageFactory(driver);
		loginPage.enterUserNameCode("Admin");
		loginPage.enterPasswordInputCode("admin123");
		loginPage.clickLoginButton();

		// Create an object for Admin Page and click admin tab
		leavePage = new LeavePageFactory(driver);
		leavePage.clickLeaveTab();
	}
	
	@Test(priority = 1)
	public void searchLeaveWithDefaultDates() throws InterruptedException {
		leavePage.selectLeaveStatus();
		Thread.sleep(1000);
		leavePage.clickSearchButton();
		
		Thread.sleep(1000);
		Assert.assertTrue(leavePage.isSearchSuccessful());
		extent.createTest("VerifyleavepageTest.searchLeaveWithDefaultDates").log(Status.PASS, "VerifyleavepageTest.searchLeaveWithDefaultDates Passed!");
		log.info("VerifyleavepageTest.searchLeaveWithDefaultDates passed");
	}
	
	@Test(priority = 2)
	public void searchLeaveWithCustomDates() throws InterruptedException {
		leavePage.selectLeaveStatus();
		leavePage.setDates();
		Thread.sleep(1000);
		leavePage.clickSearchButton();
		
		Thread.sleep(1000);
		Assert.assertTrue(leavePage.isSearchSuccessful());
		extent.createTest("VerifyleavepageTest.searchLeaveWithCustomDates").log(Status.PASS, "VerifyleavepageTest.searchLeaveWithCustomDates Passed!");
		log.info("VerifyleavepageTest.searchLeaveWithCustomDates passed");
	}
	
	@Test(priority = 3)
	public void searchLeaveWithCustomDatesAndEmployee() throws InterruptedException {
		leavePage.selectLeaveStatus();
		leavePage.setDates();
		leavePage.enterEmpName("Li");
		Thread.sleep(1000);
		leavePage.clickSearchButton();
		
		Thread.sleep(1000);
		Assert.assertTrue(leavePage.isSearchSuccessful());
		extent.createTest("VerifyleavepageTest.searchLeaveWithCustomDatesAndEmployee").log(Status.PASS, "VerifyleavepageTest.searchLeaveWithCustomDatesAndEmployee Passed!");
		log.info("VerifyleavepageTest.searchLeaveWithCustomDatesAndEmployee passed");
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		extent.flush();
		Thread.sleep(3000);
		driver.quit();
	}
	
}
