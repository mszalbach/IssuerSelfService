package de.blogspot.mszalbach.iss.websocket;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by Marcel Szalbach on 25.11.16.
 */
@Component
@EnableWebSocketMessageBroker
public class WebSocketConfiguration
    extends AbstractWebSocketMessageBrokerConfigurer {

    public static final String MESSAGE_PREFIX = "/topic";


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //cors problem without setAllowedOrigins
        registry.addEndpoint("/sockjs").setAllowedOrigins("*").withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker(MESSAGE_PREFIX);
        registry.setApplicationDestinationPrefixes("/app");
    }
}
