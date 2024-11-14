package org.tyss.university.genericutility;
import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

/**
 * This assertion utility class contains assertion POM methods which we can directly implement in the test methods.
 * @author Abhishek K H
 */
public class AssertionUtility {

	/**
	 * Validates the response status code and logs the result to the Extent Report.
	 * @param response - The response object to validate.
	 * @param expectedStatusCode - The expected HTTP status code.
	 * @author Abhishek K H
	 */
	public void validateStatusCode(Response response, int expectedStatusCode) {
		int actualStatusCode = response.getStatusCode();

		try {
			Assert.assertEquals(actualStatusCode,  expectedStatusCode, "Expected Status Code : "+ expectedStatusCode);
			UtilityObjectClass.getExtentTest().pass("Status Code Validation Passed. Expected: " + expectedStatusCode + ", Actual: " + actualStatusCode);
		} catch (AssertionError e) {
			UtilityObjectClass.getExtentTest().fail("Status Code Validation Failed. Expected: " + expectedStatusCode + ", Actual: " + actualStatusCode);
			Assert.fail("Test Failed due to Status code Missmatch", e);
		}
	}

	/**
	 * Validates the response body content-type and logs the result to the Extent Report.
	 * @param response - The response object to validate.
	 * @param expectedContentType - The expected content-type.
	 * @author Abhishek K H
	 */
	public void validateContentType(Response response, String expectedContentType) {
		String actualContentType = response.contentType();

		try {
			Assert.assertEquals(actualContentType, expectedContentType, "Expected Content-Type: " + expectedContentType);
			UtilityObjectClass.getExtentTest().pass("Content-Type Validation Passed. Expected: " + expectedContentType + ", Actual: " + actualContentType);
		} catch (AssertionError e) {
			UtilityObjectClass.getExtentTest().fail("Content-Type Validation Failed. Expected: " + expectedContentType + ", Actual: " + actualContentType);
			Assert.fail("Test Failed due to Content-Type Mismatch", e);
		}
	}

	/**
	 * Validates the response body and logs the result to the Extent Report.
	 * @param response - The response object to validate.
	 * @param expectedJsonStructure - The expected response body stored in commonData.properties.
	 * @exception JsonMappingException - Checked exception used to signal fatal problems with mapping of content.
	 * @exception JsonProcessingException - Intermediate base class for all problems encountered when processing (parsing, generating) JSON content that are not pure I/O problems.
	 * @author Abhishek K H
	 */
	public void validateResponseBody(Response response, String expectedJsonStructure) throws JsonMappingException, JsonProcessingException  {
		String actualResponseBody = response.getBody().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualJson = mapper.readValue(actualResponseBody, JsonNode.class);
		JsonNode expectedJsonNode = mapper.readValue(expectedJsonStructure, JsonNode.class);
		try {
			assertTrue(actualJson.equals(expectedJsonNode), "Expected JSON response body does not match actual response body");
			UtilityObjectClass.getExtentTest().pass("JSON Response Body Validation Passed");
		} catch (AssertionError e) {
			UtilityObjectClass.getExtentTest().fail("JSON Respose body Validation Failed. Expected: " + expectedJsonStructure);
			throw new AssertionError("JSON Response Body mismatch", e);
		}
	}

	/**
	 * Validates the response status line and logs the result to the Extent Report.
	 * @param response - The response object to validate.
	 * @param expectedStatusLine - The expected status line in string format.
	 * @author Abhishek K H
	 */
	public void validateStatusLine(Response response, String expectedStatusLine) {
		// Get the actual status line
		String actualStatusLine = response.getStatusLine().toLowerCase();
		try {
			// Check the actual status line contains the expected status line
			Assert.assertTrue(actualStatusLine.contains(expectedStatusLine.toLowerCase()), "Status Line Validation Passed");
			// Log success message to Extent Report
			UtilityObjectClass.getExtentTest().pass("Status Line Validation Passed. Actual status line: " + actualStatusLine+" contains Expected status line: "+expectedStatusLine);
		} catch (AssertionError e) {
			// Log failure if assertion fails
			UtilityObjectClass.getExtentTest().fail("Status Line Validation Failed. Actual status line: " + actualStatusLine+" doesn't contains expected status line: "+expectedStatusLine);
			Assert.fail("Test Failed due to status line mismatch", e);
		}
	}

	/**
	 * Validates the response time and logs the result to the Extent Report.
	 * @param response - The response object to validate.
	 * @param expectedMaxResponseTime - The expected response time in string format.
	 * @author Abhishek K H
	 */
	public void validateResponseTime(Response response, long expectedMaxResponseTime) {
		// Get the actual response time
		long actualResponseTime = response.getTime();
		try {
			// Validate that the actual response time is within the expected max response time
			Assert.assertTrue(actualResponseTime <= expectedMaxResponseTime, "Status Line Validation Passed");
			// Log success message
			UtilityObjectClass.getExtentTest().pass("Response Time Validation Passed. Actual response time: " + actualResponseTime + "ms is less than or equal to expected response time : " +expectedMaxResponseTime + "ms");
		} catch (AssertionError e) {
			// Log failure in case of mismatch
			UtilityObjectClass.getExtentTest().fail("Response Time Validation Failed. Actual response time: " + actualResponseTime + "ms is not less than or equal to expected response time : " +expectedMaxResponseTime + "ms" );
			Assert.fail("Test Failed due to response time mismatch", e);
		}
	}

	/**
	 * Validates the response JSON random value through JSON Path  and logs the result to the Extent Report.
	 * @param response - The response object to validate.
	 * @param jsonPath - The JSON path to get value in string format.
	 * @param expectedValue - The expected response value for the JSON Path in Object format.
	 * @author Abhishek K H
	 */
	public void validateJsonValueByJsonPath(Response response, String jsonPath, Object expectedValue) {
		// Get the actual response JsonPath
		Object actualValue = response.jsonPath().get(jsonPath).toString();
		try {
			// Validate that the actual response JsonPath is same as the expected JsonPath
			Assert.assertTrue(actualValue.equals(expectedValue), "JSON Path Validation Passed");
			// Log success message to extent report 
			UtilityObjectClass.getExtentTest().pass("JSON Path Validation Passed. Actual JSON Path: " + actualValue + " is same as expected JSON Path : " +expectedValue);
		} catch (AssertionError e) {
			// Log failure in case of mismatch to extent report 
			UtilityObjectClass.getExtentTest().fail("JSON Path Validation Passed. Actual JSON Path: " + actualValue + " is different from expected JSON Path : " +expectedValue);
			Assert.fail("Test Failed due to JSON Path mismatch", e);
		}
	}
}
