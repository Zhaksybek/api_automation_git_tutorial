package day_4_seril_desial_object_mapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runners.MethodSorters;
import utilities.CashwiseAuthorization;
import utilities.Config;
import utilities.DataGenerator;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SellerControllerPractice {

    String token = CashwiseAuthorization.getToken();


    /**
     * This test used by DataGenerator.sellerGenerator() class
     */
    @Test
    public void createSeller(){

        String url = Config.getProperty("baseUrl") + "/api/myaccount/sellers" ;

        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body( DataGenerator.sellerGenerator() )
                .post(url);

        response.prettyPrint();


    }


}
