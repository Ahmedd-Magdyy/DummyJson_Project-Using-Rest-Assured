package Utils;
import Models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static EndPoints.Routes.*;
import static io.restassured.RestAssured.given;


public class Login {
public static Response login(User body)
{
return given()
        .baseUri(BASE_URL)
        .contentType(ContentType.JSON)
        .body(body)
        .when().post(LOGIN_ENDPOINT)
        .then()
        .log().all().extract().response();

}
public static Response LoginResponseInvalidToken(String Token)
{
   return given()
           .baseUri(BASE_URL)
           .contentType(ContentType.JSON)
           .header("Authorization", Token)
           .when().get(AUTH_ENDPOINT)
           .then()
           .log().all().extract().response();
}
   public static Response GetAuthorizedProfileRes(String Token)
   {
      return  given()
              .baseUri(BASE_URL)
              .contentType(ContentType.JSON)
              .header("Authorization", "Bearer " + Token)
              .when().get(AUTH_ENDPOINT)
              .then()
              .log().all().extract().response();
   }
}
