package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.statemachine.ContextEntity;
import de.blogspot.mszalbach.iss.statemachine.DefaultStateMachineAdapter;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by ms on 21.11.16.
 */
@Component
public class SecurityCreatedEventListener
        extends AbstractRepositoryEventListener<Security> {

    private DefaultStateMachineAdapter<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, ? extends Serializable>> orderStateMachineAdapter;



    public SecurityCreatedEventListener( DefaultStateMachineAdapter<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, ? extends Serializable>> orderStateMachineAdapter ) {
        this.orderStateMachineAdapter = orderStateMachineAdapter;
    }



    @Override
    protected void onBeforeCreate( Security security ) {
        try {
            orderStateMachineAdapter.persist( orderStateMachineAdapter.create(), security );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
