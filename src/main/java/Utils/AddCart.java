package Utils;

import Models.Cart;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.ADDCART_ENDPOINT;
import static EndPoints.Routes.BASE_URL;
import static io.restassured.RestAssured.given;

public class AddCart {
    public static Response AddCartResponse(Cart body)
    {
        return   given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .log().all()
                .when().post(ADDCART_ENDPOINT)
                .then()
                .log().all().extract().response();
    }
}
