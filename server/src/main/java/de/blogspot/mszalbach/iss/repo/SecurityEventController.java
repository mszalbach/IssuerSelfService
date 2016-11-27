package de.blogspot.mszalbach.iss.repo;

import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.statemachine.SecurityStateMachineAdapter;
import de.blogspot.mszalbach.iss.websocket.WebsocketEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;

/**
 * Created by ms on 24.11.16.
 */
@BasePathAwareController
public class SecurityEventController {


    private SecurityStateMachineAdapter persister;


    private SecurityRepository repository;

    private final WebsocketEventHandler websocketEventHandler;


    @Autowired
    public SecurityEventController(SecurityStateMachineAdapter persister, SecurityRepository repository, WebsocketEventHandler websocketEventHandler) {
        this.persister = persister;
        this.repository = repository;
        this.websocketEventHandler = websocketEventHandler;
    }


    @PostMapping(path = "/securities/{id}/{event}")
    public ResponseEntity<Void> receiveEvent(@PathVariable("id") Security security, @PathVariable("event") String event)
            throws Exception {
        UntypedStateMachine stateMachine = persister.restore(security);


        if (stateMachine.canAccept(event)) {
            stateMachine.fire(event);
            persister.persist(stateMachine, security);
            repository.save(security);
            websocketEventHandler.updateSecurity(security);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
