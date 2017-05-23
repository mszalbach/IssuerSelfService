package de.blogspot.mszalbach.iss.screenplay.tasks;

import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;


public class RequestASecurity
    implements Task {

    private final String isin;



    public RequestASecurity( String isin ) {
        this.isin = isin;
    }



    public static RequestASecurity called( String isin ) {
        return new RequestASecurity( isin );
    }



    @Override
    public <T extends Actor> void performAs( T actor ) {
        actor.attemptsTo( Click.on( SecurityListPage.requestActionForSecurity( isin ) ) );
    }


}
