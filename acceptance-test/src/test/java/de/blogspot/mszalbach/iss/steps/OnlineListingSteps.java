package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.Before;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Und;
import cucumber.api.java.de.Wenn;
import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.pageobjects.LoginPage;
import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import de.blogspot.mszalbach.iss.screenplay.questions.SecurityList;
import de.blogspot.mszalbach.iss.screenplay.tasks.AddSecuritiesViaRest;
import de.blogspot.mszalbach.iss.screenplay.tasks.Opens;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by foobarkilla on 22.10.16.
 */
public class OnlineListingSteps {

    SecurityListPage securityPage;

    LoginPage loginPage;

    @Steps
    SecurityRestSteps securityRest;



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
        theActorInTheSpotlight().attemptsTo( Opens.securityListPage() );
    }



    @Dann( "^sollte seine Werpapierliste mindestens (\\d+) Einträge haben$" )
    public void securityListShouldContainAtLeast( int count )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( SecurityList.count(), is( greaterThanOrEqualTo( count ) ) ) );
    }
}
