package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.statemachine.security.SecurityStateMachineConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
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
    StateMachineFactory<SecurityState, SecurityEvent> orderStateMachineFactory;



    @Test
    public void testWorkflow()
            throws Exception {
        StateMachine<SecurityState, SecurityEvent> stateMachine = orderStateMachineFactory.getStateMachine();
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
                        .build();
        plan.test();
    }

}
