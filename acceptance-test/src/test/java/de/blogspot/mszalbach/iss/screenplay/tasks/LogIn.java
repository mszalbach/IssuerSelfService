package de.blogspot.mszalbach.iss.screenplay.tasks;

import de.blogspot.mszalbach.iss.pageobjects.LoginPage;
import de.blogspot.mszalbach.iss.screenplay.abilities.Authenticate;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.thucydides.core.annotations.Step;

/**
 * Created by ms on 09.12.16.
 */
public class LogIn
        implements Task {

    public static LogIn withCredentials() {
        return Instrumented.instanceOf( LogIn.class ).newInstance();
    }



    @Override
    @Step( "Anmelden als {0}" )
    public <T extends Actor> void performAs( T actor ) {
        actor.attemptsTo(
                Enter.theValue( authenticated( actor ).username() )
                     .into( LoginPage.USER_FIELD ),

                Enter.theValue( authenticated( actor ).password() )
                     .into( LoginPage.PASSWORD_FIELD ),

                Click.on( LoginPage.LOGIN_BUTTON )
        );
    }



    private Authenticate authenticated( Actor actor ) {
        return Authenticate.as( actor );
    }
}

