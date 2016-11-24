package de.blogspot.mszalbach.iss.domain;

import com.google.common.base.CaseFormat;
import de.blogspot.mszalbach.iss.statemachine.SecurityStateMachineAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.squirrelframework.foundation.fsm.ImmutableTransition;
import org.squirrelframework.foundation.fsm.StateMachine;

/**
 * Created by foobarkilla on 20.11.16.
 */
@BasePathAwareController
public class SecurityLinkProcessor
        implements ResourceProcessor<Resource<Security>> {

    @Autowired
    RepositoryEntityLinks entityLinks;

    @Autowired
    SecurityRepository repository;

    @Autowired
    SecurityStateMachineAdapter persister;



    @Override
    public Resource<Security> process( Resource<Security> securityResource ) {
        try {
            StateMachine machine = persister.restore( securityResource.getContent() );

            for ( Object transition : machine.getCurrentRawState().getAllTransitions() ) {
                ImmutableTransition t = ( ImmutableTransition )transition;
                securityResource.add( createLink( securityResource.getContent().getId(), t.getEvent() ) );
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }


        return securityResource;
    }



    private Link createLink( Object id, Object event ) {
        String rel = CaseFormat.UPPER_CAMEL.to( CaseFormat.LOWER_HYPHEN, event.toString() );
        return entityLinks.linkForSingleResource( Security.class, id ).slash( event ).withRel( rel );
    }
}
