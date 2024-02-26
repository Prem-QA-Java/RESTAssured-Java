package runner;

import org.json.JSONArray;
import org.json.JSONObject;

public class test {

	public static void main(String[] args) {
        try {
            // JSON string
            String jsonString = "{\r\n"
            		+ "    \"user\": {\r\n"
            		+ "        \"_id\": \"65dc8f990164a8001385a99a\",\r\n"
            		+ "        \"firstName\": \"Test\",\r\n"
            		+ "        \"lastName\": \"User\",\r\n"
            		+ "        \"email\": \"test123123454321@fake.com\",\r\n"
            		+ "        \"__v\": 1\r\n"
            		+ "    },\r\n"
            		+ "    \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2NWRjOGY5OTAxNjRhODAwMTM4NWE5OWEiLCJpYXQiOjE3MDg5NTM0OTd9.Zc3y-ShYoljbbswgNbSWDQI6in7HWaQ4mjzOSw_098U\"\r\n"
            		+ "}";

            // Convert string to JSONObject
            JSONObject jsonObject = new JSONObject(jsonString);
            System.out.println("JSONObject: " + jsonObject.getJSONObject("user").get("firstName"));

            // If your JSON represents an array, you can convert it to a JSONArray
            String jsonArrayString = "[\"value1\", \"value2\", \"value3\"]";
            JSONArray jsonArray = new JSONArray(jsonArrayString);
            System.out.println("JSONArray: " + jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
