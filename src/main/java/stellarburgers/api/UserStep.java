package stellarburgers.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import stellarburgers.constants.DataURL;

import static io.restassured.RestAssured.given;

public class UserStep {

    @Step("Создание нового пользователя")
    public void createUser(User user) {
        RestAssured.baseURI = DataURL.URL_BASE;
        given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(DataURL.URL_REGISTER);
    }

    @Step("получение токена пользователя accessToken")
    public String accessTokenUser(User user) {
        RestAssured.baseURI = DataURL.URL_BASE;
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(DataURL.URL_LOGIN)
                .then().extract().path("accessToken");
    }

    @Step("Удаление пользователя")
    public void deleteDataUser(String accessToken) {
        RestAssured.baseURI = DataURL.URL_BASE;
        given()
                .header("Authorization", accessToken)
                .delete(DataURL.URL_USER);
    }
}