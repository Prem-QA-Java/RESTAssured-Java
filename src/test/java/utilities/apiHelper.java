
package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class apiHelper extends base{
	
	Response response;

	
	public Response getRequest(String string, String string2) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		response = request.get(string + string2);
		urlPrint(request);
		return response;
	}

	/**
	 * This is used from get call with different parameters in JSON format with two
	 * path parameters
	 * 
	 * @param path
	 * @param carousel_Id
	 * @param user_Id
	 * @param params      give in the string JSON format like
	 *                    "{\"startIndex\":1,\"count\":10,\"noCache\":true,\"preview\":true,\"allFields\":false}"
	 * @return response of the call
	 */
	public Response getRequest(String path, String carousel_Id, String user_Id, String params) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
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
			request.param(key, value);
		}

		response = request.pathParam("carousel_Id", carousel_Id).pathParam("user_Id", user_Id).get(path);
		urlPrint(request);
		return response;

	}

	/**
	 * This is used from get call with different parameters in JSON format with one
	 * path parameters
	 * 
	 * @param path
	 * @param carousel_Id
	 * @param params      give in the string JSON format like
	 *                    "{\"startIndex\":1,\"count\":10,\"noCache\":true,\"preview\":true,\"allFields\":false}"
	 * @return response of the call
	 */
	public Response getRequest(String path, String carousel_Id, String params) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
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
			request.param(key, value);
		}
		response = request.pathParam("carousel_Id", carousel_Id).get(path);
		urlPrint(request);
		return response;
	}


	public Response getRequestJson(String path, String bodyPath) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("authorization"));
		
		request.header("Content-Type", "application/json");
		FileReader fr = null;
		try {
			fr = new FileReader(bodyPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		request.body(data.toString());
		response = request.get(path);
		urlPrint(request);
		response.prettyPrint();
		return response;
	}

	/**
	 * This is used from post call with body as JSON format file
	 * 
	 * @param path
	 * @param bodyPath
	 * @return response of the call
	 */
	public Response postRequestJson(String path, String bodyPath) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("authorization"));
		
		request.header("Content-Type", "application/json");
		FileReader fr = null;
		try {
			fr = new FileReader(bodyPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		request.body(data.toString());
		response = request.post(path);
		urlPrint(request);
		response.prettyPrint();
		return response;
	}

	public Response postRequestXml(String path, String bodyPath) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		File fi = new File(bodyPath);
		StringBuilder sb = null;
		try {
			Reader fileReader = new FileReader(fi);
			BufferedReader bufReader = new BufferedReader(fileReader);
			sb = new StringBuilder();
			String line = bufReader.readLine();
			while (line != null) {
				sb.append(line).append("");
				line = bufReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = sb.toString();
		request.body(data);
		response = request.post(path);
		urlPrint(request);
		return response;
	}

	/**
	 * This is used from post call with body as JSON format file and with parameters
	 * 
	 * @param path
	 * @param bodyPath
	 * @param params
	 * @return response of the call
	 */
	public Response postRequestJson(String path, String bodyPath, String params) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		File fi = new File(bodyPath);
		FileReader fr = null;
		try {
			fr = new FileReader(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		request.body(data);
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
			request.params(key, value);
		}
		response = request.post(path);
		urlPrint(request);
		return response;
	}

	
	public Response postRequestXml(String path, String bodyPath, String params) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		File fi = new File(bodyPath);
		StringBuilder stringBuild = null;
		try {
			FileReader fr = new FileReader(fi);
			BufferedReader buff = new BufferedReader(fr);
			stringBuild = new StringBuilder();
			String line = buff.readLine();
			while (line != null) {
				stringBuild.append(line);
				line = buff.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = stringBuild.toString();
		request.body(data);
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
			request.params(key, value);
		}
		response = request.post(path);
		urlPrint(request);
		return response;
	}
	
	public Response postRequestJsonContent(String path, String projectId, String bodyPath) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		request.header("Content-Type", "application/json");
//		FileReader fr = null;
//		try {
//			fr = new FileReader(bodyPath);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		JSONTokener jt = new JSONTokener(fr);
//		JSONObject data = new JSONObject(jt);
//		request.body(data.toString());
		
		File fi = new File(bodyPath);
		StringBuilder stringBuild = null;
		try {
			FileReader fr = new FileReader(fi);
			BufferedReader buff = new BufferedReader(fr);
			stringBuild = new StringBuilder();
			String line = buff.readLine();
			while (line != null) {
				stringBuild.append(line);
				line = buff.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = stringBuild.toString();
		request.body(data);
		response = request.pathParam("project", projectId).post(path);
		urlPrint(request);
		return response;
	}
	
	public Response postRequestXmlContent(String uri, String projectId, String bodyPath) {
		RequestSpecification request = RestAssured.given().config(RestAssuredConfig.newConfig()
				.encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
		request.header("Authorization", prop("projectId"));
		
		File fi = new File(bodyPath);
//		Reader read = null;
//		try {
//			read = new FileReader(fi);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		String data = read.toString();
		StringBuilder stringBuild = null;
		try {
			FileReader fr = new FileReader(fi);
			BufferedReader buff = new BufferedReader(fr);
			stringBuild = new StringBuilder();
			String line = buff.readLine();
			while (line != null) {
				stringBuild.append(line);
				line = buff.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String data = stringBuild.toString();
		request.body(data);
		response = request.pathParam("project", projectId).post(uri);
		urlPrint(request);
		return response;
	}
	 

	/**
	 * This is used from put call with body as JSON format file and with parameters
	 * and one path parameter
	 * 
	 * @param path
	 * @param Id
	 * @param bodyPath
	 * @param params
	 * @return response of the call
	 */
	public Response putRequest(String path, String Id, String bodyPath, String params) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		File fi = new File(bodyPath);
		FileReader fr = null;
		try {
			fr = new FileReader(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		request.body(data);
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
			request.param(key, value);
		}
		response = request.pathParam("user_Id", Id).put(path);
		urlPrint(request);
		return response;
	}

	/**
	 * This is used from put call with body as JSON format file and one path
	 * parameter
	 * 
	 * @param path
	 * @param Id
	 * @param bodyPath
	 * @return response of the call
	 */
	public Response putRequest(String path, String Id, String bodyPath) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		File fi = new File(bodyPath);
		FileReader fr = null;
		try {
			fr = new FileReader(fi);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		request.body(data);
		response = request.pathParam("user_Id", Id).put(path);
		urlPrint(request);
		return response;
	}

	public Response putRequestJson(String path, String bodyPath) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("authorization"));
		
		request.header("Content-Type", "application/json");
		FileReader fr = null;
		try {
			fr = new FileReader(bodyPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		request.body(data.toString());
		response = request.put(path);
		urlPrint(request);
		response.prettyPrint();
		return response;
	}

	/**
	 * This is used from delete call with two path parameter
	 * 
	 * @param path
	 * @param carousel_Id
	 * @param user_Id
	 * @return response of the call
	 */
	public Response deleteRequest(String path, String carousel_Id, String user_Id) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		response = request.pathParam("carousel_Id", carousel_Id).pathParam("user_Id", user_Id).delete(path);
		urlPrint(request);
		return response;
	}

	/**
	 * This is used from delete call with one path parameter
	 * 
	 * @param path
	 * @param Id
	 * @return response of the call
	 */
	public Response deleteRequest(String path, String Id) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("projectId"));
		
		response = request.pathParam("carousel_Id", Id).delete(path);
		urlPrint(request);
		return response;
	}

	public Response deleteRequestJson(String path, String bodyPath) {
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", prop("authorization"));
		
		request.header("Content-Type", "application/json");
		FileReader fr = null;
		try {
			fr = new FileReader(bodyPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JSONTokener jt = new JSONTokener(fr);
		JSONObject data = new JSONObject(jt);
		request.body(data.toString());
		response = request.delete(path);
		urlPrint(request);
		response.prettyPrint();
		return response;
	}
}
