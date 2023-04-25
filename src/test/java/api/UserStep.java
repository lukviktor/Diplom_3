package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class UserStep {

    @Step("Создание нового пользователя")
    public Response createUser(User user) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        return given().log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("api/auth/register");
    }
    @Step("получение токена пользователя accessToken")
    public String accessTokenUser(User user) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("api/auth/login")
                .then().extract().path("accessToken");
    }


    @Step("Удаление пользователя")
    public void deleteDataUser(String accessToken) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
        given()
                .header("Authorization", accessToken)
                .delete("api/auth/user");
               /* .then()
                .statusCode(202)
                .and()
                .assertThat().body("success", Matchers.is(true));*/
    }

@Step("Изменение данных пользователя")
public Response changingDataUser(User user, String accessToken) {
    RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    Response authorization = given()
            .header("Content-type", "application/json")
            .header("Authorization", accessToken)
            .when()
            .body(user)
            .patch("api/auth/user");
    return authorization;
}


}

