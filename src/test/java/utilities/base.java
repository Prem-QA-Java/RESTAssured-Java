package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.model.Media;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import runner.runner;

public class base {

	public RequestSpecification request = RestAssured.given();
	Response response;
	Logger logger = Logger.getLogger(getClass());
	
	QueryableRequestSpecification queryable;
	
	

	@DataProvider(name = "project")
	public Object[][] dp() {
		return new Object[][] { { "Vi" }, { "SunNxt" }, { "Manorama" } };
	}

	/*
	 * To get the values from the properties file as a String
	 */
	public String prop(String i) {
		Properties p = new Properties();
		FileInputStream f = null;
		try {
			f = new FileInputStream("src/test/resources/properties/data.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(i);
	}

	public String paramProp(String i) {
		Properties p = new Properties();
		FileInputStream f = null;
		try {
			f = new FileInputStream("src/test/resources/properties/params.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return p.getProperty(i);
	}

	/*
	 * Header for the project
	 */
	public void header() {
		request.header("X-MYRECO-Project-ID", prop("projectId"));
		request.header("X-MYRECO-API-Key", prop("apiKey"));
	}

	public void params(String key, String value) {
		request.queryParam(key, value);
	}

	public JSONObject getJson(Response repo) {
		JSONObject res = new JSONObject(repo.asString());
		return res;
	}

	public Object getValue(Response repo, String path) {
		Object value = repo.then().extract().path(path);
		return value;
	}

	public String value(Response repo, String key, String key1, int i) {
		String value = getJson(repo).getJSONArray(key).getJSONObject(i).get(key1).toString();
		return value;
	}

	public String value(Response repo, String key, String key1, String key2, int i) {
		String value = getJson(repo).getJSONArray(key).getJSONObject(i).getJSONObject(key1).get(key2).toString();
		return value;
	}

	public String value(Response repo, String key, String key1, String key2, String key3, int i) {
		String value = getJson(repo).getJSONArray(key).getJSONObject(i).getJSONObject(key1).getJSONObject(key2)
				.get(key3).toString();
		return value;
	}

	public String value(Response repo, String key) {
		String value = getJson(repo).get(key).toString();
		return value;
	}

	public boolean valueToNull(Response repo, String key, String key1, int i) {
		boolean value = getJson(repo).getJSONArray(key).getJSONObject(i).get(key1).equals(null);
		return value;
	}

	public boolean valueToNull(Response repo, String key, String key1, String key2, int i) {
		boolean value = getJson(repo).getJSONArray(key).getJSONObject(i).getJSONObject(key1).get(key2).equals(null);
		return value;
	}

	public boolean valueToNull(Response repo, String key, String key1, String key2, String key3, int i) {
		boolean value = getJson(repo).getJSONArray(key).getJSONObject(i).getJSONObject(key1).getJSONObject(key2)
				.get(key3).equals(null);
		return value;
	}

	public boolean valueToNull(Response repo, String key) {
		boolean value = getJson(repo).get(key).equals(null);
		return value;
	}

	public boolean params(String params, String keys, Object values) {
		boolean v = false;
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(params);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> entry = fields.next();
			String key = entry.getKey();
			JsonNode value = entry.getValue();
			if (key.equals(keys) && value.equals(values)) {
				v = true;
			}
		}
		return v;
	}

	public void info(List<String> message) {
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, (Media) message);
	}

	public void info(String message) {
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, message);
	}

	public void error(String message) {
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, message);
	}

	public void debug(String message) {
		ExtentCucumberAdapter.getCurrentStep().log(Status.INFO, message);
	}
	
	public void urlPrint(RequestSpecification request) {
		queryable = SpecificationQuerier.query(request);
		try {
			logger.info("\nURL: " + URLDecoder.decode(queryable.getURI(), "UTF-8"));
			info("\nURL: " + URLDecoder.decode(queryable.getURI(), "UTF-8"));
//			logger.info(queryable.getMethod());
//			logger.info(queryable.getBody());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}