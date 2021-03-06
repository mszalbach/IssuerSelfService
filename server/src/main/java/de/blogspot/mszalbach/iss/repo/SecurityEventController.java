package de.blogspot.mszalbach.iss.repo;

import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.domain.SecurityWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Will listen to rest methods which should switch the workflow and propagate them to the corresponding service class.
 */
@BasePathAwareController
public class SecurityEventController {


    private final SecurityWorkflowService service;


    @Autowired
    public SecurityEventController(SecurityWorkflowService service) {
        this.service = service;
    }


    @PostMapping(path = "/securities/{id}/{event}")
    //TODO remove switch statement
    public ResponseEntity<Void> receiveEvent(@PathVariable("id") Security security, @PathVariable("event") String event)
        throws Exception {
        switch (event) {
            case "request":
                service.request(security);
                break;
            case "accept":
                service.accept(security);
                break;
            case "cancel":
                service.cancel(security);
            default:
                return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.accepted().build();

    }

}
