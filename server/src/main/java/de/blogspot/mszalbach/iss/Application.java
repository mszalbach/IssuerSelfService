package de.blogspot.mszalbach.iss;

import de.blogspot.mszalbach.iss.domain.SecurityEvent;
import de.blogspot.mszalbach.iss.domain.SecurityState;
import de.blogspot.mszalbach.iss.statemachine.ContextEntity;
import de.blogspot.mszalbach.iss.statemachine.DefaultStateMachineAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.io.Serializable;

/**
 * Created by foobarkilla on 16.10.16.
 */
@SpringBootApplication
public class Application {

    public static void main( String[] args ) {
        SpringApplication.run( Application.class, args );
    }



    @Bean
    public DefaultStateMachineAdapter<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> SecurityStateMachineAdapter(
            StateMachineFactory<SecurityState, SecurityEvent> stateMachineFactory,
            StateMachinePersister<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> persister ) {
        return new DefaultStateMachineAdapter<>( stateMachineFactory, persister );
    }



    @Bean
    public StateMachinePersister<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> persister(
            StateMachinePersist<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> persist ) {
        return new DefaultStateMachinePersister<>( persist );
    }



    @Bean
    public StateMachinePersist<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> persist() {
        return new StateMachinePersist<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>>() {

            @Override
            public StateMachineContext<SecurityState, SecurityEvent> read(
                    ContextEntity<SecurityState, SecurityEvent, Serializable> Security )
                    throws Exception {
                return Security.getStateMachineContext();
            }



            @Override
            public void write( StateMachineContext<SecurityState, SecurityEvent> context,
                               ContextEntity<SecurityState, SecurityEvent, Serializable> Security )
                    throws Exception {
                Security.setStateMachineContext( context );
            }
        };
    }
}
