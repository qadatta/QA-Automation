package org.selenide.examples.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(glue={"org.selenide.examples.cucumber.stepDefs"}, features = "src/test/resources", plugin = { "pretty", "html:target/site/cucumber-pretty",
        "json:target/cucumber.json" })public class TestRunner {


//    @Rule
//    public SoftAsserts softAsserts = new SoftAsserts();



}

