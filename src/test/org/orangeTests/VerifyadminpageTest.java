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

import pageFactoryClasses.AdminPageFactory;
import pageFactoryClasses.LoginPageFactory;

public class VerifyadminpageTest {
	private WebDriver driver;
	private String baseUrl;
	AdminPageFactory adminPage;
	LoginPageFactory loginPage;
	private ExtentReports extent;
	private static final Logger log = LogManager.getLogger(VerifyloginpageTest.class.getName());
	private String TEST_USERNAME = "kneha" + System.currentTimeMillis();

	@Parameters("BrowserType")
	@BeforeClass
	public void setup(String sBrowserType) {
		extent = ExtentManager.createExtentReports();
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
		adminPage = new AdminPageFactory(driver);
		adminPage.clickAdminTab();
	}

	@BeforeMethod
	public void setupBeforeEachTest() throws InterruptedException {
		Thread.sleep(1000);
	}

	@Test(priority = 1)
	public void addNewUser() throws InterruptedException {
		/* Navigate to the add employee page */
		adminPage.clickAddEmployee();
		
		/* Enter employee details */
		adminPage.selectUserRole();
		adminPage.enterUserNameAddPage(TEST_USERNAME);
		adminPage.enterPasswords("blahblahTemp1");
		adminPage.selectStatus();
		adminPage.enterEmpName("a");
		adminPage.clickSaveButton();

		Assert.assertTrue(adminPage.isResultSuccess());
	}
	
	@Test(priority = 2)
	public void searchExistingUser() throws InterruptedException {
		adminPage.enterUserNameSearchPage(TEST_USERNAME);
		adminPage.clickSearchButton();

		Thread.sleep(1000); // wait for the results
		Assert.assertTrue(adminPage.isSearchSuccessful());
	}
	
	@Test(priority = 3)
	public void deleteSearchedUser() throws InterruptedException {
		adminPage.clickSearchButton();
		Thread.sleep(1000); // wait for the results
		Assert.assertTrue(adminPage.isSearchSuccessful());
			
		adminPage.deleteSearchedRecord();
		Assert.assertTrue(adminPage.isResultSuccess());
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
		Thread.sleep(3000);
		driver.quit();
	}
}
