package RunnerCucumber.InitiatingAddCart;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/Features/AddCartTest.feature"}, glue = {"AddCartTest"})

public class AddCartCucumber extends AbstractTestNGCucumberTests {

}
