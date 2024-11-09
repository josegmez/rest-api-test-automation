Feature: Retrieve a product by ID from the API

  Scenario: Get product by valid ID
    Given the API base URL is "https://api.escuelajs.co/api/v1"
    When I send a GET request to "/products/2"
    Then the response status code should be 200
    And the response body should have a field "id" with value 2

  Scenario: Product ID not found
    Given the API base URL is "https://api.escuelajs.co/api/v1"
    When I send a GET request to "/products/999"
    Then the response status code should be 400
    And the response body should have a field "name" with value "EntityNotFoundError"