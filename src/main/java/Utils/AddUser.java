package Utils;

import Models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.ADD_USER_ENDPOINT;
import static EndPoints.Routes.BASE_URL;
import static io.restassured.RestAssured.given;

public class AddUser {
    public static Response addUserResponse(User body)
    {
        return  given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post(ADD_USER_ENDPOINT)
                .then()
                .log().all().extract().response();
    }
}
