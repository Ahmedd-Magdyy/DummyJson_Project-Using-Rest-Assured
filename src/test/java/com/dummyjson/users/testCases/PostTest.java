package com.dummyjson.users.testCases;

import Models.Post;
import Utils.AddPost;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Constants.POST;
import static Utils.Constants.USERID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PostTest {

    @Test
    public void AddPost() {

        Post post = new Post(POST, USERID);

        Response response= AddPost.AddPostResponse(post);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("userId"), (equalTo("9")));
        assertThat(response.jsonPath().getString("title"), (equalTo("I am in love with my new product")));
    }

    @Test
    public void AddPostWithInvalidUserID() {

        Post post = new Post(POST, 99999);
        Response response= AddPost.AddPostResponse(post);
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("User with id '99999' not found")));
  }

}
