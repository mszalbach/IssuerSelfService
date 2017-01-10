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

    public static final Target LOGOUT_BUTTON = Target.the( "Logout Button" ).locatedBy( "#logout" );
    public static final Target USER_DROPDOWN = Target.the( "Logged in User" ).locatedBy( "#userdropdown" );
    public static final Target SIGNIN_BUTTON = Target.the( "Sign In Button" ).locatedBy( "#signin" );
}
