package RunnerCucumber.InitiatingLogout;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/Features/LogoutTest.feature"}, glue = {"LogOutTest"})

public class LogoutCucumber extends AbstractTestNGCucumberTests {

}
