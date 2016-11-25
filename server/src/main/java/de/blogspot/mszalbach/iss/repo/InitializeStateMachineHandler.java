package de.blogspot.mszalbach.iss.repo;

import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.statemachine.SecurityStateMachineAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

/**
 * Created by ms on 21.11.16.
 */
@Component
@RepositoryEventHandler(Security.class)
public class InitializeStateMachineHandler {

    private SecurityStateMachineAdapter securityStateMachineAdapter;



    @Autowired
    public InitializeStateMachineHandler( SecurityStateMachineAdapter securityStateMachineAdapter ) {
        this.securityStateMachineAdapter = securityStateMachineAdapter;
    }



    @HandleBeforeCreate
    protected void onBeforeCreate( Security security ) {
        try {
            securityStateMachineAdapter.persist( securityStateMachineAdapter.create(), security );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
