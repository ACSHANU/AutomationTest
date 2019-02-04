package com.bjss.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com/bjss/stepdefs"},
        dryRun = false,
        tags = {"@UI_Test,@REST_Test"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun.txt"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {


}
