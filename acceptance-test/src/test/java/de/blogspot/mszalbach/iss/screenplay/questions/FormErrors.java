package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.SecurityEnterForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

/**
 * Created by ms on 10.01.17.
 */
public class FormErrors
    implements Question<List<String>> {

    public static FormErrors displayed() {
        return new FormErrors();
    }



    @Override
    public List<String> answeredBy( Actor actor ) {
        System.out.println( Text.of( SecurityEnterForm.FORM_ERRORS ).viewedBy( actor ) );
        System.out.println( Text.of( SecurityEnterForm.FORM_ERRORS ).viewedBy( actor ).asList() );
        return Text.of( SecurityEnterForm.FORM_ERRORS ).viewedBy( actor ).asList();
    }
}
