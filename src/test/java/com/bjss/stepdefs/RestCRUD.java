package com.bjss.stepdefs;

import com.bjss.restapi.HTTPComponent;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;


public class RestCRUD {


    @When("Post Data to Create a User")
    public void post_Data_to_Create_a_User() throws Exception {
        HTTPComponent.postData();
    }

    @Then("Status Code should be {int}")
    public void status_Code_should_be(int responsecode) throws Exception {
        Assert.assertEquals(Integer.parseInt(HTTPComponent.getHTTPResponseCode().toString()), responsecode);
    }


    @Then("Response should contain {string}")
    public void response_should_contain(String node) {
        Assert.assertEquals(HTTPComponent.checkRessponseContainsID(node), true);
    }


    @When("Put Data to Update a user with {string}  and  {string} with ID as {string}")
    public void put_Data_to_Update_a_user_with_and_with_ID_as(String name, String job, String id) throws Exception {
        HTTPComponent.putData(name, job, id);
    }

    @Then("Status code should be {int}")
    public void status_code_should_be(int responsecode) throws Exception {
        Assert.assertEquals(Integer.parseInt(HTTPComponent.getHTTPResponseCode().toString()), responsecode);
    }

    @Then("Response should contain {string} and {string}")
    public void response_should_contain_and(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Then("Response should contain updatedAt")
    public void response_should_contain_updatedAt() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("Data is retrieved for ID {string}")
    public void data_is_retrieved_for_ID(String ID) throws Exception {

        System.out.println(HTTPComponent.getData(ID));
    }

    @Then("Response should contain id, Firstname , Lastname  ,Avatar")
    public void response_should_contain_id_Firstname_Lastname_Avatar() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @Given("Connection is made to make a DELETE call")
    public void connection_is_made_to_make_a_DELETE_call() {
        // Write code here that turns the phrase above into concrete actions
        throw new cucumber.api.PendingException();
    }

    @When("Data is deleted for ID {string}")
    public void data_is_deleted_for_ID(String id) throws Exception {
        System.out.println(HTTPComponent.deleteData(id));
    }


}
