Feature: Search Functionality

  @Test
  Scenario Outline: Verify search functionality
    Given I Launch Application with "<URL>"
    Then Search for "<SearchKeyword>"
    And Verify all search items contains "<ContainsKeyword>" keyword
    Then Add last element to cart
    And Empty cart

    Examples:
      | URL                               | SearchKeyword        | ContainsKeyword |
      | https://www.webstaurantstore.com/ | stainless work table | Table           |