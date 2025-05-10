package com.dummyjson.users.testCases;

import Models.User;
import Utils.AddUser;
import Utils.LogOut;
import Utils.Login;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static EndPoints.Routes.AUTH_ENDPOINT;
import static EndPoints.Routes.BASE_URL;
import static Utils.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class UserTest {

    public String token;

    @Test
    public void LoginTest() {

        User user = new User(USER_NAME, PASSWORD);
        Response response = Login.login(user);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("firstName"), equalTo("Emily"));
        assertThat(response.jsonPath().getString("accessToken"), not(equalTo("null")));
        JsonPath jsonPath = response.jsonPath();
        token = jsonPath.getString("accessToken");
    }

    @Test
    public void InvalidLoginTest() {

        User user = new User("ahmed", "ahmedPass");
        Response response = Login.login(user);
        assertThat(response.statusCode(), equalTo(400));
        assertThat(response.jsonPath().getString("message"), equalTo("Invalid credentials"));
    }

    @Test
    public void GetAuthorizedProfile() {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3NDYzMzA1MjIsImV4cCI6MTc0NjMzNDEyMn0.kirX-UEN9qDrimKj_LBlHOBC82rvezs5K2O32iyQp88";
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().get(AUTH_ENDPOINT)
                .then()
                .log().all().assertThat().statusCode(200)
                .assertThat().body("firstName", equalTo("Emily"))
                .assertThat().body("id", equalTo(1));

    }

    @Test
    public void GetUnAuthorizedProfile() {

        Response response = Login.LoginResponseInvalidToken("InvalidToken");
        assertThat(response.statusCode(), equalTo(401));

    }

    @Test
    public void AddUser() {

        User user = new User(ID, AGE, FIRST_NAME, LAST_NAME,EMAIL,USERNAME,PASS, PHONE);
        Response response = AddUser.addUserResponse(user);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("id"), equalTo("209"));
    }

    @Test
    public void AddUserWithInvalidName() {

        User user = new User(ID, AGE, "Ahmed@123", LAST_NAME,EMAIL,USERNAME,PASS, PHONE);
        Response response = AddUser.addUserResponse(user);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("id"), equalTo("209"));
    }

    @Test
    public void Logout() {

        Response response = LogOut.logOut();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("message"), equalTo("Logged out successfully"));
    }

    @Test
    public void InvalidLogout() {

        Response response = LogOut.invalidLogout();
        assertThat(response.statusCode(), equalTo(401));
    }

}



