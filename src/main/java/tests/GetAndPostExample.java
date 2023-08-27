package tests;
import jdk.jfr.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetAndPostExample {

    @Test
    public void testGet(){
        baseURI = "https://reqres.in/api";

        given().
                get("/users?page=2").
                then().statusCode(200).
                body("data[4].first_name",equalTo("George")).
                body("data.first_name",hasItems("George", "Rachel"));
    }

    @Test
    public void testPost(){
        Map<String, Object> map = new HashMap<>();
       /* map.put("name", "Samuel");
        map.put("job", "Teacher");
        System.out.println(map);*/

        JSONObject request = new JSONObject(map);

        request.put("name", "Samuel");
        request.put("job", "Teacher");

        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        given().
        header("Content-Type","application/json").
         //contentType(ContentType.JSON).
         body(request.toJSONString()).
                when().
                post("/users").
                then().
                statusCode(201).log().all();



    }
}
