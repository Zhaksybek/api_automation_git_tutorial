package utilities;

import com.github.javafaker.Faker;
import pojo.RequestBody;

public class DataGenerator {
    static Faker faker = new Faker();
    static RequestBody requestBody = new RequestBody();


    public static RequestBody sellerGenerator(){
        requestBody.setCompany_name(faker.company().name());
        requestBody.setSeller_name(faker.name().fullName());
        requestBody.setEmail(faker.internet().emailAddress());
        requestBody.setPhone_number(faker.phoneNumber().cellPhone());
        requestBody.setAddress(faker.address().fullAddress());
         /*
         private String company_name;
         private String seller_name;
        //  private String email;   We already have it
         private String phone_number;
         private String address;
          */
        return requestBody;
    }





}
