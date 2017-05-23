package de.blogspot.mszalbach.iss.screenplay.tasks;

import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.pageobjects.SecurityEnterForm;
import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import de.blogspot.mszalbach.iss.screenplay.questions.SecurityCreateButton;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddASecurity
    implements Task {

    private final Security security;



    public AddASecurity( Security security ) {
        this.security = security;
    }



    public static AddASecurity called( Security security ) {
        return instrumented( AddASecurity.class, security );
    }



    @Override
    @Step( "{0} legt ein Wertpapier an" )
    public <T extends Actor> void performAs( T actor ) {
        actor.attemptsTo(
            Click.on( SecurityListPage.OPEN_CREATE_DIALOG_BUTTON ),
            Enter.theValue( security.getIsin() )
                 .into( SecurityEnterForm.ISIN_FIELD ),
            Enter.theValue( security.getSymbol() )
                 .into( SecurityEnterForm.SYMBOL_FIELD ),
            Check.whether( SecurityCreateButton.isEnabled() ).andIfSo( Click.on( SecurityEnterForm.CREATE_BUTTON ) )
        );
    }
}
