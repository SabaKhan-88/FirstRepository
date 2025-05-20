package com.httpsRequests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.APIPOJOClasses.AuthPOJO;

import io.restassured.response.Response;

public class HowToCreatePostRequests {
  @Test
  public void basicPatternforPostCall() {

	  Response res= given()
	       .header("Content-Type","application/json")
	       .body("{\n"
	  		+ "    \"username\" : \"admin\",\n"
	  		+ "    \"password\" : \"password123\"\n"
	  		+ "}")

	  .when().post("https://restful-booker.herokuapp.com/auth");

	  //status code
	  System.out.println("Status code is: "+res.getStatusCode());

	  //log the response
	  res.then().log().body();

  }


  @Test
  public void postUisngHashmap()

  {
	 System.out.println("Post Using Hashmap.....");

	  HashMap<String,Object> map = new HashMap<String,Object>();
	  map.put("username","admin");
	  map.put("password","password123");

	 Response res = given()
	 .header("Content-Type", "application/json")
	 .body(map)

	 .when().post("https://restful-booker.herokuapp.com/auth");

	 //Status code
	 System.out.println("Status code is : "+res.getStatusCode());

	 //log the json response
	 res.then().log().body();

  }

	 @Test
	 public void postRequestUsingPOJO()
	 {
		 //payload

		 AuthPOJO auth = new AuthPOJO();
		 auth.setUsername("admin");
		 auth.setPassword("password123");

		 Response res=given()
		        .header("Content-Type", "application/json")
		        .body(auth)

		        .when().post("https://restful-booker.herokuapp.com/auth");

		 // status code
		 System.out.println("Status code is: "+res.getStatusCode());

		 //log the json response
		 res.then().log().body();

		 //validation

		 String un = auth.getUsername();
		 Assert.assertEquals(un,"admin");
		 System.out.println("User name is matched....");

		 String pass = auth.getPassword();
		 Assert.assertEquals(pass, "password123");
		 System.out.println("Password is matched....");

		 String token = res.jsonPath().getString("token");
		 System.out.println("Token: " + token);

	 }

  }



