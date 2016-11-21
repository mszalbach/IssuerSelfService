package de.blogspot.mszalbach.iss.statemachine.security;

import de.blogspot.mszalbach.iss.domain.SecurityEvent;
import de.blogspot.mszalbach.iss.domain.SecurityState;
import de.blogspot.mszalbach.iss.statemachine.ContextEntity;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by ms on 21.11.16.
 */
@Component
public class SecurityStateMachinePersist
        implements StateMachinePersist<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> {

    @Override
    public StateMachineContext<SecurityState, SecurityEvent> read(
            ContextEntity<SecurityState, SecurityEvent, Serializable> security )
            throws Exception {
        return security.getStateMachineContext();
    }



    @Override
    public void write( StateMachineContext<SecurityState, SecurityEvent> context,
                       ContextEntity<SecurityState, SecurityEvent, Serializable> security )
            throws Exception {
        security.setStateMachineContext( context );
    }

}
