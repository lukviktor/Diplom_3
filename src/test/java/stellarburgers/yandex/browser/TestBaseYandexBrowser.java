package stellarburgers.yandex.browser;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static stellarburgers.constants.DataURL.URL_BASE;

public class TestBaseYandexBrowser {
   // WebDriver driver;
@Test
    public void setUp() {
    /*
    Узнать версию хромиума команда в Я.браузере browser://version/
    у меня версия 110.0.5481.719 (64-разрядная версия)
    Заходим https://chromedriver.chromium.org/downloads
     */

    System.setProperty("webdriver.chrome.driver","D:/projects/Diplom_Viktor_Lukashev_17/Diplom_3/src/main/resources/chromedriver.exe");
    ChromeOptions options=new ChromeOptions();
    options.setBinary("C:/Users/lvikt/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
    WebDriver webDriver= new ChromeDriver(options);

    webDriver.get(URL_BASE);


    /*
    System.setProperty("webdriver.opera.driver", "C:\\Users\\User\\IdeaProjects\\testselenium\\drivers\\operadriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.setBinary("C:\\Users\\User\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
    WebDriver driver = (WebDriver) new ChromeOptions();
    driver.get("https://docs.seleniumhq.org");
    */
    }
}