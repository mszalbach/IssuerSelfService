package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.statemachine.security.SecurityStateMachieneAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by ms on 21.11.16.
 */
@Component
public class SecurityCreatedEventListener
        extends AbstractRepositoryEventListener<Security> {

    @Autowired
    private SecurityStateMachieneAdapter securityStateMachineAdapter;



    @Override
    protected void onBeforeCreate( Security security ) {
        try {
            securityStateMachineAdapter.persist( securityStateMachineAdapter.create(), security );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
