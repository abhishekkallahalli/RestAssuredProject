package org.tyss.university.modules;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.university.genericutility.BaseClass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.restassured.response.Response;
@Listeners(org.tyss.university.genericutility.ListenerImplementationClass.class)

/**
 * This class has 2 Tests to run HTTP GET protocol for the API in Positive and Negative scenario.
 * This Protocol and API is used to Fetch the universities content in the server.
 * @author Abhishek K H
 */
public class GetUniversitiesTest extends BaseClass {

	@Test(priority = 1)
	public void GetUniversities () throws JsonMappingException, JsonProcessingException {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "universities", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 200);
		assertionUtility.validateStatusLine(responseBody, "OK");
		assertionUtility.validateContentType(responseBody, "application/json");
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateJsonValueByJsonPath(responseBody, "[0].UniversityName", "University of Toronto");
		assertionUtility.validateResponseBody(responseBody, responseExpectedData1);
	}

	@Test(priority = 2)
	public void validateGetForUnauthorization() {
		Response responseBody = restAssuredUtility.sendGetRequest(baseUrl, "universities");
		assertionUtility.validateStatusCode(responseBody, 401);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Unauthorized");
	}

}
