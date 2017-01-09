package de.blogspot.mszalbach.iss.screenplay.tasks;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.screenplay.abilities.Authenticate;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.apache.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by ms on 09.01.17.
 */
public class AddASecurityViaRest
    implements Task {

    private final Security security;



    public AddASecurityViaRest( Security security ) {
        this.security = security;
        RestAssured.baseURI = System.getProperty( "api.url", "http://localhost:8081/api" );
    }



    public static AddASecurityViaRest called( Security security ) {
        return instrumented( AddASecurityViaRest.class, security );
    }



    @Override
    @Step( "{0} legt ein Wertpapier via Rest an" )
    public <T extends Actor> void performAs( T actor ) {
        given().contentType( ContentType.JSON ).body( security ).auth()
               .basic( authenticated( actor ).username(), authenticated( actor ).password() ).when()
               .post( "securities" ).then().statusCode( is(
            HttpStatus.SC_CREATED ) );
    }



    private Authenticate authenticated( Actor actor ) {
        return Authenticate.as( actor );
    }
}
