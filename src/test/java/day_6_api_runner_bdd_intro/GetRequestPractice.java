package day_6_api_runner_bdd_intro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import utilities.APIRunner;
import utilities.Config;

import java.util.HashMap;
import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;

public class GetRequestPractice {


    @Test
    public void test_1_getSingleProduct() throws JsonProcessingException {
       // https://backend.cashwise.us /api/myaccount/products/1013
        // Step - 1 baseUrl + path
        String url = Config.getProperty("baseUrl") + "/api/myaccount/products/1013";

        // Step - 2 hit GET request
        Response response = RestAssured.given()
                .auth().oauth2(  getToken()  )
                .get(url );
        // Step - 3 Create Object mapper class
        ObjectMapper mapper = new ObjectMapper();
        // Step - 4  get value of customResponse
        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);


        System.out.println("ID:" + customResponse.getProduct_id());
        System.out.println("Product tile:" + customResponse.getProduct_title());
        System.out.println("Product price:" + customResponse.getProduct_price());
        System.out.println("Product description:" + customResponse.getProduct_description());


        Assert.assertNotNull(  customResponse.getProduct_id()  );
        Assert.assertNotNull(  customResponse.getProduct_title()  );
        Assert.assertNotNull(  customResponse.getProduct_price()  );
        Assert.assertNotNull(  customResponse.getProduct_description() );

        /**
         *     private int product_id;
         *     private String product_title;
         *     private double product_price;
         *     private String product_description;
         */

    }

    @Test
    public void test_2_getSingleProductAPIRunner() throws JsonProcessingException {
        // https://backend.cashwise.us /api/myaccount/products/1013
        String path = "/api/myaccount/products/1013";
        APIRunner.runGET(path);
        int productId = APIRunner.getCustomResponse().getProduct_id();
        System.out.println("Product ID: "+ productId);

    }

    @Test
    public void test_3_getSingleBankAccount(){
        // https://backend.cashwise.us  /api/myaccount/bankaccount/1208
        String path = "/api/myaccount/bankaccount/1208";
        APIRunner.runGET(path);
        String bankAccountName = APIRunner.getCustomResponse().getBank_account_name();
        System.out.println(bankAccountName);
        // read value
        System.out.println("My response body: "+APIRunner.getCustomResponse().getResponseBody());


    }

    // Example for list of Bank Accounts
    @Test
    public void test_4_getListOfBanks(){
        // https://backend.cashwise.us /api/myaccount/bankaccount
        String path = "/api/myaccount/bankaccount";

        APIRunner.runGET(path);
        CustomResponse[] customResponses = APIRunner.getcustomResponseArray();

        for(int i=0; i< customResponses.length; i++) {
            System.out.println("My id" + APIRunner.getcustomResponseArray()[i].getId());
            Assert.assertNotNull( APIRunner.getcustomResponseArray()[i].getId()  );

            System.out.println("My id" + APIRunner.getcustomResponseArray()[i].getBank_account_name());
            Assert.assertNotNull( APIRunner.getcustomResponseArray()[i].getBank_account_name()  );

            System.out.println("My id" + APIRunner.getcustomResponseArray()[i].getBalance());
            Assert.assertNotNull( APIRunner.getcustomResponseArray()[i].getBalance()  );

        }
        /**
                 *      private String id;
                 *     private String bank_account_name;
                 *     private double balance;
         */


    }

    @Test
    public void test_5_getListOfProduct(){
        // https://backend.cashwise.us /api/myaccount/products/find/all
        APIRunner.runGET("/api/myaccount/products/find/all");
        System.out.println(APIRunner.getcustomResponseArray()[0].getProduct_id());


    }


    // GET list of responses  isArchived
    @Test
    public void test_6_getListOfSellersIsArchived(){

        // https://backend.cashwise.us  /api/myaccount/sellers   ?isArchived=false &page=1&size=10
        String path =  "/api/myaccount/sellers";
        Map<String,Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page", 1);
        params.put("size", 10);

        APIRunner.runGET(path, params);
        System.out.println( "Seller ID: "+  APIRunner.getCustomResponse().getResponses()[0].getSeller_id() );
        Assert.assertNotNull( APIRunner.getCustomResponse().getResponses()[0].getSeller_id() );

        //System.out.println( "Seller ID: "+  APIRunner.getcustomResponseArray()[0].getSeller_id() );
    }



}
