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
import utilities.DataGenerator;

import static utilities.CashwiseAuthorization.getToken;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellerController {
    Faker faker = new Faker();

    static String seller_id="" ;


    /**
     * This test used by DataGenerator.sellerGenerator() class
     */
    @Test
    public void test_1_createSeller() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers" ;

        RequestBody requestBody = new RequestBody();
        requestBody.setCompany_name( faker.company().name() );
        requestBody.setSeller_name( faker.name().fullName() );
        requestBody.setEmail( faker.internet().emailAddress() );
        requestBody.setPhone_number( faker.phoneNumber().cellPhone() );
        requestBody.setAddress( faker.address().fullAddress() );

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .contentType(ContentType.JSON)
                .body( DataGenerator.sellerGenerator() )
                .post(url);

        response.prettyPrint();

        System.out.println("======================================");


        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse =mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println( "Seller id is: "+ customResponse.getSeller_id() );


        seller_id =  customResponse.getSeller_id() +"" ;
                /*
                Validate those:
                    private int seller_id;
                    private String seller_name;
                    private String phone_number;
                    private String address;

                 */


    }


    @Test
    public void test_2_getSingleSeller() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/"+seller_id;

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .get(url);

        response.prettyPrint();

        System.out.println("======================================");


        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse =mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println("=====TEST STARTED====================");
            Assert.assertNotNull( customResponse.getSeller_id() );
            Assert.assertNotNull( customResponse.getSeller_name() );
            Assert.assertNotNull( customResponse.getPhone_number() );
            Assert.assertNotNull( customResponse.getAddress() );
        System.out.println("=====TEST PASSED====================");

    }


    @Test
    public void test_3_getAllSellers() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/all";

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .get(url);

        response.prettyPrint();



        ObjectMapper mapper = new ObjectMapper();
        CustomResponse[] customResponse =mapper.readValue(response.asString(), CustomResponse[].class);

        for (int i=0; i<customResponse.length; i++ ) {
            System.out.println("=====TEST STARTED====================");
            Assert.assertNotNull(customResponse[i].getSeller_id());
            Assert.assertNotNull(customResponse[i].getSeller_name());
            Assert.assertNotNull(customResponse[i].getPhone_number());
            Assert.assertNotNull(customResponse[i].getAddress());
            System.out.println("=====TEST PASSED====================");

        }


    }


    @Test
    public void test_4_deleteSeller() throws JsonProcessingException {

        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers/" + seller_id;

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .delete(url);

        response.prettyPrint();

        System.out.println("======================================");



    }




    }
