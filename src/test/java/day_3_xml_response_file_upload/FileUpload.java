package day_3_xml_response_file_upload;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.CashwiseAuthorization;

import java.io.File;

public class FileUpload {

    @Test
    public void fileUpload(){
        String url = "https://backend.cashwise.us/api/myaccount/file/v2";
        String filePath ="DataToUpload.json"; // write file path
        String token = CashwiseAuthorization.getToken();
        File file = new File(filePath);

        Response response = RestAssured.given()
                .auth().oauth2( token )
                .multiPart("file",file,"multiple/form-data")
                .post(url);

        System.out.println( response.statusCode());
        response.prettyPrint();

        String fileName =response.jsonPath().getString("fileName");

    }

}
