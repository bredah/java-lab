package rest.spark.java8.restassured;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static spark.SparkBase.awaitInitialization;
import static spark.SparkBase.stop;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import db.entity.Contact;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import rest.spark.java8.Server;

public class ServerITest
{
	
	private Contact contact;
	
	@BeforeClass
	public static void setUpClass() {
		// Start Server
		Server server = new Server();
		server.establishRoutes();
		// Wait for server initialization
		awaitInitialization();
		
		// Set the base URL
		RestAssured.baseURI = "http://localhost";
		// Set the PORT
		RestAssured.port = 8888;
		// Set the PROXY
		// RestAssured.proxy("http://127.0.0.1", 8080);
		
	}
	
	@AfterClass
	public static void tearDownClass() {
		// Stop server
		stop();
	}
	
	@Before
	public void setUp() {
		contact = new Contact(1, "XPTO", "xpto@email.com", "1234567890");
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testGet() {
		Response response = when().get("/api").then()
				// Validate: Response Code
				.statusCode(200)
				// Validate: Header
				.contentType("text/html").header("foo", "xpto").header("header01", "001").header("header02", "002")
				// Validate: Body
				.extract().response();
		// Validate: Response
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals(contact, new Gson().fromJson(response.asString(), Contact.class));
	}
	
	@Test
	public void testGetWithParameter() {
		String name = "Person";
		when().get("/get/" + name).then()
				// Validate: Response Code
				.statusCode(200)
				// Validate: Body
				.body(containsString("Hello " + name));
	}
	
	@Test
	public void testPost() {
		when().post("/api").then()
				// Validate: Response Code
				.statusCode(200)
				// Validate: Body
				.body(containsString("POST"));
	}
	
	@Test
	public void testPostWithBody() {
		Response response = given()
				// Set body request
				.body(new Gson().toJson(contact)).when()
				// Path to request
				.post("/post").then().extract().response();
		// Validate: Response
		Assert.assertEquals(200, response.statusCode());
		Assert.assertEquals(contact.toString(), response.asString());
	}
	
	@Test
	public void testPut() {
		
		boolean response = when()
				// Path to request
				.put("/api").then()
				// Validate: Response Code
				.statusCode(200)
				// Validate: Body
				.extract().jsonPath().getBoolean("response");
		Assert.assertTrue(response);
	}
	
	@Test
	public void testDelete() {
		String response = when().delete("/api").then()
				// Validate: Response Code
				.statusCode(200)
				// Validate: Body
				.extract().jsonPath().getString("response");
		Assert.assertEquals("failed", response);
	}
	
}
