package com.parallel.grid.tests.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.parallel.grid.tests.stepDefinitions"},
        plugin = {"pretty"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
}


//How to Run?
//A.FOR HUB AND NODE SETUP
//1.Open cmd prompt on hub machine and type ipconfig of hub machine. Copy downside IP adress
//2.Start the hub using command: java -jar C:\Users\\user\selenium-server-4.41.0.jar hub

//3.Open cmd prompt on node machine and type ipconfig of node machine
//4.Start the node using command: java -jar C:\Users\\user\selenium-server-4.41.0.jar node --hub http://192.168.1.10:4444

//where 192.168.1.10 is ip address of hub machine

//5.driver = new RemoteWebDriver(
//      new URL("http://192.168.1.10:4444/wd/hub"),
//      new ChromeOptions()
//);  paste ip address of hub machine in step definitions-> LoginSteps

//B.LOCAL MACHINE EXECUTION
//1. Open the cmd Prompt and give command: java -jar C:\Users\\user\selenium-server-4.41.0.jar standalone
//2. driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);