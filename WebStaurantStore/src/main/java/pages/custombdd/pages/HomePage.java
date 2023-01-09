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

public class HomePage {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);
	protected WebDriverWait driverWait;
	private WebDriver driver;


	@FindBy(how = How.ID, using = "searchval")
	private WebElement searchField;

	@FindBy(how = How.CSS, using = "button[value='Search']")
	private WebElement searchBtn;

	@FindBy(how = How.CSS, using = "[data-testid='itemDescription']")
	private List<WebElement> items;

	@FindBy(how = How.XPATH, using = "//div[@class='notification__action']/a[normalize-space()='Checkout']")
	private WebElement checkoutBtn;




	public HomePage(WebDriver driver) {
		driverWait = new WebDriverWait(driver, Integer.parseInt(PropertyValues.getProperty("driverWait")));
		this.driver = driver;
		PageFactory.initElements(driver, this);
		LOGGER.info("On Home Page.");
	}

	public void search(String searchText) {
		LOGGER.info("Searching for  "+searchText);
		searchField.sendKeys(searchText);
		searchBtn.click();

		// Wait until results > 0
		driverWait.until(driver -> items.size() > 0);
	}

	public void addToCart(WebElement element){
		LOGGER.info("Addint item to cart "+element.getText());
		element.findElement(By.xpath("..//..//input[@value='Add to Cart']")).click();
		driverWait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));
		checkoutBtn.click();
	}

	public List<WebElement> getAllItems(){
		LOGGER.info("Getting all search items");
		return items;
	}
}
