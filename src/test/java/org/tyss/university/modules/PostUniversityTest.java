package org.tyss.university.modules;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.university.genericutility.BaseClass;

import io.restassured.response.Response;
@Listeners(org.tyss.university.genericutility.ListenerImplementationClass.class)

/**
 * This class has 3 Tests to run HTTP Post protocol for the API in Positive and Negative scenarios.
 * This Protocol and API is used to create the new University content in the server.
 * @author Abhishek K H
 */
public class PostUniversityTest extends BaseClass {

	@Test(priority = 1)
	public void createUniversityWithPost () {
		Response responseBody = restAssuredUtility.sendPostRequest(baseUrl, "university", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 201);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Created");
	}


	@Test(priority = 2)
	public void validatePostForUnauthorization() {
		Response responseBody = restAssuredUtility.sendPostRequest(baseUrl, "university");
		assertionUtility.validateStatusCode(responseBody, 401);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Unauthorized");
	}

	@Test(priority = 3)
	public void validatePostForNotFoundRequest() {
		Response responseBody = restAssuredUtility.sendPostRequest(baseUrl, "zafin-interview-dummy-host.io/api/university", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 404);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Not Found");
	}
}
