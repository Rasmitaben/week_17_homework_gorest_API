package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static String name = "Zara" + TestUtils.getRandomValue();
    static String email = "Zara" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "Female";
    static String status = "active";
    static int userId;


    @Test
    public void test001() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .body(userPojo)
                .when()
                .post().then().extract().response();
        response.then().log().all().statusCode(201);
        userId = response.jsonPath().get("id");
        System.out.println("Id is: "+ userId);
    }

    @Test
    public void test002() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .get("/" + userId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void test003() {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name + "_Updated");
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .body(userPojo)
                .when()
                .put("/" + userId);
        response.then().log().all().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public void test004() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Access", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .get("/" +userId);
        response.then().statusCode(200);
        response.prettyPrint();

        String actualName = response.then().extract().body().path("name");
        Assert.assertEquals("Name is not updated", name + "_Updated", actualName);
        System.out.println("ActualName is: " + actualName);
        System.out.println("ExpectedName is: " + name + "_Updated");

    }
    @Test
    public void test005(){
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 61aafcb694ad2a184e92adcee5aae71f2288cf87d88930daf6a8b86ae21da215")
                .when()
                .delete("/" + userId);
        response.then().log().all().statusCode(204);
        response.prettyPrint();


    }
}


