package br.com.rodrigopeleias.vertx;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class RestAPIVerticleTest {

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
		final int id = get("/api/books").then().assertThat().statusCode(200)
				.extract().jsonPath()
				.getInt("find { it.name=='Clean Code'}.id");
		get("/api/books/" + id).then().assertThat().statusCode(200)
				.body("name", equalTo("Clean Code"))
				.body("author", equalTo("Uncle Bob")).body("id", equalTo(id));
	}

	@Test
	public void checkWeCanAddAndDeleteAProduct() {
		Book book = given()
				.body("{\"name\":\"Clean Code\", \"origin\":\"Uncle Bob\", \"numberOfPages\":\"500\"}")
				.request().post("/api/whiskies").thenReturn().as(Book.class);
		assertThat(book.getName()).isEqualToIgnoringCase("Clean Code");
		assertThat(book.getAuthor()).isEqualToIgnoringCase("Uncle Bob");
		assertThat(book.getId()).isNotZero();

		get("/api/books/" + book.getId()).then().assertThat().statusCode(200)
				.body("name", equalTo("Jameson"))
				.body("origin", equalTo("Ireland"))
				.body("id", equalTo(book.getId()));

		delete("/api/books/" + book.getId()).then().assertThat()
				.statusCode(200);

		get("/api/books/" + book.getId()).then().assertThat().statusCode(404);
	}
}
