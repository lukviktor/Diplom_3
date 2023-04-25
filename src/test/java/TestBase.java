

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;



public class TestBase {
    WebDriver driver;

    @Before
    public void setUp() {
        //Меняем на нужный драйвер

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
       /*
         WebDriverManager.edgedriver().setup();
         driver = new EdgeDriver();
        */
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.nomoreparties.site/");


    }

   /* @After
    public void tearDown() {
        driver.close();
        driver.quit();
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        UserStep userStep = new UserStep();
        User user = new User(USER_EMAIL, USER_PASSWORD, USER_NAME);

        String accessToken = userStep.accessTokenUser(user);
        if (accessToken != null) {
            userStep.deleteDataUser(accessToken);
        }
    }*/
}