package de.blogspot.mszalbach.iss.websocket;

import de.blogspot.mszalbach.iss.RestRepositoryTestBase;
import de.blogspot.mszalbach.iss.domain.Security;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.AbstractSubscribableChannel;

import java.nio.charset.Charset;

import static de.blogspot.mszalbach.iss.websocket.WebsocketEventHandler.DELETE_SECURITY_TOPIC;
import static de.blogspot.mszalbach.iss.websocket.WebsocketEventHandler.NEW_SECURITY_TOPIC;
import static de.blogspot.mszalbach.iss.websocket.WebsocketEventHandler.UPDATE_SECURITY_TOPIC;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Marcel Szalbach on 25.11.16.
 */
public class WebSocketTest extends RestRepositoryTestBase {

    @Autowired
    private AbstractSubscribableChannel brokerChannel;

    private TestChannelInterceptor brokerChannelInterceptor;

    @Before
    public void setUp()
        throws Exception {
        super.setUp();
        this.brokerChannelInterceptor = new TestChannelInterceptor();
        this.brokerChannel.addInterceptor(this.brokerChannelInterceptor);
    }


    @Test
    public void should_inform_about_new_security_event()
        throws Exception {

        this.brokerChannelInterceptor.setIncludedDestinations(NEW_SECURITY_TOPIC);
        mockMvc.perform(post("/api/securities").with(asEmittent)
            .content(
                "{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}"))
            .andExpect(status().isCreated());

        Security createdSecurity = securityRepository.findByIsin("US02079K1079").get(0);

        Message wsMessage = brokerChannelInterceptor.awaitMessage(5);
        assertThat(wsMessage, notNullValue());
        String message = new String((byte[]) wsMessage.getPayload(), Charset.forName("UTF-8"));
        assertThat(message, is("/api/securities/" + createdSecurity.getId()));


    }


    @Test
    public void should_inform_about_deleted_security_event()
        throws Exception {

        this.brokerChannelInterceptor.setIncludedDestinations(DELETE_SECURITY_TOPIC);

        securityRepository.save(new Security("US02079K1079", "Alphabet Inc"));
        Security createdSecurity = securityRepository.findByIsin("US02079K1079").get(0);
        mockMvc.perform(delete("/api/securities/" + createdSecurity.getId()).with(asEmittent))
            .andExpect(status().isNoContent());

        Message wsMessage = brokerChannelInterceptor.awaitMessage(5);
        assertThat(wsMessage, notNullValue());
        String message = new String((byte[]) wsMessage.getPayload(), Charset.forName("UTF-8"));
        assertThat(message, is("/api/securities/" + createdSecurity.getId()));


    }


    @Test
    public void should_inform_about_updated_security_event()
        throws Exception {

        this.brokerChannelInterceptor.setIncludedDestinations(UPDATE_SECURITY_TOPIC);

        securityRepository.save(new Security("US02079K1079", "Alphabet Inc"));
        Security createdSecurity = securityRepository.findByIsin("US02079K1079").get(0);
        mockMvc.perform(post("/api/securities/" + createdSecurity.getId() + "/request").with(asEmittent)
        )
            .andExpect(status().isAccepted());

        Message wsMessage = brokerChannelInterceptor.awaitMessage(5);
        assertThat(wsMessage, notNullValue());
        String message = new String((byte[]) wsMessage.getPayload(), Charset.forName("UTF-8"));
        assertThat(message, is("/api/securities/" + createdSecurity.getId()));


    }


}
