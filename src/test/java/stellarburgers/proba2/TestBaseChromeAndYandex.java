package stellarburgers.proba2;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import stellarburgers.api.User;
import stellarburgers.api.UserStep;
import stellarburgers.paga.LoginPage;
import stellarburgers.paga.MainPage;
import stellarburgers.paga.RegisterPage;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static stellarburgers.constants.DataURL.URL_BASE;
import static stellarburgers.constants.UserData.*;

@RunWith(Parameterized.class)
public class TestBaseChromeAndYandex {
    private WebDriver driver;

    private final Browser browser;

    public TestBaseChromeAndYandex(Browser browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Browser.CHROME},
                {Browser.YANDEX}
        });
    }

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        String binaryPath = browser.getBinaryPath();
        if (binaryPath != null) {
            options.setBinary(binaryPath);
        }

        String driverPath = browser.getDriverPath();
        if (driverPath != null) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(URL_BASE);

        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD);

        String accessToken = userStep.accessTokenUser(user);
        if (accessToken != null) {
            userStep.deleteDataUser(accessToken);
        }
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

    @DisplayName("Регистрация")
    @Description("Успешную регистрацию")
    @Test
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickBtnPersonAccount();

        loginPage.clickBtnRegistration();
        registerPage.registrationUser(USER_NAME, USER_EMAIL, USER_PASSWORD);

        assertTrue(loginPage.checkLoginPage());
    }

    private enum Browser {
        CHROME("src/main/resources/chrome/chromedriver.exe", null),
        YANDEX("src/main/resources/yandex/chromedriver.exe", "C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");

        private final String driverPath;
        private final String binaryPath;

        Browser(String driverPath, String binaryPath) {
            this.driverPath = driverPath;
            this.binaryPath = binaryPath;
        }

        public String getDriverPath() {
            return driverPath;
        }

        public String getBinaryPath() {
            return binaryPath;
        }
    }
}
