package test.org.orangeTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static ExtentReports extentReports = null;
    public synchronized static ExtentReports fetchExtentReport() {
    	if (extentReports == null) {
    		extentReports = createExtentReports();
    	}
    	return extentReports;
    }
    
    private static ExtentReports createExtentReports() {
    	extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter("target/sparkReport.html");
        reporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Author", "Neha Kumari");
        return extentReports;
    }
}
