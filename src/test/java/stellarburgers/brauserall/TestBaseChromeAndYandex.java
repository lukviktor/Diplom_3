package stellarburgers.brauserall;

import io.github.bonigarcia.wdm.WebDriverManager;
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
public class TestBaseChromeAndYandex {
    private WebDriver driver;
    private Browser browser;

    public TestBaseChromeAndYandex(Browser browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection<Browser> data() {
        return Arrays.asList(Browser.values());
    }

    @Test
    public void test() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        switch (browser) {
            case CHROME:
                driver = new ChromeDriver();
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chrome/chromedriver.exe");
                options.setBinary("path/to/chrome/binary");
                break;
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandex/chromedriver.exe");
                options.setBinary("C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.get(URL_BASE);

        driver.quit();
    }


}