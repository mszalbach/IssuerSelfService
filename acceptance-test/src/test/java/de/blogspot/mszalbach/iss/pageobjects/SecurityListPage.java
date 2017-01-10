package de.blogspot.mszalbach.iss.pageobjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;

/**
 * Created by foobarkilla on 22.10.16.
 */
@DefaultUrl( "http://localhost:8080/#/securities" )
public class SecurityListPage
    extends PageObject {

    public static Target SECURITY_TABLE_ENTRIES    = Target.the( "Einträge der Security Tabelle" )
                                                           .locatedBy( "#securities tbody tr" );
    public static Target OPEN_CREATE_DIALOG_BUTTON = Target.the( "Einträge der Security Tabelle" )
                                                           .locatedBy( "#openCreate" );
}
