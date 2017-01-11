package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

import java.util.List;

/**
 * Created by ms on 11.01.17.
 */
public class Actions
    implements Question<List<String>> {

    private final String isin;



    public Actions( String isin ) {
        this.isin = isin;
    }



    public static Actions forSecurity( String isin ) {
        return new Actions( isin );
    }



    @Override
    public List<String> answeredBy( Actor actor ) {
        return Text.of( SecurityListPage.actionsForSecurity( isin ) ).viewedBy( actor ).asList();
    }
}
