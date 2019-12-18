package apiTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class LandlordsAPI {
	//Post Request--Create a new Lnadlord
	//@Test
	public void addAlandlord() {
		
		Map<String,String> landlord = new HashMap<String, String>();
		landlord.put("firstName", "MD"); //data for body
		landlord.put("lastName", "Alam");
		//landlord.put("trusted", "false");
		 
		
		
		given()
			.contentType("application/json")// set the body content type
			.body(landlord)
		.when()
			.post("http://localhost:8085/landlords")// set the url
			
		.then()
			.statusCode(201)
			.log().body()
			.body("firstName", equalTo("MD"))
			.body("lastName", equalTo("Alam"));
			
		
		
		
	}
	//Get Request--- get all the landlords
	@Test
	public void getallLandlords() {
		
		Response res=
		given()
		.when()
			.get("http://localhost:8085/landlords")
		.then()
		.statusCode(200)
		.log().body()
		
		.extract().response();
		
		String jsonData = res.asString();
		
		Assert.assertEquals(jsonData.contains("md"), true);// check firstname in response
		Assert.assertEquals(jsonData.contains("alam"), true);// check lastname in response

	}
	//Update a landlord
	@Test
	public void UpdateAlandlord() {
		
		
		Map<String,String> landlord = new HashMap<String, String>();
		landlord.put("firstName", "Akar"); // insert updated values
		landlord.put("lastName", "Wadud");
		
		Response res=
		
		given()
			.contentType("application/json")
			.body(landlord)
		.when()
			.put("http://localhost:8085/landlords/iK8pPKnx") //must update the endpoint with id
		.then()
		.log().body()
		.statusCode(200)
		.extract().response();
		
		
		 String response = res.asString();
		 Assert.assertEquals(response.contains("successfully updated"), true );
		
	}

}
