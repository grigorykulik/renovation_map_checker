package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

@Slf4j
public class MapPage implements AutoCloseable {
    private static final PropertiesConfiguration config = new PropertiesConfiguration();
    private static final By searchField = new By.ByXPath("/html/body/footer/div/div[1]/form/div/input");
    private static final By searchButton = new By.ByXPath("/html/body/footer/div/div[1]/form/button");
    private static final String propertiesFileName = "src/main/resources/application.properties";
    private WebDriver driver;

    public MapPage () {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--headless");

        try {
            config.load(propertiesFileName);
        } catch (ConfigurationException e) {
            log.error("Could not load properties file.");
        }

        driver = new ChromeDriver(options);
        driver.get(config.getString("MAP_URL_ADDRESS"));
    }

    public void typeInAddress(String address) {
        driver.findElement(searchField).sendKeys(address);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public List<WebElement> getResults() {
        return driver.findElements(new By.ByClassName("search-page-item"));
    }

    @Override
    public void close() {
        driver.quit();
    }
}
