package RunnerCucumber.InitiatingCheckOut;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/Features/CheckoutTest.feature"}, glue = {"CheckOutTest"})

public class CheckoutCucumber extends AbstractTestNGCucumberTests {

}
