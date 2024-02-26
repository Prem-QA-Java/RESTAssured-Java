package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Test extends base {

	public static void main(String[] args) {

//		try {
//			FileReader fileRead = new FileReader("json.json");
//			JSONTokener tokener = new JSONTokener(fileRead);
//			JSONObject jsonObject = new JSONObject(tokener);
//
//			while (jsonObject.length() == 2)
//				jsonObject.getJSONObject("optimizationParams").put("Randomize", new String[] { "Enable" });
//			jsonObject.getJSONObject("optimizationParams").put("Reappear criteria (days)", new String[] { "5" });
//			jsonObject.put("carouselId", false);
//			FileWriter fileWrite = new FileWriter("json.json");
//			fileWrite.write(jsonObject.toString());
//			fileWrite.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		updateCarousel(new String[] { "tagsBasedBlacklisting", "optimizationParams" }, new String[] { "Adult", "Randomize", "Reappear criteria (days)" },
				new String[] { "Exclude Matched Users", "Disable", "40" });
		updateCarousel(new String[] { "Carousel Title" }, new String[] { "Carousel automated" });
	}

	public static void updateCarousel(String[] Object, String[] properties, String[] values) {

		File file = new File("./json");
		JSONObject outerJsonObject = null;
		try {
			if (file.mkdirs() == true) {
				JSONObject innerJsonObject = new JSONObject();
				outerJsonObject = new JSONObject();
				for (int j = 0; j < Object.length; j++) {
					for (int i = 0; i < values.length; i++) {
						innerJsonObject.putOnce(properties[i], new String[] { values[i] });
						outerJsonObject.put(Object[j], innerJsonObject);
					}
				}
			} else {
				FileReader fileRead = new FileReader("./json/josjs.json");
				JSONTokener token = new JSONTokener(fileRead);
				JSONObject innerJsonObject = new JSONObject();
				outerJsonObject = new JSONObject(token);
				for (int j = 0; j < Object.length; j++) {
					for (int i = 0; i < properties.length; i++) {
						innerJsonObject.putOnce(properties[i], new String[] { values[i] });
						outerJsonObject.put(Object[j], innerJsonObject);
					}
				}
			}
			FileWriter fileWriter = new FileWriter("./json/josjs.json");
			fileWriter.write(outerJsonObject.toString());
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void updateCarousel(String[] properties, String[] values) {

		JSONObject jsonObject = new JSONObject();
		for (int i = 0; i < values.length; i++) {
			jsonObject.put(properties[i], values[i]);
		}
		try {
			FileWriter fileWriter = new FileWriter("automatedCarouselUpdate.json");
			fileWriter.write(jsonObject.toString());
			fileWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
