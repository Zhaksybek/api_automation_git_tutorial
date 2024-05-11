package day_4_seril_desial_object_mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

public class CategoriesPractice {

    @Test
    public void getSingleCategories() throws JsonProcessingException {
        String pathID = "805";
        String url = "https://backend.cashwise.us/api/myaccount/categories/"+pathID;
        String token = CashwiseAuthorization.getToken();


        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);

        // response.prettyPrint();

        String jsonResponse = response.asString();
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse customResponse = mapper.readValue(jsonResponse, CustomResponse.class);

        System.out.println(customResponse.getCategory_id());
        System.out.println(customResponse.getCategory_title());


    }

    @Test
    public void getListOfCategories() throws JsonProcessingException {

        String url = "https://backend.cashwise.us/api/myaccount/categories";
        String token = CashwiseAuthorization.getToken();


        Response response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);

        //response.prettyPrint();

        String jsonResponse = response.asString();
        ObjectMapper mapper = new ObjectMapper();
        CustomResponse[] customResponse = mapper.readValue(jsonResponse, CustomResponse[].class);

       for (int i=0; i<customResponse.length; i++){
            System.out.println(customResponse[i].getCategory_title());
           Assert.assertNotNull(customResponse[i].getCategory_title());

       }

    }


    @Test
    public void createCategory() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl")+ "/api/myaccount/categories";
        String token = CashwiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();
        requestBody.setCategory_title("Furniture New");
        requestBody.setCategory_description("Furniture New");
        requestBody.setFlag(true);

          Response response = RestAssured.given()
                  .auth().oauth2(token)
                  .contentType(ContentType.JSON)
                  .body(requestBody)
                  .post(url);

          response.prettyPrint();


        // GET category
        url = Config.getProperty("baseUrl")+ "/api/myaccount/categories/853" ;

        response = RestAssured.given()
                .auth().oauth2(token)
                .get(url);


        response.prettyPrint();
        ObjectMapper mapper = new ObjectMapper();

        CustomResponse customResponse = mapper.readValue(response.asString(), CustomResponse.class);

        System.out.println("=====Custom Response=====================");
        System.out.println(customResponse.getCategory_id());
        System.out.println(customResponse.getCategory_title());
        System.out.println(customResponse.getCategory_description());


    }



    @Test
    public void deleteCategory() throws JsonProcessingException {
        String url = Config.getProperty("baseUrl") + "/api/myaccount/categories/860";
        String token = CashwiseAuthorization.getToken();

        RequestBody requestBody = new RequestBody();


        Response response = RestAssured.given()
                .auth().oauth2(token)
                .delete(url);

        response.prettyPrint();


    }








}
