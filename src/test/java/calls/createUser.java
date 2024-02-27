package calls;

import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import utilities.*;

public class createUser extends base {

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

	public void c_User(String url) {
		excelReadAndWrite.readExcel("userExcelFile");
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			userFirstName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 0);
			userLastName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 1);
			userEmail = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 2);
			userPassword = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 3);

			json.changeProperties(getClass().getSimpleName(),
					new String[] { "firstName", "lastName", "email", "password" },
					new String[] { userFirstName, userLastName, userEmail, userPassword });
			repo = apiHelper.postRequestJson(url, json.body);
			excelReadAndWrite.setDataToExcel("userExcelFile", i, 4,
					repo.then().extract().path("token").toString());
			excelReadAndWrite.setDataToExcel("userExcelFile", i, 5, repo.asPrettyString());
		}
	}

	public void reponse() {
		if (repo.statusCode() == 201) {
			excelReadAndWrite.readExcel("userExcelFile");
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
				userFirstName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 2);

				String response = excelReadAndWrite.getDataFromEcxel("userExcelFile", i,
						5);
				JSONObject jsonObject = new JSONObject(response);

				sa.assertEquals(userFirstName, jsonObject.getJSONObject("user").get("firstName"));
				sa.assertEquals(userLastName, jsonObject.getJSONObject("user").get("lastName"));
				sa.assertEquals(userEmail, jsonObject.getJSONObject("user").get("email"));
			}
		} else if (repo.statusCode() == 400) {
			excelReadAndWrite.readExcel("userExcelFile");
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {

				String response = excelReadAndWrite.getDataFromEcxel("userExcelFile", i,
						6);
				JSONObject jsonObject = new JSONObject(response);

				sa.assertEquals(prop("mailExistsMsg"), jsonObject.getJSONObject("message"));
			}
		} else {
			repo.prettyPrint();
		}
	}

	public void e_User(String url) {
		excelReadAndWrite.readExcel("userExcelFile");
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			userFirstName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 0);
			userLastName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 1);
			userEmail = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 2);
			userPassword = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 3);

			json.changeProperties(getClass().getSimpleName(),
					new String[] { "firstName", "lastName", "email", "password" },
					new String[] { userFirstName, userLastName, userEmail, userPassword });
			repo = apiHelper.postRequestJson(url, json.body);
			excelReadAndWrite.setDataToExcel("userExcelFile", i, 6, repo.asPrettyString());
		}
	}

	public void differentMetods(String url, String method) {
		excelReadAndWrite.readExcel("userExcelFile");
		if (method.equals("Get")) {
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
				userFirstName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 2);
				userPassword = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 3);

				json.changeProperties(getClass().getSimpleName(),
						new String[] { "firstName", "lastName", "email", "password" },
						new String[] { userFirstName, userLastName, userEmail, userPassword });
				repo = apiHelper.getRequestJson(url, json.body);
				excelReadAndWrite.setDataToExcel("userExcelFile", i, 6, repo.asPrettyString());
			}
		} else if (method.equals("Put")) {
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
				userFirstName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 2);
				userPassword = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 3);

				json.changeProperties(getClass().getSimpleName(),
						new String[] { "firstName", "lastName", "email", "password" },
						new String[] { userFirstName, userLastName, userEmail, userPassword });
				repo = apiHelper.putRequestJson(url, json.body);
				excelReadAndWrite.setDataToExcel("userExcelFile", i, 6, repo.asPrettyString());
			}
		} else if (method.equals("Delete")) {
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
				userFirstName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 2);
				userPassword = excelReadAndWrite.getDataFromEcxel("userExcelFile", i, 3);

				json.changeProperties(getClass().getSimpleName(),
						new String[] { "firstName", "lastName", "email", "password" },
						new String[] { userFirstName, userLastName, userEmail, userPassword });
				repo = apiHelper.deleteRequestJson(url, json.body);
				excelReadAndWrite.setDataToExcel("userExcelFile", i, 6, repo.asPrettyString());
			}

		}

	}

}
