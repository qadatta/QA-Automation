package org.selenide.examples.cucumber;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.junit.ScreenShooter;
import com.codeborne.selenide.junit.TextReport;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue={"org.selenide.examples.cucumber"}, features = "src/test/resources", plugin = { "pretty", "html:target/site/cucumber-pretty",
        "json:target/cucumber.json" })public class GoogleTest {

}

