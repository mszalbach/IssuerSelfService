package de.blogspot.mszalbach.iss.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by ms on 08.12.16.
 */
@DefaultUrl("http://localhost:8080/")
public class IssPage
    extends PageObject {


    private static final String HOME_LINK = "//a[contains(text(), 'Issuer Self Service')]";
    public static Target USER_DROPDOWN = Target.the("Logged in User").locatedBy("#userdropdown");


    public void openApplication() {
        open();
        waitForTheApplicationToLoad();
    }


    private void waitForTheApplicationToLoad() {
        withTimeoutOf(60, TimeUnit.SECONDS).waitFor(HOME_LINK);
    }


    public String loggedInAs() {
        WebElement userdropdown = findBy("#userdropdown");
        clickOn(userdropdown);
        return userdropdown.getText();
    }


    public void logout() {

    }


    public void goToLogin() {
        this.switchToPage(LoginPage.class).open();
    }


}
