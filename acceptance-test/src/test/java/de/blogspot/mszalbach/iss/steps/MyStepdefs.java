package de.blogspot.mszalbach.iss.steps;

import cucumber.api.java.de.Angenommen;
import cucumber.api.java.de.Dann;
import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.pageobjects.SecurityListPage;
import net.thucydides.core.annotations.Steps;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by foobarkilla on 22.10.16.
 */
public class MyStepdefs {


    SecurityListPage securityList;

    @Steps
    SecurityRestSteps securityRest;

    @Angenommen("Ralf hat folgende Wertpapiere$")
    public void createSecuritiesForUser(List<Security> securities) {

        for (Security security : securities) {
            securityRest.create_security(security);
        }

    }

    @Dann("^sollte seine Werpapierliste (\\d+) Einträge haben$")
    public void checkSecurityCount(int count) {
        assertThat(securityList.getCount(), is(count));
    }

}
