package de.blogspot.mszalbach.iss.statemachine;

import org.springframework.hateoas.Identifiable;
import org.springframework.statemachine.StateMachineContext;

import java.io.Serializable;

/**
 * Created by ms on 21.11.16.
 */
public interface ContextEntity<S, E, ID extends Serializable>
        extends Identifiable<ID> {

    StateMachineContext<S, E> getStateMachineContext();



    void setStateMachineContext( StateMachineContext<S, E> context );

}
