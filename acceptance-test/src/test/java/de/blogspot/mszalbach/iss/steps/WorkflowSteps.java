package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Und;
import de.blogspot.mszalbach.iss.screenplay.questions.Actions;
import de.blogspot.mszalbach.iss.screenplay.questions.State;
import de.blogspot.mszalbach.iss.screenplay.tasks.RequestASecurity;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

/**
 * BDD sentences needed for testing Workflow actions
 */
public class WorkflowSteps {

    @Dann( "^kann (?:er|sie) folgende Aktionen auf \"([^\"]*)\" ausführen$" )
    public void checkForAvailableActionsForSecurity( String isin, List<String> actions ) {
        theActorInTheSpotlight().should( seeThat( Actions.forSecurity( isin ), is( actions ) ) );
    }



    //Used because using two annotations is not working for the intelij cucumber plugin
    @Und( "^er kann folgende Aktionen auf \"([^\"]*)\" ausführen$" )
    public void checkForAvailableActionsForSecurity2( String isin, List<String> actions ) {
        checkForAvailableActionsForSecurity( isin, actions );
    }



    @Und( "^das Wertpapier \"([^\"]*)\" beantragt$" )
    public void requestSecurity( String isin )
        throws Throwable {
        theActorInTheSpotlight().attemptsTo( RequestASecurity.called( isin ) );
    }



    @Dann( "^sollte \"([^\"]*)\" den Status \"([^\"]*)\" haben$" )
    public void checkCorrectStateForSecurity( String isin, String state )
        throws Throwable {
        theActorInTheSpotlight().should( seeThat( State.forSecurity( isin ), is( state ) ) );
    }
}
