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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PetSteps {

    private final String PETID="1999";

    private Response petResponse = null;
    private JsonPath jsonPathPet = null;

    @Before("@Pet")
    public static void before(){
        RestAssured.baseURI="https://petstore.swagger.io/v2/";
    }

    //Post request add pet
    @Given("the following POST request adds a pet")
    public void postAddPet(){
      File file = new File("src/main/resources/JSON/pet/postPet.json");
        petResponse =  given().contentType(ContentType.JSON).body(file).post("/pet");
    }

    @Then("the status code is 200 for the POST pet add request")
    public void validatePostAddPetStatus() {
        assertTrue("The response is not 200",petResponse.statusCode()==200);
    }

    @And("the body response of the POST request contains the {string} and {string} of the pet added")
    public void validateResponsePostAddPet(String valueId,String valueName) {
        jsonPathPet = new JsonPath(petResponse.body().asString());
        String jsonPetId = jsonPathPet.getString("id");
        String jsonPetName = jsonPathPet.getString("name");
        assertEquals("The value of the ID field is not what is expected",valueId,jsonPetId);
        assertEquals("The value of the name field is not what is expected",valueName,jsonPetName);
    }

    //Put request update pet
    @Given("the following PUT request update a pet")
    public void putUpdatePet(){
        File file = new File("src/main/resources/JSON/pet/putPet.json");
        petResponse =  given().contentType(ContentType.JSON).body(file).put("/pet");
    }

    @Then("the status code is 200 for the PUT pet update request")
    public void validatePutUpdatePetStatus() {
        System.out.println("Staus code is " + petResponse.statusCode());
        assertTrue("The response is not 200",petResponse.statusCode()==200);
    }

    @Then("the body response of the PUT request contains the {string} and {string} of the pet updated")
    public void validateResponsePutUpdatePet(String valueId,String valueName) {
        jsonPathPet = new JsonPath(petResponse.body().asString());
        String jsonPetId = jsonPathPet.getString("id");
        String jsonPetName = jsonPathPet.getString("name");
        assertEquals("The value of the ID field is not what is expected",valueId,jsonPetId);
        assertEquals("The value of the name field is not what is expected",valueName,jsonPetName);
    }

    //Get pet by status
    @Given("the following GET request that brings the pets by his status")
    public void getPetByStatus(){
        petResponse =  given().log().all().param("status","available").get("/pet/findByStatus");
    }

    @Then("the status code is 200 for the GET pets by status request")
    public void validateGetPetByStatus() {
        assertTrue("The response is not 200",petResponse.statusCode()==200);
    }

    @And("the body response of the GET request contains the previously added pet with the status {string}")
    public void validateResponseGetPetStatus(String valueStatus) {
        JsonPath jsonPathPet = new JsonPath(petResponse.body().asString());
        String status = jsonPathPet.get("find {it.id =="+1999+"}.status").toString();
        assertEquals("The value of the status field is not what is expected",valueStatus,status);
    }

    //Get pet by ID
    @Given("the following GET request that brings the pets by his ID")
    public void getPetByd(){
        petResponse =  given().log().all().get("/pet/" + PETID);
    }

    @Then("the status code is 200 for the GET pets by ID request")
    public void validateGetPetByIdStatus() {
        assertTrue("The response is not 200",petResponse.statusCode()==200);
    }

    @And("the body response of the GET pet by ID request contains the {string} and {string} of the pet")
    public void validateResponseGetPetByID(String valueId,String valueName) {
        jsonPathPet = new JsonPath(petResponse.body().asString());
        String jsonPetId = jsonPathPet.getString("id");
        String jsonPetName = jsonPathPet.getString("name");
        assertEquals("The value of the ID field is not what is expected",valueId,jsonPetId);
        assertEquals("The value of the name field is not what is expected",valueName,jsonPetName);
    }

    //Delete pet
    @Given("the following DELETE request that deletes a pet by his ID")
    public void deletePetByID(){
        petResponse =  given().log().all().delete("/pet/" + PETID);
    }

    @And("the status code is 200 for the DELETE pets by ID request")
    public void validateDeletePetByIDStatus() {
        assertTrue("The response is not 200",petResponse.statusCode()==200);
    }

    @Then("the body response of the DELETE pet by ID request contains the {string} of the pet")
    public void validateResponseDeletePetByID(String valueId) {
        jsonPathPet = new JsonPath(petResponse.body().asString());
        String jsonPetId = jsonPathPet.getString("message");
        assertEquals("The value of the ID field is not what is expected",valueId,jsonPetId);
    }


}



