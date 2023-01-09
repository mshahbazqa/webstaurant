package com.stepDefinitions;

import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import pages.custombdd.pages.HomePage;
import com.cucumber.helper.DriverManager;

import java.util.List;

public class HomePageSteps {

    public WebDriver driver;
    public DriverManager driverManager;
    public HomePage homePage;
    static List<WebElement> searchItems;


    @Before
    public void before() throws Throwable {
        driverManager = new DriverManager();
        driver = driverManager.getDriver();
        homePage = new HomePage(driver);
    }

    @After
    public void after(Scenario scenario) throws Throwable {
        driverManager.closeDriver(scenario);
    }

    @Given("I Launch Application with {string}")
    public void iLaunchApplicationWith(String url) {
        driver.get(url);

    }

    @Then("Search for {string}")
    public void searchFor(String searchText) {
        homePage.search(searchText);
    }

    @And("Verify all search items contains {string} keyword")
    public void verifyAllSearchItemsContainsKeyword(String keyword) {
        searchItems = homePage.getAllItems();
        Assert.assertTrue("Problem in Loading Error Message in Application Login Page.", searchItems.stream().allMatch(t -> t.getText().contains(keyword)));
    }

    @Then("Add last element to cart")
    public void addLastElementToCart() {
        if (searchItems.size() > 0)
            homePage.addToCart(searchItems.get(searchItems.size()-1));
    }

}