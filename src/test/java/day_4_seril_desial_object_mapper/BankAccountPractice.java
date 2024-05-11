package day_4_seril_desial_object_mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.BankAccountPOJO;
import pojo.CustomResponse;
import pojo.RequestBody;
import utilities.CashwiseAuthorization;
import utilities.Config;

public class BankAccountPractice {


    @Test
    public void test_1_getBankAccount() throws JsonProcessingException {
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjb2Rld2lzZV9iYXRjaDRfYXBpQGdtYWlsLmNvbSIsImV4cCI6MTcwNjQwNTUyOSwiaWF0IjoxNzA1ODAwNzI5fQ.YidPtkHfXioUt2nh3b87IUkykEJQbHsNbrLLEFt9MbXT-5Kf2U33P6YuGdd6Bzu2h1pvIHECFNSDrLexdRqeUw";
        String url ="https://backend.cashwise.us/api/myaccount/bankaccount/958";

        Response response = RestAssured
                .given().auth().oauth2(token)
                .get(url);

        ObjectMapper mapper = new ObjectMapper();
        BankAccountPOJO bankAccount = mapper.readValue(response.asString(), BankAccountPOJO.class );

        System.out.println(bankAccount.getBank_account_name());
    }

    @Test
    public void test_2_createBankAccount() throws JsonProcessingException {
        Faker faker = new Faker();
        String token = CashwiseAuthorization.getToken();
        String url = Config.getProperty("baseUrl")+ "/api/myaccount/bankaccount";

        String bankAccountName = faker.company().name();
        String description = faker.company().logo() + " Financial company";
        double randomBalance = faker.number().numberBetween(200, 15000);



        RequestBody requestBody = new RequestBody();

        requestBody.setType_of_pay("BANK");
        requestBody.setBank_account_name( bankAccountName );
        requestBody.setDescription(  description );
        requestBody.setBalance(randomBalance);



        Response response = RestAssured.given()
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(url);

        response.prettyPrint();

    }




}
