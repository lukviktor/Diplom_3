package stellarburgers.paga;

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

    private final By btnBuns = By.xpath("//span[text()='Булки']");
    ////Кнопка перехода "Булки"

    private final By btnSauces = By.xpath("//span[text()='Соусы']");
    ////Кнопка перехода "Соусы"
    private final By btnFillings = By.xpath("//span[text()='Начинки']");
    ////Кнопка перехода "Начинки"



    @Step("Видимость сета выбора при переходе по кнопке булки")
    public boolean chooseBuns(String fluorescentBuns, String craterBuns) { // метод выбора города по названию
        // выбор булки
        return driver.findElement(By.xpath(String.format("//*[text()='%s']", fluorescentBuns))).isDisplayed() |
                driver.findElement(By.xpath(String.format("//*[text()='%s']", craterBuns))).isDisplayed();
    }

    @Step("Видимость сета выбора при переходе по кнопке соусы")
    public boolean chooseSauces(String spicy_XSous, String spaceSous) { // метод выбора города по названию
        // выбор булки
        return driver.findElement(By.xpath(String.format("//*[text()='%s']", spicy_XSous))).isDisplayed() |
                driver.findElement(By.xpath(String.format("//*[text()='%s']", spaceSous))).isDisplayed();
    }

    @Step("Видимость сета выбора при переходе по кнопке Начинки")
    public boolean chooseFillings(String meatShellfish, String beefMeteorite) { // метод выбора города по названию
        // выбор булки
        return driver.findElement(By.xpath(String.format("//*[text()='%s']", meatShellfish))).isDisplayed() |
                driver.findElement(By.xpath(String.format("//*[text()='%s']", beefMeteorite))).isDisplayed();
    }


    private final By fillingElement = By.xpath("//p[text()='Мясо бессмертных моллюсков Protostomia']");

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

    @Step("Переход на кнопка Булки") //MaimPage
    public void clickBtnBuns() {
        driver.findElement(btnBuns).click();
    }

    @Step("Переход на кнопка Соусы") //MaimPage
    public void clickBtnSauces() {
        driver.findElement(btnSauces).click();
    }

    @Step("Переход на кнопка Начинки") //MaimPage
    public void clickBtnFillings() {
        driver.findElement(btnFillings).click();
    }

}