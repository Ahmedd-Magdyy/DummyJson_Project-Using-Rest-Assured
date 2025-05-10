package Utils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.BASE_URL;
import static EndPoints.Routes.GETTODO_ENDPOINT;
import static io.restassured.RestAssured.given;

public class DeleteToDo {
    public static Response DeleteToDoResponse(String EndPointID)
    {
        return  given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .when().delete(GETTODO_ENDPOINT+EndPointID)
                .then()
                .log().all().extract().response();
    }
}
