package de.blogspot.mszalbach.iss.screenplay.tasks;

import net.serenitybdd.core.steps.Instrumented;

/**
 * Created by ms on 09.01.17.
 */
public class Opens {

    public static OpenSecurityListPage securityListPage() {
        return Instrumented.instanceOf( OpenSecurityListPage.class).newInstance();
    }

    public static OpenLoginPage loginPage() {
        return Instrumented.instanceOf(OpenLoginPage.class).newInstance();
    }

}
