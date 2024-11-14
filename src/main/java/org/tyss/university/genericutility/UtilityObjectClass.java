package org.tyss.university.genericutility;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

/**
 * UtilityObjectClass is a utility class that leverages ThreadLocal variables to handle WebDriver instances,
 * Extent Reports, and other context-specific information like client name, browser name, and test case details
 * in a thread-safe manner. This class ensures that each thread has its own copy of the variables, making it suitable
 * for parallel test execution.
 * @author Abhishek K H
 */
public class UtilityObjectClass {

	// ThreadLocal variables to store WebDriver, XmlTest, ExtentReports, ExtentTest, and test-specific data
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentReports> extentReports = new ThreadLocal<ExtentReports>();

	/**
	 * Retrieves the ExtentTest instance for the current thread, used for logging
	 * test execution steps in the Extent Report.
	 * @return ExtentTest instance for the current thread.
	 */
	public static ExtentTest getExtentTest() {
		return extentTest.get();
	}

	/**
	 * Sets the ExtentTest instance for the current thread.
	 * @param actExtentTest - The ExtentTest instance to set.
	 */
	public static void setExtentTest(ExtentTest actExtentTest) {
		extentTest.set(actExtentTest);
	}

	/**
	 * Retrieves the ExtentReports instance for the current thread, which represents
	 * the complete report for the entire test execution.
	 * @return ExtentReports instance for the current thread.
	 */
	public static ExtentReports getExtentReports() {
		return extentReports.get();
	}

	/**
	 * Sets the ExtentReports instance for the current thread.
	 * @param actExtentReports - The ExtentReports instance to set.
	 */
	public static void setExtentReports(ExtentReports actExtentReports) {
		extentReports.set(actExtentReports);
	}

}
