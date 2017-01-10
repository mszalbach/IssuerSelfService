package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.SecurityEnterForm;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Enabled;

/**
 * Created by ms on 10.01.17.
 */
public class SecurityCreateButton
    implements Question<Boolean> {

    public static SecurityCreateButton isEnabled() {
        return new SecurityCreateButton();
    }



    @Override
    public Boolean answeredBy( Actor actor ) {
        return Enabled.of( SecurityEnterForm.CREATE_BUTTON ).viewedBy( actor ).resolve();
    }
}
