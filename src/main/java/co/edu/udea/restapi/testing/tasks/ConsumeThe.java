package co.edu.udea.restapi.testing.tasks;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.*;

import java.util.Map;

public class ConsumeThe implements Task {

    private String endpoint;
    private String httpMethod;
    private String body;
    private Map<String, String> queryParams;

    public ConsumeThe(String endpoint, String httpMethod) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
    }

    public ConsumeThe(String endpoint, String httpMethod, String body) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.body = body;
    }

    public ConsumeThe(String endpoint, String httpMethod, Map<String, String> queryParams) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.queryParams = queryParams;
    }

    public ConsumeThe(String endpoint, String httpMethod, String body, Map<String, String> queryParams) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.body = body;
        this.queryParams = queryParams;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        switch (httpMethod.toUpperCase()) {
            case "GET":
                if (queryParams != null) {
                    actor.attemptsTo(Get.resource(endpoint).with(request -> request.params(queryParams)));
                } else {
                    actor.attemptsTo(Get.resource(endpoint));
                }
                break;
            case "POST":
                actor.attemptsTo(Post.to(endpoint).with(request -> request.header("Content-Type", "application/json").body(body)));
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

    public static ConsumeThe withParams(String endpoint, String httpMethod, Map<String, String> queryParams) {
        return Tasks.instrumented(ConsumeThe.class, endpoint, httpMethod, queryParams);
    }

    public static ConsumeThe withBodyAndParams(String endpoint, String httpMethod, String body, Map<String, String> queryParams) {
        return Tasks.instrumented(ConsumeThe.class, endpoint, httpMethod, body, queryParams);
    }

    public static ConsumeThe withBody(String endpoint, String httpMethod, String body) {
        return Tasks.instrumented(ConsumeThe.class, endpoint, httpMethod, body);
    }

}