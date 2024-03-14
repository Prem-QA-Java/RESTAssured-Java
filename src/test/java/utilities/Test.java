package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.XmlPath.CompatibilityMode;
import io.restassured.response.Response;

public class Test extends base {

	public static void main(String[] args) {
		xml();
	}
	
	public static void xml() {
		XmlPath htmlPath = new XmlPath(CompatibilityMode.HTML, "<!DOCTYPE html>\r\n"
				+ "	<html>\r\n"
				+ "	  <head>\r\n"
				+ "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "		<meta charset=\"utf-8\">\r\n"
				+ "		<title>Application Error</title>\r\n"
				+ "		<style media=\"screen\">\r\n"
				+ "		  html,body,iframe {\r\n"
				+ "			margin: 0;\r\n"
				+ "			padding: 0;\r\n"
				+ "		  }\r\n"
				+ "		  html,body {\r\n"
				+ "			height: 100%;\r\n"
				+ "			overflow: hidden;\r\n"
				+ "		  }\r\n"
				+ "		  iframe {\r\n"
				+ "			width: 100%;\r\n"
				+ "			height: 100%;\r\n"
				+ "			border: 0;\r\n"
				+ "		  }\r\n"
				+ "		</style>\r\n"
				+ "	  </head>\r\n"
				+ "	  <body>\r\n"
				+ "		<iframe src=\"//www.herokucdn.com/error-pages/application-error.html\"></iframe>\r\n"
				+ "	  </body>\r\n"
				+ "	</html>");
		
		System.out.println(htmlPath.getString("html.head.title"));
	}

//	public static void updateCarousel(String[] Object, String[] properties, String[] values) {
//
//		File file = new File("./json");
//		JSONObject outerJsonObject = null;
//		try {
//			if (file.mkdirs() == true) {
//				JSONObject innerJsonObject = new JSONObject();
//				outerJsonObject = new JSONObject();
//				for (int j = 0; j < Object.length; j++) {
//					for (int i = 0; i < values.length; i++) {
//						innerJsonObject.putOnce(properties[i], new String[] { values[i] });
//						outerJsonObject.put(Object[j], innerJsonObject);
//					}
//				}
//			} else {
//				FileReader fileRead = new FileReader("./json/josjs.json");
//				JSONTokener token = new JSONTokener(fileRead);
//				JSONObject innerJsonObject = new JSONObject();
//				outerJsonObject = new JSONObject(token);
//				for (int j = 0; j < Object.length; j++) {
//					for (int i = 0; i < properties.length; i++) {
//						innerJsonObject.putOnce(properties[i], new String[] { values[i] });
//						outerJsonObject.put(Object[j], innerJsonObject);
//					}
//				}
//			}
//			FileWriter fileWriter = new FileWriter("./json/josjs.json");
//			fileWriter.write(outerJsonObject.toString());
//			fileWriter.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void updateCarousel(String[] properties, String[] values) {
//
//		JSONObject jsonObject = new JSONObject();
//		for (int i = 0; i < values.length; i++) {
//			jsonObject.put(properties[i], values[i]);
//		}
//		try {
//			FileWriter fileWriter = new FileWriter("automatedCarouselUpdate.json");
//			fileWriter.write(jsonObject.toString());
//			fileWriter.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
