package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static constant.DataURL.*;
import static io.restassured.RestAssured.given;

public class UserStep {

    @Step("Создание нового пользователя")
    public Response createUser(User user) {
        RestAssured.baseURI = URL_BASE;
        return given().log().all()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(URL_REGISTER);
    }
    @Step("получение токена пользователя accessToken")
    public String accessTokenUser(User user) {
        RestAssured.baseURI = URL_BASE;
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(URL_LOGIN)
                .then().extract().path("accessToken");
    }


    @Step("Удаление пользователя")
    public void deleteDataUser(String accessToken) {
        RestAssured.baseURI = URL_BASE;
        given()
                .header("Authorization", accessToken)
                .delete(URL_USER);
               /* .then()
                .statusCode(202)
                .and()
                .assertThat().body("success", Matchers.is(true));*/
    }

@Step("Изменение данных пользователя")
public Response changingDataUser(User user, String accessToken) {
    RestAssured.baseURI = URL_BASE;
    Response authorization = given()
            .header("Content-type", "application/json")
            .header("Authorization", accessToken)
            .when()
            .body(user)
            .patch(URL_USER);
    return authorization;
}


}

