package co.edu.udea.restapi.testing.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import io.restassured.path.json.JsonPath;
import java.util.List;

public class ErrorMessage implements Question<String> {

    private final String expectedMessage;

    // Constructor para inicializar el mensaje esperado a verificar
    public ErrorMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    @Override
    public String answeredBy(Actor actor) {
        // Obtener la respuesta del cuerpo en formato JSON usando JsonPath
        JsonPath jsonPath = SerenityRest.lastResponse().jsonPath();

        // Verificar si el campo "message" es una lista (array)
        Object message = jsonPath.get("message");

        // Si "message" es una lista, recorrerla
        if (message instanceof List) {
            List<String> messageList = (List<String>) message;
            for (String msg : messageList) {
                if (msg.contains(expectedMessage)) {
                    return msg;
                }
            }
        }
        // Si "message" es un solo valor
        else if (message instanceof String) {
            String msg = (String) message;
            if (msg.contains(expectedMessage)) {
                return msg;
            }
        }

        return "Message not found";
    }

    // Método estático para crear una instancia de la clase con el mensaje esperado
    public static ErrorMessage withExpectedMessage(String message) {
        return new ErrorMessage(message);
    }
}
