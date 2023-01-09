package com.cucumber.helper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.google.common.io.Files;

import io.cucumber.core.api.Scenario;

public class DriverManager {

    public static WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverManager.class);

    public WebDriver getDriver() throws MalformedURLException {
        if (driver == null)
            driver = startDriver();
        return driver;
    }

    private WebDriver startDriver() throws MalformedURLException {

        BrowserType browserType = BrowserType.valueOf(PropertyValues.getProperty(Property.BROWSERTYPE));

        if (BrowserType.chrome == browserType) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("disable-infobars");
            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().setScriptTimeout(Integer.parseInt(PropertyValues.getProperty("setScriptTimeout")), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(PropertyValues.getProperty("pageLoadTimeout")), TimeUnit.SECONDS);
        }
        return driver;
    }

    public void closeDriver(Scenario scenario) throws Throwable {
        if (driver != null) {
            try {
                LOGGER.info("Trying to quit the WebDriver (" + driver.getTitle() + ")");
                LOGGER.info("-------------------------------------------------------------------------");
                takeScreenShot(scenario);
                driver.close();
            } catch (Exception ex) {
                LOGGER.info("Error when quitting the WebDriver: " + ex.getMessage());
                LOGGER.info("-------------------------------------------------------------------------");
                driver.quit();
            }
        }
    }

    public void takeScreenShot(Scenario scenario) throws Exception {
        LOGGER.info("Scenario Status" + scenario.getStatus());
        System.out.println("Scenario Status" + scenario.getStatus());
        String status = scenario.getStatus().toString().trim();

        if (scenario.isFailed() || status.contains("PASSED")) {
            System.out.println("Scenario Status");
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            String screenshotName = scenario.getName().replaceAll(" ", "_");
            screenshotName = scenario.getName() + timeStamp;
            LOGGER.info(screenshotName + "  screenshotName");
            System.out.println("screenshotName" + screenshotName);
            try {
                File directory = new File("test-output/Screenshots");
                FileUtils.forceMkdir(directory);

                File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");

                String workingDir = System.getProperty("user.dir");
                String defaultAssetPath = workingDir + "/test-output/Screenshots/" + screenshotName + ".png";

                File destinationPath = new File(defaultAssetPath);
                Files.copy(sourcePath, destinationPath);

                ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(destinationPath.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}