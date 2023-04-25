import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import paga.LoginPage;
import paga.MainPage;
import paga.RegisterPage;

import static constant.UserData.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends TestBase {



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
    public void notSuccessfulRegistration() {
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
   // @Test
    public void LoginAccountMainPageTest() {

    }
//@Test
    public void sfgf(){
   /* mainPage.clickBtnSignInAccount();
    loginPage.clickBtnRegistration();*/
}

}
