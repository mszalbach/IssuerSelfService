package de.blogspot.mszalbach.iss.pageobjects;

import de.blogspot.mszalbach.iss.domain.Security;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

/**
 * Created by foobarkilla on 29.10.16.
 */
@DefaultUrl("http://localhost:8080/#/securities")
public class SecurityEnterPage
    extends PageObject {


    public SecurityEnterPage insertSecurity(Security security) {

        enter(security.getIsin()).intoField(By.id("root_isin"));
        enter(security.getSymbol()).intoField(By.id("root_symbol"));
        return this;
    }


    public void submitForm() {
        find(By.id("create")).click();

    }

}
