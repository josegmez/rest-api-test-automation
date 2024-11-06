Feature: Create a new product in the API

  Scenario: Successfully create a new product
    Given the API base URL is "http://localhost:3001/api/v1"
    And the request body contains the following JSON:
      """
      {
        "title": "Nike Airmax Pulse",
        "price": 300,
        "description": "Tenis Airmax Pulse",
        "categoryId": 1,
        "images": ["https://example.com/image.jpg"]
      }
      """
    When I send a POST request to "/products"
    Then the response status code should be 201
    And the response body should contain the created product details
    And the response body should have a field "title" with value "Nike Airmax Pulse"
    And the response body should have a field "price" with value "300"

 Scenario: Missing required fields (title)
    Given the API base URL is "http://localhost:3001/api/v1"
    And the request body contains the following JSON:
      """
      {
        "price": 300,
        "description": "Tenis Airmax Pulse",
        "categoryId": 1,
        "images": ["https://example.com/image.jpg"]
      }
      """
    When I send a POST request to "/products"
    Then the response status code should be 400
    And the response body should contain an error message "title should not be empty"

  Scenario: Negative price value
    Given the API base URL is "http://localhost:3001/api/v1"
    And the request body contains the following JSON:
      """
      {
        "title": "Nike Airmax Pulse",
        "price": -500,
        "description": "Tenis Airmax Pulse",
        "categoryId": 1,
        "images": ["https://example.com/image.jpg"]
      }
      """
    When I send a POST request to "/products"
    Then the response status code should be 400
    And the response body should contain an error message "price must be a positive number"
