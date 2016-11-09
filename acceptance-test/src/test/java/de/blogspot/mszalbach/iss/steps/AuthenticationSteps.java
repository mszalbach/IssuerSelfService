package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import de.blogspot.mszalbach.iss.pageobjects.LoginPage;
import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by foobarkilla on 09.11.16.
 */
public class AuthenticationSteps {

    LoginPage loginPage;
    SecurityListPage securityListPage;


    @Wenn("^(\\w+) sich einloggt mit Passwort \"([^\"]*)\"$")
    public void loginAsUserWithPassword(String user, String password) throws Throwable {
        loginPage.open();
        loginPage.login(user, password);
        loginPage.submitForm();
    }

    @Dann("^sollte er informiert werden das der Login fehlgeschlagen ist$")
    public void checkForErrorMessage() throws Throwable {
        assertThat(loginPage.getLoginError(), is("401:Bad credentials"));
    }

    @Dann("^sollte er eingelogt sein als (\\w+)$")
    public void shouldBeLoggedInAs(String username) throws Throwable {
        assertThat(securityListPage.loggedInAs(), is(username));
    }
}
