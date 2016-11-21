package de.blogspot.mszalbach.iss.statemachine.security;

import de.blogspot.mszalbach.iss.domain.SecurityEvent;
import de.blogspot.mszalbach.iss.domain.SecurityState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * Created by ms on 21.11.16.
 */
@Configuration
@EnableStateMachineFactory( contextEvents = false )
public class SecurityStateMachineConfiguration
        extends EnumStateMachineConfigurerAdapter<SecurityState, SecurityEvent> {

    @Override
    public void configure( StateMachineStateConfigurer<SecurityState, SecurityEvent> states )
            throws Exception {
        states
                .withStates()
                .initial( SecurityState.Open )
                .end( SecurityState.Accepted )
                .states( EnumSet.allOf( SecurityState.class ) );
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<SecurityState, SecurityEvent> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(SecurityState.Open).target(SecurityState.Accepted)
                .event(SecurityEvent.Event1)
                .and()
                .withExternal()
                .source(SecurityState.Open).target(SecurityState.Canceled)
                .event(SecurityEvent.Event2);
    }


}
