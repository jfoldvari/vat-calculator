Feature: VAT Calculator

  Scenario: VAT and gross amounts are successfully retrieved
    When the client calls endpoint "/calculate?net=100&rate=10"
    Then response status code is 200
    And returned string should be "{\"net\":100.0,\"vat\":10.0,\"gross\":110.0}"
