package Utils;

import Models.Products;

import java.util.List;

public class Constants {
    public static String USER_NAME="emilys";
    public static String PASSWORD="emilyspass";
    public static int ID=209;
    public static int AGE=27;
    public static String FIRST_NAME="Ahmed";
    public static String LAST_NAME="Magdy";
    public static String EMAIL="ahmed.magdy@x.dummyjson.com";
    public static String USERNAME="ahmed.magdy";
    public static String PASS="ahmed123";
    public static String PHONE="+201114721311";
    //TODO
    public static String TODO="Want to buy a new product";
    public static Boolean COMPLETED=true;
    public static int USERID=9;
    //PRODUCT
    public static String PRODUCT_ID="168";
    public static int PRICE=130;

    //Post
    public static String POST="I am in love with my new product";

    //cart
    public static String USERIDOFCART="9";
    public static int PID_CART=168;
    public static int PQNT_CART=1;




    public static List<Products> CART_PRODUCTS()
    {
        List<Products> products = new java.util.ArrayList<>();
        Products product1 = new Products(150, 4);
        Products product2 = new Products(1, 1);

        products.add(product1);
        products.add(product2);
        return products;
    }













}
