package Utils;

import Models.Cart;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.BASE_URL;
import static EndPoints.Routes.UPDATECART_ENDPOINT;
import static io.restassured.RestAssured.given;

public class UpdateCart {
    public static Response UpdatecartResponse(Cart cart,String EndPoint)
    {
        return  given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(cart)
                .when().put(UPDATECART_ENDPOINT+EndPoint)
                .then()
                .log().all().extract().response();
    }
}
