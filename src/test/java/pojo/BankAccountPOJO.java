package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class BankAccountPOJO {

    private String id;
    private String bank_account_name;
    private String description;
    private String type_of_pay;
    private double balance;



}
