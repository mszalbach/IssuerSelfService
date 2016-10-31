package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import cucumber.api.java.de.Wenn;
import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by foobarkilla on 22.10.16.
 */
public class OnlineListingSteps {

    SecurityListPage securityPage;

    @Steps
    SecurityRestSteps securityRest;

    @Angenommen("Ralf hat folgende Wertpapiere$")
    public void createSecuritiesForUser(List<Security> securities) {
        securityRest.create_securities(securities);
    }

    @Dann("^sollte seine Werpapierliste (\\d+) Einträge haben$")
    public void checkSecurityCount(int count) {
        securityPage.open();
        assertThat(securityPage.getCount(), is(count));
    }

    @Angenommen("^Ralf darf Wertpapiere anlegen$")
    public void goToSecurityEntry() throws Throwable {
        securityPage.open();
    }

    @Wenn("^er ein Wertpapier mit folgenden Daten anlegt$")
    public void enterSecurityViaWebsite(List<Security> security) throws Throwable {
        securityPage.openCreateSecurityDialog().insertSecurity(security.get(0)).submitForm();
    }

    @Dann("^sollte es folgendes Wertpapier existieren$")
    public void checkForExistingSecurity(List<Security> security) throws Throwable {
        assertThat(securityRest.findSecurity(security.get(0)).size(), greaterThanOrEqualTo(1));
    }

    @Wenn("^er das Wertpapier \"([^\"]*)\" löscht$")
    public void deleteSecurityViaWebsite(String isin) throws Throwable {
        securityPage.open();
        securityPage.deleteSeurity(new Security(isin, ""));
    }

    @Dann("^gibt es kein Wertpapier \"([^\"]*)\" mehr$")
    public void checkSecurityDidNotExist(String isin) throws Throwable {
        assertThat(securityRest.findSecurity(new Security(isin, "")), empty());
    }
}
