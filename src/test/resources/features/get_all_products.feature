Feature: Retrieve all products from the API

  Scenario: Get all products with default limit and offset
    Given the API base URL is "https://api.escuelajs.co/api/v1"
    When I send a GET request to "/products" with query parameters "limit" set to "10" and "offset" set to "0"
    Then the response status code should be 200
    And the response body should contain a list of products
    And the response body should have a field "length" with value "10"

  Scenario: Invalid limit parameter (non-integer value)
    Given the API base URL is "https://api.escuelajs.co/api/v1"
    When I send a GET request to "/products" with query parameters "limit" set to "abc" and "offset" set to "0"
    Then the response status code should be 400
    And the response body should contain an error message "limit must be a number conforming to the specified constraints"

  Scenario: Invalid offset parameter (non-integer value)
    Given the API base URL is "https://api.escuelajs.co/api/v1"
    When I send a GET request to "/products" with query parameters "limit" set to "10" and "offset" set to "xyz"
    Then the response status code should be 400
    And the response body should contain an error message "offset must be a number conforming to the specified constraints"