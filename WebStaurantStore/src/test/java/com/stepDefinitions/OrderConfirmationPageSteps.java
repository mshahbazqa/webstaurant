package com.stepDefinitions;

import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import com.cucumber.helper.DriverManager;
import pages.custombdd.pages.OrderConfirmationPage;

public class OrderConfirmationPageSteps {

    public WebDriver driver = getDriver();
    public OrderConfirmationPage orderConfirmationPage;

    public WebDriver getDriver() {
        return DriverManager.driver;
    }

    @And("Empty cart")
    public void emptyCart() {
        orderConfirmationPage = new OrderConfirmationPage(getDriver());
        orderConfirmationPage.emptyCart();
    }
}