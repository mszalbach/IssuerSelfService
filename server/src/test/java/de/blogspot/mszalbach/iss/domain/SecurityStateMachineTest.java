package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.statemachine.SecurityStateMachineFactory;
import de.blogspot.mszalbach.iss.statemachine.SecurityStateMachineAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.squirrelframework.foundation.fsm.StateMachine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ms on 21.11.16.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest( classes = { SecurityStateMachineAdapter.class, SecurityStateMachineFactory.class } )
public class SecurityStateMachineTest {

    @Autowired
    private SecurityStateMachineAdapter stateMachineFactory;

    StateMachine machine;



    @Before
    public void init() {
        machine = stateMachineFactory.create();
    }



    @Test
    public void should_start_in_open_state()
            throws Exception {
        assertThat( machine.getCurrentState(), is( "Open" ) );
    }



    @Test
    public void should_change_to_requested_when_using_request_on_open_state() {
        machine.fire( "request" );
        assertThat( machine.getCurrentState(), is( "Requested" ) );
    }

    @Test
    public void should_not_be_able_to_use_cancel_on_open_state() {
        assertThat( machine.canAccept("cancel"), is( false ) );
        machine.fire( "cancel" );
        assertThat( machine.getCurrentState(), is( "Open" ) );
    }

}
