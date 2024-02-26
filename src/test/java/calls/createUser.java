package calls;

import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import utilities.*;

public class createUser extends base {

	apiHelper apiHelper = new apiHelper();
	jsonReadAndWrite json = new jsonReadAndWrite();
	variables variables = new variables();

	Response repo;
	SoftAssert sa = new SoftAssert();

	String userFirstName;
	String userLastName;
	String userEmail;
	String userPassword;

	public void c_User(String url) {
		excelReadAndWrite.readExcel("\\src\\test\\resources\\excels\\users.xlsx");
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			userFirstName = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 0);
			userLastName = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 1);
			userEmail = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 2);
			userPassword = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 3);

			System.out.println(userFirstName+" "+userLastName+" "+userEmail+" "+userPassword);
			json.changeProperties(getClass().getSimpleName(),
					new String[] { "firstName", "lastName", "email", "password" },
					new String[] { userFirstName, userLastName, userEmail, userPassword });
			repo = apiHelper.postRequestJson(url, json.body);
			excelReadAndWrite.setDataToExcel("\\src\\test\\resources\\excels\\users.xlsx", i, 4,
					repo.then().extract().path("token").toString());
			excelReadAndWrite.setDataToExcel("\\src\\test\\resources\\excels\\users.xlsx", i, 5, repo.asPrettyString());
		}
	}

	public void reponse() {
		if (repo.statusCode() == 201) {
			excelReadAndWrite.readExcel("\\src\\test\\resources\\excels\\users.xlsx");
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
				userFirstName = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 2);

				String response = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i,
						5);
				JSONObject jsonObject = new JSONObject(response);

				sa.assertEquals(userFirstName, jsonObject.getJSONObject("user").get("firstName"));
				sa.assertEquals(userLastName, jsonObject.getJSONObject("user").get("lastName"));
				sa.assertEquals(userEmail, jsonObject.getJSONObject("user").get("email"));
			}
		} else if(repo.statusCode() == 400) {
			excelReadAndWrite.readExcel("\\src\\test\\resources\\excels\\users.xlsx");
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {

				String response = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i,
						6);
				JSONObject jsonObject = new JSONObject(response);

				sa.assertEquals(userFirstName, jsonObject.getJSONObject("message"));
			}
		} else {
			repo.prettyPrint();
		}
	}
	
	public void e_User(String url) {
		excelReadAndWrite.readExcel("\\src\\test\\resources\\excels\\users.xlsx");
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			userFirstName = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 0);
			userLastName = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 1);
			userEmail = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 2);
			userPassword = excelReadAndWrite.getDataFromEcxel("\\src\\test\\resources\\excels\\users.xlsx", i, 3);

			System.out.println(userFirstName+" "+userLastName+" "+userEmail+" "+userPassword);
			json.changeProperties(getClass().getSimpleName(),
					new String[] { "firstName", "lastName", "email", "password" },
					new String[] { userFirstName, userLastName, userEmail, userPassword });
			repo = apiHelper.postRequestJson(url, json.body);
			excelReadAndWrite.setDataToExcel("\\src\\test\\resources\\excels\\users.xlsx", i, 6, repo.asPrettyString());
		}
	}

}
