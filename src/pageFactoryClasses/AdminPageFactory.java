package pageFactoryClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPageFactory {

	WebDriver driver;

	// Elements present in this Page

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a")
	private WebElement adminTab;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")
	private WebElement addButton;

	@FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]")
	private WebElement selectUserRole;

	@FindBy(xpath = "(//div[@role='listbox']//child::div)[2]")
	private WebElement userRoleListElem2;

	@FindBy(xpath = "//div[3]//div[1]//div[2]//div[1]//div[1]//div[2]//i[1]")
	private WebElement selectStatus;

	@FindBy(xpath = "(//div[@role='listbox']//child::div)[2]")
	private WebElement statusListElem2;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	private WebElement empName;

	@FindBy(xpath = "//*[@role='listbox']")
	private List<WebElement> empNameAutoSuggestions;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input")
	private WebElement userNameAddPage;

	@FindBy(xpath = "//input[@type='password']")
	private List<WebElement> passwordElements;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]")
	private WebElement saveButton;

	@FindBy(xpath = "(//p[@class='oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text'])")
	private WebElement result;

	/* Search Page elements */
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
	private WebElement userNameSearchPage;

	@FindBy(xpath = "//*[@type='submit' and contains(., \"Search\")]")
	private WebElement searchButton;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")
	private WebElement searchResultHeader;

	@FindBy(xpath = "(//i[@class='oxd-icon bi-trash'])[1]")
	private WebElement deleteRecord;

	@FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")
	private WebElement confirmDelete;

	Actions actions;

	// Constructor to initialize this Class and the Elements
	public AdminPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
	}

	// Methods/Actions for the elements

	public void clickAdminTab() {
		adminTab.click();
	}

	public void clickAddEmployee() {
		addButton.click();
	}

	public void selectUserRole() {
		actions.moveToElement(selectUserRole).click().build().perform();
		userRoleListElem2.click();
	}

	public void selectStatus() {
		actions.moveToElement(selectStatus).click().build().perform();
		statusListElem2.click();
	}

	public void enterUserNameAddPage(String userName) {
		this.userNameAddPage.sendKeys(userName);
	}

	public void enterEmpName(String empName) throws InterruptedException {
		this.empName.sendKeys(empName);
		Thread.sleep(2000);
		empNameAutoSuggestions.get(0).click();
	}

	public void enterPasswords(String password) {
		for (WebElement passwordElem : passwordElements) {
			passwordElem.sendKeys(password);
		}
	}

	public void clickSaveButton() {
		saveButton.click();
	}

	public boolean isResultSuccess() {
		return "success".equalsIgnoreCase(result.getText());
	}

	public void enterUserNameSearchPage(String userName) {
		this.userNameSearchPage.sendKeys(userName);
	}

	public void clickSearchButton() {
		searchButton.click();
	}

	public void deleteSearchedRecord() {
		deleteRecord.click();
		confirmDelete.click();
	}

	public boolean isSearchSuccessful() {
		return "(1) record found".equalsIgnoreCase(searchResultHeader.getText());
	}
}