package com.github.felipemantoan.user_api.cucumber.definitions;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.github.felipemantoan.user_api.RandomCpfUtil;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request.CreateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request.UpdateUserRequestDTO;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.rest.SerenityRest;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Log4j2
public class DefaultDefinition {
    
    @LocalServerPort
    private int port;

    private String latestUserId;

    private Map<String, Map<String, String>> storage;

    @Before
    public void initialize() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/users";
        storage = new HashMap<String, Map<String, String>>();
    }

    private void i_created_a_new_user_with_map(Map<String, String> map) {
        Random random = new Random();

        String name = map.getOrDefault("name", "Walter White");
        String cpf = map.getOrDefault("cpf", RandomCpfUtil.cpf());
        String email = map.getOrDefault("email", MessageFormat.format(
                "luis.{0}.test.{1}@infityWar.com", 
                random.nextInt(100), 
                random.nextInt(1000)
            )
        );

        String phoneNumber = map.getOrDefault("phone_number", "19988887333");

        CreateUserRequestDTO requestDTO = new CreateUserRequestDTO(
            name,
            cpf,
            email,
            phoneNumber
        );

        RequestSpecification request = SerenityRest.given().contentType(ContentType.JSON);
        Response response = request.body(requestDTO).when().post();
        
        response.then().assertThat().statusCode(201);
        
        Map<String, String> user = response.getBody().as(new TypeRef<Map<String, String>>() { });
        
        Assertions.assertNotNull(user.get("id"));
        Assertions.assertNotNull(user.get("created_at"));
        Assertions.assertNotNull(user.get("updated_at"));

        Assertions.assertEquals(requestDTO.name(), user.get("name"));
        Assertions.assertEquals(requestDTO.cpf(), user.get("cpf"));
        Assertions.assertEquals(requestDTO.email(), user.get("email"));
        Assertions.assertEquals(requestDTO.phoneNumber(), user.get("phone_number"));
        
        latestUserId = user.get("id");

        storage.put(latestUserId, user);
    }

    @Given("I created a new user with:")
    public void i_created_a_new_user_with(DataTable datatable) {
        
        List<Map<String, String>> table = datatable.asMaps(String.class, String.class);
        
        table.forEach(userMap -> i_created_a_new_user_with_map(userMap));
    }
    
    @Given("I created a new user")
    public void i_created_a_new_user() {
        i_created_a_new_user_with_map(Map.of());
    }

    @When("I see a user created")
    @When("I see a user updated")
    public void i_see_a_user_created_or_updated() {
        Assertions.assertTrue(storage.containsKey(latestUserId));
    }

    @Then("I should be user field {string} filled with {string}")
    public void i_should_be_user_field_filled_with_value(String field, String value) {
        Map<String, String> user = storage.get(latestUserId);

        Assertions.assertTrue(user.containsKey(field));
        Assertions.assertEquals(value, user.get(field));
    }

    private void i_update_created_user_with_map(String userId, Map<String, String> map) {

        Map<String, String> storageUser = storage.get(userId);

        String name = map.getOrDefault("name", storageUser.get("name"));
        String email = map.getOrDefault("email", storageUser.get("email"));
        String phoneNumber = map.getOrDefault("phone_number", storageUser.get("phone_number"));

        UpdateUserRequestDTO requestDTO = new UpdateUserRequestDTO(name, email, phoneNumber);

        RequestSpecification request = SerenityRest.given().contentType(ContentType.JSON);
        Response response = request.body(requestDTO).when().put("/{id}", userId);

        response.then().assertThat().statusCode(200);

        Map<String, String> user = response.getBody().as(new TypeRef<Map<String, String>>() { });
        
        Assertions.assertNotNull(user.get("id"));
        Assertions.assertNotNull(user.get("created_at"));
        Assertions.assertNotEquals(storageUser.get("updated_at"), user.get("updated_at"));

        Assertions.assertEquals(requestDTO.name(), user.get("name"));
        Assertions.assertEquals(requestDTO.email(), user.get("email"));
        Assertions.assertEquals(requestDTO.phoneNumber(), user.get("phone_number"));
        
        latestUserId = user.get("id");

        storage.put(latestUserId, user);
    }

    @Given("I update latest created user with:")
    public void i_update_created_user_with(DataTable datatable) {
        List<Map<String, String>> table = datatable.asMaps(String.class, String.class);
        table.forEach(userMap -> i_update_created_user_with_map(latestUserId, userMap));
    }

}
