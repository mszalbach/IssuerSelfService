package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.IssPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import static net.serenitybdd.screenplay.questions.ValueOf.the;

/**
 * Created by ms on 09.12.16.
 */
@Subject( "Username" )
public class LoggedInAs
        implements Question<String> {

    @Override
    public String answeredBy( Actor actor ) {
        return the( Text.of( IssPage.USER_DROPDOWN ).viewedBy( actor ) );
    }



    public static LoggedInAs username() {
        return new LoggedInAs();
    }
}
