package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellarburgers.api.User;
import stellarburgers.api.UserStep;
import stellarburgers.paga.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static stellarburgers.constants.UserData.*;

public class RegistrationTest extends BaseSetBrowsers {
    public RegistrationTest(String browserType, String binaryPath) {
        super(browserType, binaryPath);
    }

    @DisplayName("Регистрация")
    @Description("Успешная регистрацию")

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
    @Description("Ошибка для некорректного пароля. Минимальный пароль — шесть символов.")
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
    @Description("Вход по кнопке Войти в аккаунт» на главной странице")
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
    @Description("Вход через кнопку в форме восстановления пароля")
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
