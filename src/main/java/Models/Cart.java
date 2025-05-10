package Models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Cart {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("products")
    private List<Products> products;

    public Cart() {
    }
    public Cart( String userId, List<Products> products) {

        this.userId = userId;
        this.products = products;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Products> getProductsList() {
        return products;
    }

    public void setProductsList(List<Products> productsList) {
        this.products = productsList;
    }

}
