package stellarburgers.yandex.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import stellarburgers.api.User;
import stellarburgers.api.UserStep;

import java.util.concurrent.TimeUnit;

import static stellarburgers.constants.DataURL.URL_BASE;
import static stellarburgers.constants.UserData.*;

public class TestBaseYandexBrowser1 {
    WebDriver driver;

    @Before
    //@Test
    public void setUp() {
    /*
    Узнать версию хромиума команда в Я.браузере browser://version/
    у меня версия 110.0.5481.719 (64-разрядная версия)
    Заходим https://chromedriver.chromium.org/downloads и скачиваем
    В пропертя прописываем путь к драйверу
     */
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/yandex/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

        // У каждого свой путь к бинарнику YandexBrowser. Необходимо менять путь как на твоем ПК.
        options.setBinary("C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL_BASE);

    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();

        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD);

        String accessToken = userStep.accessTokenUser(user);
        if (accessToken != null) {
            userStep.deleteDataUser(accessToken);
        }
    }
}