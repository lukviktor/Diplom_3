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
}
