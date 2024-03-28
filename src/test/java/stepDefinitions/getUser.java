package stepDefinitions;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import utilities.apiHelper;
import utilities.excelReadAndWrite;

public class getUser extends apiHelper{
	
	Logger log = Logger.getLogger(getClass());
	
	Response repo;
	SoftAssert sa = new SoftAssert();
	
	String userExcelFile = "\\src\\test\\resources\\excels\\users.xlsx";
	
	String userFirstName;
	String userLastName;
	String userEmail;
	String userPassword;
	
	public void getTheUserDetails(String url) {
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			
			repo = getRequest(url, excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 4));
			
			excelReadAndWrite.setDataToExcel(userExcelFile, i, 17, String.valueOf(repo.statusCode()));
			excelReadAndWrite.setDataToExcel(userExcelFile, i, 18, repo.asPrettyString());
		}
	}
	
	public void getUserSuccessVaild() {
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			sa.assertEquals(excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 17), "200");
			String response = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 18);
			JSONObject jsonObject = new JSONObject(response);
			sa.assertEquals(jsonObject.getString("firstName"), excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 0));
			sa.assertEquals(jsonObject.getString("lastName"), excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 1));
			sa.assertEquals(jsonObject.getString("email"), excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 2));
		}
		sa.assertAll();
	}

}
