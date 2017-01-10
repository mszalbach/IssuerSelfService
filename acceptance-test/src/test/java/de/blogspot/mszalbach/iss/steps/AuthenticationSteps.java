package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.Before;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Gegebensei;
import cucumber.api.java.de.Wenn;
import de.blogspot.mszalbach.iss.screenplay.abilities.AccessRest;
import de.blogspot.mszalbach.iss.screenplay.abilities.Authenticate;
import de.blogspot.mszalbach.iss.screenplay.questions.LoggedInAs;
import de.blogspot.mszalbach.iss.screenplay.questions.LoginError;
import de.blogspot.mszalbach.iss.screenplay.tasks.LogIn;
import de.blogspot.mszalbach.iss.screenplay.tasks.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by foobarkilla on 09.11.16.
 */
public class AuthenticationSteps {


    @Before
    public void set_the_stage() {
        OnStage.setTheStage( new OnlineCast() );
    }



    @Wenn( "^(\\w+) sich einloggt mit Passwort \"([^\"]*)\"$" )
    public void loginAsUserWithPassword( String user, String password )
        throws Throwable {
        theActorInTheSpotlight().can( Authenticate.with( user, password ) ).attemptsTo( LogIn.withCredentials() );
        theActorInTheSpotlight().can( AccessRest.viaApiProperty() );
    }



    @Dann( "^sollte der Login fehlschlagen mit \"([^\"]*)\"$" )
    public void checkForErrorMessage( String errorMessage )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( LoginError.text(), is( errorMessage ) ) );
    }



    @Dann( "^sollte er eingelogt sein als (\\w+)$" )
    public void shouldBeLoggedInAs( String username )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( LoggedInAs.username(), is( username ) ) );
    }



    @Gegebensei( "^(\\w+) will sich anmelden$" )
    public void userWantsToLogin( String user )
        throws Throwable {
        theActorCalled( user ).wasAbleTo( Open.loginPage() );
    }



    @Gegebensei( "^(\\w+) ist angemeldet mit Password \"([^\"]*)\"$" )
    public void loginUserWithPassword( String user, String password )
        throws Throwable {
        userWantsToLogin( user );
        loginAsUserWithPassword( user, password );
        shouldBeLoggedInAs( user );
    }
}
