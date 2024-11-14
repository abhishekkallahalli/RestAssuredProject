package org.tyss.university.modules;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.university.genericutility.BaseClass;
import io.restassured.response.Response;
@Listeners(org.tyss.university.genericutility.ListenerImplementationClass.class)

/**
 * This class has 4 Tests to run HTTP Delete protocol for the API in Positive and Negative scenarios.
 * This Protocol and API is used to delete the content in the server.
 * @author Abhishek K H
 */
public class DeleteUniversityNameTest extends BaseClass {
	
	@Test(priority = 1)
	public void DeleteUniversityName () {
		Response responseBody = restAssuredUtility.sendDeleteRequest(baseUrl, "university?universityName=delectus", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 204);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "NO CONTENT");
	}

	
	@Test(priority = 2)
	public void validateDeleteForUnauthorization() {
		Response responseBody = restAssuredUtility.sendDeleteRequest(baseUrl, "university?universityName=delectus");
		assertionUtility.validateStatusCode(responseBody, 401);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Unauthorized");
	}
	
	@Test(priority = 3)
	public void validateDeleteForNotFoundRequest() {
		Response responseBody = restAssuredUtility.sendDeleteRequest(baseUrl, "zafin-interview-dummy-host.io/api/university?universityName=delectus", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 404);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Not Found");
	}
	
	@Test(priority = 4)
	public void validateDeleteForNotValidRequest() {
		Response responseBody = restAssuredUtility.sendDeleteRequest(baseUrl, "university?universityName=", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 422);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Unprocessable Entity");
	}
}
