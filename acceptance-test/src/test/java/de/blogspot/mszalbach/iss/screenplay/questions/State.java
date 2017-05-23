package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import static net.serenitybdd.screenplay.questions.ValueOf.the;

public class State
    implements Question<String> {

    private final String isin;



    public State( String isin ) {
        this.isin = isin;
    }



    public static State forSecurity( String isin ) {
        return new State( isin );
    }



    @Override
    public String answeredBy( Actor actor ) {
        return the( Text.of( SecurityListPage.stateForSecurity( isin ) ).viewedBy( actor ) );
    }
}
