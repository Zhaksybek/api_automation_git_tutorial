package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
//@Getter  // day
//@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestBody {
    /**
     * day_3
     * Purpose of this class is set RequestBody
     */

    // CashwiseAuthorization
    private String email;
    private String password;

    // Category-controller
    private String category_title;
    private String category_description;
    private boolean flag;


    // Bank Account
    private String bank_account_name;
    private String description;
    private String type_of_pay;
    private double balance;

    // Seller controller
    private String company_name;
    private String seller_name;
   //  private String email;   We already have it
    private String phone_number;
    private String address;



    // day_6 Product controller
    private String product_title;
    private double product_price;
    private int service_type_id;
    private int category_id;
    private String product_description;
    /**
         * {
         *     "product_title": "Toyota12",
         *     "product_price": 100000,
         *     "service_type_id": 2,
         *     "category_id": 805,
         *     "product_description": "Car"
         * }
     */





/*

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

 */


}
