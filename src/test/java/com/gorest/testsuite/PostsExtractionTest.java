package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .queryParam("page", 1)
                .queryParam("per_page", 25)
                .get("/posts")
                .then().statusCode(200);
    }
    //1. Extract the title
    @Test
    public void test001(){
        List<String> title = response.extract().path("title");
        System.out.println("The title of all data : "+title);
    }
    //2. Extract the total number of record
    @Test
    public void test002(){
        int size=response.extract().path("total.size()");
        System.out.println("The total number of records : "+size);
    }
    //3. Extract the body of 15th record
    @Test
    public void test003(){
        String record=response.extract().path("[14].body");
        System.out.println("The body of 15th record : "+record);
    }

    //4. Extract the user_id of all the records

    @Test
    public void test004(){
        List<Integer> ids=response.extract().path("user_id");
        System.out.println("The user_id of all the records : "+ids);
    }
    //5. Extract the title of all the records
    @Test
    public void test005(){
        List<String> title=response.extract().path("title");
        System.out.println("The title of all the records : "+title);
    }
    //6. Extract the title of all records whose user_id = 2223281
    @Test
    public void test006(){
       List<String> title = response.extract().path("findAll{it.user_id == 2223281 }.title");
        System.out.println("Title of all records whose user_id = 2223281" + title);
    }

    //7. Extract the body of all records whose id = 2671
    @Test
    public void test007() {
        String body = response.extract().path("find{it.id == 38825}.body");
        System.out.println("the body of all records whose id = 38825 are " + body);
    }
}