package de.blogspot.mszalbach.iss.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl( "http://localhost:8080/#/securities" )
public class SecurityEnterForm
    extends PageObject {

    public static final Target ISIN_FIELD    = Target.the( "ISIN Field" ).locatedBy( "#root_isin" );
    public static final Target SYMBOL_FIELD  = Target.the( "Symbol Field" ).locatedBy( "#root_symbol" );
    public static final Target CREATE_BUTTON = Target.the( "Create Button" ).locatedBy( "#create" );
    public static final Target FORM_ERRORS   = Target.the( "Form Errors" ).locatedBy( ".text-danger" );

}
