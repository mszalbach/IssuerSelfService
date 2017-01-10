package de.blogspot.mszalbach.iss.screenplay.tasks;

import de.blogspot.mszalbach.iss.pageobjects.IssPage;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

/**
 * Created by ms on 09.12.16.
 */
public class LogOut
    implements Task {

    public static LogOut now() {
        return Instrumented.instanceOf( LogOut.class ).newInstance();
    }



    @Override
    @Step( "{0} loggt sich aus" )
    public <T extends Actor> void performAs( T actor ) {
        actor.attemptsTo(
            Click.on( IssPage.USER_DROPDOWN ),
            Click.on( IssPage.LOGOUT_BUTTON )
        );
    }

}

