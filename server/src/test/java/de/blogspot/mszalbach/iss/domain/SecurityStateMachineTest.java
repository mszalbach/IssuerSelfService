package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.statemachine.security.SecurityStateMachineConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.test.StateMachineTestPlan;
import org.springframework.statemachine.test.StateMachineTestPlanBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ms on 21.11.16.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest( classes = { StateMachineFactory.class, SecurityStateMachineConfiguration.class } )
public class SecurityStateMachineTest {

    @Autowired
    private StateMachineFactory<SecurityState, SecurityEvent> orderStateMachineFactory;

    private StateMachine<SecurityState, SecurityEvent> stateMachine;

    private final Logger log = LoggerFactory.getLogger( this.getClass() );



    @Before
    public void setUp() {
        stateMachine = orderStateMachineFactory.getStateMachine();
        stateMachine.addStateListener( new TestListener() );
    }



    @After
    public void tearDown() {
        stateMachine.stop();
    }



    @Test
    public void testWorkflow()
            throws Exception {
        StateMachineTestPlan<SecurityState, SecurityEvent> plan =
                StateMachineTestPlanBuilder.<SecurityState, SecurityEvent>builder()
                        .stateMachine( stateMachine )
                        .step()
                        .expectState(
                                SecurityState.Open )
                        .and()
                        .step()
                        .sendEvent( SecurityEvent.Event1 )
                        .expectState(
                                SecurityState.Accepted )
                        .and()
                        .step()
                        .sendEvent( SecurityEvent.Event1 )
                        .expectState( SecurityState.Accepted )
                        .and()
                        .build();
        plan.test();
    }



    private class TestListener
            extends StateMachineListenerAdapter<SecurityState, SecurityEvent> {


        @Override
        public void eventNotAccepted( Message<SecurityEvent> event ) {
            log.error( "Event not accepted: {} ", event.getPayload() );
        }



        @Override
        public void stateChanged( State<SecurityState, SecurityEvent> from, State<SecurityState, SecurityEvent> to ) {
            if ( from == null ) {
                log.info( "State change to {}", to.getId() );
            } else {
                log.info( "State change from {} to {}", from.getId(), to.getId() );
            }

        }



        @Override
        public void stateMachineError( StateMachine<SecurityState, SecurityEvent> stateMachine, Exception exception ) {
            log.error( exception.getMessage(), exception );
        }

    }

}
