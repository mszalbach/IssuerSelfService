package de.blogspot.mszalbach.iss.statemachine;

import de.blogspot.mszalbach.iss.config.SecurityStateMachineFactory;
import de.blogspot.mszalbach.iss.domain.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;

/**
 * Created by ms on 24.11.16.
 */
@Component
public class SecurityStateMachineAdapter {

    @Autowired
    SecurityStateMachineFactory factory;



    public UntypedStateMachine restore( Security contextObject )
            throws Exception {
        UntypedStateMachine stateMachine = create();
        stateMachine.loadSavedData( contextObject.getStateMachine() );
        return stateMachine;
    }



    public void persist( UntypedStateMachine stateMachine, Security contextObject )
            throws Exception {
        contextObject.setStateMachine( stateMachine.dumpSavedData() );
    }



    public UntypedStateMachine create() {
        UntypedStateMachine machine = factory.create().newUntypedStateMachine( "Open" );
        machine.start();
        return machine;
    }


}
