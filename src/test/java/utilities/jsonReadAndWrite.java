package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONTokener;

public class jsonReadAndWrite {

	public String body;

	public void changeProperties(String fileNamed, String Object, String[] properties,
			String[] values) {
		try {

			FileReader fileRead = new FileReader(
					"src/test/resources/jsons/" + fileNamed + "Carousel.json");
			body = "src/test/resources/jsons/" + fileNamed + "Carousel.json";
			JSONTokener token = new JSONTokener(fileRead);
			JSONObject jsonObject = new JSONObject(token);

			for (int i = 0; i < properties.length; i++) {
				jsonObject.getJSONObject(Object).put(properties[i], new String[] { values[i] });
			}

			FileWriter fileWrite = new FileWriter(
					"src/test/resources/jsons/" + fileNamed + "Carousel.json");
			fileWrite.write(jsonObject.toString());
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeProperties(String carouselType_name_as_fileNamed, String[] properties, String[] values) {
		try {

			FileReader fileRead = new FileReader(
					"src/test/resources/jsons/" + carouselType_name_as_fileNamed + ".json");
			body = "src/test/resources/jsons/" + carouselType_name_as_fileNamed + ".json";
			JSONTokener token = new JSONTokener(fileRead);
			JSONObject jsonObject = new JSONObject(token);

			for (int i = 0; i < properties.length; i++) {
				jsonObject.put(properties[i], values[i]);
			}

			FileWriter fileWrite = new FileWriter(
					"src/test/resources/jsons/" + carouselType_name_as_fileNamed + ".json");
			fileWrite.write(jsonObject.toString());
			fileWrite.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCarousel(String[] properties, String[] values) {

		File file = new File("./json");
		JSONObject jsonObject = null;
		try {
			if (file.mkdirs() == true) {
				jsonObject = new JSONObject();
			} else {
				FileReader fileRead = new FileReader("./json/automatedCarouselUpdate.json");
				JSONTokener token = new JSONTokener(fileRead);
				jsonObject = new JSONObject(token);
			}
			for (int i = 0; i < values.length; i++) {
				jsonObject.put(properties[i], values[i]);
			}
			FileWriter fileWriter = new FileWriter("./json/automatedCarouselUpdate.json");
			fileWriter.write(jsonObject.toString());
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCarousel(String[] Object, String[] properties, String[] values) {

		File file = new File("./json");
		JSONObject jsonObject = new JSONObject();
		JSONObject outer = null;
		try {
			if (file.mkdirs() == true) {
				outer = new JSONObject();
			} else {
				FileReader filread = new FileReader("./json/automatedCarouselUpdate.json");
				JSONTokener token = new JSONTokener(filread);
				outer = new JSONObject(token);
			}
			for (int j = 0; j < Object.length; j++) {
				for (int i = 0; i < values.length; i++) {
					jsonObject.putOnce(properties[i], values[i]);
					jsonObject.put(Object[j], jsonObject);
				}
			}

			FileWriter fileWriter = new FileWriter("./json/automatedCarouselUpdate.json");
			fileWriter.write(jsonObject.toString());
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
