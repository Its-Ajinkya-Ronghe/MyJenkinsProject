package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import utils.ExcelReader;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class LoginSteps {

    RemoteWebDriver driver;
    static List<String[]> loginData = ExcelReader.getLoginData("login.xls");
    static int index = 0;

    String currentUsername;
    String currentPassword;
    String expectedStatus;

    @Given("user opens the browser")
    public void openBrowser() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
        driver.manage().window().maximize();

        // get next row from excel
        if(index < loginData.size()) {
            String[] row = loginData.get(index++);
            currentUsername = row[0];
            currentPassword = row[1];
            expectedStatus = row[2];
        }
    }

    @When("user navigates to {string}")
    public void navigateTo(String url) {
        driver.get(url);
    }

    @When("user enters username {string} and password {string}")
    public void enterCredentials(String u, String p) {
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
    }
}