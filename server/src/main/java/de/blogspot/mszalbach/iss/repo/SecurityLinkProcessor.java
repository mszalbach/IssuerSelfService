package de.blogspot.mszalbach.iss.repo;

import com.google.common.base.CaseFormat;
import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.domain.SecurityWorkflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

/**
 * Created by foobarkilla on 20.11.16.
 */
@BasePathAwareController
public class SecurityLinkProcessor
    implements ResourceProcessor<Resource<Security>> {

    private final RepositoryEntityLinks entityLinks;

    private final SecurityWorkflowService workflowService;


    @Autowired
    public SecurityLinkProcessor(RepositoryEntityLinks entityLinks, SecurityWorkflowService workflowService) {
        this.entityLinks = entityLinks;
        this.workflowService = workflowService;
    }


    @Override
    public Resource<Security> process(Resource<Security> securityResource) {
        try {

            for (String transition : workflowService.getEvents(securityResource.getContent())) {
                securityResource.add(createLink(securityResource.getContent().getId(), transition));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return securityResource;
    }


    private Link createLink(Object id, Object event) {
        String rel = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, event.toString());
        return entityLinks.linkForSingleResource(Security.class, id).slash(event).withRel(rel);
    }
}
