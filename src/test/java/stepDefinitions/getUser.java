package stepDefinitions;

import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import utilities.apiHelper;
import utilities.excelReadAndWrite;

public class getUser extends apiHelper{
	
	Response repo;
	SoftAssert sa = new SoftAssert();
	
	String userExcelFile = "\\src\\test\\resources\\excels\\users.xlsx";
	
	

}
