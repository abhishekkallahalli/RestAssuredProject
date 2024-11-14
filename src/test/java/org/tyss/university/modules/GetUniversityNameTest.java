package org.tyss.university.modules;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.university.genericutility.BaseClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.response.Response;
@Listeners(org.tyss.university.genericutility.ListenerImplementationClass.class)

/**
 * This class has 4 Tests to run HTTP GET protocol for the API in Positive and Negative scenarios.
 * This Protocol and API is used to Fetch the University Names content in the server.
 * @author Abhishek K H
 */
public class GetUniversityNameTest extends BaseClass {
	
	@Test(priority = 1)
	public void getUniversityName () throws JsonMappingException, JsonProcessingException {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "university?universityName=ut", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 200);
		assertionUtility.validateStatusLine(responseBody, "OK");
		assertionUtility.validateContentType(responseBody, "application/json");
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateJsonValueByJsonPath(responseBody, "UniversityName", "University of Toronto");
		assertionUtility.validateResponseBody(responseBody, responseExpectedData);
	}
	
	@Test(priority = 2)
	public void validateGetUniversityNameForBadRequest() {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "university?universityName", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 400);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "BAD REQUEST");
	}
	
	@Test(priority = 3)
	public void validateGetUniversityNameForUnauthorized() {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "university?universityName");
		assertionUtility.validateStatusCode(responseBody, 401);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "UNAUTHORIZED");
	}
	
	@Test(priority = 4)
	public void validateGetUniversityNameForNotFound() {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "zafin-interview-dummy-host.io/api/university?universityName=ut", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 404);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "NOT FOUND");
	}
}
