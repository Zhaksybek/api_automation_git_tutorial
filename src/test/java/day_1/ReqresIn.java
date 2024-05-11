package day_1;

import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.HashMap;
import java.util.Map;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReqresIn {


   @Test
   public void test_0_getSingleUser(){ // Hello my friend and dostar

        String url = "https://reqres.in/api/users/1";

        Response response = RestAssured.get(  url );

        response.prettyPrint();

        String firstName = response.jsonPath().getString("data.first_name");
        String lastName = response.jsonPath().getString("data.last_name");

        System.out.println(firstName);
        System.out.println(lastName);
   }


    @Test
    public void test_1_getListOfUsers(){
        String url = "https://reqres.in/api/users?page=2";

        Response response = RestAssured.get(  url );

        response.prettyPrint();
    }

    @Test
    public void test_2_createUser(){
        String url = "https://reqres.in/api/users?page=2";

        String body = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        Response response = RestAssured.given().body(body).post(url);
        response.prettyPrint();
    }


    @Test
    public void test_3_createUserMap(){
        String url = "https://reqres.in/api/users?page=2";


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Dayana");
        requestBody.put("job", "Developer");

        Response response = RestAssured.given().body(requestBody).post(url);
        response.prettyPrint();
    }

    @Test
    public void test_4_updateUserMap() {
        String url = "https://reqres.in/api/users/2";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Kiara");
        requestBody.put("job", "Cat");

        Response response = RestAssured.given().body(requestBody).put(url);
        response.prettyPrint();
   }

    @Test
    public void test_5_deleteUser() {
        String url = "https://reqres.in/api/users/2";

        Response response = RestAssured.given().delete(url);
        response.prettyPrint();

        System.out.println(response.statusCode());

    }

    @Test
    public void test_6_delete_user() {
        String url = "https://reqres.in/api/users/2";
        Response response = RestAssured.given().delete(url);
        response.prettyPrint();
        System.out.println(response.statusCode());
    }




}
