package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExcelReader;
import org.junit.Assert;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class LoginSteps {

    WebDriver driver;
    static List<String[]> loginData = ExcelReader.getLoginData("login.xlsx");
    static int index = 0;

    String currentUsername;
    String currentPassword;
    String expectedStatus;

    @Given("user opens the browser")
    public void openBrowser() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");

        // Ensure your Selenium Grid is running at this URL!
        // Use localhost if running locally, or your specific IP
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        driver.manage().window().maximize();

        // Thread-safe check for index to handle parallel execution
        synchronized (LoginSteps.class) {
            if (index < loginData.size()) {
                String[] row = loginData.get(index++);
                currentUsername = row[0];
                currentPassword = row[1];
                expectedStatus = row[2];
            }
        }
    }

    @When("user navigates to {string}")
    public void navigateTo(String url) {
        driver.get(url);
    }

    @When("user enters username {string} and password {string}")
    public void enterCredentials(String u, String p) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for elements to be visible before typing
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userName"))).sendKeys(currentUsername);
        driver.findElement(By.id("password")).sendKeys(currentPassword);

        // Use JavascriptExecutor if the login button is hidden by ads or overlays
        WebElement loginBtn = driver.findElement(By.id("login"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", loginBtn);
    }

    @Then("login should be {string}")
    public void verifyLogin(String status) {
        boolean loginSuccess;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            // "submit" is the Logout button ID on DemoQA after a successful login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit")));
            loginSuccess = true;
        } catch (TimeoutException e) {
            loginSuccess = false;
        }

        try {
            if (expectedStatus != null && expectedStatus.equalsIgnoreCase("pass")) {
                Assert.assertTrue("Login expected to PASS but it FAILED", loginSuccess);
            } else {
                Assert.assertFalse("Login expected to FAIL but it PASSED", loginSuccess);
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}