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

public class UserSteps {

    private final String USERNAME="Roberto";
    private final String PASSWORD="1234";

    private Response userResponse = null;
    private JsonPath jsonPathUser = null;

    @Before("@User")
    public static void before(){
        RestAssured.baseURI="https://petstore.swagger.io/v2/user";
    }

    //Post Add user with array
    @Given("the following POST request that create using a array")
    public void postAddUserArray(){
        File file = new File("src/main/resources/JSON/user/CreateUserArray.json");
        userResponse =  given().contentType(ContentType.JSON).body(file).post("/createWithArray");
    }

    @Then("the status code is 200 for the POST request that create using a array")
    public void validatePostAddUserArrayStatus() {
        assertTrue("The response is not 200",userResponse.statusCode()==200);
    }

    @And("the body response for the POST request that create using a array contains the name 'message' with the value 'ok'")
    public void validateResponsePostAddUserArray() {
        jsonPathUser = new JsonPath(userResponse.body().asString());
        String jsonUserMessage = jsonPathUser.getString("message");
        assertEquals("The value of the message field is not what is expected","ok",jsonUserMessage);
    }

    //Post Add user with list
    @Given("the following POST request that create using a list")
    public void postAddUserList(){
        File file = new File("src/main/resources/JSON/user/CreateUserArray.json");
        userResponse =  given().contentType(ContentType.JSON).body(file).post("/createWithList");
    }

    @Then("the status code is 200 for the POST request that create using a list")
    public void validatePostAddUserListStatus() {
        assertTrue("The response is not 200",userResponse.statusCode()==200);
    }

    @And("the body response for the POST request that create using a list contains the name 'message' with the value 'ok'")
    public void validateResponsePostAddUserList() {
        jsonPathUser = new JsonPath(userResponse.body().asString());
        String jsonUserMessage = jsonPathUser.getString("message");
        assertEquals("The value of the name field is not what is expected","ok",jsonUserMessage);
    }

    //Get user by name
    @Given("the following GET request that brings user by his name")
    public void getUserByName(){
        userResponse =  given().log().all().get("/" + USERNAME);
    }

    @Then("the status code is 200 for the GET user by name request")
    public void validateGetUserByNameStatus() {
        assertTrue("The response is not 200",userResponse.statusCode()==200);
    }

    @And("the body response of the GET user by name request contains the {string} of the user")
    public void validateResponseGetUserByName(String valueName) {
        jsonPathUser = new JsonPath(userResponse.body().asString());
        String jsonUserName = jsonPathUser.getString("username");
        assertEquals("The value of the name field is not what is expected",valueName,jsonUserName);
    }

    //Update User
    @Given("the following PUT request update a user")
    public void putUserUpdate() {
        File file = new File("src/main/resources/json/user/UpdateCreateUser.json");
        userResponse =  given().contentType(ContentType.JSON).body(file).put("/" + USERNAME);
    }

    @And("the status code is 200 for the PUT user update request")
    public void validatePutUserUpdateStatus() {
        assertTrue("The response is not 200",userResponse.statusCode()==200);
    }

    @Then("the body response of the PUT request contains the {string} of the user updated")
    public void validateResponsePutUserUpdate(String valueID) {
        jsonPathUser = new JsonPath(userResponse.body().asString());
        String jsonUserId = jsonPathUser.getString("message");
        assertEquals("The value of the ID field is not what is expected",valueID,jsonUserId);
    }

    //Delete User by name
    @Given("the following DELETE request that deletes a user by his name")
    public void deleteUserByName(){
        userResponse =  given().log().all().delete("/" + USERNAME);
    }

    @And("the status code is 200 for the DELEsE user by name request")
    public void validateDeleteUserByNameStatus() {
        assertTrue("The response is not 200",userResponse.statusCode()==200);
    }

    @Then("the body response of the DELETE user by name request contains the {string} of the user")
    public void validateResponseDeleteUserByName(String valueName) {
        jsonPathUser = new JsonPath(userResponse.body().asString());
        String jsonUserName = jsonPathUser.getString("message");
        assertEquals("The value of the name field is not what is expected",valueName,jsonUserName);
    }

    //User login
    @Given("the following GET request logs the user")
    public void UserLogin(){
        userResponse =  given().log().all().param("username",USERNAME).param("password",PASSWORD).get("/login");
    }

    @Then("the status code is 200 for the GET log user request")
    public void validateResponseUserLogin() {
        assertTrue("The response is not 200",userResponse.statusCode()==200);
    }

    @And("the body response of the GET log user request contains contains the name 'message' with the value 'logged in user session'")
    public void validateResponseLogInUserName() {
        jsonPathUser = new JsonPath(userResponse.body().asString());
        String jsonUserMessage = jsonPathUser.getString("message");
        assertTrue("The value of the name field is not what is expected",jsonUserMessage.contains("logged in user session"));
    }

    //User logout
    @Given("the following GET request logout the user")
    public void UserLogout(){
        userResponse =  given().log().all().get("/logout");
    }

    @Then("the status code is 200 for the GET logout user request")
    public void validateResponseUserLogout() {
        assertTrue("The response is not 200",userResponse.statusCode()==200);
    }

    @And("the body response of the GET logout user request contains contains the name 'message' with the value ok")
    public void validateResponseUserLogoutBody() {
        jsonPathUser = new JsonPath(userResponse.body().asString());
        String jsonUserMessage = jsonPathUser.getString("message");
        assertEquals("The value of the name field is not what is expected","ok",jsonUserMessage);
    }



}
