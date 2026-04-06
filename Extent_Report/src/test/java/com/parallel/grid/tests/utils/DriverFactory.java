package com.parallel.grid.tests.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initDriver() {
        try {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");

            // ⬇️ 100% correct URL for Selenium Standalone
            driver.set(new RemoteWebDriver(
                    new URL("http://localhost:4444/wd/hub"),
                    options
            ));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return driver.get();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        getDriver().quit();
        driver.remove();
    }
}