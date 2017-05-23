package de.blogspot.mszalbach.iss.screenplay.questions;

import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.screenplay.abilities.AccessRest;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/**
 * Created by ms on 09.01.17.
 */
public class CountSecuritiesViaRest
    implements Question<Integer> {

    private final String isin;



    public CountSecuritiesViaRest( String isin ) {
        this.isin = isin;
    }



    public static CountSecuritiesViaRest byIsin( String isin ) {
        return new CountSecuritiesViaRest( isin );
    }



    @Override
    public Integer answeredBy( Actor actor ) {
        Security[] securities = AccessRest.as( actor ).given().contentType( ContentType.JSON )
                                          .when()
                                          .get( "securities/search/findByIsin?isin=" + isin ).jsonPath()
                                          .getObject( "_embedded.securities", Security[].class );
        return securities.length;
    }


}
