package de.blogspot.mszalbach.iss.steps;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import de.blogspot.mszalbach.iss.domain.Security;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.rest.SerenityRest.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by foobarkilla on 22.10.16.
 */
public class SecurityRestSteps {

    public SecurityRestSteps() {

        RestAssured.baseURI = System.getProperty("api.url", "http://localhost:8081/api");
    }

    @Step
    public void create_security(Security security) {
        given().contentType(ContentType.JSON).body(security).when().post("securities").then().statusCode(is(HttpStatus.SC_CREATED));
    }

    @Step
    public void create_securities(List<Security> securities) {
        for (Security security : securities) {
            create_security(security);
        }

    }

    public List<Security> findSecurity(Security security) {
        Security[] securities = given().contentType(ContentType.JSON).when().get("securities/search/findByIsin?isin=" + security.isin).jsonPath().getObject("_embedded.securities", Security[].class);

        return new ArrayList<>(Arrays.asList(securities));
    }
}
