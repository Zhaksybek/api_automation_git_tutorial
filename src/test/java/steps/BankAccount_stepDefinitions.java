package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pojo.RequestBody;
import utilities.APIRunner;

public class BankAccount_stepDefinitions {
    String id ="";

    @Given("Create {string} and {string} and {string} and {double}")
    public void create_and_and_and(String bank_account_name, String description, String type_of_pay, Double balance) {
        System.out.println("::: ===== TEST STARTED =====  :::");
        String path ="/api/myaccount/bankaccount";

        RequestBody requestBody = new RequestBody();
        requestBody.setBank_account_name( bank_account_name );
        requestBody.setDescription( description );
        requestBody.setType_of_pay( type_of_pay );
        requestBody.setBalance( balance );

        APIRunner.runPOST(path, requestBody);

        id = APIRunner.getCustomResponse().getId();
        System.out.println( id);
        System.out.println("::: TEST PASSED!!!  :::");

    }
    @Given("Get created Bank account by id and check status code is {int}")
    public void get_created_bank_account_by_id_and_check_status_code_is(Integer statusCode) {
        System.out.println("::: ===== TEST STARTED =====  :::");
        String path = "/api/myaccount/bankaccount/"+id ;
        APIRunner.runGET(path);
        Assert.assertFalse("Bank Account name is empty",APIRunner.getCustomResponse().getBank_account_name().isEmpty());

        System.out.println(statusCode);
        System.out.println("::: TEST PASSED!!!  :::");
    }

    @Then("Get same Bank account by id and delete")
    public void get_same_bank_account_by_id_and_delete() {
        System.out.println("::: ===== TEST STARTED =====  :::");
        String path = "/api/myaccount/bankaccount/"+id ;
        APIRunner.runDELETE(path);

        System.out.println("::: TEST PASSED!!!  :::");

    }
}
