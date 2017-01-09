package de.blogspot.mszalbach.iss.repo;

import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.statemachine.SecurityStateMachineAdapter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Will build and set a new StateMachine for newly created Securities.
 */
@Component
@RepositoryEventHandler(Security.class)
public class InitializeStateMachineHandler {

    private final static Logger LOGGER = Logger.getLogger( InitializeStateMachineHandler.class);
    private SecurityStateMachineAdapter securityStateMachineAdapter;


    @Autowired
    public InitializeStateMachineHandler(SecurityStateMachineAdapter securityStateMachineAdapter) {
        this.securityStateMachineAdapter = securityStateMachineAdapter;
    }


    @HandleBeforeCreate
    protected void onBeforeCreate(Security security) {
        try {
            securityStateMachineAdapter.persist(securityStateMachineAdapter.create(), security);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

}
