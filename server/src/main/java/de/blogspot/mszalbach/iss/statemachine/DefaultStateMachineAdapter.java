package de.blogspot.mszalbach.iss.statemachine;

import org.springframework.context.annotation.Bean;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * Created by ms on 21.11.16.
 */
public class DefaultStateMachineAdapter<S, E, T> {

    private StateMachineFactory<S, E> stateMachineFactory;

    private StateMachinePersister<S, E, T> persister;



    public DefaultStateMachineAdapter( StateMachineFactory<S, E> stateMachineFactory, StateMachinePersister<S, E, T> persister ) {
        this.stateMachineFactory = stateMachineFactory;
        this.persister = persister;
    }



    public StateMachine<S, E> restore( T contextObject )
            throws Exception {
        StateMachine<S, E> stateMachine = stateMachineFactory.getStateMachine();
        return persister.restore( stateMachine, contextObject );
    }



    public void persist( StateMachine<S, E> stateMachine, T order )
            throws Exception {
        persister.persist( stateMachine, order );
    }



    public StateMachine<S, E> create() {
        StateMachine<S, E> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.start();
        return stateMachine;
    }

}
