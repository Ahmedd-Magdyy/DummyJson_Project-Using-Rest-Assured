package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.BASE_URL;
import static EndPoints.Routes.UPDATECART_ENDPOINT;
import static io.restassured.RestAssured.given;

public class GetCartById {
    public static Response GetCartByIdResponse(String EndPointID)
    {
        return   given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().get(UPDATECART_ENDPOINT+EndPointID)
                .then()
                .log().all().extract().response();
    }
}
