package com.github.felipemantoan.user_api.cucumber.definitions;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.github.felipemantoan.user_api.RandomCpfUtil;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.request.CreateUserRequestDTO;
import com.github.felipemantoan.user_api.infrastructure.adapter.in.http.dto.response.UserResponseDTO;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
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

    private Map<String, UserResponseDTO> storage;

    @Before
    public void initialize() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
        RestAssured.basePath = "/api/v1/users";
        storage = new HashMap<String, UserResponseDTO>();
    }

    @Given("I created a new user")
    public void i_created_a_new_user() {

        Random random = new Random();

        // Deixar este trecho dinamico
        CreateUserRequestDTO requestDTO = new CreateUserRequestDTO(
            "Walter White",
            RandomCpfUtil.cpf(),
            MessageFormat.format("luis.test.{0}@infityWar.com", random.nextInt(100)),
            "19988887333"
        );

        RequestSpecification request = SerenityRest.given().contentType(ContentType.JSON);
        Response response = request.body(requestDTO).when().post();
        
        response.then().assertThat().statusCode(201);
        
        UserResponseDTO responseDTO = response.getBody().as(UserResponseDTO.class);
        
        Assertions.assertNotNull(responseDTO.id());
        Assertions.assertNotNull(responseDTO.createdAt());
        Assertions.assertNotNull(responseDTO.updatedAt());

        Assertions.assertEquals(requestDTO.name(), responseDTO.name());
        Assertions.assertEquals(requestDTO.cpf(), responseDTO.cpf());
        Assertions.assertEquals(requestDTO.email(), responseDTO.email());
        Assertions.assertEquals(requestDTO.phoneNumber(), responseDTO.phoneNumber());
        
        latestUserId = responseDTO.id();

        storage.put(latestUserId, responseDTO);
    }

    @When("I see a user created")
    public void i_see_a_user_created() {
        Assertions.assertTrue(storage.containsKey(latestUserId));
    }

    @Then("I should be cpf filled")
    public void i_should_be_cpf_filled() {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
    }


}
