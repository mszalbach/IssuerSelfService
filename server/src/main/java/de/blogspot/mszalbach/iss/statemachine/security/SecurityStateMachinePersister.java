package de.blogspot.mszalbach.iss.statemachine.security;

import de.blogspot.mszalbach.iss.domain.SecurityEvent;
import de.blogspot.mszalbach.iss.domain.SecurityState;
import de.blogspot.mszalbach.iss.statemachine.ContextEntity;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by ms on 21.11.16.
 */
@Component
public class SecurityStateMachinePersister
        extends DefaultStateMachinePersister<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> {


    public SecurityStateMachinePersister( SecurityStateMachinePersist stateMachinePersist ) {
        super( stateMachinePersist );
    }
}
