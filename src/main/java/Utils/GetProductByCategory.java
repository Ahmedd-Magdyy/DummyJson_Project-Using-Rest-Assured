package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.BASE_URL;
import static EndPoints.Routes.GETPRODUCT_ENDPOINT;
import static io.restassured.RestAssured.given;

public class GetProductByCategory {
    public static Response GetProductByCategoryResponse(String EndPoint)
    {
        return  given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().get(GETPRODUCT_ENDPOINT+EndPoint)
                .then()
                .log().all().extract().response();
    }
}
