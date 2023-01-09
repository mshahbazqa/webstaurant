package pages.custombdd.pages;

import com.cucumber.helper.PropertyValues;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OrderConfirmationPage {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConfirmationPage.class);
	protected WebDriverWait driverWait;
	private WebDriver driver;

	@FindBy(how = How.XPATH, using = "//span[normalize-space()='Cart']")
	private WebElement cartBtn;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Empty Cart']")
	private WebElement emptyCartBtn;

	@FindBy(how = How.XPATH, using = "(//button[normalize-space()='Empty Cart'])[2]")
	private WebElement emptyCartConfirmationBtn;


	public OrderConfirmationPage(WebDriver driver) {
		driverWait = new WebDriverWait(driver, Integer.parseInt(PropertyValues.getProperty("driverWait")));
		this.driver = driver;
		PageFactory.initElements(driver, this);
		LOGGER.info("On Order Confirmation Page.");
	}

	public void emptyCart() {
		LOGGER.info("Clicking Empty Cart Buttton...!!! ");
		cartBtn.click();
		driverWait.until(ExpectedConditions.elementToBeClickable(emptyCartBtn));
		emptyCartBtn.click();
		emptyCartConfirmationBtn.click();
	}
}
