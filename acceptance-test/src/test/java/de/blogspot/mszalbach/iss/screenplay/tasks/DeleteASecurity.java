package de.blogspot.mszalbach.iss.screenplay.tasks;

import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

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
            Click.on( SecurityListPage.deleteActionForSecurity( security ) )
        );
    }
}
