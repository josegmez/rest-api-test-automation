package co.edu.udea.restapi.testing.stepdefinition;

import co.edu.udea.restapi.testing.questions.StatusCode;
import co.edu.udea.restapi.testing.tasks.ConnectTo;
import co.edu.udea.restapi.testing.tasks.ConsumeThe;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import org.hamcrest.Matchers;

public class GetProductByIdSteps {

    Actor user = Actor.named("tae user");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("tae user");
    }

    @Given("the API base URL is {string}")
    public void theAPIBaseURLIs(String baseURL) {
        user.attemptsTo(ConnectTo.theService());
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        user.attemptsTo(ConsumeThe.service(endpoint, "GET"));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        user.should(seeThat("The response code is " + statusCode, StatusCode.status(), Matchers.equalTo(statusCode)));
    }

    @And("the response body should contain the product with ID {string}")
    public void theResponseBodyShouldContainTheProductWithID(String productId) {
        user.should(seeThatResponse("The response body should contain the product with ID " + productId,
                response -> response.body("id", Matchers.equalTo(Integer.parseInt(productId)))));
    }

    @And("the response body should have a field {string} with value {string}")
    public void theResponseBodyShouldHaveAFieldWithValue(String field, String value) {
        user.should(seeThatResponse("The response body should have field " + field + " with value " + value,
                response -> response.body(field, Matchers.equalTo(Integer.parseInt(value)))));
    }

    @And("the response body should contain an error name {string}")
    public void theResponseBodyShouldContainAnErrorName(String errorName) {
        user.should(seeThatResponse("The response body should contain an error name " + errorName,
                response -> response.body("error", Matchers.equalTo(errorName))));
    }
}
