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
public class ChromeAndYandexTest {
    private WebDriver driver;
    private String browser;

    public ChromeAndYandexTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<String> data() {
        return Arrays.asList(new String[]{"chrome", "yandex"});
    }

    @Before
    public void test() {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        } else if (browser.equalsIgnoreCase("yandex")) {
            WebDriverManager.chromedriver().setup();
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandex/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();

            // У каждого свой путь к бинарнику YandexBrowser. Необходимо менять путь как на твоем ПК.
            options.setBinary("C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        driver.get(URL_BASE);
        driver.quit();
    }
    @After
    public void tearDown(){}
}