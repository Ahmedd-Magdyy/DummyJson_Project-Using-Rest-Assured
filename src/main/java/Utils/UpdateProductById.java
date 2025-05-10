package Utils;

import Models.Product;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UpdateProductById {
    public static Response UpdateProductByIdResponse(Product body)
    {
        return  given()
                .baseUri("https://dummyjson.com")
                .contentType(ContentType.JSON)
                .body(body)
                .when().patch("/products/168")
                .then()
                .log().all().extract().response();
    }
}
