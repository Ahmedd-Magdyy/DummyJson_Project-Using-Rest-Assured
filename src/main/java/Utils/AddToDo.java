package Utils;

import Models.ToDo;
import Models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.*;
import static io.restassured.RestAssured.given;

public class AddToDo {
    public static Response addToDoResponse(ToDo body)
    {
       return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post(ADDTODO_ENDPOINT)
                .then()
                .log().all().extract().response();
    }
}
