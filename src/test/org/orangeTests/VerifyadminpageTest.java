package test.org.orangeTests;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

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

	@BeforeClass
	public void setup() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/sparkReport.html");
		extent.attachReporter(spark);
	}

	@BeforeMethod
	public void setupBeforeEachTest() {
		driver = new ChromeDriver();
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

	@Test(priority = 1)
	public void addNewUser() throws InterruptedException {
		/* Navigate to the add employee page */
		adminPage.clickAddEmployee();
		
		/* Enter employee details */
		adminPage.selectUserRole();
		adminPage.enterUserNameAddPage(TEST_USERNAME);
		adminPage.enterPasswords("blahblahTemp1");
		adminPage.selectStatus();
		adminPage.enterEmpName("Li");
		adminPage.clickSaveButton();

		Assert.assertTrue(adminPage.isResultSuccess());
		extent.createTest("VerifyadminpageTest.addNewUser").log(Status.PASS, "VerifyadminpageTest.addNewUser Passed!");

		log.info("VerifyadminpageTest.addNewUser passed");
	}
	
	@Test(priority = 2)
	public void searchExistingUser() throws InterruptedException {
		adminPage.enterUserNameSearchPage(TEST_USERNAME);
		adminPage.clickSearchButton();

		Thread.sleep(1000); // wait for the results
		Assert.assertTrue(adminPage.isSearchSuccessful());
		extent.createTest("VerifyadminpageTest.searchExistingUser").log(Status.PASS, "VerifyadminpageTest.searchExistingUser Passed!");
		log.info("VerifyadminpageTest.searchExistingUser passed");
	}
	
	@Test(priority = 3)
	public void deleteSearchedUser() throws InterruptedException {
		adminPage.enterUserNameSearchPage(TEST_USERNAME);
		adminPage.clickSearchButton();

		Thread.sleep(1000); // wait for the results
		Assert.assertTrue(adminPage.isSearchSuccessful());
		
		adminPage.deleteSearchedRecord();
		Assert.assertTrue(adminPage.isResultSuccess());
		
		extent.createTest("VerifyadminpageTest.deleteSearchedUser").log(Status.PASS, "VerifyadminpageTest.deleteSearchedUser Passed!");
		log.info("VerifyadminpageTest.deleteSearchedUser passed");
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		extent.flush();
		Thread.sleep(3000);
		driver.quit();
	}
}
