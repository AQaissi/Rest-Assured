import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.util.JSONPObject;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class UserAPITests {

	public String generateAPIWithID(String id, int statusCode) {
		return given().pathParam("id", id).
		          when().get("{id}").then().
					statusCode(statusCode).
					extract().response().asString(); 
		
	}
	



	/*
	 *setting up the API url and path
	 */
	@BeforeClass
	public void classSetup() {
		baseURI = "https://reqres.in/api";
		basePath = "/users";

	}


	/* 
	 * The CreateUser test is to create a user through API post request and then extract the id of the created user. 
	 */

	@Test
	public void Create_User() {

		JSONObject request = new JSONObject();

		request.put("name", "NAME");
		request.put("job", "JOB");


		String id = given().
				header("Content-Type", "application/json" ).
				contentType(ContentType.JSON).accept(ContentType.JSON).
				body(request.toJSONString()).
				when().post().then().statusCode(201).
				extract().jsonPath().getString("id");

		System.out.println("The returned id is "+id);

	}

	/*
	 * getDataForUserByID is a test to get the firstname and lastname of a user by his ID
	 */
	@Test
	public void Get_Data_For_User_By_ID() {
		
		String response = generateAPIWithID("7", 200);
		
		JsonPath jsonpath = new JsonPath(response);
		String firstname = jsonpath.getString("data.first_name");
		String lastname = jsonpath.getString("data.last_name");
		
		System.out.println("The user with ID #7 is "+ firstname + " " + lastname);
	}

	
	/*
	 * A negative test to get the data of non-exsiting id
	 */
	@Test
	public void Get_Data_For_Non_Existing_User() {
		String response = generateAPIWithID("$$", 404);
		Assert.assertEquals(response, "{}");
		
	}
	

}
