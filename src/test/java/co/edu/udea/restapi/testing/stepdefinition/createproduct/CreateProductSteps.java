package co.edu.udea.restapi.testing.stepdefinition.createproduct;

import co.edu.udea.restapi.testing.questions.ErrorMessage;
import co.edu.udea.restapi.testing.questions.StatusCode;
import co.edu.udea.restapi.testing.tasks.ConnectTo;
import co.edu.udea.restapi.testing.tasks.ConsumeThe;
import com.google.gson.Gson;
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
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CreateProductSteps {

    private Map<String, Object> requestBody;

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

    @And("^the request body contains the following data$")
    public void theRequestBodyContainsTheFollowingData(Map<String, Object> requestBody) {
        Gson gson = new Gson();
        List<String> images = gson.fromJson(requestBody.get("images").toString(), List.class);
        Map<String, Object> requestBodyCopy = new HashMap<>(requestBody);
        requestBodyCopy.put("images", images);
        this.requestBody = requestBodyCopy;
    }

    @When("I send a POST request to {string}")
    public void iSendAPOSTRequestTo(String arg0) {
        user.attemptsTo(ConsumeThe.withBody(arg0, "POST", this.requestBody));
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int arg0) {
        user.should(seeThat("The response code is " + arg0, StatusCode.status(), Matchers.equalTo(arg0)));
    }

    @And("the response body should have a field {string} with value {string}")
    public void theResponseBodyShouldHaveAFieldWithStringValue(String field, String value) {
        user.should(seeThatResponse("the response body should have a field" + field, response -> response.body(field, Matchers.equalTo(value))));
    }

    @And("the response body should have a field {string} with value {int}")
    public void theResponseBodyShouldHaveAFieldWithIntValue(String field, int value) {
        user.should(seeThatResponse("the response body should have a field" + field, response -> response.body(field, Matchers.equalTo(value))));
    }

    @And("the response body should contain an error message {string}")
    public void theResponseBodyShouldContainAnErrorMessage(String arg0) {
        user.should(seeThat("The response body should contain an error message", ErrorMessage.withExpectedMessage(arg0), Matchers.equalTo(arg0)));
    }
}
