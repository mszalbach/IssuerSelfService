package de.blogspot.mszalbach.iss.screenplay.tasks;

import de.blogspot.mszalbach.iss.pageobjects.LoginPage;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.core.annotations.Step;

/**
 * Created by ms on 09.12.16.
 */
public class Start
        implements Task {

    private LoginPage loginPage;



    @Override
    @Step( "{0} öffnet die Login Seite" )
    public <T extends Actor> void performAs( T actor ) {
        actor.attemptsTo(
                Open.browserOn().the( loginPage )
        );
    }



    public static Start onLoginPage() {
        return Instrumented.instanceOf( Start.class ).newInstance();
    }
}
