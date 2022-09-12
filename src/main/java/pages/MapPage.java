package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

@Slf4j
public class MapPage implements AutoCloseable {
    private static final String MAP_URL_ADDRESS = "https://rzt.spb.ru/districts/";
    private final By searchField = new By.ByXPath("/html/body/footer/div/div[1]/form/div/input");
    private final By searchButton = new By.ByXPath("/html/body/footer/div/div[1]/form/button");

    private static WebDriver driver;

    public static MapPage openPage () {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.addArguments("--headless");

        driver = new ChromeDriver(options);
        driver.get(MAP_URL_ADDRESS);
        return new MapPage();
    }

    public void typeInAddress(String address) {
        driver.findElement(this.searchField).sendKeys(address);
    }

    public void clickSearch() {
        driver.findElement(searchButton).click();
    }

    public List<WebElement> getResults() {
        List<WebElement> results = driver.findElements(new By.ByClassName("search-page-item"));
        return results;
    }

    @Override
    public void close() {
        driver.quit();
    }
}
