package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.questions.Text;

@Subject( "Security Count" )
public class SecurityListCount
    implements Question<Integer> {


    @Override
    public Integer answeredBy( Actor actor ) {
        return Text.of( SecurityListPage.SECURITY_TABLE_ENTRIES ).viewedBy( actor ).asList().size();

    }
}
