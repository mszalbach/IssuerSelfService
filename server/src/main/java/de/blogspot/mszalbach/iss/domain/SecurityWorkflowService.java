package de.blogspot.mszalbach.iss.domain;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Created by ms on 05.12.16.
 */
@Service
public class SecurityWorkflowService {


    //do not add this to rest controller use this at a service class
    @PreAuthorize( "hasRole('EMITTENT')" )
    public void update(Security security, String event) {

    }
}
