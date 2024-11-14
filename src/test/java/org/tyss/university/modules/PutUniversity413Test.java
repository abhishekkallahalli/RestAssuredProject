package org.tyss.university.modules;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.tyss.university.genericutility.BaseClass;

import io.restassured.response.Response;
@Listeners(org.tyss.university.genericutility.ListenerImplementationClass.class)

/**
 * This class has 2 Tests to run HTTP PUUT protocol for the API in Positive and Negative scenarios.
 * This Protocol and API is used to Update the existing University content in the server.
 * @author Abhishek K H
 */
public class PutUniversity413Test extends BaseClass {

	@Test(priority = 1)
	public void updateUniversity413 () {
		Response responseBody = restAssuredUtility.sendPutRequest(baseUrl, "university413", "api_key", apiKeyValue);
		assertionUtility.validateStatusCode(responseBody, 201);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Created");
	}

	@Test(priority = 2)
	public void validateUpdateForUnauthorization() {
		Response responseBody = restAssuredUtility.sendPutRequest(baseUrl, "university413");
		assertionUtility.validateStatusCode(responseBody, 401);
		assertionUtility.validateResponseTime(responseBody, 2000);
		assertionUtility.validateStatusLine(responseBody, "Unauthorized");
	}

}
