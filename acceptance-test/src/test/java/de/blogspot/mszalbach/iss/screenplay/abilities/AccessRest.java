package de.blogspot.mszalbach.iss.screenplay.abilities;


import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

/**
 * Created by ms on 10.01.17.
 */
public class AccessRest
    implements Ability {


    public static AccessRest viaApiProperty() {
        RestAssured.baseURI = System.getProperty( "api.url", "http://localhost:8081/api" );
        return new AccessRest();
    }



    public static AccessRest as( Actor actor ) {

        // complain if someone's asking the impossible
        if ( actor.abilityTo( AccessRest.class ) == null ) {
            throw new IllegalStateException( actor.getName() + " not able to query given api." );
        }

        // need username and password for the rest api and can be used from the Authenticate ability
        if ( actor.abilityTo( Authenticate.class ) == null ) {
            throw new IllegalStateException( actor.getName() + " not able to authenticate." );
        }

        SerenityRest.setDefaultAuthentication(
            SerenityRest.basic( Authenticate.as( actor ).username(), Authenticate.as( actor ).password() ) );

        return actor.abilityTo( AccessRest.class );
    }



    public RequestSpecification given() {
        return SerenityRest.given();
    }

}
