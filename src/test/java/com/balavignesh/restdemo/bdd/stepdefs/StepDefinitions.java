package com.balavignesh.restdemo.bdd.stepdefs;


import com.balavignesh.restdemo.bdd.CucumberIntegrationTest;
import com.balavignesh.restdemo.domain.Account;
import com.fasterxml.jackson.databind.JsonNode;
import io.cucumber.java8.En;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefinitions extends CucumberIntegrationTest implements En {

    private  static  final  String  BASE_SERVICE_URL  =  "/api/account";
    private  static  final  String  HEALTH_SERVICE_URL  =  "/actuator/health";
    private ResponseEntity<Account> accountResponse;
    private ResponseEntity<String> delResponse;
    private ResponseEntity<List<Account>> getAllResponse;

    public StepDefinitions() {

        Given("When Application is up", () -> {
            System.out.println("Check for Application is up");
            JsonNode resp = restTemplate.getForObject(buildUrl(HEALTH_SERVICE_URL) , JsonNode.class);
            assertTrue(resp.get("status").asText().equalsIgnoreCase("UP"));
        });

        When("I sent GET all Account request", () -> {
            getAllResponse = restTemplate.exchange(
                    buildUrl(BASE_SERVICE_URL),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Account>>(){});
        });

        When("I sent GET an Account request with {long}", (Long accountIdentifier) -> {
            accountResponse =
                    restTemplate.getForEntity(
                            buildUrl(BASE_SERVICE_URL,String.valueOf(accountIdentifier)),
                            Account.class);
        });

        When("I sent DELETE an Account request with {long}", (Long accountIdentifier) -> {
            delResponse =
                    restTemplate.exchange(
                            buildUrl(BASE_SERVICE_URL,String.valueOf(accountIdentifier)),HttpMethod.DELETE,
                            null,
                            String.class);
        });

        When("I sent CREATE Account request with name {word}", (String accountName) -> {

            Account account = new Account();
            account.setAccountName(accountName);
            account.setAccountType("DDA");
            accountResponse =
                    restTemplate.postForEntity(
                            buildUrl(BASE_SERVICE_URL),account,
                            Account.class);
            System.out.println("Created");
        });

        Then("I receive valid HTTP Response status code {int}", (Integer code) -> {
            System.out.println("HTTP Response code: "+ code);
            assertEquals(accountResponse.getStatusCodeValue(),code.intValue());
        });

        Then("I receive valid Get All HTTP Response status code {int}", (Integer code) -> {
            System.out.println("HTTP Response code: "+ code);
            assertEquals(getAllResponse.getStatusCodeValue(),code.intValue());
        });

        Then("I receive valid Delete HTTP Response status code {int}", (Integer code) -> {
            System.out.println("HTTP Response code: "+ code);
            assertEquals(delResponse.getStatusCodeValue(),code.intValue());
        });

        And("the response contains Account Identifier {long}", (Long accountIdentifier) -> {
            System.out.println("Account Identifier: "+ accountIdentifier);
            assertTrue(getAllResponse.getBody()!=null);
            List<Account> accountList = getAllResponse.getBody();
            assertTrue(accountList.stream().anyMatch
                    (mat -> accountIdentifier.equals(mat.getAccountIdentifier())));
        });

        And("the response equals to Account Identifier {long}", (Long accountIdentifier) -> {
            System.out.println("Account Identifier: "+ accountIdentifier);
            assertEquals(accountResponse.getBody().getAccountIdentifier(),accountIdentifier.intValue());
        });

        And("the response equals to {string}", (String message) -> {
            System.out.println("message: "+ message);
            assertTrue(message.equalsIgnoreCase(delResponse.getBody()));
        });

    }
}
