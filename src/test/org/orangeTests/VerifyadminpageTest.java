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
		
		// Create an object for Admin Page, click admin tab and then add employee button
		adminPage = new AdminPageFactory(driver);
		adminPage.clickAdminTab();
		adminPage.clickAddEmployee();
	}
	
	@Test(priority = 1)
	public void addNewUser() throws InterruptedException {
		adminPage.selectUserRole();
		adminPage.selectStatus();
		adminPage.enterUserName("kneha");
		adminPage.enterEmpName("Test 90  Collings");
		adminPage.enterPasswords("blahblahTemp1");
		/*
		loginPage.enterUserNameCode("Admin");
		loginPage.enterPasswordInputCode("admin123");
		loginPage.clickLoginButton();
		boolean actual_adminText = loginPage.findDashBorad();
		Assert.assertTrue(actual_adminText);
		extent.createTest("VerifyloginpageTest.validCredentials").log(Status.PASS,
				"VerifyloginpageTest.validCredentials Passed!");

		log.info("Got the stuff done");
		log.debug("Got debug stuff done");
		*/
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		extent.flush();
		Thread.sleep(3000);
		driver.quit();
	}
}

