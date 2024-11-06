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

public class GetProductsSteps {

    Actor user = Actor.named("tae user");

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("tae user");
    }

    @Given("the API base URL is {string}")
    public void theAPIBaseURLIs(String arg0) {
        user.attemptsTo(ConnectTo.theService());
    }

    @When("I send a GET request to {string} with query parameters {string} set to {string} and {string} set to {string}")
    public void iSendAGETRequestToWithQueryParametersSetToAndSetTo(String arg0, String arg1, String arg2, String arg3, String arg4) {
        user.attemptsTo(ConsumeThe.service(arg0, "GET"));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int arg0) {
        user.should(seeThat("The response code is 200", StatusCode.status(), Matchers.equalTo(arg0)));
    }

    @And("the response body should contain a list of products")
    public void theResponseBodyShouldContainAListOfProducts() {
        user.should(seeThatResponse("The response body should contain a list of products", response -> response.body("data", Matchers.notNullValue())));
    }

    @And("the response body should contain an error message {string}")
    public void theResponseBodyShouldContainAnErrorMessage(String arg0) {
        user.should(seeThatResponse("The response body should contain an error message", response -> response.body("error", Matchers.equalTo(arg0))));
    }
}