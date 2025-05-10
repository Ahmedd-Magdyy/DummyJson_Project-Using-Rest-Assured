package com.dummyjson.users.Scenarios;

import Models.*;
import Utils.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static Utils.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class InvalidScenario {

    @Test
    public void InvalidLoginTest(){

        User user = new User("ahmed", "ahmedPass");
        Response response = Login.login(user);
        assertThat(response.statusCode(), equalTo(400));
        assertThat(response.jsonPath().getString("message"), equalTo("Invalid credentials"));
    }

    @Test
    public void GetUnAuthorizedProfile() {
        Response response = Login.LoginResponseInvalidToken("InvalidToken");
        assertThat(response.statusCode(), equalTo(401));

    }

    @Test
    public void AddUserWithInvalidName() {

        User user = new User(ID, AGE, "Ahmed@123", LAST_NAME,EMAIL,USERNAME,PASS, PHONE);
        Response response = AddUser.addUserResponse(user);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("id"), equalTo("209"));

    }

    @Test
    public void AddToDoWithInvalidUserID() {

        ToDo todo = new ToDo(TODO, COMPLETED, 99999);
        Response response= AddToDo.addToDoResponse(todo);
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), equalTo("User with id '99999' not found"));
   }

    @Test
    public void GetToDoByInvalidID() {

        Response response= GetToDo.GetToDoById("999999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), equalTo("Todo with id '999999' not found"));
    }

    @Test
    public void GetProductByInvalidCategory() {
        Response response= GetProductByCategory.GetProductByCategoryResponse("belts");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("total"), (equalTo("0")));

    }

    @Test
    public void UpdateProductByInvalidID() {
        Product product = new Product("99999", PRICE);
        Response response= UpdateProductById.UpdateProductByIdResponse(product);
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("Product with id '99999' not found")));
    }

    @Test
    public void DeleteProductWithInvalidID() {
        Response response= DeleteProduct.DeleteProductResponse("99999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("Product with id '99999' not found")));
    }

    @Test
    public void AddCartByInvalidUserID() {
        List<Products> products = Constants.CART_PRODUCTS();
        Cart cart = new Cart("99999", products);
        Response response= AddCart.AddCartResponse(cart);
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("User with id '99999' not found")));
    }

    @Test
    public void UpdateCartWithInvalidID() {
        Cart cart = new Cart();
        List<Products> products = new java.util.ArrayList<>();
        Products product1 = new Products(PID_CART, PQNT_CART);
        products.add(product1);
        Response response= UpdateCart.UpdatecartResponse(cart,"99999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("Cart with id '99999' not found")));
    }

    @Test
    public void GetCartByInvalidID() {

        Response response= GetCartById.GetCartByIdResponse("99999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("Cart with id '99999' not found")));
    }

    @Test
    public void AddPostWithInvalidUserID() {

        Post post = new Post(POST, 99999);
        Response response= AddPost.AddPostResponse(post);
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("User with id '99999' not found")));
    }

    @Test
    public void DeleteToDoByInvalidID() {

        Response response= DeleteToDo.DeleteToDoResponse("999999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), equalTo("Todo with id '999999' not found"));

    }

    @Test
    public void InvalidLogout() {
        Response response = LogOut.invalidLogout();
        assertThat(response.statusCode(), equalTo(401));

    }

}
