package de.blogspot.mszalbach.iss.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

/**
 * Created by ms on 08.12.16.
 */
@DefaultUrl( "http://localhost:8080/" )
public class IssPage
    extends PageObject {

    public static Target USER_DROPDOWN = Target.the( "Logged in User" ).locatedBy( "#userdropdown" );
}
