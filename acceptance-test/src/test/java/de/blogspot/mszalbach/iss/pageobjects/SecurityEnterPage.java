package de.blogspot.mszalbach.iss.pageobjects;

import de.blogspot.mszalbach.iss.domain.Security;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

/**
 * Created by foobarkilla on 29.10.16.
 */
@DefaultUrl("http://localhost:8080")
public class SecurityEnterPage extends PageObject {

    public void insertSecurity(Security security) {
        typeIntoField("isin", security.isin);
        typeIntoField("symbol", security.symbol);
    }


    public void submitForm() {
        submitForm("create");

    }

    private void typeIntoField(String id, String value) {
        WebElement element = find(By.id(id));
        element.clear();
        element.sendKeys(value);
    }

    private void submitForm(String id) {
        find(By.id(id)).click();
    }
}
