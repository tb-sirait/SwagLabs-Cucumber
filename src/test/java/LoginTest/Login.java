package LoginTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;

public class Login {
    WebDriver driver;
    ChromeOptions options;

    @Before
    public void setUp(){
        options = new ChromeOptions();
        options.setBinary("C:\\MyTools\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(options);
        int TIMEOUT = 5;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
    }

    @Given("User is on Sauce Demo page {string}")
    public void landingFirstPage(String url){
        driver.get(url);
    }

    @When("User enters username as {string} and password as {string}")
    public void loginSession(String username, String password) {
        try {
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            usernameField.sendKeys(username);
            Thread.sleep(1000);
            passwordField.sendKeys(password);
            Thread.sleep(1000);
            loginButton.click();
            Thread.sleep(1000);
        }
        catch (NoSuchElementException e){
            Assert.fail("Failed to find username or password field or login button: " + e.getMessage());
        }
        catch (Exception e){
            Assert.fail("Failed to enter credentials or click login button: " + e.getMessage());
        }
    }

    @Then("User should be able to login sucessfully and new page open")
    public void loginSuccesfully(){
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login berhasil");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Expected URL: " + expectedUrl);
        System.out.println("Assertion passed: Current URL match the expected URL.");
    }

    @Then("User should be able to see error message {string}")
    public void loginNotSuccesfully(String expectedErrorMessage) {
        try {
            WebElement errorElement = driver.findElement(By.cssSelector("h3[data-test='error']"));
            String actualErrorMessage = errorElement.getText();

            Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message tidak sesuai!");

            System.out.println("Actual error message: " + actualErrorMessage);
            System.out.println("Expected error message: " + expectedErrorMessage);
            System.out.println("Assertion passed: Error message matches expected.");
        } catch (NoSuchElementException e) {
            Assert.fail("Failed to find error message element: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeSession(){driver.quit();}

}
