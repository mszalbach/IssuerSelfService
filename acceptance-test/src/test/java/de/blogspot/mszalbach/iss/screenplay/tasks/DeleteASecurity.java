package de.blogspot.mszalbach.iss.screenplay.tasks;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Created by ms on 09.01.17.
 */
public class DeleteASecurity
    implements Task {

    private final String security;



    public DeleteASecurity( String security ) {
        this.security = security;
    }



    public static DeleteASecurity called( String security ) {
        return instrumented( DeleteASecurity.class, security );
    }



    @Override
    @Step( "{0} löscht ein Wertpapier" )
    public <T extends Actor> void performAs( T actor ) {
        actor.attemptsTo(
            Click.on( By.id( "delete_" + security ) )
        );
    }
}
