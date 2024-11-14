package org.tyss.university.modules;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.university.genericutility.BaseClass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.response.Response;
@Listeners(org.tyss.university.genericutility.ListenerImplementationClass.class)

/**
 * This class has 3 Tests to run HTTP GET protocol for the API in Positive and Negative scenarios.
 * This Protocol and API is used to Fetch the University247 content in the server.
 * @author Abhishek K H
 */
public class GetUniversity247Test extends BaseClass {

	@Test(priority = 1)
	public void GetUniversity247 () throws JsonMappingException, JsonProcessingException {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "university247", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 200);
		assertionUtility.validateStatusLine(responseBody, "OK");
		assertionUtility.validateContentType(responseBody, "application/json");
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateJsonValueByJsonPath(responseBody, "UniversityName", "University of Toronto");
		assertionUtility.validateResponseBody(responseBody, responseExpectedData);
	}

	@Test(priority = 2)
	public void validateGetForUnauthorization() {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "university247");
		assertionUtility.validateStatusCode(responseBody, 401);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Unauthorized");
	}

	@Test(priority = 3)
	public void validateGetForNotFoundRequest() {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "university/api", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 404);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Not Found");
	}
}
