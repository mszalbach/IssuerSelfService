package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.SecurityEnterForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

public class FormErrors
    implements Question<List<String>> {

    public static FormErrors displayed() {
        return new FormErrors();
    }



    @Override
    public List<String> answeredBy( Actor actor ) {
        return Text.of( SecurityEnterForm.FORM_ERRORS ).viewedBy( actor ).asList();
    }
}
