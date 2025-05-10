package com.dummyjson.users.testCases;

import Models.Cart;
import Models.Products;
import Utils.AddCart;
import Utils.Constants;
import Utils.GetCartById;
import Utils.UpdateCart;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.awt.geom.RectangularShape;
import java.util.List;

import static Utils.Constants.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CartTest {

    @Test
    public void AddCart() {
        List<Products> products = Constants.CART_PRODUCTS();
        Cart cart = new Cart(USERIDOFCART, products);
        Response response= AddCart.AddCartResponse(cart);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("userId"), (equalTo("9")));
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
    public void UpdateCart() {
        Cart cart = new Cart();
        List<Products> products = new java.util.ArrayList<>();
        Products product1 = new Products(PID_CART, PQNT_CART);
        products.add(product1);
        Response response= UpdateCart.UpdatecartResponse(cart,"1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), (equalTo("1")));
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
    public void GetCartByID() {
        Response response= GetCartById.GetCartByIdResponse("1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), (equalTo("1")));
    }
    @Test
    public void GetCartByInvalidID() {
        Response response= GetCartById.GetCartByIdResponse("99999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("Cart with id '99999' not found")));
    }

}
