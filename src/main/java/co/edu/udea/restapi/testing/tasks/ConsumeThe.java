package co.edu.udea.restapi.testing.tasks;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.*;

public class ConsumeThe implements Task {

    private String endpoint;
    private String httpMethod;

    public ConsumeThe(String endpoint, String httpMethod) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        switch (httpMethod.toUpperCase()) {
            case "GET":
                actor.attemptsTo(Get.resource(endpoint).with(request -> request.header("Content-Type", "application/json")));
                break;
            case "POST":
                actor.attemptsTo(Post.to(endpoint).with(request -> request.header("Content-Type", "application/json")));
                break;
            case "PUT":
                actor.attemptsTo(Put.to(endpoint).with(request -> request.header("Content-Type", "application/json")));
                break;
            case "DELETE":
                actor.attemptsTo(Delete.from(endpoint).with(request -> request.header("Content-Type", "application/json")));
                break;
            default:
                throw new IllegalArgumentException("Not supported method " + httpMethod);
        }
    }

    public static ConsumeThe service(String endpoint, String httpMethod) {
        return Tasks.instrumented(ConsumeThe.class, endpoint, httpMethod);
    }
}