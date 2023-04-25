import api.User;
import api.UserStep;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import paga.*;

import static constant.UserData.*;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest extends TestBase{

    @DisplayName("Переход в личный кабинет")
    @Description("Проверь переход по клику на «Личный кабинет». Без авторизации")
    @Test
    public void clickThroughPersonalAccountNotAuthorizationTest(){
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        mainPage.clickBtnPersonAccount();
        assertTrue(loginPage.checkLoginPage());
    }

    @DisplayName("Переход в личный кабинет")
    @Description("Проверь переход по клику на «Личный кабинет». С авторизации")
    @Test
    public void clickThroughPersonalAccountAuthorizationTest(){
        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD);
        userStep.createUser(user);

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickBtnPersonAccount();
        loginPage.userInputAccount(USER_EMAIL, USER_PASSWORD);
        mainPage.clickBtnPersonAccount();
        assertTrue(profilePage.checkingProfileNameData());
        assertTrue(profilePage.checkingProfilePasswordData());
        assertTrue(profilePage.checkingProfileEmailData());
        assertTrue(profilePage.checkingProfileUser());
        assertTrue(profilePage.checkingConstructor());
    }

    /*@DisplayName("Переход из личного кабинета в конструктор ")
    @Description("Проверь переход по клику на «Конструктор» и на логотип Stellar Burgers")
    @Test*/
}
