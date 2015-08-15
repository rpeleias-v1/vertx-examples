package br.com.rodrigopeleias.vertx;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MyRestIT {

	@Before
	public void configureRestAssured() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.basePath = "yourbasepath";
		RestAssured.port = Integer.getInteger("http.port", 8080);
	}
	
	@After
	public void unconfigureRestAssured() {
		RestAssured.reset();
	}
	
	@Test
	public void checkThatWeCanRetrieveIndividualProduct() {
		//get the list of whiskies
		final int id = get("/api/whiskies").then()
				.assertThat()
				.statusCode(200)
				.extract()
				.jsonPath().getInt("find { it.name=='Black Label'}.id");
		// get individual resource and check the content
		get("/api/whiskies/" + id).then()
			.assertThat()
			.statusCode(200)
			.body("name", equalTo("Black Label"))
			.body("origin", equalTo("Scotland"))
			.body("id", equalTo(id));
	}
	
	@Test
	public void checkWeCanAddAndDeleteAProduct() {
		Whisky whisky = given()
				.body("{\"name\":\"Jameson\", \"origin\":\"Ireland\"}")
				.request().post("/api/whiskies").thenReturn().as(Whisky.class);
		assertThat(whisky.getName()).isEqualToIgnoringCase("Jameson");
		assertThat(whisky.getOrigin()).isEqualToIgnoringCase("Ireland");
		assertThat(whisky.getId()).isNotZero();
		
		// check if the resource is successfully caved
		get("/api/whiskies/" + whisky.getId()).then()
			.assertThat()
			.statusCode(200)
			.body("name", equalTo("Jameson"))
			.body("origin", equalTo("Ireland"))
			.body("id", equalTo(whisky.getId()));
		
		//delete the resoruce
		delete("/api/whiskies/" + whisky.getId()).then().assertThat().statusCode(200);
		
		//check to see if resource is deleted 
		get("/api/whiskies/" + whisky.getId()).then()
			.assertThat()
			.statusCode(404);
	}
}
