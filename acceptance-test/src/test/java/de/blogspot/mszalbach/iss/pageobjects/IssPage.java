package de.blogspot.mszalbach.iss.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by ms on 08.12.16.
 */
@DefaultUrl( "http://localhost:8080/" )
public class IssPage
        extends PageObject {


    private static final String HOME_LINK     = "//a[contains(text(), 'Issuer Self Service')]";
    private static final String USER_DROPDOWN = "#userdropdown";



    public void openApplication() {
        open();
        waitForTheApplicationToLoad();
    }



    private void waitForTheApplicationToLoad() {
        withTimeoutOf( 60, TimeUnit.SECONDS ).waitFor( HOME_LINK );
    }



    public String loggedInAs() {
        WebElement userdropdown = findBy( USER_DROPDOWN );
        clickOn( userdropdown );
        return userdropdown.getText();
    }



    public void logout() {

    }



    public void goToLogin() {
        this.switchToPage( LoginPage.class ).open();
    }



}
