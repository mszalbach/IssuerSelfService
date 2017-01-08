package de.blogspot.mszalbach.iss.auth;

import de.blogspot.mszalbach.iss.RestRepositoryTestBase;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by foobarkilla on 29.10.16.
 */
public class AuthenticationTest
    extends RestRepositoryTestBase {

    @Test
    public void should_not_allow_access_to_resources_for_unauthorized()
        throws Exception {

        mockMvc.perform(get("/api/securities"))
            .andExpect(status().isUnauthorized());
    }


    @Test
    public void should_allow_access_to_resources_for_ralf()
        throws Exception {

        mockMvc.perform(get("/api/securities").with(httpBasic("Ralf", "ralf")))
            .andExpect(status().isOk()).andExpect(authenticated().withUsername("Ralf"));
    }


    @Test
    public void should_not_allow_access_to_resources_for_wrong_password()
        throws Exception {

        mockMvc.perform(get("/api/securities").with(httpBasic("Ralf", "wrong")))
            .andExpect(status().isUnauthorized());
    }


    @Test
    public void should_not_allow_access_to_resources_for_wrong_user()
        throws Exception {

        mockMvc.perform(get("/api/securities").with(httpBasic("Ralf.Wrong", "ralf")))
            .andExpect(status().isUnauthorized());
    }


    @Test
    public void should_allow_access_to_session_resource()
        throws Exception {

        mockMvc.perform(get("/api/session")).andExpect(status().isOk());
    }


    @Test
    public void should_generate_a_token_when_logging_in_via_session()
        throws Exception {
        mockMvc.perform(post("/api/session").contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"Ralf\",\"password\":\"ralf\"}"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("token", is(notNullValue())))
            .andExpect(jsonPath("userName", is("Ralf")));
    }

}
