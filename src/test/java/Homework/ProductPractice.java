package Homework;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import utilities.CashwiseAuthorization;
import utilities.Config;

import static utilities.CashwiseAuthorization.getToken;



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
public class ProductPractice {


        static int product_id;
        static Faker faker = new Faker();

        @Test
        public void test_1_createProduct() throws JsonProcessingException {

            String url = Config.getProperty("baseUrl") + "/api/myaccount/products";

            RequestBody productRequestBody = new RequestBody();
            productRequestBody.setProduct_title(faker.book().title());
            productRequestBody.setProduct_price(faker.number().numberBetween(2000, 3000));
            productRequestBody.setService_type_id(2);
            productRequestBody.setCategory_id(805);
            productRequestBody.setProduct_description(faker.book().author());


            Response response = RestAssured.given().auth().oauth2(getToken()).contentType(ContentType.JSON)
                    .body(productRequestBody).post(url);

            System.out.println("Status code " + response.statusCode());
            Assert.assertEquals(201, response.statusCode());
            response.prettyPeek();


            ObjectMapper mapper = new ObjectMapper();
            CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);
            product_id = customResponse.getProduct_id();
            System.out.println("Seller Id is: " + product_id);
            System.out.println(response);
            response.prettyPeek();


        }

        @Test
        public void test_2_getListOFProducts() throws JsonProcessingException {
            String url = Config.getProperty("baseUrl") + "/api/myaccount/products/all";

            Response response = RestAssured.given().auth().oauth2(getToken()).get(url);
            Assert.assertEquals(200, response.statusCode());

            System.out.println(response);
            response.prettyPeek();

            ObjectMapper mapper = new ObjectMapper();
            CustomResponse customResponse[] = mapper.readValue(response.asString(), CustomResponse[].class);

            for (int i = 0; i < customResponse.length; i++) {
                System.out.println(customResponse[i].getProduct_id());
                System.out.println(customResponse[i].getProduct_title());
                Assert.assertNotNull(customResponse[i].getProduct_id());

            }


        }

        @Test
        public void test_3_getSingleProduct() throws JsonProcessingException {
            String url = Config.getProperty("baseUrl") + "/api/myaccount/products/" + product_id;

            Response response = RestAssured.given().auth().oauth2(getToken()).get(url);
            System.out.println("Status code is: " + response.statusCode());
            response.prettyPeek();

            ObjectMapper mapper = new ObjectMapper();
            CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);


            System.out.println(customResponse.getProduct_id());
            System.out.println(customResponse.getProduct_title());

            System.out.println("===== Assert Test=================");
            Assert.assertNotNull( customResponse.getProduct_id() );
            Assert.assertNotNull( customResponse.getProduct_title() );


        }

        @Test
        public void test_4_deleteProduct() {
            String url = Config.getProperty("baseUrl") + "/api/myaccount/products/" + product_id;

            Response response = RestAssured.given().auth().oauth2(getToken()).delete(url);

            System.out.println(response.statusCode());
            response.prettyPeek();

        }
    }
