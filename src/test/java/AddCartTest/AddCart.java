package AddCartTest;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;

public class AddCart {
    WebDriver driver;
    ChromeOptions options;

    String Username = "";
    String Password = "";

    @Before
    public void setUp(){
        options = new ChromeOptions();
        options.setBinary("C:\\MyTools\\chrome-win64\\chrome.exe");
        driver = new ChromeDriver(options);
        int TIMEOUT = 5;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
        driver.manage().window().maximize();
    }

    @Given("User access to website {string}")
    public void initiatingWebsite(String url) {
        driver.get(url);
    }

    @Given("User login with username {string} and password {string}")
    public void initiatingUserCredential(String arg0, String arg1) {
        Username = arg0;
        Password = arg1;
    }

    @When("User is succesfully login with valid username and password")
    public void loginSession() {
        try {
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            usernameField.sendKeys(Username);
            Thread.sleep(1000);
            passwordField.sendKeys(Password);
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

    @Then("User is directed to the inventory page")
    public void inventoryLandingPage() {
        try {
            String expectedUrl = "https://www.saucedemo.com/inventory.html";
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Login berhasil");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Expected URL: " + expectedUrl);
            System.out.println("Assertion passed: Current URL match the expected URL.");
        }
        catch (Exception e){
            Assert.fail("Failed to enter credentials or click login button: " + e.getMessage());
        }
    }

    @Given("User want to add Sauce Labs Backpack to cart")
    public String addBackpackToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement item = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'inventory_item_name') and normalize-space(text())='Sauce Labs Backpack']")
        ));
        String actualItem = item.getText();
        System.out.println(actualItem);
        Assert.assertEquals(actualItem, "Sauce Labs Backpack", "Nama barang tidak sesuai");
        return actualItem;
    }


    @When("{string} is available in inventory")
    public void isAvailableInInventory(String item) {
        if(Objects.equals(item, addBackpackToCart())){
            System.out.println(item + " terdapat dalam keranjang");
        }
        else {
            Assert.fail("Barang tidak terdapat dalam keranjang");
        }
    }

    @Then("add Sauce Labs Backpack into Cart")
    public void addBackpackIntoCart() {
        try {
            WebElement addToCart1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack")); // Sauce Labs Backpack
            addToCart1.click();
            Thread.sleep(1000);
        } catch (Exception e){
            Assert.fail("Barang tidak dapat ditambahkan ke dalam keranjang");
        }
    }

    @Given("User want to add Sauce Labs Fleece Jacket to cart")
    public String addSauceLabsFleeceJacketToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement item = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'inventory_item_name') and normalize-space(text())='Sauce Labs Fleece Jacket']")
        ));
        String actualItem = item.getText();
        System.out.println(actualItem);
        Assert.assertEquals(actualItem, "Sauce Labs Fleece Jacket", "Nama barang tidak sesuai");
        return actualItem;
    }

    @When("{string} is available in sauce demo inventory")
    public void isAvailableInSauceDemoInventory(String item) {
        if(Objects.equals(item, addSauceLabsFleeceJacketToCart())){
            System.out.println(item + " terdapat dalam keranjang");
        }
        else {
            Assert.fail("Barang tidak terdapat dalam keranjang");
        }
    }

    @Then("add Sauce Labs Fleece Jacket into Cart")
    public void addSauceLabsFleeceJacketIntoCart() {
        try {
            WebElement addToCart2 = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")); // Sauce Labs Fleece Jacket
            addToCart2.click();
            Thread.sleep(1000);
        } catch (Exception e){
            Assert.fail("Barang tidak dapat ditambahkan ke dalam keranjang");
        }
    }


}
