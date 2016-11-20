package de.blogspot.mszalbach.iss.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by foobarkilla on 20.11.16.
 */
@BasePathAwareController
public class SecurityWorkflowProcessor implements ResourceProcessor<Resource<Security>> {

    @Autowired
    RepositoryEntityLinks entityLinks;

    @Autowired
    SecurityRepository repository;

    @PostMapping("/securities/{id}/request")
    ResponseEntity<Security> request(@PathVariable("id") Security security) {
        return new ResponseEntity<>(security, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PostMapping("/securities/{id}/accept")
    ResponseEntity<Security> accept(@PathVariable("id") Security security) {
        return new ResponseEntity<>(security, HttpStatus.METHOD_NOT_ALLOWED);
    }


    @Override
    public Resource<Security> process(Resource<Security> securityResource) {

        Long securityId = securityResource.getContent().id;
        securityResource.add(
                createLink(securityId, "request"),
                createLink(securityId, "accept")
        );
        return securityResource;
    }


    private Link createLink(Object id, String path) {
        return entityLinks.linkForSingleResource(Security.class, id).slash(path).withRel(path + "-link");
    }
}
