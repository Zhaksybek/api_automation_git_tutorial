package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pojo.RequestBody;
import utilities.APIRunner;
import utilities.DataGenerator;

import java.util.HashMap;
import java.util.Map;

public class Sellers_stepDefinitions {
    static String seller_id ="";
    static String sellerName="";
    static String phoneNumber="";

    @Given("user hits get all sellers api with {string} and parameters: isArchived {string}, page {int}, size {int}")
    public void user_hits_get_all_sellers_api_with_and_parameters_is_archived_page_size(String path, String isArchived, Integer page, Integer size) {
        System.out.println(path);
        System.out.println(isArchived);
        System.out.println(page);
        System.out.println(size);

        System.out.println("=========");
        String pathParam = path;
        Map<String, Object> params = new HashMap<>();
        params.put("isArchived", false);
        params.put("page",page);
        params.put("size",size);
        APIRunner.runGET(pathParam,params);
        System.out.println("Test passed");
    }

    @Then("user gets all sellers phone number")
    public void user_gets_all_sellers_phone_number() {
        int size =  APIRunner.getCustomResponse().getResponses().length;
        for (int i=0; i<size; i++){
            System.out.println(APIRunner.getCustomResponse().getResponses()[i].getPhone_number());
            Assert.assertNotNull( "Phone number is empty",APIRunner.getCustomResponse().getResponses()[i].getPhone_number()  );
        }

        System.out.println("Test passed");
    }



    @Given("user created new Seller with Faker class and api path {string}")
    public void userCreatedNewSellerWithFakerClassAndApiPath(String path) {
        APIRunner.runPOST( path, DataGenerator.sellerGenerator() );
        seller_id = APIRunner.getCustomResponse().getSeller_name();
        sellerName = APIRunner.getCustomResponse().getSeller_name();
        phoneNumber= APIRunner.getCustomResponse().getPhone_number();
    }


    @When("get same seller by api path {string} id and validate name and phone number are matching")
    public void getSameSellerByApiPathIdAndValidateNameAndPhoneNumberAreMatching(String path) {
    String pathApi = path+"/"+seller_id;
    APIRunner.runGET(pathApi);


    }

    @Then("delete same seller by api path {string} and id")
    public void deleteSameSellerByApiPathAndId(String path) {
    }
}
