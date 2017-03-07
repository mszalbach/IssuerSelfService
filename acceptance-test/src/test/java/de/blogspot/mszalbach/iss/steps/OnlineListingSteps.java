package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.Before;
import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.screenplay.questions.CountSecuritiesViaRest;
import de.blogspot.mszalbach.iss.screenplay.questions.FormErrors;
import de.blogspot.mszalbach.iss.screenplay.questions.SecurityCreateButton;
import de.blogspot.mszalbach.iss.screenplay.questions.SecurityList;
import de.blogspot.mszalbach.iss.screenplay.tasks.AddASecurity;
import de.blogspot.mszalbach.iss.screenplay.tasks.AddSecuritiesViaRest;
import de.blogspot.mszalbach.iss.screenplay.tasks.DeleteASecurity;
import de.blogspot.mszalbach.iss.screenplay.tasks.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * BDD sentences needed for Listing Tests
 */
public class OnlineListingSteps {


    @Before
    public void set_the_stage() {
        OnStage.setTheStage( new OnlineCast() );
    }



    @Angenommen( "^(\\w+) hat folgende[s]* Wertpapier[e]*$" )
    public void createSecurities( String actor, List<Security> securities )
        throws Throwable {
        theActorCalled( actor ).attemptsTo( AddSecuritiesViaRest.called( securities ) );
    }



    @Wenn( "^(?:er|sie) auf die Wertpapierliste geht$" )
    public void openSecurityListPage()
        throws Throwable {
        theActorInTheSpotlight().attemptsTo( Open.securityListPage() );
    }



    @Wenn( "^(?:er|sie) ein Wertpapier mit folgenden Daten anlegt$" )
    public void createSecurity( List<Security> securities )
        throws Throwable {
        theActorInTheSpotlight().attemptsTo( AddASecurity.called( securities.get( 0 ) ) );
    }



    @Wenn( "^(?:er|sie) das Wertpapier \"([^\"]*)\" löscht$" )
    public void deleteSecurityWithIsin( String isin )
        throws Throwable {
        theActorInTheSpotlight().attemptsTo( DeleteASecurity.called( isin ) );
    }



    @Dann( "^sollte seine Wertpapierliste mindestens (\\d+) Einträge haben$" )
    public void securityListShouldContainAtLeast( int count )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( SecurityList.count(), is( greaterThanOrEqualTo( count ) ) ) );
    }



    @Dann( "^sollte ein Wertpapier mit folgenden Daten exisitieren$" )
    public void checkIfSecurityExists( List<Security> securities )
        throws Throwable {
        theActorInTheSpotlight().should(
            seeThat( CountSecuritiesViaRest.byIsin( securities.get( 0 ).getIsin() ),
                     is( greaterThanOrEqualTo( 1 ) ) ) );
    }



    @Dann( "^sollte das Anlegen fehlschlagen mit folgenden Fehlern:$" )
    public void checkForErrorsInSecurityCreateDialog( List<String> errors )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( SecurityCreateButton.isEnabled(), is( false ) ) );
        theActorInTheSpotlight()
            .should( seeThat( FormErrors.displayed(), is( errors ) ) );
    }



    @Dann( "^gibt es kein Wertpapier \"([^\"]*)\" mehr$" )
    public void checkIfIsinDidNotExistOnServer( String isin )
        throws Throwable {
        seeThat( CountSecuritiesViaRest.byIsin( isin ),
                 is( 0 ) );
    }
}
