package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Products
{

    @JsonProperty("id")
    private int id;
    @JsonProperty("quantity")
    private int quantity;

    public Products(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
    public Products() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
