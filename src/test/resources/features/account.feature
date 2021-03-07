Feature: Account REST Testing
  Scenario: Get All Accounts
    Given When Application is up
    When I sent GET all Account request
    Then I receive valid HTTP Response status code 200
    And the response contains Account Identifier 10000

  Scenario: Get a Accounts
    Given When Application is up
    When I sent GET a Account request with 10001
    Then I receive valid HTTP Response status code 200
    And the response contains Account Identifier 10001

  Scenario: Get a Accounts
    Given When Application is up
    When I sent DELETE a Account request with 10002
    Then I receive valid HTTP Response status code 200