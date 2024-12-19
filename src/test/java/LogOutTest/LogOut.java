package LogOutTest;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
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

public class LogOut {
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

    @Given("User is on the login page {string}")
    public void loginPage(String url){ driver.get(url);}

    @When("the user logs in with valid credentials with username {string} and password {string}")
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

    @Then("the user is redirected to the inventory page")
    public void landingToInventory() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login berhasil");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Expected URL: " + expectedUrl);
        System.out.println("Assertion passed: Current URL match the expected URL.");
    }


    @When("the user clicks the burger button on the top left corner")
    public void directToBurgerMenu() {
        WebElement burgerBar = driver.findElement(By.id("react-burger-menu-btn"));
        burgerBar.click();
    }


    @And("the user clicks the logout button")
    public void logOutSession() {
        WebElement logOutButton = driver.findElement(By.id("logout_sidebar_link"));
        logOutButton.click();
    }


    @Then("the user is redirected to the login page")
    public void logoutToApp() {
        String expectedUrl = "https://www.saucedemo.com/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Logout berhasil");
        System.out.println("Current URL: " + driver.getCurrentUrl());
        System.out.println("Expected URL: " + expectedUrl);
        System.out.println("Assertion passed: Current URL match the expected URL.");
    }

    @After
    public void closeSession(){driver.quit();}
}
