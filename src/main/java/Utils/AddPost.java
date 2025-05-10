package Utils;

import Models.Post;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.BASE_URL;
import static EndPoints.Routes.POST_ENDPOINT;
import static io.restassured.RestAssured.given;

public class AddPost {
    public static Response AddPostResponse(Post body)
    {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post(POST_ENDPOINT)
                .then()
                .log().all().extract().response();
    }
}
