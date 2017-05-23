package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.IssPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

import static net.serenitybdd.screenplay.questions.ValueOf.the;

@Subject( "Sign In Button Text" )
public class SignInButton
    implements Question<String> {

    public static SignInButton text() {
        return new SignInButton();
    }



    @Override
    public String answeredBy( Actor actor ) {
        return the( Text.of( IssPage.SIGNIN_BUTTON ).viewedBy( actor ) );
    }
}
