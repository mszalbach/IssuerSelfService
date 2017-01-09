package de.blogspot.mszalbach.iss.screenplay.tasks;

import com.jayway.restassured.RestAssured;
import net.serenitybdd.screenplay.Task;

/**
 * Configures RestAssured and should be used by all Tasks which want to use Rest
 */
public abstract class RestTask implements Task {

    RestTask() {
        RestAssured.baseURI = System.getProperty( "api.url", "http://localhost:8081/api" );
    }
}
