package day_6_api_runner_bdd_intro;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import utilities.APIRunner;

public class NestedJsonObjectPayment {

    // Validate Nested Json Object
    @Test
    public void test_1_getNestedJSONObject() throws JsonProcessingException {
        // https://backend.cashwise.us /api/myaccount/payments/160
        //String path = Config.getProperty("baseUrl")+ "/api/myaccount/payments/204";
        String path = "/api/myaccount/payments/204";



        APIRunner.runGET(path);
        System.out.println( APIRunner.getCustomResponse().getCategory_response()[0].getCategory_id() );
        String categoryTitle = APIRunner.getCustomResponse().getCategory_response()[0].getCategory_title();
        String categoryDescription = APIRunner.getCustomResponse().getCategory_response()[0].getCategory_description();

        System.out.println(  categoryDescription  );
        System.out.println(categoryTitle);




    }


}
