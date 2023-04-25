package paga;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By inputNameRegister = By.xpath("//fieldset[1]/div/div/input");
    //поле имя

    private final By inputEmailRegister = By.xpath("//fieldset[2]/div/div/input");
    //поле имя

    private final By inputPasswordRegister = By.xpath("//input[@name='Пароль']");
    // поле пароль

    private final By btnRegisterRegistration = By.xpath("//*[@id='root']/div/main/div/form/button");
            //"button[text()='Зарегистрироваться']");
    // кнопка регистрация

    private final By messageInvalidPassword = By.xpath("//p[@class='input__error text_type_main-default']");

    // сообщение неверный пароль
@Step("Ввод данных пользователя для регистрации")
    public void registrationUser(String name, String email, String password) {
        driver.findElement(inputNameRegister).sendKeys(name);
        driver.findElement(inputEmailRegister).sendKeys(email);
        driver.findElement(inputPasswordRegister).sendKeys(password);
        driver.findElement(btnRegisterRegistration).click();

    }
    @Step("Получение текста сообщения неверный пароль")
    public String messageInvalidPassword(){
        return driver.findElement(messageInvalidPassword).getText().toString();
    }

}
