package com.github.felipemantoan.cucumber.definitions;

import org.springframework.boot.test.context.SpringBootTest;

import com.github.felipemantoan.user_api.UserApiApplication;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = {
        UserApiApplication.class
    }
)
public class DefaultDefinition {
    
    @Given("I created a new user")
    public void i_created_a_new_user() {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
    }
    @When("I see a user created")
    public void i_see_a_user_created() {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
    }
    @Then("I should be cpf filled")
    public void i_should_be_cpf_filled() {
        // Write code here that turns the phrase above into concrete actions
        // throw new io.cucumber.java.PendingException();
    }


}
