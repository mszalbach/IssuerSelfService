package de.blogspot.mszalbach.iss.statemachine;

import de.blogspot.mszalbach.iss.domain.SecurityStateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * Factory to create StateMachines for Securities.
 */
@Configuration
public class SecurityStateMachineFactory {

    @Bean
    public UntypedStateMachineBuilder create() {
        return StateMachineBuilderFactory.create(SecurityStateConfig.class);
    }

}
