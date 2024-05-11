package utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class CashwiseAuthorization {

    private static String token;

    /*
    public static String  getToken(){
      //  String url = "https://backend.cashwise.us/api/myaccount/auth/login";

        String url = Config.getProperty("basUrl") + "/api/myaccount/auth/login";

        Map<String, Object> requestBody = new HashMap<>();
//        requestBody.put("email" ,"codewise_batch4_api@gmail.com" );

//      requestBody.put("password" ,"123456" );
        requestBody.put("email" , Config.getProperty("email") );
        requestBody.put("password" ,Config.getProperty("password") );

        Response response = RestAssured
                .given().contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);
        //response.prettyPrint();
        token = response.jsonPath().getString("jwt_token");

       return token;

    }

     */


    /**
     * Token Generator with RequestBody
     * @return
     */

    public static String  getToken(){
        String url = Config.getProperty("baseUrl") + "/api/myaccount/auth/login";


        RequestBody requestBody = new RequestBody();
        requestBody.setEmail(Config.getProperty("email"));
        requestBody.setPassword(Config.getProperty("password"));


        Response response = RestAssured
                .given().contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);
        // response.prettyPrint();
        token = response.jsonPath().getString("jwt_token");


        return token ;

    }



}
