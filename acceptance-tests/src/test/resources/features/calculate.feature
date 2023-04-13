Feature: VAT Calculator

  Scenario: VAT and gross amounts are successfully calculated
    When the client calls endpoint "/calculate?net=19.543&rate=20"
    Then response status code is 200
    And returned response body should be "{\"amounts\":{\"net\":19.543,\"vat\":3.91,\"gross\":23.46}}"

  Scenario: Net and gross amounts are successfully calculated
    When the client calls endpoint "/calculate?vat=19.543&rate=20"
    Then response status code is 200
    And returned response body should be "{\"amounts\":{\"net\":97.71,\"vat\":19.543,\"gross\":117.26}}"

  Scenario: Net and VAT amounts are successfully calculated
    When the client calls endpoint "/calculate?gross=19.543&rate=20"
    Then response status code is 200
    And returned response body should be "{\"amounts\":{\"net\":16.29,\"vat\":3.26,\"gross\":19.543}}"

  Scenario: Invalid input
    When the client calls endpoint "/calculate?net=asd&rate=20"
    Then response status code is 400
    And returned response body should be "{\"error\":{\"code\":\"001\",\"message\":\"Missing or invalid amount.\"}}"

  Scenario: More than one input is provided
    When the client calls endpoint "/calculate?net=19.543&gross=21.45&rate=20"
    Then response status code is 400
    And returned response body should be "{\"error\":{\"code\":\"002\",\"message\":\"Zero or more than one input. Either net, VAT or gross amount has to be provided.\"}}"

  Scenario: Missing VAT rate
    When the client calls endpoint "/calculate?net=19.543"
    Then response status code is 400
    And returned response body should be "{\"error\":{\"code\":\"003\",\"message\":\"Missing or invalid VAT rate. Valid VAT rates are 10, 13 and 20 percent.\"}}"
