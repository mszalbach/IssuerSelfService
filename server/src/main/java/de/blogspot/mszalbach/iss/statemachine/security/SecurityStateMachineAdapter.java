package de.blogspot.mszalbach.iss.statemachine.security;

import de.blogspot.mszalbach.iss.domain.SecurityEvent;
import de.blogspot.mszalbach.iss.domain.SecurityState;
import de.blogspot.mszalbach.iss.statemachine.ContextEntity;
import de.blogspot.mszalbach.iss.statemachine.DefaultStateMachineAdapter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by ms on 21.11.16.
 */
@Component
public class SecurityStateMachineAdapter
        extends DefaultStateMachineAdapter<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> {

}
