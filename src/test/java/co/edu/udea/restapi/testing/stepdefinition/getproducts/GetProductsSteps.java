package co.edu.udea.restapi.testing.stepdefinition.getproducts;

import co.edu.udea.restapi.testing.questions.ErrorMessage;
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
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

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
        Map<String, String> params = new HashMap<>();
        params.put(arg1, arg2);
        params.put(arg3, arg4);
        user.attemptsTo(ConsumeThe.withParams(arg0, "GET", params));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int arg0) {
        user.should(seeThat("The response code is " + arg0, StatusCode.status(), Matchers.equalTo(arg0)));
    }

    @And("the response body should contain a list of products")
    public void theResponseBodyShouldContainAListOfProducts() {
        user.should(seeThatResponse("The response body should contain a list of products", response -> response.body("data", Matchers.notNullValue())));
    }

    @And("the response body should contain an error message {string}")
    public void theResponseBodyShouldContainAnErrorMessage(String arg0) {
        user.should(seeThat("The response body should contain an error message", ErrorMessage.withExpectedMessage(arg0), Matchers.equalTo(arg0)));
    }
}
