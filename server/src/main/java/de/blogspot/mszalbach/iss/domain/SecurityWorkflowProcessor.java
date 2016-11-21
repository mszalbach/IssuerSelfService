package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.statemachine.ContextEntity;
import de.blogspot.mszalbach.iss.statemachine.DefaultStateMachineAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.transition.Transition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;

/**
 * Created by foobarkilla on 20.11.16.
 */
@BasePathAwareController
public class SecurityWorkflowProcessor
        implements ResourceProcessor<Resource<Security>> {

    @Autowired
    RepositoryEntityLinks entityLinks;

    @Autowired
    SecurityRepository repository;

    @Autowired
    DefaultStateMachineAdapter<SecurityState, SecurityEvent, ContextEntity<SecurityState, SecurityEvent, Serializable>> stateMachineAdapter;



    @PostMapping( "/securities/{id}/request" )
    ResponseEntity<Security> request( @PathVariable( "id" ) Security security )
            throws Exception {
        return new ResponseEntity<>( security, HttpStatus.METHOD_NOT_ALLOWED );
    }



    @PostMapping( "/securities/{id}/accept" )
    ResponseEntity<Security> accept( @PathVariable( "id" ) Security security ) {
        return new ResponseEntity<>( security, HttpStatus.METHOD_NOT_ALLOWED );
    }



    @Override
    public Resource<Security> process( Resource<Security> securityResource ) {

        try {
            StateMachine<SecurityState, SecurityEvent> stateMachine = stateMachineAdapter
                    .restore( securityResource.getContent() );
            for ( Transition<SecurityState, SecurityEvent> transition : stateMachine.getTransitions() ) {
                if ( stateMachine.getState().getId().equals( transition.getSource().getId() ) ) {
                    SecurityEvent event = transition.getTrigger().getEvent();
                    securityResource.add( createLink( securityResource.getContent().getId(), event.toString() ) );
                }
            }

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return securityResource;
    }



    private Link createLink( Object id, String path ) {
        return entityLinks.linkForSingleResource( Security.class, id ).slash( path ).withRel( path + "-link" );
    }
}
