package stellarburgers.brauserall;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import stellarburgers.api.User;
import stellarburgers.api.UserStep;
import stellarburgers.paga.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stellarburgers.constants.DataURL.URL_BASE;
import static stellarburgers.constants.UserData.*;

@RunWith(Parameterized.class)
public class TestBaseChromeAndYandex {
    private WebDriver driver;


    private Browser browser;



    @Parameterized.Parameters()
    public static Collection<Browser> data() {
        return Arrays.asList(Browser.values());
    }

    @Before
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
                driver = new ChromeDriver(options);
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandex/chromedriver.exe");
                options.setBinary("C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(URL_BASE);

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

    @DisplayName("Регистрация")
    @Description("Ошибку для некорректного пароля. Минимальный пароль — шесть символов.")
    @Test
    public void notSuccessfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        mainPage.clickBtnSignInAccount();
        loginPage.clickBtnRegistration();
        registerPage.registrationUser(USER_NAME, USER_EMAIL, "12345");
        String expected = registerPage.messageInvalidPassword();
        assertEquals(expected, "Некорректный пароль");
    }

    @DisplayName("Вход")
    @Description("вход по кнопке Войти в аккаунт» на главной странице")
    @Test
    public void loginAccountMainPageTest() {
        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD);
        userStep.createUser(user);


        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickBtnSignInAccount();
        loginPage.userInputAccount(USER_EMAIL, USER_PASSWORD);
        mainPage.clickBtnPersonAccount();
        assertTrue(profilePage.checkingProfileNameData());
        assertTrue(profilePage.checkingProfilePasswordData());
        assertTrue(profilePage.checkingProfileEmailData());
        assertTrue(profilePage.checkingProfileUser());

    }

    @DisplayName("Вход")
    @Description("вход через кнопку в форме восстановления пароля")
    @Test
    public void loginButtonPasswordRecoveryTest() {
        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD);
        userStep.createUser(user);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        Forgot_password forgotPassword = new Forgot_password(driver);

        mainPage.clickBtnSignInAccount();
        loginPage.clickBtnRegistration();

        forgotPassword.clickBtnEnter();
        loginPage.userInputAccount(USER_EMAIL, USER_PASSWORD);
        mainPage.clickBtnPersonAccount();
        assertTrue(profilePage.checkingProfileNameData());
        assertTrue(profilePage.checkingProfilePasswordData());
        assertTrue(profilePage.checkingProfileEmailData());
        assertTrue(profilePage.checkingProfileUser());
    }

}