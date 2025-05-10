package com.dummyjson.users.testCases;

import Models.Product;
import Utils.DeleteProduct;
import Utils.GetProductByCategory;
import Utils.UpdateProductById;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static EndPoints.Routes.CATEGORY_ENDPOINT;
import static Utils.Constants.PRICE;
import static Utils.Constants.PRODUCT_ID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Every.everyItem;

public class ProductTest {

    @Test
    public void GetProductByCategory() {
        Response response= GetProductByCategory.GetProductByCategoryResponse(CATEGORY_ENDPOINT);
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getList("products.category"), everyItem(equalTo("smartphones")));

    }

    @Test
    public void GetProductByInvalidCategory() {
        Response response= GetProductByCategory.GetProductByCategoryResponse("belts");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("total"), (equalTo("0")));

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
    public void UpdateProductByInvalidID() {

        Product product = new Product("99999", PRICE);
        Response response= UpdateProductById.UpdateProductByIdResponse(product);
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("Product with id '99999' not found")));
  }

    @Test
    public void DeleteProduct() {

        Response response= DeleteProduct.DeleteProductResponse("1");
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("id"), (equalTo("1")));
        assertThat(response.jsonPath().getString("isDeleted"), (equalTo("true")));

    }

    @Test
    public void DeleteProductWithInvalidID() {

        Response response= DeleteProduct.DeleteProductResponse("99999");
        assertThat(response.statusCode(), equalTo(404));
        assertThat(response.jsonPath().getString("message"), (equalTo("Product with id '99999' not found")));
    }

}
