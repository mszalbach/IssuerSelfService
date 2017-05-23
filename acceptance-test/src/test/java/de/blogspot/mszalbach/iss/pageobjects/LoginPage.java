package de.blogspot.mszalbach.iss.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl( "http://localhost:8080/#/login" )
public class LoginPage
    extends PageObject {

    public static Target USER_FIELD     = Target.the( "Username Field" ).locatedBy( "#user" );
    public static Target PASSWORD_FIELD = Target.the( "Password Field" ).locatedBy( "#password" );
    public static Target LOGIN_BUTTON   = Target.the( "Login Button" ).locatedBy( "#login" );
    public static Target ERROR_TEXT     = Target.the( "Login Error Text" ).locatedBy( "#loginError" );
}
