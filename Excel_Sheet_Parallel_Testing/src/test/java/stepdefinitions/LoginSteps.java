package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
<<<<<<< HEAD
import utils.ExcelReader;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class LoginSteps {

    RemoteWebDriver driver;
    static List<String[]> loginData = ExcelReader.getLoginData("login.xls");
=======
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExcelReader;
import org.junit.Assert;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class LoginSteps {

    WebDriver driver; // Changed to WebDriver interface for flexibility
    static List<String[]> loginData = ExcelReader.getLoginData("login.xlsx");
>>>>>>> cb7c773 (initial commit)
    static int index = 0;

    String currentUsername;
    String currentPassword;
    String expectedStatus;

    @Given("user opens the browser")
    public void openBrowser() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
<<<<<<< HEAD
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        driver.manage().window().maximize();

        // get next row from excel
        if(index < loginData.size()) {
            String[] row = loginData.get(index++);
            currentUsername = row[0];
            currentPassword = row[1];
            expectedStatus = row[2];
=======

        // Ensure your Selenium Grid is running at this URL!
        driver = new RemoteWebDriver(new URL("http://172.21.0.197:4444/wd/hub"), caps);
        driver.manage().window().maximize();

        // Thread-safe check for index
        synchronized (LoginSteps.class) {
            if (index < loginData.size()) {
                String[] row = loginData.get(index++);
                currentUsername = row[0];
                currentPassword = row[1];
                expectedStatus = row[2];
            }
>>>>>>> cb7c773 (initial commit)
        }
    }

    @When("user navigates to {string}")
    public void navigateTo(String url) {
        driver.get(url);
    }

    @When("user enters username {string} and password {string}")
    public void enterCredentials(String u, String p) {
<<<<<<< HEAD
        driver.findElement(By.id("userName")).sendKeys(currentUsername);
        driver.findElement(By.id("password")).sendKeys(currentPassword);
        driver.findElement(By.id("login")).click();
    }

    @Then("login should be {string}")
    public void verifyLogin(String s) {
        boolean loginSuccess;
        try {
            loginSuccess = driver.findElement(By.id("submit")).isDisplayed();
        } catch (Exception e) {
            loginSuccess = false;
        }

        if(expectedStatus.equalsIgnoreCase("pass")) {
            assertTrue(loginSuccess);
        } else {
            assertFalse(loginSuccess);
        }

        driver.quit();
=======
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for elements to be visible before typing
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName"))).sendKeys(currentUsername);
        driver.findElement(By.id("password")).sendKeys(currentPassword);

        // Use JavascriptExecutor if the login button is hidden by ads on DemoQA
        WebElement loginBtn = driver.findElement(By.id("login"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);
    }

    @Then("login should be {string}")
    public void verifyLogin(String status) {
        boolean loginSuccess;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // On DemoQA, the "Log out" button (id=submit) appears only after successful login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
            loginSuccess = true;
        } catch (TimeoutException e) {
            loginSuccess = false;
        }

        try {
            if (expectedStatus.equalsIgnoreCase("pass")) {
                Assert.assertTrue("Login expected to PASS but it FAILED", loginSuccess);
            } else {
                Assert.assertFalse("Login expected to FAIL but it PASSED", loginSuccess);
            }
        } finally {
            // Ensure the browser closes even if the assertion fails
            if (driver != null) {
                driver.quit();
            }
        }
>>>>>>> cb7c773 (initial commit)
    }
}