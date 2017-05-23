package de.blogspot.mszalbach.iss.websocket;

import de.blogspot.mszalbach.iss.domain.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import static de.blogspot.mszalbach.iss.websocket.WebSocketConfiguration.MESSAGE_PREFIX;

/**
 * Created by Marcel Szalbach on 25.11.16.
 */
@Component
@RepositoryEventHandler(Security.class)
public class WebsocketEventHandler {

    public static final String NEW_SECURITY_TOPIC = MESSAGE_PREFIX + "/newSecurity";
    public static final String DELETE_SECURITY_TOPIC = MESSAGE_PREFIX + "/deleteSecurity";
    public static final String UPDATE_SECURITY_TOPIC = MESSAGE_PREFIX + "/updateSecurity";
    private final SimpMessagingTemplate websocket;

    private final EntityLinks entityLinks;


    @Autowired
    public WebsocketEventHandler(SimpMessagingTemplate websocket, EntityLinks entityLinks) {
        this.websocket = websocket;
        this.entityLinks = entityLinks;
    }


    @HandleAfterCreate
    public void newSecurity(Security security) {
        this.websocket.convertAndSend(
            NEW_SECURITY_TOPIC, getPath(security));
    }


    @HandleAfterDelete
    public void deleteSecurity(Security security) {
        this.websocket.convertAndSend(
            DELETE_SECURITY_TOPIC, getPath(security));
    }


    @HandleAfterSave
    public void updateSecurity(Security security) {
        this.websocket.convertAndSend(
            UPDATE_SECURITY_TOPIC, getPath(security));
    }


    private String getPath(Security security) {
        return this.entityLinks.linkForSingleResource(security.getClass(),
            security.getId()).toUri().getPath();
    }
}
