package utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.Getter;
import org.junit.Assert;
import pojo.CustomResponse;
import pojo.RequestBody;

import java.util.Map;

import static utilities.CashwiseAuthorization.getToken;



/** Day_5 APIRunner (Description about this class)
 * APIRunner class contains custom methods for CRUD commands
 * With help of this class we can focus on test logic, instead of automation
 * script
 */
public class APIRunner {

    //
    private static CustomResponse customResponse;

    private static CustomResponse[] customResponseArray;



 // This is for regular
  /* public static CustomResponse runGET(String path) {
     // baseUrl  + path ;
     // We have to hit GET request
     String url = Config.getProperty("baseUrl") + path;
     // getToken();

       Response response = RestAssured.given()
               .auth().oauth2( getToken() )
               .get(  url );

       ObjectMapper mapper = new ObjectMapper();

      // CustomResponse customResponse = mapper.readValue( response.asString(), CustomResponse.class );
           try {
               customResponse = mapper.readValue( response.asString(), CustomResponse.class );
           } catch (JsonProcessingException e) {
               System.out.println( " This is list response ");

           }

       return customResponse;
   }

   */

    /**
     * Executes a GET request to the specified path and returns a CustomResponse object.
     * @param path The endpoint path to append to the base URL.
     * @return CustomResponse object containing the response details.
     */
    // This is for regular modified version for
    public static CustomResponse runGET(String path)  {
        // baseUrl  + path ;
        // We have to hit GET request
        String url = Config.getProperty("baseUrl") + path;
        // getToken();

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .get(  url );

        ObjectMapper mapper = new ObjectMapper();

        // CustomResponse customResponse = mapper.readValue( response.asString(), CustomResponse.class );
        try {
            customResponse = mapper.readValue( response.asString(), CustomResponse.class );
            customResponse.setResponseBody( response.asString() );

        } catch (JsonProcessingException e) {
                // If we have list of response (Ex: list of banks)
                System.out.println( " This is list response ");
                try {
                    customResponseArray = mapper.readValue(response.asString(), CustomResponse[].class);
                } catch (JsonProcessingException ex) {
                    throw new RuntimeException(ex);
                }

        }

        return customResponse;
    }


    public static CustomResponse runGET( String path, Map<String ,Object> param )  {
        // baseUrl  + path ;
        // We have to hit GET request
        String url = Config.getProperty("baseUrl") + path;
        // getToken();

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .params(param)
                .get(  url );

        ObjectMapper mapper = new ObjectMapper();

        // CustomResponse customResponse = mapper.readValue( response.asString(), CustomResponse.class );
        try {
            customResponse = mapper.readValue( response.asString(), CustomResponse.class );
            customResponse.setResponseBody( response.asString() );

        } catch (JsonProcessingException e) {
            // If we have list of response (Ex: list of banks)
            System.out.println( " This is list response ");
            try {
                customResponseArray = mapper.readValue(response.asString(), CustomResponse[].class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }

        return customResponse;
    }

    public static CustomResponse runPOST(String path, RequestBody requestBody)  {
        // baseUrl  + path ;
        // We have to hit GET request
        String url = Config.getProperty("baseUrl") + path;
        // getToken();

        Response response = RestAssured.given()
                .auth().oauth2( getToken() )
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(  url );

        System.out.println("Current status code: "+response.statusCode());

        ObjectMapper mapper = new ObjectMapper();

        // CustomResponse customResponse = mapper.readValue( response.asString(), CustomResponse.class );
        try {
            customResponse = mapper.readValue( response.asString(), CustomResponse.class );
            customResponse.setResponseBody( response.asString() );

        } catch (JsonProcessingException e) {
            // If we have list of response (Ex: list of banks)
            System.out.println( " This is list response ");
            try {
                customResponseArray = mapper.readValue(response.asString(), CustomResponse[].class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }

        return customResponse;
    }

    public static CustomResponse runDELETE(String path) {
        // baseUrl  + path ;
        // We have to hit GET request
        String url = Config.getProperty("baseUrl") + path;
        // getToken();

        Response response = RestAssured.given()
                .auth().oauth2(getToken())
                .delete(url);

        System.out.println("Status code: "+response.statusCode());
        ObjectMapper mapper = new ObjectMapper();

        // CustomResponse customResponse = mapper.readValue( response.asString(), CustomResponse.class );
        try {
            customResponse = mapper.readValue( response.asString(), CustomResponse.class );
            customResponse.setResponseBody( response.asString() );

        } catch (JsonProcessingException e) {
            // If we have list of response (Ex: list of banks)
            System.out.println( " This is list response ");
            try {
                customResponseArray = mapper.readValue(response.asString(), CustomResponse[].class);
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }

        }

        return customResponse;
    }










    public static CustomResponse getCustomResponse() {
        return customResponse;
    }

    public static CustomResponse[] getcustomResponseArray() {
        return customResponseArray;
    }
}
