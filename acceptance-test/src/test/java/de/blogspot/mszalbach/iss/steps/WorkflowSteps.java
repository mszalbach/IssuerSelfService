package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.de.Dann;
import de.blogspot.mszalbach.iss.screenplay.questions.Actions;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.is;

/**
 * BDD sentences needed for testing Workflow actions
 */
public class WorkflowSteps {

    @Dann( "kann (?:er|sie) folgende Aktionen auf \"([^\"]*)\" ausführen" )
    public void checkForAvailableActionsForSecurity( String isin, List<String> actions ) {
        theActorInTheSpotlight().should( seeThat( Actions.forSecurity( isin ), is( actions ) ) );
    }

}
