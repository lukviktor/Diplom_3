package paga;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Forgot_password {
    WebDriver driver;

    public Forgot_password(WebDriver driver) {
        this.driver = driver;
    }

/*    private final By inputPasswordRecovery =
            By.xpath("//input[@class='text input__textfield text_type_main-default']");
//поле Восстановление пароля*/

    private final By btnEnter = By.xpath("//a[@class='Auth_link__1fOlj']");
    // кнопка Войти в форме Восстановление пароля


    @Step("ввод в поле Восстановление пароля email")
    public void clickBtnEnter(){
        driver.findElement(btnEnter).click();
    }
}
