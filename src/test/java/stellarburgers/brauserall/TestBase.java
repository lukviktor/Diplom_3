package stellarburgers.brauserall;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static stellarburgers.constants.DataURL.URL_BASE;
@RunWith(Parameterized.class)
public class TestBase {
    WebDriver driver;
    String browserType;
    String binaryPath;
    private static String setPropertyYandexBrowser = "src/main/resources/yandex/chromedriver.exe";
    private static String setBinaryYandexBrowser = "C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";

    public TestBase(String browserType, String binaryPath) {
        this.browserType = browserType;
        this.binaryPath = binaryPath;
    }

    @Before
    public void setUp() {
        switch (browserType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                if (binaryPath != null) {
                    System.setProperty("webdriver.chrome.driver", setPropertyYandexBrowser);
                    options.setBinary(binaryPath);

                }
                driver = new ChromeDriver(options);
                break;
            // Добавить поддержку других типов браузеров
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL_BASE);
    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> getBrowserData() {
        return Arrays.asList(new Object[][]{
                {"chrome", null},
                {"chrome", setBinaryYandexBrowser}
        });
    }
    @Test
    public void dfg(){
        TestBase testGoogleChrome = new TestBase("chrome", null);
        TestBase testYandexBrowser = new TestBase("chrome", setBinaryYandexBrowser);
    }
}