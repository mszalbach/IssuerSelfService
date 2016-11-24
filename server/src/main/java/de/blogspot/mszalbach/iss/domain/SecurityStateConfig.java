package de.blogspot.mszalbach.iss.domain;

import org.springframework.stereotype.Component;
import org.squirrelframework.foundation.fsm.annotation.StateMachineParameters;
import org.squirrelframework.foundation.fsm.annotation.Transit;
import org.squirrelframework.foundation.fsm.annotation.Transitions;
import org.squirrelframework.foundation.fsm.impl.AbstractUntypedStateMachine;

/**
 * Created by ms on 24.11.16.
 */
@StateMachineParameters( stateType = String.class, eventType = String.class, contextType = Integer.class )
@Transitions( {
        @Transit( from = "Open", to = "Requested", on = "request" ),
        @Transit( from = "Requested", to = "Accepted", on = "accept" ),
        @Transit( from = "Requested", to = "Open", on = "cancel" )
} )
public class SecurityStateConfig
        extends AbstractUntypedStateMachine {

}

