package utilities;

import io.cucumber.java.Scenario;

public class variables extends base{

//	public String [] userIds = prop("userIds").split(",");
	
	public static String automatedCarousel;
	
	public static boolean scenarioFailed;
	
	public String userCreateToken;
	public String contactCreateToken;
	
	public static void getScenario(Scenario scenario) {
		if (scenarioFailed) {
			if (scenario.isFailed())
				scenarioFailed = true;
		}
	}
	
	
}
