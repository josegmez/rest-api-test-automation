package co.edu.udea.restapi.testing.stepdefinition;

import co.edu.udea.restapi.testing.tasks.ConsumeThe;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import org.hamcrest.Matchers;

import java.util.Map;

public class CreateProductSteps {
    Actor user = Actor.named("tae user");
    Map<String, Object> requestBody;

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("tae user");
    }

    @And("^the request body contains the following data$")
    public void theRequestBodyContainsTheFollowingData(Map<String, Object> requestBody) {
        this.requestBody = requestBody;
    }

    @When("I send a POST request to {string}")
    public void iSendAPOSTRequestTo(String arg0) {
        ConsumeThe.withBody(arg0, "POST", this.requestBody);
    }

    @And("the response body should have a field {string} with value {string}")
    public void theResponseBodyShouldHaveAFieldWithValue(String field, String value) {
        user.should(seeThatResponse("the response body should have a field"+field, response -> response.body(field, Matchers.equalTo(value))));
    }
}
