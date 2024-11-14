package org.tyss.university.genericutility;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentTest;

/*
 * This Base class contains public variables and TestNG annotation before and after methods  which we can directly implement in the test methods.
 * @author Abhishek K H
 */
public class BaseClass {

	public FileUtility fileUtility = new FileUtility();
	public JavaUtility javaUtility = new JavaUtility();
	public AssertionUtility assertionUtility = new AssertionUtility();
	public RestAssuredUtility restAssuredUtility = new RestAssuredUtility();
	public String baseUrl = "";
	public String apiKeyValue = "";
	public String responseExpectedData = "";
	public String responseExpectedData1 = "";


	/**
	 * Before method Executes before each test method within a class, Initialize Extent report object, variables data.
	 * @param ITestResult - The response object to validate.
	 * @author Abhishek K H
	 */
	@BeforeMethod
	public void setUp(ITestResult result) {
		ExtentTest extentTestReport = UtilityObjectClass.getExtentReports().createTest(javaUtility.convertToTitleCase(result.getMethod().getMethodName()));
		UtilityObjectClass.setExtentTest(extentTestReport);
		baseUrl = fileUtility.getDataFromPropertyFile(IConstants.PROPERTY_FILE_PATH, "BASE_URL");
		apiKeyValue = fileUtility.getDataFromPropertyFile(IConstants.PROPERTY_FILE_PATH, "API_KEY");
		responseExpectedData = fileUtility.getDataFromPropertyFile(IConstants.PROPERTY_FILE_PATH, "RESPONSE_BODY");
		responseExpectedData1 = fileUtility.getDataFromPropertyFile(IConstants.PROPERTY_FILE_PATH, "RESPONSE_BODY2");
	}

}
