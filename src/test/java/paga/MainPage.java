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
    private final By btnSignInAccount = By.xpath("//button[text()='Войти в аккаунт']");
    //By.xpath("//*[@id='root']/div/main/section[2]/div/button");
    // кнопка «Войти в аккаунт» на главной странице"

    private final By headlineAssembleBurger = By.xpath("//h1[@class='text text_type_main-large mb-5 mt-10']");
    // заголовок Соберите Бургер

    private final By btnPlaceOrder = By.xpath("//button[text()='Оформить заказ']");
    // Кнопка Оформить заказ

    private final By constructor = By.xpath("//p[text()='Конструктор']");
    //Ссылка на конструктор

    private final By logoStellarBurgers = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']/a");
    //Ссылка логотип stellarburgers




    @Step("Нажать по кнопке Личный Кабинет на главной странице")
    public void clickBtnPersonAccount() {
        driver.findElement(btnPersonalAccount).click();
    }

    @Step("Нажать по кнопке «Войти в аккаунт» на главной странице")
    public void clickBtnSignInAccount() {
        driver.findElement(btnSignInAccount).click();
    }

    @Step("Видимость заголовок соберите бургер")
    public boolean checkHeadlineAssembleBurger() {
        return driver.findElement(headlineAssembleBurger).isDisplayed();
    }

    ;

    @Step("Видимость кнопки оформить заказ")
    public boolean checkBtnPlaceOrder() {
        return driver.findElement(btnPlaceOrder).isDisplayed();
    }

    @Step("Переход по ссылке конструктор")
    public void clickConstructor() {
        driver.findElement(constructor).click();
    }

    @Step("Переход по ссылке логотип stellarburgers")
    public void clickLogoStellarBurgers() {
        driver.findElement(logoStellarBurgers).click();
    }

}
