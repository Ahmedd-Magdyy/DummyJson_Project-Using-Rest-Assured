package Utils;

import Models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.*;
import static io.restassured.RestAssured.given;

public class LogOut {
    public static Response logOut()
    {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().post(LOGOUT_ENDPOINT)
                .then()
                .log().all().extract().response();
    }
    public static Response invalidLogout()
    {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().post(LOGOUT1_ENDPOINT)
                .then()
                .log().all().extract().response();
    }
}
