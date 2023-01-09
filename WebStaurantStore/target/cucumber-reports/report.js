$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/SearchItems.feature");
formatter.feature({
  "name": "Search Functionality",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Verify search functionality",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Test"
    }
  ]
});
formatter.step({
  "name": "I Launch Application with \"\u003cURL\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Search for \"\u003cSearchKeyword\u003e\"",
  "keyword": "Then "
});
formatter.step({
  "name": "Verify all search items contains \"\u003cContainsKeyword\u003e\" keyword",
  "keyword": "And "
});
formatter.step({
  "name": "Add last element to cart",
  "keyword": "Then "
});
formatter.step({
  "name": "Empty cart",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "URL",
        "SearchKeyword",
        "ContainsKeyword"
      ]
    },
    {
      "cells": [
        "https://www.webstaurantstore.com/",
        "stainless work table",
        "Table"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Verify search functionality",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Test"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I Launch Application with \"https://www.webstaurantstore.com/\"",
  "keyword": "Given "
});
formatter.match({
  "location": "HomePageSteps.iLaunchApplicationWith(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Search for \"stainless work table\"",
  "keyword": "Then "
});
formatter.match({
  "location": "HomePageSteps.searchFor(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Verify all search items contains \"Table\" keyword",
  "keyword": "And "
});
formatter.match({
  "location": "HomePageSteps.verifyAllSearchItemsContainsKeyword(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Add last element to cart",
  "keyword": "Then "
});
formatter.match({
  "location": "HomePageSteps.addLastElementToCart()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Empty cart",
  "keyword": "And "
});
formatter.match({
  "location": "OrderConfirmationPageSteps.emptyCart()"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});