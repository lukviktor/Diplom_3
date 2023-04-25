package paga;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By btnPersonalAccount = By.xpath("//a[@href='/account']");
//Кнопка Личный Кабинет на главной странице
    private final By btnSignInAccount = By.xpath("//*[@id='root']/div/main/section[2]/div/button");
    // кнопка «Войти в аккаунт» на главной странице"

@Step("Нажать по кнопке Личный Кабинет на главной странице")
public void clickBtnPersonAccount(){
    driver.findElement(btnPersonalAccount).click();
}
@Step("Нажать по кнопке «Войти в аккаунт» на главной странице")
    public void clickBtnSignInAccount() {
        driver.findElement(btnSignInAccount).click();
    }
}
