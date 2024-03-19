package stepDefinitions;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.response.Response;
import utilities.*;

public class deleteUser extends apiHelper {

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

	public void getTokensForExcelAndDeleteUser(String url) {
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			String token = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 4);

			repo = deleteRequest(url, token);

			excelReadAndWrite.setDataToExcel(userExcelFile, i, 15, String.valueOf(repo.statusCode()));
		}

	}
	
	public void getTokensForExcel(String url) {
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			String token = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 4);

			repo = deleteRequest(url, token);
			
			excelReadAndWrite.setDataToExcel(userExcelFile, i, 16, String.valueOf(repo.statusCode()));
		}

	}

	public void deleteSuccessCodeVadilation() {
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			sa.assertEquals(excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 15), "200");
		}
		sa.assertAll();
	}
	
	public void getUserDetailsAndValidateResponse(String url) {
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			repo = getRequest(url, excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 4));
			sa.assertEquals(String.valueOf(repo.statusCode()), "401");
			sa.assertEquals(repo.path("error"), "Please authenticate.");
		}		
		sa.assertAll();
	}
	
	public void deleteFailedCodeValidation() {
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			sa.assertEquals(excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 16), "401");
		}
		sa.assertAll();
	}
	
    public void deleteWithOutHeader(String url) {
		repo = deleteRequestWithOutHeader(url);
	}

	public void deleteHeaderLessResponse() {
		sa.assertEquals(repo.statusCode(), 401);
		sa.assertEquals(repo.path("error"), "Please authenticate.");
		sa.assertAll();
	}
}
