package co.edu.udea.restapi.testing.tasks;


import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.rest.interactions.*;

import java.util.Map;

public class ConsumeThe implements Task {

    private final String endpoint;
    private final String httpMethod;
    private Map<String, Object> body;
    private Map<String, String> queryParams;

    public ConsumeThe(String endpoint, String httpMethod) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
    }

    public ConsumeThe(String endpoint, String httpMethod, Map<String, String> queryParams) {
        this.endpoint = endpoint;
        this.httpMethod = httpMethod;
        this.queryParams = queryParams;
    }

    public ConsumeThe(String endpoint, String httpMethod, Map<String, Object> body, Map<String, String> queryParams) {
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
                actor.attemptsTo(
                        Post.to(endpoint)
                                .with(request -> request
                                        .contentType(ContentType.JSON)
                                        .body(body)
                                        .relaxedHTTPSValidation()
                                )
                );
                break;
            case "PUT":
                actor.attemptsTo(Put.to(endpoint).with(request -> request.contentType(ContentType.JSON).body(body)));
                break;
            case "DELETE":
                actor.attemptsTo(Delete.from(endpoint).with(request -> request.contentType(ContentType.JSON)));
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

    public static ConsumeThe withBody(String endpoint, String httpMethod, Map<String, Object> body) {
        return Tasks.instrumented(ConsumeThe.class, endpoint, httpMethod, body, null);
    }

}