package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.Before;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.screenplay.questions.CountSecuritiesViaRest;
import de.blogspot.mszalbach.iss.screenplay.questions.FormErrors;
import de.blogspot.mszalbach.iss.screenplay.questions.SecurityCreateButton;
import de.blogspot.mszalbach.iss.screenplay.questions.SecurityList;
import de.blogspot.mszalbach.iss.screenplay.tasks.AddASecurity;
import de.blogspot.mszalbach.iss.screenplay.tasks.AddSecuritiesViaRest;
import de.blogspot.mszalbach.iss.screenplay.tasks.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
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



    @Und( "^er hat folgende Wertpapiere$" )
    public void create_securities( List<Security> securities )
        throws Throwable {
        theActorInTheSpotlight().attemptsTo( AddSecuritiesViaRest.called( securities ) );
    }



    @Wenn( "^er auf die Wertpapierliste geht$" )
    public void openSecurityListPage()
        throws Throwable {
        theActorInTheSpotlight().attemptsTo( Open.securityListPage() );
    }



    @Dann( "^sollte seine Werpapierliste mindestens (\\d+) Einträge haben$" )
    public void securityListShouldContainAtLeast( int count )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( SecurityList.count(), is( greaterThanOrEqualTo( count ) ) ) );
    }



    @Wenn( "^er ein Wertpapier mit folgenden Daten anlegt$" )
    public void createSecurity( List<Security> securities )
        throws Throwable {
        theActorInTheSpotlight().attemptsTo( AddASecurity.called( securities.get( 0 ) ) );
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
}
