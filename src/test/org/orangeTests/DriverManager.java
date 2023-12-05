package test.org.orangeTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverManager {
	private static WebDriver chromeDriver = null;
	private static WebDriver firefoxDriver = null;
	
    public synchronized static WebDriver fetchDriver(String browserType) {
		if (browserType.equals("chrome")) {
			if (chromeDriver == null) {
				chromeDriver = new ChromeDriver();				
			}
			return chromeDriver;
		} else if (browserType.equals("firefox")) {
			if (firefoxDriver == null) {
				firefoxDriver = new FirefoxDriver();				
			}
			return firefoxDriver;
		}
		return null;
    }
}
