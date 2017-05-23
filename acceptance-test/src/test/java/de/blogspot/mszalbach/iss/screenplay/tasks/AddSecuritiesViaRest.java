package de.blogspot.mszalbach.iss.screenplay.tasks;

import com.google.common.collect.ImmutableList;
import de.blogspot.mszalbach.iss.domain.Security;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AddSecuritiesViaRest
    implements Task {

    private final List<Security> securities;
    private final int            securitiesSize;



    public AddSecuritiesViaRest( List<Security> securities ) {
        this.securities = ImmutableList.copyOf( securities );
        this.securitiesSize = securities.size();
    }



    public static AddSecuritiesViaRest called( List<Security> items ) {
        return instrumented( AddSecuritiesViaRest.class, items );
    }



    @Override
    @Step( "{0} fügt #securitiesSize Wertpapiere via Rest hinzu" )
    public <T extends Actor> void performAs( T actor ) {
        securities.forEach(
            todo -> actor.attemptsTo( AddASecurityViaRest.called( todo ) )
        );
    }
}
