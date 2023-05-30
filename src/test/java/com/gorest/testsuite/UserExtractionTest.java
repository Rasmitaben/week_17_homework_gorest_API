package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        //RestAssured.port= ;
        //  RestAssured.basePath="/sers?page=1&per_page=20";

        response = given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().statusCode(200);
    }

    //1. Extract the All Ids

    @Test
    public void test001() {
        List<Integer> ids = response.extract().path("id");
        System.out.println("All Ids are : " + ids);
    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> names = response.extract().path("name");
        System.out.println("All Names are : " + names);
    }

    //3. Extract the name of 5th object
    @Test
    public void test003() {

        String name = response.extract().path("name[4]");
        System.out.println("Name of 5th object is " + name);
    }

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> names = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("the names of all object whose status = inactive " + names);
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> names = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("The gender of all the object whose status = active : " + names);
    }


    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> names = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("The names of the object whose gender = female : " + names);
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<String> emails = response.extract().path("findAll{it.status =='inactive'}.email");
        System.out.println("The emails of the object where status = inactive : " + emails);
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<Integer> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("the ids of the object where gender = male : " + ids);
    }

    //9. Get all the status
    @Test
    public void test009() {
        List<String> status = response.extract().path("status");
        System.out.println("all the status are : " + status);
    }

    //10. Get email of the object where name = "Meghnath Devar
    @Test
    public void test010() {
        List<String> email = response.extract().path("findAll{it.name == 'Meghnath Devar'}.email");
        System.out.println("Email of the object where name = Sen. Menaka Devar : " + email);
    }

    //11. Get gender of id = 2223227
    @Test
    public void test011() {
        String gender = response.extract().path("find{it.id==2223221}.gender");
        System.out.println(" gender of id = 5471" + gender);

    }
}