package de.blogspot.mszalbach.iss.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;

/**
 * Created by foobarkilla on 22.10.16.
 */
@DefaultUrl("http://localhost:8080")
public class SecurityListPage extends PageObject {
    public int getCount() {
        return -3;
    }
}
