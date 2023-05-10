package stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import stellarburgers.api.User;
import stellarburgers.api.UserStep;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static stellarburgers.constants.DataURL.URL_BASE;
import static stellarburgers.constants.UserData.*;

@RunWith(Parameterized.class)
public class BaseSetBrowsers {
    WebDriver driver;
    String browserType;
    String binaryPath;
    private static final String setPropertyYandexBrowser = "src/main/resources/yandex/chromedriver.exe";

    private static final String systemPropertySettings = "webdriver.chrome.driver";
    private static final String chromeBrowser = "chrome";
    private static final String yandexBrowser = chromeBrowser;

    private static final String setBinaryYandexBrowser = "C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe";
    //Необходимо менять путь к бинарнику YandexBrowser как на твоем ПК.

    public BaseSetBrowsers(String browserType, String binaryPath) {
        this.browserType = browserType;
        this.binaryPath = binaryPath;
    }
    @Before
    public void setUp() {
        if (browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            if (binaryPath != null) {
                System.setProperty(systemPropertySettings, setPropertyYandexBrowser);
                options.setBinary(binaryPath);

            }
            driver = new ChromeDriver(options);
            // Добавить поддержку других типов браузеров
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL_BASE);
    }

    @After
    public void tearDown() {
        driver.quit();

        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD);

        String accessToken = userStep.accessTokenUser(user);
        if (accessToken != null) {
            userStep.deleteDataUser(accessToken);
        }
    }

    @Parameterized.Parameters(name = "Browser: {0}") // для совместного отчета по браузерам
    public static Collection<Object[]> getBrowserData() {
        return Arrays.asList(new Object[][]{
                {chromeBrowser, null},
                {yandexBrowser, setBinaryYandexBrowser}
        });
    }
}