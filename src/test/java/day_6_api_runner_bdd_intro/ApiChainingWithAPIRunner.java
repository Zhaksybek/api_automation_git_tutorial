package day_6_api_runner_bdd_intro;

import com.github.javafaker.Faker;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import pojo.RequestBody;
import utilities.APIRunner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiChainingWithAPIRunner {
    Faker faker = new Faker();
    static String bankID="";

    @Test
    public void test_1_createNewBankAccount(){
    // https://backend.cashwise.us /api/myaccount/bankaccount
        String path = "/api/myaccount/bankaccount";

        RequestBody requestBody = new RequestBody();
        requestBody.setType_of_pay("CASH");
        requestBody.setBank_account_name(faker.company().name());
        requestBody.setDescription(faker.commerce().department() + " company");
        requestBody.setBalance(faker.number().numberBetween(200, 15000));

        APIRunner.runPOST(path, requestBody);
        bankID = APIRunner.getCustomResponse().getId();
        System.out.println("Bank ID: "+bankID);


    }

    @Test
    public void test_2_getSingleBankAccount(){
        // https://backend.cashwise.us  /api/myaccount/bankaccount/  1208
        String path = "/api/myaccount/bankaccount/"+bankID;

        APIRunner.runGET(path);
        System.out.println(   APIRunner.getCustomResponse().getId() );
        System.out.println(   APIRunner.getCustomResponse().getBank_account_name() );
        System.out.println(   APIRunner.getCustomResponse().getBalance() );


    }

    @Test
    public void test_3_getListOfBankAccounts(){
        String path = "/api/myaccount/bankaccount/"+bankID;
        APIRunner.runDELETE(path);


    }



    @Test
    public void test_4_deleteBankAccount(){
        String path = "/api/myaccount/bankaccount/"+bankID;
        APIRunner.runDELETE(path);


    }





}
