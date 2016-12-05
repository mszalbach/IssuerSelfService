package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.repo.SecurityRepository;
import de.blogspot.mszalbach.iss.statemachine.SecurityStateMachineAdapter;
import de.blogspot.mszalbach.iss.websocket.WebsocketEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.squirrelframework.foundation.fsm.ImmutableTransition;
import org.squirrelframework.foundation.fsm.StateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ms on 05.12.16.
 */
@Service
public class SecurityWorkflowService {

    private final SecurityStateMachineAdapter persister;
    private final SecurityRepository          repository;
    private final WebsocketEventHandler       websocketEventHandler;



    @Autowired
    public SecurityWorkflowService( SecurityStateMachineAdapter persister, SecurityRepository repository, WebsocketEventHandler websocketEventHandler ) {
        this.persister = persister;
        this.repository = repository;
        this.websocketEventHandler = websocketEventHandler;
    }



    //@PreAuthorize( "hasRole('EMITTENT')" )
    public void request( Security security )
            throws Exception {
        fireEvent( security, "request" );
    }



    public void accept( Security security )
            throws Exception {
        fireEvent( security, "accept" );
    }



    public void cancel( Security security )
            throws Exception {
        fireEvent( security, "cancel" );
    }



    //TODO check if event is allowed for current user based on PreAuthorize
    public List<String> getEvents( Security security )
            throws Exception {
        List<String> events = new ArrayList<>();
        StateMachine machine = persister.restore( security );

        for ( Object transition : machine.getCurrentRawState().getAllTransitions() ) {
            ImmutableTransition t = ( ImmutableTransition )transition;
            events.add( ( String )t.getEvent() );
        }
        return events;
    }



    private void fireEvent( Security security, String event )
            throws Exception {
        UntypedStateMachine stateMachine = persister.restore( security );

        if ( stateMachine.canAccept( event ) ) {
            stateMachine.fire( event );
            persister.persist( stateMachine, security );
            repository.save( security );
            websocketEventHandler.updateSecurity( security );
        } else {
            throw new IllegalStateException();
        }
    }
}
