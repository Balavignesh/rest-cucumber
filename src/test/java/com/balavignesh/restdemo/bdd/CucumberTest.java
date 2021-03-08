package com.balavignesh.restdemo.bdd;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        plugin = {  "pretty","json:target/cucumber.json",
        "html:target/cucumber-pretty" },monochrome = true)
public class CucumberTest extends CucumberIntegrationTest {
}