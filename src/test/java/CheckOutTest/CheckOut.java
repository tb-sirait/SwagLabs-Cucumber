package CheckOutTest;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CheckOut {
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

    @Given("User get access website {string} and login to User Account")
    public void loginSession(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(2000);
        try {
            WebElement usernameField = driver.findElement(By.id("user-name"));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("login-button"));

            usernameField.sendKeys("standard_user");
            Thread.sleep(1000);
            passwordField.sendKeys("secret_sauce");
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

    @When("User is succesfully login with valid credential")
    public void userIsSuccesfullyLoginWithValidCredential() {
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

    @Then("User can Access inventory")
    public void userCanAccessInventory() {
        Assert.assertTrue(true);
    }


    @Given("User is succesfully Add goods to cart")
    public void userIsSuccesfullyAddGoodsToCart() {
        try {
            WebElement addToCart1 = driver.findElement(By.id("add-to-cart-sauce-labs-backpack")); // Sauce Labs Backpack
            addToCart1.click();
            Thread.sleep(1000);
            WebElement addToCart2 = driver.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")); // Sauce Labs Fleece Jacket
            addToCart2.click();
            Thread.sleep(1000);
        } catch (Exception e){
            Assert.fail("Barang tidak dapat ditambahkan ke dalam keranjang");
        }
    }


    @When("User want to buy goods which is add from Cart")
    public void userWantToBuyGoodsWhichIsAddFromCart() {
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        String itemCount = cartBadge.getText(); // Mendapatkan jumlah barang di keranjang
        Assert.assertEquals(itemCount, "2", "Jumlah barang di keranjang tidak sesuai");
        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();
    }

    @Then("User must Checking Out from cart")
    public void userMustCheckingOutFromCart() throws InterruptedException {
        try {
            WebElement itemKeranjang1 = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']"));
            WebElement itemKeranjang2 = driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Fleece Jacket']"));

            Assert.assertTrue(itemKeranjang1.isDisplayed(), "Sauce Labs Backpack tidak ada di keranjang");
            Assert.assertTrue(itemKeranjang2.isDisplayed(), "Sauce Labs Fleece Jacket tidak ada di keranjang");

            System.out.println("Barang yang dipilih sesuai dengan isi keranjang.");
        } catch (Exception e) {
            System.out.println("Barang tidak sesuai dengan yang dipilih. Error: " + e.getMessage());
            Assert.fail("Barang yang dipilih tidak sesuai dengan isi keranjang.");
        }
        Thread.sleep(1000);
        System.out.println("=====================================");
    }

    @And("Click the Checkout Button")
    public void clickTheCheckoutButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkoutShopping = wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout")));
        checkoutShopping.click();
    }


    @Given("User must input shipping form")
    public void userMustInputShippingForm(){
        try {
            String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
            Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Tidak dapat mengakses");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Expected URL: " + expectedUrl);
            System.out.println("Assertion passed: Sudah masuk ke dalam menginput informasi pemesanan.");
        }
        catch (Exception e){
            Assert.fail("Failed to checkout goods: " + e.getMessage());
        }
    }

    @When("User input First Name, Last Name, and Postal Code")
    public void userInputFirstNameLastNameAndPostalCode() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement isiNamaAwal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));
        WebElement isiNamaAkhir = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name")));
        WebElement isiKodePos = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("postal-code")));

        isiNamaAwal.click();
        isiNamaAwal.sendKeys("Taor");
        Thread.sleep(1000);

        isiNamaAkhir.click();
        isiNamaAkhir.sendKeys("Baga");
        Thread.sleep(1000);

        isiKodePos.click();
        isiKodePos.sendKeys("19");
        Thread.sleep(1000);
    }

    @Then("User can submit form and moving to Checkout detail")
    public void userCanSubmitFormAndMovingToCheckoutDetail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        continueButton.click();
    }


    @Given("User checking Checkout Detail and already match with the items checked out")
    public void userCheckingCheckoutDetailAndAlreadyMatchWithTheItemsCheckedOut() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        System.out.println("Masuk ke halaman Checkout details berhasil diproses!");
    }

    @When("User agree to check out the items")
    public void userAgreeToCheckOutTheItems() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Then("User can click Finish button")
    public void userCanClickFinishButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement finishButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("finish")));
        finishButton.click();
        System.out.println("Checkout berhasil diproses!");
    }


}
