package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        //RestAssured.port= ;
        //  RestAssured.basePath="/sers?page=1&per_page=20";

        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001(){
        response.body("total.size()",equalTo(20));
        //response.body("id", hasSize(20));
    }

    //2. Verify the if the name of id = 2223229 is equal to ”Deeptiman Varma”
    @Test
    public void test002() {
       response.body("findAll{it.id == 2223229}.name " , hasItem("Deeptiman Varma"));
    }


    //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
    @Test
    public void test003() {
        response.body("name", hasItem("Deeptiman Varma"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
    //Guha, Karthik Dubashi IV)
    @Test
    public void testoo4(){
        response.body("name",hasItems("Deeptiman Varma","Meghnath Devar","Krishnadas Chaturvedi"));
    }
    //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”

    @Test
    public void test005(){

        response.body("find { it.id == 2223229 }.email" , equalTo("deeptiman_varma@stoltenberg-olson.test"));
    }
    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006(){
        //response.body("")
        response.body("find { it.name == 'Aatreya Shah' }.status" , equalTo("active"));
    }
    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007(){
        response.body("find {it.name == 'Deeptiman Varma'}.gender",equalTo("male"));
    }
}
