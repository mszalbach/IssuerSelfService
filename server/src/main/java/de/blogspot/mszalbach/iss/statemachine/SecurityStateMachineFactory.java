package de.blogspot.mszalbach.iss.statemachine;

import de.blogspot.mszalbach.iss.domain.SecurityStateConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;
import org.squirrelframework.foundation.fsm.UntypedStateMachineBuilder;

/**
 * Created by ms on 24.11.16.
 */
@Configuration
public class SecurityStateMachineFactory {

    @Bean
    public UntypedStateMachineBuilder create() {
        UntypedStateMachineBuilder builder = StateMachineBuilderFactory.create( SecurityStateConfig.class );
        return builder;
    }

}
