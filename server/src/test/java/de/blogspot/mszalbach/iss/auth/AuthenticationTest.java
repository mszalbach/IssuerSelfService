package de.blogspot.mszalbach.iss.auth;

import de.blogspot.mszalbach.iss.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by foobarkilla on 29.10.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class, AuthenticationConfiguration.class})
@WebAppConfiguration
public class AuthenticationTest {

    private MockMvc mockMvc;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).dispatchOptions(true).addFilters(filterChainProxy).build();
    }


    @Test
    public void should_not_allow_access_to_resources_for_unauthorized() throws Exception {

        mockMvc.perform(get("/api/securities"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_allow_access_to_resources_for_ralf() throws Exception {

        mockMvc.perform(get("/api/securities").with(httpBasic("Ralf", "ralf")))
                .andExpect(status().isOk()).andExpect(authenticated().withUsername("Ralf"));
    }

    @Test
    public void should_not_allow_access_to_resources_for_wrong_password() throws Exception {

        mockMvc.perform(get("/api/securities").with(httpBasic("Ralf", "wrong")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_not_allow_access_to_resources_for_wrong_user() throws Exception {

        mockMvc.perform(get("/api/securities").with(httpBasic("Ralf.Wrong", "ralf")))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void should__allow_access_to_session_resource() throws Exception {

        mockMvc.perform(get("/api/session")).andExpect(status().isOk());
    }

}
