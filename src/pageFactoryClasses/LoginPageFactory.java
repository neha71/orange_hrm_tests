package pageFactoryClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageFactory {

	WebDriver driver;

	// Elements present in this Page

	@FindBy(xpath = "//input[@name=\"username\"]")
	private WebElement usernameInput;

	@FindBy(xpath = "//input[@type=\"password\"]")
	private WebElement passwordInput;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;

	@FindBy(partialLinkText = "Dashboard")
	private WebElement dashboard;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]")
	private WebElement errormsg;
	
	@FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon'])[1]")
	private WebElement userDropDownArrow;
	
	@FindBy(partialLinkText = "Logout")
	private WebElement logoutButton;

	// Constructor to initialize this Class and the Elements
	public LoginPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Methods/Actions for the elements

	public void enterUserNameCode(String userNameCode) {
		usernameInput.clear();
		usernameInput.sendKeys(userNameCode);
	}

	public void enterPasswordInputCode(String passNameCode) {
		passwordInput.clear();
		passwordInput.sendKeys(passNameCode);
	}

	public void clickLoginButton() {
		loginButton.click();
	}
	
	public void logout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		userDropDownArrow.click();
		wait.until(ExpectedConditions.visibilityOf(logoutButton));
		logoutButton.click();
	}

	public String findErrorMsg() {
		String actual_error = errormsg.getText();
		return actual_error;

	}

	public boolean findDashBorad() {
		boolean dashboard_display = dashboard.isDisplayed();
		return dashboard_display;
	}
}