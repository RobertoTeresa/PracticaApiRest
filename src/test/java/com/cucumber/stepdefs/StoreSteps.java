package com.cucumber.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.*;

public class StoreSteps {

    private final String ORDERID="11121999";

    private Response storeResponse = null;
    private JsonPath jsonPathOrder = null;

    @Before("@Store")
    public static void before(){
        RestAssured.baseURI="https://petstore.swagger.io/v2/store";
    }

    //Post Add order
    @Given("the following POST request that adds a order")
    public void postAddOrder(){
        File file = new File("src/main/resources/JSON/order/postStore.json");
        storeResponse =  given().contentType(ContentType.JSON).body(file).post("/order");
    }

    @Then("the status code is 200 for the POST order add request")
    public void validatePostAddOrderStatus() {
        assertTrue("The response is not 200",storeResponse.statusCode()==200);
    }

    @And("the body response of the POST request contains the {string} and {string} of the order added")
    public void validateResponsePostAddOrder(String valueId,String valuePetId) {
        jsonPathOrder = new JsonPath(storeResponse.body().asString());
        String jsonOrderId = jsonPathOrder.getString("id");
        String jsonPetId = jsonPathOrder.getString("petId");
        assertEquals("The value of the ID field of the order is not what is expected",valueId,jsonOrderId);
        assertEquals("The value of the ID field  of the pet is not what is expected",valuePetId,jsonPetId);
    }

    //Get order by ID
    @Given("the following GET request that brings the order by his ID")
    public void geteOrderByID(){
        storeResponse =  given().log().all().get("/order/" + ORDERID);
    }

    @And("the status code is 200 for the GET order by ID request")
    public void validateGetOrderByIDStatus() {
        assertTrue("The response is not 200",storeResponse.statusCode()==200);
    }

    @Then("the body response of the GET order by ID request contains the {string} and {string} of the order")
    public void validateResponseGetOrderByID(String valueId,String valuePetId) {
        jsonPathOrder = new JsonPath(storeResponse.body().asString());
        String jsonOrderId = jsonPathOrder.getString("id");
        String jsonPetId = jsonPathOrder.getString("petId");
        assertEquals("The value of the ID field of the order is not what is expected",valueId,jsonOrderId);
        assertEquals("The value of the ID field  of the pet is not what is expected",valuePetId,jsonPetId);
    }

    //Delete order by ID
    @Given("the following DELETE request that deletes a order by his ID")
    public void deleteOrderByID(){
        storeResponse =  given().log().all().delete("/order/" + ORDERID);
    }

    @Then("the status code is 200 for the DELETE order by ID request")
    public void validateDeleteOrderByIDStatus() {
        assertTrue("The response is not 200",storeResponse.statusCode()==200);
    }

    @And("the body response of the DELETE order by ID request contains the {string} of the order")
    public void validateResponseDeleteOrderByID(String valueId) {
        jsonPathOrder = new JsonPath(storeResponse.body().asString());
        String jsonOrderId = jsonPathOrder.getString("message");
        assertEquals("The value of the ID field is not what is expected",valueId,jsonOrderId);
    }


    //Get inventory
    @Given("the following GET request that brings the order inventory")
    public void getOrderInventory(){
        storeResponse =  given().log().all().get("/inventory");
    }

    @Then("the status code is 200 for the GET order inventory request")
    public void validateResponseGetOrderInventory() {
        assertTrue("The response is not 200",storeResponse.statusCode()==200);
    }

    @And("the body response of the GET order inventory request is not empty")
    public void validateBodyGetOrderInventory() {
        assertNotNull(storeResponse.body());
        assertNotEquals("", storeResponse.body().toString());
    }













}
