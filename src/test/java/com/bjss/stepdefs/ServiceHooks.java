package com.bjss.stepdefs;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ServiceHooks {
    public static WebDriver driver;
    public static Scenario scenario;

    @Before
    public void setup(Scenario scenario) {

        this.scenario = scenario;
        //System.out.println(scenario.getSourceTagNames().toString());


        if (scenario.getSourceTagNames().toString().contains("@UI")) {

            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            //   driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        }
    }

    @After
    public void teardown(Scenario scenario) throws Exception {

        if (scenario.getSourceTagNames().toString().contains("@UI")) {
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png"); // ... and embed it in the report.
            }
            driver.quit();
        }
    }


}
