package day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import utilities.CashwiseAuthorization;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BankAccount {


    /**
     * As a user I am able to login application
     */
   @Test
   public void test_0_token(){
    String url = "https://backend.cashwise.us/api/myaccount/auth/login";

       Map<String, Object> requestBody = new HashMap<>();
       requestBody.put("email" ,"codewise_batch4_api@gmail.com" );
       requestBody.put("password" ,"123456" );

       Response response = RestAssured
               .given().contentType(ContentType.JSON)
               .body(requestBody)
               .post(url);
       //response.prettyPrint();
       String token = response.jsonPath().getString("jwt_token");

       System.out.println("Token: "+token);

   }


    /**
     * A a user I am able to add new bank account to application
     */
   @Test
    public void test_1_createBankAccount(){
       String url = "https://backend.cashwise.us/api/myaccount/bankaccount";
       String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb2Rld2lzZV9iYXRjaDRfYXBpQGdtYWlsLmNvbSIsImV4cCI6MTcwNjQwNTUyOSwiaWF0IjoxNzA1ODAwNzI5fQ.YidPtkHfXioUt2nh3b87IUkykEJQbHsNbrLLEFt9MbXT-5Kf2U33P6YuGdd6Bzu2h1pvIHECFNSDrLexdRqeUw";


      Map<String, Object> requestBody = new HashMap<>();
      requestBody.put("type_of_pay", "CASH");
      requestBody.put("bank_account_name", "New Feature bank3");
      requestBody.put("description", "Financial company");
      requestBody.put("balance", 150090);

      Response response = RestAssured
              .given().auth().oauth2(token)
              .contentType(ContentType.JSON)
              .log().all()
              .body(requestBody)
              .post(url);


      System.out.println(response.statusCode());
      response.prettyPrint();

   }

    /**
     * As a user I am able to get all my bank Account list
     *
     */
    @Test
    public void test_2_getListOfBankAccounts(){

       String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb2Rld2lzZV9iYXRjaDRfYXBpQGdtYWlsLmNvbSIsImV4cCI6MTcwNjQwNTUyOSwiaWF0IjoxNzA1ODAwNzI5fQ.YidPtkHfXioUt2nh3b87IUkykEJQbHsNbrLLEFt9MbXT-5Kf2U33P6YuGdd6Bzu2h1pvIHECFNSDrLexdRqeUw";
       String url ="https://backend.cashwise.us/api/myaccount/bankaccount";

        Response response = RestAssured
                .given().auth().oauth2(token)
                .get(url);

        System.out.println("===================");
        System.out.println(response.statusCode());
        response.prettyPrint();


    }

    /**
     * As a user I am able to GET single bank account
     */
    @Test
    public void test_3_getSingleBankAccount(){

        String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb2Rld2lzZV9iYXRjaDRfYXBpQGdtYWlsLmNvbSIsImV4cCI6MTcwNjQwNTUyOSwiaWF0IjoxNzA1ODAwNzI5fQ.YidPtkHfXioUt2nh3b87IUkykEJQbHsNbrLLEFt9MbXT-5Kf2U33P6YuGdd6Bzu2h1pvIHECFNSDrLexdRqeUw";
        String url ="https://backend.cashwise.us/api/myaccount/bankaccount/851";

        Response response = RestAssured
                .given().auth().oauth2(token)
                .get(url);

        System.out.println("===================");
        System.out.println("Status code: "+response.statusCode());
        response.prettyPrint();
   }



   //=========Test Config reader==========================================

    /**
     * USE CONFIG READER FILE
     * As a user I am able to GET single bank account
     */
    @Test
    public void test_4_getSingleBankAccount(){

    //String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb2Rld2lzZV9iYXRjaDRfYXBpQGdtYWlsLmNvbSIsImV4cCI6MTcwNjQwNTUyOSwiaWF0IjoxNzA1ODAwNzI5fQ.YidPtkHfXioUt2nh3b87IUkykEJQbHsNbrLLEFt9MbXT-5Kf2U33P6YuGdd6Bzu2h1pvIHECFNSDrLexdRqeUw";
    String token = CashwiseAuthorization.getToken();

    //System.out.println( token );
        String url ="https://backend.cashwise.us/api/myaccount/bankaccount/851";

        String baseUrl = Config.getProperty("baseUrl");
        System.out.println(baseUrl);

    Response response = RestAssured
            .given()
            .auth().oauth2(token)
            .get(url);

    response.prettyPrint();

        System.out.println( "ID: "+response.jsonPath().getString("id") );
        System.out.println( "Bank account: "+response.jsonPath().getString("bank_account_name") );
        System.out.println( "Description: "+response.jsonPath().getString("description") );
        System.out.println( "Type of pay: "+response.jsonPath().getString("type_of_pay") );
        System.out.println( "Balance: "+response.jsonPath().getString("balance") );

   }


    @Test
    public void test_5_getListOfBankAccounts(){

    String token = CashwiseAuthorization.getToken();
    String url = Config.getProperty("baseUrl") +"/api/myaccount/bankaccount" ;

        Response response = RestAssured
                .given().auth().oauth2(token)
                .get(url);

        System.out.println("===================");
//        System.out.println(response.statusCode());
//        response.prettyPrint();

        // Get list of array
        int size = response.jsonPath().getList("").size();
        System.out.println("Size is: "+size);

        for ( int i =0; i< size;i++ ) {
            System.out.println("======= TEST Begin ======================");
            System.out.println("ID: " + response.jsonPath().getString("id["+i+"]"));
            System.out.println("Bank account: " + response.jsonPath().getString("bank_account_name["+i+"]"));
            System.out.println("Description: " + response.jsonPath().getString("description["+i+"]"));
            System.out.println("Type of pay: " + response.jsonPath().getString("type_of_pay["+i+"]"));
            System.out.println("Balance: " + response.jsonPath().getString("balance["+i+"]"));
            System.out.println("======= TEST Ends ======================");
        }

    }

    @Test
    public void test_6_getListOfBankAccounts(){
        String token = CashwiseAuthorization.getToken();
        System.out.println(token);
    }




}