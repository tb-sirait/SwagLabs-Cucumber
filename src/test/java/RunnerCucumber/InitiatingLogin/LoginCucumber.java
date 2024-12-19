package RunnerCucumber.InitiatingLogin;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/Features/LoginTest.feature"}, glue = {"LoginTest"})
public class LoginCucumber extends AbstractTestNGCucumberTests {

}
