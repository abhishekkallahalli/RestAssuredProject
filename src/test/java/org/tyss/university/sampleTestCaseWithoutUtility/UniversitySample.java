package org.tyss.university.sampleTestCaseWithoutUtility;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class UniversitySample {
	@Test
	public void testGetUniversities() {
		baseURI = "http://127.0.0.1:4010";
		given()
				.header("api_key", "f3c84cbb-1f9a-4b87-bb5b-2d1691b24e1e")
		.when()
				.get("/universities")
		.then()
				.assertThat()
					.statusCode(200)
					.statusLine("HTTP/1.1 200 OK");
	}



}
