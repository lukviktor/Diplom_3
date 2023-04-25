package paga;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By inputNameDataAccount = By.xpath("//input[@name='Name']");
    // поле имени профиля

    private final By inputEmailDatsAccount = By.xpath("//ul/li[2]/div/div/input");
    // поле Email профиля

    private final By inputPasswordDatsAccount = By.xpath("//ul/li[3]/div/div/input");
    // поле Email профиля



    private By profileUser = By.xpath("//a[@href='/account/profile']");
    //Ссылка на профиль

    private final By btnExit = By.xpath("//button[text()='Выход']");
    //Кнопка выход с аккаунта



    @Step("Проверка данных Name профиля при входе в аккаунт")
    public boolean checkingProfileNameData() {
        return driver.findElement(inputNameDataAccount).isDisplayed();
    }
    @Step("Проверка данных Email профиля при входе в аккаунт")
    public boolean checkingProfileEmailData() {
        return driver.findElement(inputEmailDatsAccount).isDisplayed();
    }
    @Step("Проверка данных Password профиля при входе в аккаунт")
    public boolean checkingProfilePasswordData() {
          return driver.findElement(inputPasswordDatsAccount).isDisplayed();
    }

    @Step("Проверка видимости ссылки на профиль")
    public boolean checkingProfileUser(){
        return driver.findElement(profileUser).isDisplayed();
    }

    @Step("выход по кнопке «Выйти» в личном кабинете")
    public void clickBtnExit() {
        driver.findElement(btnExit).click();
    }

}
