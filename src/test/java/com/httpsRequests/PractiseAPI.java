package com.httpsRequests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PractiseAPI {
  //@Test
  public void testsingleUser() {

	  given()

	  .when()
	   .get("https://reqres.in/api/users/2")

	  .then()
	   .statusCode(200)
	   
	   .body("data.id", equalTo(2))
	   
	   .log().body();
  }
	   
	   @Test
	   public void testSingleUserUsingBDD() 
	   {
		   
		   given()
		   
		   .when()
		    .get("https://reqres.in/api/users/2")
		   
		   .then()
		     .statusCode(200)
		     
		     //validate id should 2
		     .body("data.id", equalTo(2))
		     
		     //.log().all()
		   
		     //.log().headers()
		     
		     .log().body();
		   
		   
	   }
	   
	   @Test
	   public void testlistofUsers()
	   {
		   
		  Response res=given()
		  
		  .when().get("https://reqres.in/api/users?page=2");
		  
		  //validating status code
		  int statuscode=res.getStatusCode();
		  Assert.assertEquals(statuscode,200);
		  System.out.println("status code matched");
		  
		  //validate json body or log the body
		  res.then().log().body();
		  
		  //jsonvalidation
		 int records=res.jsonPath().getInt("total");
		 Assert.assertEquals(records, 12);
		 System.out.println("Records matched....."+records);
		  
	   }

  }

