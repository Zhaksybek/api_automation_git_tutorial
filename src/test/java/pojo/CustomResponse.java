package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data // It will automatically add getter and Setter method

@JsonIgnoreProperties(ignoreUnknown = true) // com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException: Unrecognized field "category_description"
public class CustomResponse {

    /**
     * Category-controller
     */
    private int category_id;
    private String category_title;
    private String category_description;
    private boolean flag;


    // Bank Account
    private String id;
    private String bank_account_name;
    private double balance;


    // Seller controller
    private int seller_id;
    private String seller_name;
    private String phone_number;
    private String address;

    private CustomResponse[] responses; // When we have list of CustomResponses


    /**
     * "product_id": 719,
     *     "product_title": "Apple magic mouse",
     *     "product_price": 350.0,
     */
    private int product_id;
    private String product_title;
    private double product_price;
    private String product_description;


    private String responseBody; // It will return response body as a String (Instead os prettyPrint)

    // Profile
    private CustomResponse bank_account;
    //Category
    private CustomResponse[] category_response;





}
