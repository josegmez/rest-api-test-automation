package co.edu.udea.restapi.testing.tasks;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class ConnectTo implements Task {

    private EnvironmentVariables enviromentVariable = SystemEnvironmentVariables.createEnvironmentVariables();
    private String url_string;

    public ConnectTo() {
        url_string = enviromentVariable.optionalProperty("webdriver.base.url").orElse("https://api.escuelajs.co/api/v1");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.whoCan(CallAnApi.at(url_string));
    }

    public static ConnectTo theService() {
        return Tasks.instrumented(ConnectTo.class);
    }
}