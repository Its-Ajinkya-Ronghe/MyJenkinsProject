package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.URL;

public class GoogleTest1 {

    @Test
    public void testGoogle1() throws Exception {

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);

        driver.get("https://www.google.com");
        System.out.println("Test1 Title: " + driver.getTitle());

        driver.quit();
    }
}