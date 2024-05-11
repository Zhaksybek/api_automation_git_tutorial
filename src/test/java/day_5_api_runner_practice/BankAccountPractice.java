package day_5_api_runner_practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccountPractice {
    Faker faker = new Faker();
    static  String bankID ="";

    @Test
    public void test_1_createBankAccount() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount";

        //getToken()
        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name());
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(200, 15000));



        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response.prettyPrint();

        System.out.println("===========GET ID FROM RESPONSE BODY====================");
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(response.asString() , CustomResponse.class);

        bankID =  customResponse.getId();

        System.out.println(bankID);

    }


    @Test
    public void test_2_getSingleBankAccount() throws JsonProcessingException {
        // Step - 1 baseUrl + path
        String url = Config.getProperty("baseUrl") +"/api/myaccount/bankaccount/"+bankID ;

        // Step - 2 hit GET request
        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);

        response.prettyPrint();

        System.out.println("========================================");
        // Step - 3 Create Object mapper class
        ObjectMapper mapper = new ObjectMapper();
        // Step - 4  get value of customResponse
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);


        System.out.println(  customResponse.getId() );
        System.out.println(  customResponse.getBank_account_name() );
        System.out.println(  customResponse.getBalance()  );

        Assert.assertNotNull(  customResponse.getId()  );
        Assert.assertNotNull(   customResponse.getBank_account_name()  );
        Assert.assertNotNull(   customResponse.getBalance()  );

        System.out.println( "=============================================="  );

    }

    @Test
    public void test_3_getAllBankAccounts() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") +"/api/myaccount/bankaccount";


        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .get(url);

       // response.prettyPrint();

        System.out.println("========================================");

        ObjectMapper mapper = new ObjectMapper();
        CustomResponse[] customResponse = mapper.readValue(response.asString(), CustomResponse[].class);

        System.out.println(  customResponse[0].getBank_account_name() );

        // Size of Array (List of banks)
        System.out.println(  customResponse.length );


        System.out.println("========= TEST STARTED ===============================");

        for (int i=0; i<customResponse.length; i++ ){
            System.out.println(   customResponse[i].getBank_account_name()     );
            Assert.assertNotNull(customResponse[i].getBank_account_name()    );
        }


    }

    @Test
    public void test_4_updateBankAccount() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/"+bankID;


        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name());
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(200, 15000));


        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .contentType(ContentType.JSON)
                .body(requestBody)
                .put(url);

        response.prettyPrint();

        Assert.assertEquals("Status code is NOT 200",200,response.statusCode());



    }


    @Test
    public void test_6_deleteBankAccount() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/bankaccount/"+bankID;


        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .delete(url);

        response.prettyPrint();
        System.out.println(response.statusCode());

        Assert.assertEquals("Status code is NOT 200",200,response.statusCode());


    }

    }
