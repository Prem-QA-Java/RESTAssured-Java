package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {		
		  "src/test/resources/features/createUser.feature"
		  }, glue = "steps",tags="@tt", plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "pretty" })
public class runner extends AbstractTestNGCucumberTests {


}
