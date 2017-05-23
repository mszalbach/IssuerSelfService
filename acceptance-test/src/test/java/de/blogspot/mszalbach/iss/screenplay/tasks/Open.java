package de.blogspot.mszalbach.iss.screenplay.tasks;

import net.serenitybdd.core.steps.Instrumented;

public class Open {

    public static OpenSecurityListPage securityListPage() {
        return Instrumented.instanceOf( OpenSecurityListPage.class).newInstance();
    }

    public static OpenLoginPage loginPage() {
        return Instrumented.instanceOf(OpenLoginPage.class).newInstance();
    }

}
