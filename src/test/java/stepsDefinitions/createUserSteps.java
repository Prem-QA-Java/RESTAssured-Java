package stepsDefinitions;

import calls.createUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import utilities.uri;

public class createUserSteps {

    uri uri = new uri();
    createUser createUser = new createUser();

    @Given("User name, mail and password and send the request")
    public void user_name_mail_and_password_and_send_the_request() {
        createUser.c_User(uri.createUser);
    }

    @Then("Vailded the response")
    public void vailded_the_response() {
    	createUser.reponse();
    }

    @Given("Give already existing user mail in body")
    public void give_already_existing_user_mail_in_body() {
    	
    }

    @Then("Vaild 400 and user already exists message.")
    public void vaild_and_user_already_exists_message() {

    }

    @Given("^Different method like (.+) to the call$")
    public void different_method_like_get_to_the_call(String methods) {

    }

    @Then("Vaild the 404 in html response.")
    public void vaild_the_404_in_html_response() {
    }

    @Given("In body give empty body.")
    public void in_body_give_or_empty_body() {

    }

    @Then("Vaild the user vaildation fail response")
    public void vaild_the_user_vaildation_fail_response() {

    }

    @Given("Request without headers")
    public void request_without_headers() {

    }

    @Then("Vaild the response of request without headers.")
    public void vaild_the_response_of_request_without_headers() {

    }
}
