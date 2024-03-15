package stepDefinitions;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.asserts.SoftAssert;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.response.Response;
import utilities.*;

public class createUser extends apiHelper {

	Logger log = Logger.getLogger(getClass());
	
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
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			userFirstName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 0);
			userLastName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 1);
			userEmail = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 2);
			userPassword = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 3);
			
			json.changeProperties(getClass().getSimpleName(),
					new String[] { "firstName", "lastName", "email", "password" },
					new String[] { userFirstName, userLastName, userEmail, userPassword });
			repo = postRequestJson(url, null, json.body);
			if(repo.toString().contains("token")) {
			excelReadAndWrite.setDataToExcel(userExcelFile, i, 4,
					repo.then().extract().path("token").toString());
			excelReadAndWrite.setDataToExcel(userExcelFile, i, 5, repo.asPrettyString());
			excelReadAndWrite.setDataToExcel(userExcelFile, i, 6, String.valueOf(repo.statusCode()));
			}
			excelReadAndWrite.setDataToExcel(userExcelFile, i, 7, repo.asPrettyString());
			excelReadAndWrite.setDataToExcel(userExcelFile, i, 8, String.valueOf(repo.statusCode()));
			
		}
	}

	public void reponse() {
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			if (excelReadAndWrite.getDataFromEcxel(userExcelFile, 0, 6).equals("Success Code") 
					&& excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 6).equals("201")) {
				userFirstName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 2);

				String response = excelReadAndWrite.getDataFromEcxel(userExcelFile, i,
						5);
				JSONObject jsonObject = new JSONObject(response);

				sa.assertEquals(userFirstName, jsonObject.getJSONObject("user").get("firstName"));
				sa.assertEquals(userLastName, jsonObject.getJSONObject("user").get("lastName"));
				sa.assertEquals(userEmail, jsonObject.getJSONObject("user").get("email"));
			} else if (excelReadAndWrite.getDataFromEcxel(userExcelFile, 0, 8).equals("Edit Code")) {
				String response = excelReadAndWrite.getDataFromEcxel(userExcelFile, i,
						7);
				JSONObject jsonArray = new JSONObject(response);

				sa.assertEquals(prop("mailExistsMsg"), jsonArray.getString("message"));
			} else{
				repo.prettyPrint();
			}
		}
		sa.assertAll();
	}

	public void differentMetods(String url, String method) {
		excelReadAndWrite.readExcel(userExcelFile);
		if (method.equals("Get")) {
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
				userFirstName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 2);
				userPassword = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 3);

				json.changeProperties(getClass().getSimpleName(),
						new String[] { "firstName", "lastName", "email", "password" },
						new String[] { userFirstName, userLastName, userEmail, userPassword });
				repo = getRequestJson(url, null, json.body);
				excelReadAndWrite.setDataToExcel(userExcelFile, i, 9, repo.asPrettyString());
				excelReadAndWrite.setDataToExcel(userExcelFile, i, 10, String.valueOf(repo.statusCode()));
			}
		} else if (method.equals("Put")) {
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
				userFirstName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 2);
				userPassword = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 3);

				json.changeProperties(getClass().getSimpleName(),
						new String[] { "firstName", "lastName", "email", "password" },
						new String[] { userFirstName, userLastName, userEmail, userPassword });
				repo = putRequestJson(url, null, json.body);
				excelReadAndWrite.setDataToExcel(userExcelFile, i, 11, repo.asPrettyString());
				excelReadAndWrite.setDataToExcel(userExcelFile, i, 12, String.valueOf(repo.statusCode()));
			}
		} else if (method.equals("Delete")) {
			for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
				userFirstName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 0);
				userLastName = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 1);
				userEmail = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 2);
				userPassword = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 3);

				json.changeProperties(getClass().getSimpleName(),
						new String[] { "firstName", "lastName", "email", "password" },
						new String[] { userFirstName, userLastName, userEmail, userPassword });
				repo = deleteRequestJson(url, null, json.body);
				excelReadAndWrite.setDataToExcel(userExcelFile, i, 13, repo.asPrettyString());
				excelReadAndWrite.setDataToExcel(userExcelFile, i, 14, String.valueOf(repo.statusCode()));
			}

		}

	}

	public void differentMethodResponse(){
		excelReadAndWrite.readExcel(userExcelFile);
		for (int i = 1; i <= excelReadAndWrite.lastRow(); i++) {
			String response = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 9);
			XmlPath htmlPath = new XmlPath(CompatibilityMode.HTML, response);

			sa.assertEquals("404", htmlPath.getString("html.head.title"));
			sa.assertEquals("Page Not Found", htmlPath.getString("html.body.div.header.h1"));

			response = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 11);
			htmlPath = new XmlPath(CompatibilityMode.HTML, response);

			sa.assertEquals("Application Error", htmlPath.getString("html.head.title"));

			response = excelReadAndWrite.getDataFromEcxel(userExcelFile, i, 13);
			htmlPath = new XmlPath(CompatibilityMode.HTML, response);

			sa.assertEquals("Application Error", htmlPath.getString("html.head.title"));
		}
		sa.assertAll();
	}

	public void emptyBody(String url) {
		repo = postRequestJson(url, null);
	}
	
	public void emptyBodyResponseAndWithOutHeadersResponse() {
		sa.assertEquals(repo.getStatusCode(), 400);
		sa.assertEquals(repo.path("_message"), "User validation failed");
		sa.assertEquals(repo.path("message"), "User validation failed: password: Path `password` is required., lastName: Path `lastName` is required., firstName: Path `firstName` is required.");
		sa.assertEquals(repo.path("errors.password.name"), "ValidatorError");
		sa.assertEquals(repo.path("errors.password.message"), "Path `password` is required.");
		sa.assertEquals(repo.path("errors.password.kind"), "required");
		sa.assertEquals(repo.path("errors.password.path"), "password");
		sa.assertEquals(repo.path("errors.password.properties.message"), "Path `password` is required.");
		sa.assertEquals(repo.path("errors.password.properties.type"), "required");
		sa.assertEquals(repo.path("errors.password.properties.path"), "password");
		
		sa.assertEquals(repo.path("errors.lastName.name"), "ValidatorError");
		sa.assertEquals(repo.path("errors.lastName.message"), "Path `lastName` is required.");
		sa.assertEquals(repo.path("errors.lastName.kind"), "required");
		sa.assertEquals(repo.path("errors.lastName.path"), "lastName");
		sa.assertEquals(repo.path("errors.lastName.properties.message"), "Path `lastName` is required.");
		sa.assertEquals(repo.path("errors.lastName.properties.type"), "required");
		sa.assertEquals(repo.path("errors.lastName.properties.path"), "lastName");
		
		sa.assertEquals(repo.path("errors.firstName.name"), "ValidatorError");
		sa.assertEquals(repo.path("errors.firstName.message"), "Path `firstName` is required.");
		sa.assertEquals(repo.path("errors.firstName.kind"), "required");
		sa.assertEquals(repo.path("errors.firstName.path"), "firstName");
		sa.assertEquals(repo.path("errors.firstName.properties.message"), "Path `firstName` is required.");
		sa.assertEquals(repo.path("errors.firstName.properties.type"), "required");
		sa.assertEquals(repo.path("errors.firstName.properties.path"), "firstName");
		sa.assertAll();
	}

	public void withOutHeaders(String url) {
		repo = postRequestJsonWithOutHeader(url);
		
	}

}
