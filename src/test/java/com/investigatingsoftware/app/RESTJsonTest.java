package com.investigatingsoftware.app;

import org.testng.annotations.*;
import org.testng.Assert;
import static io.restassured.RestAssured.*;
import com.google.gson.*;


public class RESTJsonTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void checkResponseCode()
    {
        System.out.println("JSON Test 1 running");
        get("https://api.github.com/users/phoughton/repos").then().assertThat().statusCode(200);

    }
    @Test
    public void checkJsonResponseLength()
    {
        System.out.println("JSON Test 2 running");
        JsonArray jsonArray = new JsonArray();

        jsonArray = get("https://api.github.com/users/phoughton/repos").as(JsonArray.class);
        System.out.println(jsonArray.size());
        assert(jsonArray.size()==30); 
    }

    @Test
    public void checkJsonResponseForCribbage()
    {
        Gson gson = new Gson();

        System.out.println("JSON Test 3 running");
        JsonArray jsonArray = new JsonArray();
        jsonArray = get("https://api.github.com/users/phoughton/repos").as(JsonArray.class);
        System.out.println(jsonArray.size());
        for (JsonElement el : jsonArray) {
            Repo repo = gson.fromJson(el, Repo.class);
            System.out.println(repo.name + " " + repo.full_name + " " + repo.owner.login);
            // System.out.println(el);
            Assert.assertEquals(repo.owner.login, "phoughton", "Repo owner did not match!");
        }
    }
}
