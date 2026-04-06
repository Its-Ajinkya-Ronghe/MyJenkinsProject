package com.parallel.grid.tests.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.parallel.grid.tests.utils.DriverFactory;
import com.parallel.grid.tests.utils.ExtentManager;

import java.net.MalformedURLException;

public class Hooks {

    static ExtentReports extent = ExtentManager.getInstance();
    public static ExtentTest test;

    @Before
    public void setup(io.cucumber.java.Scenario scenario) throws MalformedURLException {
        test = extent.createTest(scenario.getName());
        DriverFactory.initDriver();
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        extent.flush();
    }
}