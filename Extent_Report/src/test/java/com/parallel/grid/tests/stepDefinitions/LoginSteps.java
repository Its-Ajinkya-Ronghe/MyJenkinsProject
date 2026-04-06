package com.parallel.grid.tests.stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginSteps {

    WebDriver driver;

    @Given("user opens the browser")
    public void user_opens_the_browser() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://172.21.0.197:4444/wd/hub"), options);
    }

    @When("user navigates to {string}")
    public void user_navigates_to(String url) {
        driver.get(url);
    }

    @Then("page title should contain {string}")
    public void page_title_should_contain(String keyword) {
        if (!driver.getTitle().contains(keyword)) {
            throw new AssertionError("Title does not contain " + keyword);
        }
        driver.quit();
    }
}