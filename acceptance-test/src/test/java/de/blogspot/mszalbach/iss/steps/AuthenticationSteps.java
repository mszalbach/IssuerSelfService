package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.Before;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import de.blogspot.mszalbach.iss.screenplay.abilities.AccessRest;
import de.blogspot.mszalbach.iss.screenplay.abilities.Authenticate;
import de.blogspot.mszalbach.iss.screenplay.questions.LoggedInAs;
import de.blogspot.mszalbach.iss.screenplay.questions.LoginError;
import de.blogspot.mszalbach.iss.screenplay.questions.SignInButton;
import de.blogspot.mszalbach.iss.screenplay.tasks.LogIn;
import de.blogspot.mszalbach.iss.screenplay.tasks.LogOut;
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



    @Angenommen( "^(\\w+) will sich anmelden$" )
    public void userWantsToLogin( String user )
        throws Throwable {
        theActorCalled( user ).wasAbleTo( Open.loginPage() );
    }



    @Angenommen( "^(\\w+) ist angemeldet mit Password \"([^\"]*)\"$" )
    public void loginUserWithPassword( String user, String password )
        throws Throwable {
        userWantsToLogin( user );
        loginAsUserWithPassword( user, password );
        shouldBeLoggedInAs( user );
    }



    @Wenn( "^(\\w+) sich einloggt mit Passwort \"([^\"]*)\"$" )
    public void loginAsUserWithPassword( String user, String password )
        throws Throwable {
        theActorCalled( user ).can( Authenticate.with( user, password ) ).attemptsTo( LogIn.withCredentials() );
        theActorCalled( user ).can( AccessRest.viaApiProperty() );
    }



    @Wenn( "^(?:er|sie) sich ausloggt$" )
    public void logout()
        throws Throwable {
        theActorInTheSpotlight().attemptsTo( LogOut.now() );
    }



    @Dann( "^sollte der Login fehlschlagen mit \"([^\"]*)\"$" )
    public void checkForErrorMessage( String errorMessage )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( LoginError.text(), is( errorMessage ) ) );
    }



    @Dann( "^sollte (?:er|sie) eingelogt sein als (\\w+)$" )
    public void shouldBeLoggedInAs( String username )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( LoggedInAs.username(), is( username ) ) );
    }



    @Dann( "^sollte (?:er|sie) den \"([^\"]*)\" Knopf sehen$" )
    public void checkForSignInButton( String buttonText )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( SignInButton.text(), is( buttonText ) ) );
    }
}
