

import api.User;
import api.UserStep;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.util.concurrent.TimeUnit;

import static constant.DataURL.URL_BASE;
import static constant.UserData.*;


public class TestBase {
    WebDriver driver;
    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL_BASE);
    }
    @After
    public void tearDown(){
        driver.close();
        driver.quit();

        UserStep userStep = new UserStep();
        User user = new User(USER_NAME, USER_EMAIL, USER_PASSWORD );

        String accessToken = userStep.accessTokenUser(user);
        if (accessToken != null) {
            userStep.deleteDataUser(accessToken);
        }
    }

}