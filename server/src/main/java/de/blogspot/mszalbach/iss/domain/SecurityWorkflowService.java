package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.auth.SecurityChecker;
import de.blogspot.mszalbach.iss.repo.SecurityRepository;
import de.blogspot.mszalbach.iss.statemachine.SecurityStateMachineAdapter;
import de.blogspot.mszalbach.iss.websocket.WebsocketEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.squirrelframework.foundation.fsm.ImmutableTransition;
import org.squirrelframework.foundation.fsm.StateMachine;
import org.squirrelframework.foundation.fsm.UntypedStateMachine;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to control workflow changes on Security. This class is also responsible to ensure which roles can access which
 * workflow transition.
 */
@Service
public class SecurityWorkflowService {

    private final SecurityStateMachineAdapter persister;
    private final SecurityRepository repository;
    private final WebsocketEventHandler websocketEventHandler;
    private final SecurityChecker checker;


    @Autowired
    public SecurityWorkflowService( SecurityStateMachineAdapter persister, SecurityRepository repository, WebsocketEventHandler websocketEventHandler, SecurityChecker checker ) {
        this.persister = persister;
        this.repository = repository;
        this.websocketEventHandler = websocketEventHandler;
        this.checker = checker;
    }


    @PreAuthorize("hasRole('EMITTENT') or hasRole('ADMIN')")
    public void request(Security security)
        throws Exception {
        fireEvent(security, "request");
    }


    @PreAuthorize("hasRole('ADMIN')")
    public void accept(Security security)
        throws Exception {
        fireEvent(security, "accept");
    }


    @PreAuthorize("hasRole('ADMIN')")
    public void cancel(Security security)
        throws Exception {
        fireEvent(security, "cancel");
    }


    public List<String> getEvents(Security security)
        throws Exception {

        List<String> events = new ArrayList<>();
        StateMachine machine = persister.restore(security);

        for (Object transition : machine.getCurrentRawState().getAllTransitions()) {
            ImmutableTransition t = (ImmutableTransition) transition;
            if (canAccessMethod(t.getEvent().toString())) {
                events.add((String) t.getEvent());
            }
        }
        return events;
    }


    private void fireEvent(Security security, String event)
        throws Exception {
        UntypedStateMachine stateMachine = persister.restore(security);

        if (stateMachine.canAccept(event)) {
            stateMachine.fire(event);
            persister.persist(stateMachine, security);
            repository.save(security);
            websocketEventHandler.updateSecurity(security);
        } else {
            throw new IllegalStateException();
        }
    }


    private boolean canAccessMethod(String method)
        throws NoSuchMethodException {
        Method me = this.getClass().getMethod(method, Security.class);
        PreAuthorize preAuthorize = me.getAnnotation(PreAuthorize.class);
        return preAuthorize == null || checker.check(preAuthorize.value());

    }
}
