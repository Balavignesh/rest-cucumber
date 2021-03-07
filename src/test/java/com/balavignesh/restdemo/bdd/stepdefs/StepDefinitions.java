package com.balavignesh.restdemo.bdd.stepdefs;


import com.balavignesh.restdemo.bdd.CucumberIntegrationTest;
import com.balavignesh.restdemo.domain.Account;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class StepDefinitions extends CucumberIntegrationTest implements En {

    private  static  final  String  BASE_SERVICE_URL  =  "/api/account";
    private  static  final  String  HEALTH_SERVICE_URL  =  "/actuator/health";
    private String url;
    private ResponseEntity<Account> response;

    public StepDefinitions() {

        Given("When Application is up", () -> {
            log.debug("URL initialize Headers"+serverPort);
            JsonNode resp = restTemplate.getForObject(buildUrl(HEALTH_SERVICE_URL) , JsonNode.class);
            assertTrue(resp.get("status").asText().equalsIgnoreCase("UP"));
        });



        When("I sent GET all Account request", () -> {
            response =
                    restTemplate.getForEntity(
                            "http://localhost:"+serverPort+"/api/account/10000",
                            Account.class);
        });

        When("I sent GET a Account request with {long}", (Long accountIdentifier) -> {

        });

        When("I sent DELETE a Account request with {long}", (Long accountIdentifier) -> {

        });

        Then("I receive valid HTTP Response status code {int}", (Integer code) -> {
            log.debug("HTTP Response code: %n\n", code);
        });

        And("the response contains Account Identifier {long}", (Long accountIdentifier) -> {
            log.debug("Account Identifier: %n\n", accountIdentifier);
        });
    }
}
