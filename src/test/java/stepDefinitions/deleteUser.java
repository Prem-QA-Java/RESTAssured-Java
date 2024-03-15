package stepDefinitions;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.response.Response;
import utilities.*;

public class deleteUser extends base {

	Logger log = Logger.getLogger(getClass());
	
	apiHelper apiHelper = new apiHelper();
	jsonReadAndWrite json = new jsonReadAndWrite();
	variables variables = new variables();

	Response repo;
	SoftAssert sa = new SoftAssert();

	String userExcelFile = "\\src\\test\\resources\\excels\\users.xlsx";

	String userFirstName;
	String userLastName;
	String userEmail;
	String userPassword;

}
