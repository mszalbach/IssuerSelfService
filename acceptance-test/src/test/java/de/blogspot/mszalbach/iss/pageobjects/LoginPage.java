package de.blogspot.mszalbach.iss.pageobjects;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

/**
 * Created by foobarkilla on 08.11.16.
 */
@DefaultUrl("http://localhost:8080/#/login")
public class LoginPage extends PageObject {

    public void login( String username, String password ) {

        enter( username ).intoField( By.id( "user" ) );
        enter( password ).intoField( By.id( "password" ) );
    }



    public void submitForm() {
        findBy( "#login" ).click();

    }



    public String getLoginError() {
        WebElement loginError = findBy( "#loginError"  );
        element( loginError ).waitUntilPresent();
        return loginError.getText().trim();
    }
}
