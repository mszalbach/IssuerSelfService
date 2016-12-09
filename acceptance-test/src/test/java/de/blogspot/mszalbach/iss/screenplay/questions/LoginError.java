package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import static net.serenitybdd.screenplay.questions.ValueOf.the;

/**
 * Created by ms on 09.12.16.
 */
@Subject( "Login Error" )
public class LoginError
        implements Question<String> {

    @Override
    public String answeredBy( Actor actor ) {
        return the( Text.of( LoginPage.ERROR_TEXT ).viewedBy( actor ) );
    }



    public static LoginError text() {
        return new LoginError();
    }
}
