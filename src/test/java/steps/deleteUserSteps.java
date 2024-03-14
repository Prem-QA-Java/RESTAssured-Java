package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import stepDefinitions.deleteUser;
import utilities.uri;

public class deleteUserSteps {

    uri uri = new uri();
    deleteUser deleteUser = new deleteUser();

    @Then("Give already exists user tokens")
    public void given_already_exists_user_token() {
    	
    }
    
    @Then("Get the token from excel and delete user")
    public void get_the_token_from_excel_and_delete_user() {
    	
    }

    @Given("Give already deleted user tokens")
    public void give_already_deleted_user_tokens() {
    	
    }

    @Then("Vaild 401 code")
    public void vaild_401_code() {
    	
    }

    @And("Vailed 200 response")
    public void vailed_200_response() {
    }

    @And("Try to get the user")
    public void try_to_get_the_user() {

    }

    @Given("Request without headers delete")
    public void request_without_headers_delete() {

    }

    @Then("Vaild the response of request without headers delete")
    public void vaild_the_response_of_request_without_headers_delete() {

    }
}
