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

    public static Target SECURITY_TABLE_ENTRIES    = Target.the( "Security Table Entries" )
                                                           .locatedBy( "#securities tbody tr" );
    public static Target OPEN_CREATE_DIALOG_BUTTON = Target.the( "Create Security Button" )
                                                           .locatedBy( "#openCreate" );



    /**
     * @param isin to search actions for.
     * @return button texts from the first row with the specific isin.
     */
    public static Target actionsForSecurity( String isin ) {
        //TODO since test did not correctly ensure state only return first value found, because there will be multiple rows with same isin.
        return Target.the( "Actions for " + isin ).locatedBy( "(//*[@id='actions_" + isin + "'])[1]/button" );
    }



    public static Target requestActionForSecurity( String isin ) {
        return Target.the( "Request Action for " + isin ).locatedBy( "(//button[@id='request_" + isin + "'])[1]" );
    }



    public static Target deleteActionForSecurity( String isin ) {
        return Target.the( "Request Action for " + isin ).locatedBy( "(//button[@id='delete_" + isin + "'])[1]" );
    }



    public static Target stateForSecurity( String isin ) {
        return Target.the( "State of " + isin ).locatedBy( "(//td[@id='state_" + isin + "'])[1]" );
    }
}
