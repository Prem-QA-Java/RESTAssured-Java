package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import step.getUser;
import utilities.uri;

public class getUserSteps extends getUser{

	uri uri = new uri();
	
	@Given("Get the user details of newly created user")
	public void get_the_user_details_of_newly_created_user() {
	    getTheUserDetails(uri.getUser);
	}

	@And("Vailed 200 response and user details")
	public void vailed_response_and_user_details() {
	    getUserSuccessVaild();
	}

	@Given("Give already deleted user tokens to get")
	public void give_already_deleted_user_tokens_to_get() {
	    
	}

	@Then("Vaild 401 code for get")
	public void vaild_code_for_get() {
	    
	}

	@Given("Request without headers get")
	public void request_without_headers_get() {
	    
	}

	@Then("Vaild the response of request without headers get")
	public void vaild_the_response_of_request_without_headers_get() {
	    
	}
}
