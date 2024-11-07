Feature: Create a new product in the API

  Scenario: Successfully create a new product
    Given the API base URL is "https://api.escuelajs.co/api/v1"
    And the request body contains the following data
      | title             | Nike Airmax 270   |
      | price             | 250               |
      | description       | Stylish sneakers  |
      | categoryId        | 2                 |
      | images            | ["https://example.com/image.jpg"] |
    When I send a POST request to "/products"
    Then the response status code should be 201
    And the response body should have a field "title" with value "Nike Airmax Pulse"

 Scenario: Missing required fields (title)
    Given the API base URL is "https://api.escuelajs.co/api/v1"
    And the request body contains the following data
      | price             | 250               |
      | description       | Stylish sneakers  |
      | categoryId        | 2                 |
      | images            | ["https://example.com/image.jpg"] |
    When I send a POST request to "/products"
    Then the response status code should be 400
    And the response body should contain an error message "title should not be empty"

  Scenario: Negative price value
    Given the API base URL is "https://api.escuelajs.co/api/v1"
      | title             | Nike Airmax 270   |
      | price             | -250               |
      | description       | Stylish sneakers  |
      | categoryId        | 2                 |
      | images            | ["https://example.com/image.jpg"] |
    When I send a POST request to "/products"
    Then the response status code should be 400
    And the response body should contain an error message "price must be a positive number"
