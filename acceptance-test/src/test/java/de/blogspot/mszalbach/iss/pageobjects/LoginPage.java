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

    public void login(String username, String password) {
        typeIntoField("user", username);
        typeIntoField("password", password);
    }


    public void submitForm() {
        submitForm("login");

    }

    private void typeIntoField(String id, String value) {
        WebElement element = find(By.id(id));
        element.clear();
        element.sendKeys(value);
    }

    private void submitForm(String id) {
        find(By.id(id)).click();
    }

    public String getLoginError() {
        WebElement loginError = find(By.id("loginError"));
        element(loginError).waitUntilPresent();
        return loginError.getText().trim();
    }
}
