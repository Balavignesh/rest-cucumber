Feature: Account REST Testing
  Scenario: Get All Accounts
    Given When Application is up
    When I sent GET all Account request
    Then I receive valid Get All HTTP Response status code 200
    And the response contains Account Identifier 10000

  Scenario: Get an Account
    Given When Application is up
    When I sent GET an Account request with 10001
    Then I receive valid HTTP Response status code 200
    And the response equals to Account Identifier 10001

  Scenario: Delete an Account
    Given When Application is up
    When I sent DELETE an Account request with 10002
    Then I receive valid Delete HTTP Response status code 200
    And the response equals to 'Account deleted'

  Scenario: Create an Accounts
    Given When Application is up
    When I sent CREATE Account request with name Vignesh
    Then I receive valid HTTP Response status code 201