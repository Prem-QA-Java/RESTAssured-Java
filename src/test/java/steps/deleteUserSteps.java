package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import stepDefinitions.deleteUser;
import utilities.uri;

public class deleteUserSteps extends deleteUser{

    uri uri = new uri();

    @Given("Already exists user tokens in excel and taken then to delete user")
    public void given_already_exists_user_token() {
    	getTokensForExcelAndDeleteUser(uri.deleteUser);
    }

    @Given("Give already deleted user tokens")
    public void give_already_deleted_user_tokens() {
    	getTokensForExcel(uri.deleteUser);
    }

    @Then("Vaild 401 code")
    public void vaild_401_code() {
    	deleteFailedCodeValidation();
    }

    @And("Vailed 200 response")
    public void vailed_200_response() {
    	deleteSuccessCodeVadilation();
    }

	@And("Try to get the user")
    public void try_to_get_the_user() {
		getUserDetailsAndValidateResponse(uri.getUser);
    }

    @Given("Request without headers delete")
    public void request_without_headers_delete() {
    	deleteWithOutHeader(uri.deleteUser);
    }

	@Then("Vaild the response of request without headers delete")
    public void vaild_the_response_of_request_without_headers_delete() {
		deleteHeaderLessResponse();
    }

}
