package co.edu.udea.restapi.testing.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/create_product.feature",
        glue = "co.edu.udea.restapi.testing.stepdefinition",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)

public class CreateProduct {
}
