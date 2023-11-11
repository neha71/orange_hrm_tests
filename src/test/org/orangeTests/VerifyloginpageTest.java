package test.org.orangeTests;

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

import pageFactoryClasses.LoginPageFactory;
import java.time.Duration;

public class VerifyloginpageTest {
	private WebDriver driver;
	private String baseUrl;
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

		// Create an object for the Page Class
		loginPage = new LoginPageFactory(driver);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		driver.get(baseUrl);
	}

	// To verify login is successful
	@Test(priority = 1)
	public void validCredentials() throws InterruptedException {
		loginPage.enterUserNameCode("Admin");
		loginPage.enterPasswordInputCode("admin123");
		loginPage.clickLoginButton();
		boolean actual_adminText = loginPage.findDashBorad();
		Assert.assertTrue(actual_adminText);
		extent.createTest("VerifyloginpageTest.validCredentials").log(Status.PASS,
				"VerifyloginpageTest.validCredentials Passed!");

		log.info("Got the stuff done");
		log.debug("Got debug stuff done");
	}

	// To verify that error message occur when username is wrong
	@Test(priority = 2)
	public void invalidUsername() throws InterruptedException {
		loginPage.enterUserNameCode("ad");
		loginPage.enterPasswordInputCode("admin123");
		loginPage.clickLoginButton();
		String actualError = loginPage.findErrorMsg();
		String expectedError = "Invalid credentials";
		Assert.assertEquals(actualError, expectedError);
		extent.createTest("VerifyloginpageTest.invalidUsername").log(Status.PASS,
				"VerifyloginpageTest.invalidUsername Passed!");
	}

	// To verify that error message occur when password is wrong
	@Test(priority = 3)
	public void invalidPassword() throws InterruptedException {
		loginPage.enterUserNameCode("admin");
		loginPage.enterPasswordInputCode("ad123");
		loginPage.clickLoginButton();
		String actualError = loginPage.findErrorMsg();
		String expectedError = "Invalid credentials";
		Assert.assertEquals(actualError, expectedError);
		extent.createTest("VerifyloginpageTest.invalidPassword").log(Status.PASS,
				"VerifyloginpageTest.invalidPassword Passed!");
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		extent.flush();
		Thread.sleep(3000);
		driver.quit();
	}
}