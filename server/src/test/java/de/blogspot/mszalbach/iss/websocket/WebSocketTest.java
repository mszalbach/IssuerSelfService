package de.blogspot.mszalbach.iss.websocket;

import de.blogspot.mszalbach.iss.domain.Security;
import de.blogspot.mszalbach.iss.repo.SecurityRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.AbstractSubscribableChannel;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by ms on 25.11.16.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
@WebAppConfiguration
public class WebSocketTest {

    @Autowired
    private AbstractSubscribableChannel brokerChannel;

    private TestChannelInterceptor brokerChannelInterceptor;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SecurityRepository securityRepository;

    private MockMvc mockMvc;



    @Before
    public void setUp()
            throws Exception {

        this.brokerChannelInterceptor = new TestChannelInterceptor();

        this.brokerChannel.addInterceptor( this.brokerChannelInterceptor );

        this.mockMvc = webAppContextSetup( webApplicationContext ).build();
    }



    @Test
    public void should_inform_about_new_security_event()
            throws Exception {

        this.brokerChannelInterceptor.setIncludedDestinations( "/topic/newSecurity" );
        mockMvc.perform( post( "/api/securities" )
                                 .content( "{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}" ) )
               .andExpect( status().isCreated() );

        Security createdSecurity = securityRepository.findByIsin( "US02079K1079" ).get( 0 );

        Message wsMessage = brokerChannelInterceptor.awaitMessage( 5 );
        assertThat( wsMessage, notNullValue() );
        String message = new String( ( byte[] )wsMessage.getPayload(), Charset.forName( "UTF-8" ) );
        assertThat( message, is( "/api/securities/" + createdSecurity.getId() ) );


    }


}
