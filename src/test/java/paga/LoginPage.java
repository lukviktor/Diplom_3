package paga;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By inputEmail = By.xpath("//input[@name='name']");
    //поле email

    private final By inputPassword = By.xpath("//input[@name='Пароль']");
    //поле password

    private final By btnInput = By.xpath("//form/button['Войти']");
    // кнопка войти

    private final By btnRegisterLogin = By.xpath("//a[@href='/register']");
    //кнопка форме регистрации

    private final By btnRecoverPassword = By.xpath("//a[@href='/forgot-password']");
    //кнопка формы восстановления пароля

    private final By messageInvalidPassword = By.xpath("//p[@class='input__error text_type_main-default']");
    //сообщение не корректный пароль

    @Step("Нажать на кнопку в форме регистрации")
    public void clickBtnRegistration() {
        driver.findElement(btnRegisterLogin).click();
    }


    @Step("Проверка что находимся на странице Логин")
    public boolean checkLoginPage(){
        return driver.findElement(btnRegisterLogin).isDisplayed();
    }

    @Step("Сообщение при не корректном вводе пароля при регистрации")
    public boolean messageInvalidPassword(){
        return driver.findElement(messageInvalidPassword).isDisplayed();
    }
    @Step("Вход в аккаунт")
    public void userInputAccount(String email, String password) {
        driver.findElement(inputEmail).sendKeys(email);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(btnInput).click();
    }
}
