package com.dummyjson.users.Scenarios;

import Models.*;
import Utils.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static EndPoints.Routes.CATEGORY_ENDPOINT;
import static Utils.Constants.*;
import static Utils.Constants.PHONE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Every.everyItem;

public class ValidScenario {

  public static String accessToken;

    @BeforeClass
    public void LoginToGetToken() {

        User user = new User(USER_NAME, PASSWORD);
        Response response = Login.login(user);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("firstName"), equalTo("Emily"));
        assertThat(response.jsonPath().getString("accessToken"), not(equalTo("null")));
        JsonPath jsonPath = response.jsonPath();
        accessToken = jsonPath.getString("accessToken");
        }

    @Test
    public void GetAuthorizedProfile() {

       Response response=Login.GetAuthorizedProfileRes(accessToken);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("firstName"), equalTo("Emily"));
        assertThat(response.jsonPath().getString("id"), equalTo("1"));

        }

    @Test
    public void AddUser() {

        User user = new User(ID, AGE, FIRST_NAME, LAST_NAME,EMAIL,USERNAME,PASS, PHONE);
        Response response = AddUser.addUserResponse(user);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("id"), equalTo("209"));
    }

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
    public void GetToDoByID() {

        Response response= GetToDo.GetToDoById("1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), equalTo("1"));
        assertThat(response.jsonPath().getString("todo"), equalTo("Do something nice for someone you care about"));
        assertThat(response.jsonPath().getString("userId"), equalTo("152"));
    }

    @Test
    public void GetProductByCategory() {

        Response response= GetProductByCategory.GetProductByCategoryResponse(CATEGORY_ENDPOINT);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getList("products.category"), everyItem(equalTo("smartphones")));
    }

    @Test
    public void UpdateProductByID() {

        Product product = new Product(PRODUCT_ID, PRICE);
        Response response= UpdateProductById.UpdateProductByIdResponse(product);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), (equalTo("168")));
        assertThat(response.jsonPath().getString("price"), (equalTo("130")));
    }

    @Test
    public void DeleteProduct() {

        Response response= DeleteProduct.DeleteProductResponse("1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), (equalTo("1")));
        assertThat(response.jsonPath().getString("isDeleted"), (equalTo("true")));

    }

    @Test
    public void AddCart() {

        List<Products> products = Constants.CART_PRODUCTS();
        Cart cart = new Cart(USERIDOFCART, products);
        Response response= AddCart.AddCartResponse(cart);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("userId"), (equalTo("9")));
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
    public void GetCartByID() {

        Response response= GetCartById.GetCartByIdResponse("1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), (equalTo("1")));
    }

    @Test
    public void AddPost() {

        Post post = new Post(POST, USERID);
        Response response= AddPost.AddPostResponse(post);
        assertThat(response.statusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("userId"), (equalTo("9")));
        assertThat(response.jsonPath().getString("title"), (equalTo("I am in love with my new product")));
    }

    @Test
    public void DeleteToDo() {

        Response response= DeleteToDo.DeleteToDoResponse("1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), equalTo("1"));
        assertThat(response.jsonPath().getString("isDeleted"), equalTo("true"));

    }

    @Test
    public void Logout() {

        Response response = LogOut.logOut();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("message"), equalTo("Logged out successfully"));
    }

  }


