package parabank.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriver() throws IOException {
        String browserType = PropertiesLoader.loadProperty("browser.name");
        if (driver == null) {
            switch (browserType) {
                case "chrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    driver.manage().window().maximize();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Niewspierana przeglądarka: " + browserType);
            }
        }

        return driver;

    }


    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
