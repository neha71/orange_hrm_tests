package pageFactoryClasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeavePageFactory {
	
	WebDriver driver;

	// Elements present in this Page
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[3]/a/span")
	private WebElement leaveTab;

	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/input")
	private WebElement fromDate;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/input")
	private WebElement toDate;

	@FindBy(xpath = "(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]")
	private WebElement selectLeaveStatus;
	
	@FindBy(xpath = "(//div[@role='listbox']//child::div)[4]")
	private WebElement leaveStatusListElem2;
	
	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	private WebElement empName;

	@FindBy(xpath = "//*[@role='listbox']")
	private List<WebElement> empNameAutoSuggestions;
	
	@FindBy(xpath = "//*[@type='submit' and contains(., \"Search\")]")
	private WebElement searchButton;
	
	@FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/span")
	private WebElement searchResultHeader;
	
	Actions actions;

	// Constructor to initialize this Class and the Elements
	public LeavePageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
	}	

	public void clickLeaveTab() {
		leaveTab.click();
	}
	
	public void selectLeaveStatus() {
		actions.moveToElement(selectLeaveStatus).click().build().perform();
		leaveStatusListElem2.click();
	}
	
	public void setDates() {
		fromDate.sendKeys("2022-01-01");
		toDate.sendKeys("2022-12-31");
	}
	
	public void enterEmpName(String empName) throws InterruptedException {
		this.empName.sendKeys(empName);
		Thread.sleep(2000);
		empNameAutoSuggestions.get(0).click();
	}
	
	public void clickSearchButton() {
		searchButton.click();
	}
	
	public boolean isSearchSuccessful() {
		String result = searchResultHeader.getText().toLowerCase();
		return (result.contains("records found") || result.contains("record found"));
	}
	
}
