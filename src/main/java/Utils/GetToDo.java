package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.*;
import static io.restassured.RestAssured.given;

public class GetToDo {
    public static Response GetToDoById(String EndPointID)
    {
        return  given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().get(GETTODO_ENDPOINT+EndPointID)
                .then()
                .log().all().extract().response();
    }
}
