package org.tyss.university.genericutility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * This utility class implements ItestListner methods to set and generates Extent reports for the Tests.
 * @author Abhishek K H
 */
public class ListenerImplementationClass implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		JavaUtility javaUtility = new JavaUtility();
		// Create ExtentSparkReporter to generate the HTML report
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(
				javaUtility.getCurrentProjectDirectory() + "\\Execution_Reports\\Suite_Report_"
						+ javaUtility.getDateAndTimeInSpecifiedFormat("yyyyMMdd_HHmmss") + ".html");
		sparkReporter.config().setDocumentTitle("Automation Test Report");
		sparkReporter.config().setReportName("University Automation Suite Report");
		sparkReporter.config().setTheme(com.aventstack.extentreports.reporter.configuration.Theme.DARK);
		// Initialize ExtentReports
		ExtentReports extentReports = new ExtentReports();
		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Customer Name", "University");
		extentReports.setSystemInfo("Environment", "Mock-Server");
		extentReports.setSystemInfo("Framework", "Maven Built Rest Assured(JAVA)");
		extentReports.setSystemInfo("User", "Abhishek K H");
		// Set the ExtentReports instance for future use
		UtilityObjectClass.setExtentReports(extentReports);
	}

	@Override
	public void onTestStart(ITestResult result) {
		UtilityObjectClass.getExtentTest().assignAuthor("Abhishek_K_H");
		UtilityObjectClass.getExtentTest().assignCategory("Generic Test Scripts");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		UtilityObjectClass.getExtentTest().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		UtilityObjectClass.getExtentTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		UtilityObjectClass.getExtentTest().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		UtilityObjectClass.getExtentReports().flush();
	}

}
