package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.api.User;
import stellarburgers.api.UserStep;
import stellarburgers.paga.LoginPage;
import stellarburgers.paga.MainPage;
import stellarburgers.paga.ProfilePage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stellarburgers.constants.DataURL.URL_BASE;
import static stellarburgers.constants.UserData.*;

public class PersonalAccountTest extends BaseSetBrowsers {
    public PersonalAccountTest(String browserType, String binaryPath) {
        super(browserType, binaryPath);
    }

    @Before
    public void createUser() {
        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD);
        userStep.createUser(user);
    }

    @DisplayName("Переход в личный кабинет")
    @Description("Проверь переход по клику на «Личный кабинет». Без авторизации. Плавающий bug(иногда открывается черный экран)")
    @Test
    public void clickThroughPersonalAccountNotAuthorizationTest() {
        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD);
        String accessToken = userStep.accessTokenUser(user);
        userStep.deleteDataUser(accessToken);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickBtnPersonAccount();
        assertTrue(loginPage.checkLoginPage());
    }

    @DisplayName("Переход в личный кабинет")
    @Description("Проверь переход по клику на «Личный кабинет». С авторизации")
    @Test
    public void clickThroughPersonalAccountAuthorizationTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickBtnPersonAccount();
        loginPage.userInputAccount(USER_EMAIL, USER_PASSWORD);
        mainPage.clickBtnPersonAccount();
        assertTrue(profilePage.checkingProfileUser());
        assertTrue(profilePage.checkingTextProfilePage());
    }

    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверь переход по клику на «Конструктор»")
    @Test
    public void switchingPersonalAccountConstructorTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickBtnPersonAccount();
        loginPage.userInputAccount(USER_EMAIL, USER_PASSWORD);
        mainPage.clickConstructor();
        assertTrue(mainPage.checkBtnPlaceOrder());
    }

    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверь переход по клику на логотип Stellar Burgers")
    @Test
    public void switchingPersonalAccountLogoSBTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickBtnPersonAccount();
        loginPage.userInputAccount(USER_EMAIL, USER_PASSWORD);
        mainPage.clickLogoStellarBurgers();
        assertEquals(URL_BASE, driver.getCurrentUrl());
    }

    @DisplayName("Выход из аккаунта")
    @Description("Проверь выход по кнопке «Выйти» в личном кабинете.")
    @Test
    public void LogOutAccount() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickBtnPersonAccount();
        loginPage.userInputAccount(USER_EMAIL, USER_PASSWORD);
        mainPage.clickBtnPersonAccount();
        profilePage.clickBtnExit();
        assertTrue(loginPage.checkLoginPage());
        assertEquals((URL_BASE + "login"), driver.getCurrentUrl());
    }
}
