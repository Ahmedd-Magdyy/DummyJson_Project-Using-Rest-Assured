package com.dummyjson.users.testCases;

import Models.ToDo;
import Models.User;
import Utils.AddToDo;
import Utils.DeleteToDo;
import Utils.GetToDo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class ToDoTest {

    @Test
    public void AddToDo() {

        ToDo todo = new ToDo(TODO, COMPLETED, USERID);

        Response response= AddToDo.addToDoResponse(todo);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("userId"), equalTo("9"));
        assertThat(response.jsonPath().getString("todo"), equalTo("Want to buy a new product"));
        assertThat(response.jsonPath().getString("completed"), equalTo("true"));

    }

    @Test
    public void AddToDoWithInvalidUserID() {

        ToDo todo = new ToDo(TODO, COMPLETED, 99999);
        Response response=AddToDo.addToDoResponse(todo);
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), equalTo("User with id '99999' not found"));

    }

    @Test
    public void GetToDoByID() {
      Response response= GetToDo.GetToDoById("1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), equalTo("1"));
        assertThat(response.jsonPath().getString("todo"), equalTo("Do something nice for someone you care about"));
        assertThat(response.jsonPath().getString("userId"), equalTo("152"));
    }

    @Test
    public void GetToDoByInvalidID() {
        Response response= GetToDo.GetToDoById("999999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), equalTo("Todo with id '999999' not found"));
    }

    @Test
    public void DeleteToDo() {

        Response response= DeleteToDo.DeleteToDoResponse("1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), equalTo("1"));
        assertThat(response.jsonPath().getString("isDeleted"), equalTo("true"));

    }

    @Test
    public void DeleteToDoByInvalidID() {

        Response response= DeleteToDo.DeleteToDoResponse("999999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), equalTo("Todo with id '999999' not found"));


    }

}
