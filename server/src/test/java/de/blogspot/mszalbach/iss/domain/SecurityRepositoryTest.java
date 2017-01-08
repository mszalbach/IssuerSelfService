package de.blogspot.mszalbach.iss.domain;

import com.jayway.jsonpath.JsonPath;
import de.blogspot.mszalbach.iss.RestRepositoryTestBase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by foobarkilla on 29.10.16.
 */
public class SecurityRepositoryTest
    extends RestRepositoryTestBase {

    @Test
    public void should_return_securities()
        throws Exception {
        securityRepository.save(new Security("US02079K1079", "Alphabet Inc"));
        securityRepository.save(new Security("US5949181045", "Microsoft Corp"));

        assertThat(securityRepository.count(), is(2L));

        mockMvc.perform(get("/api/securities").with(asEmittent))
            .andExpect(status().isOk())
            .andExpect(jsonPath("_embedded.securities[0].isin", is("US02079K1079")))
            .andExpect(jsonPath("_embedded.securities[1].isin", is("US5949181045")));
    }


    @Test
    public void should_find_securities_by_isin()
        throws Exception {
        securityRepository.save(new Security("US02079K1079", "Alphabet Inc"));


        mockMvc.perform(get("/api/securities/search/findByIsin").param("isin", "US02079K1079").with(asEmittent))
            .andExpect(status().isOk())
            .andExpect(jsonPath("_embedded.securities[0].isin", is("US02079K1079")))
            .andExpect(jsonPath("_embedded.securities[0].symbol", is("Alphabet Inc")));
    }


    @Test
    public void should_be_able_to_create_securities()
        throws Exception {
        assertThat(securityRepository.count(), is(0L));
        mockMvc.perform(post("/api/securities")
            .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}")
            .with(asEmittent))
            .andExpect(status().isCreated());
        assertThat(securityRepository.count(), is(1L));
    }


    @Test
    public void should_return_shema_for_securities()
        throws Exception {
        mockMvc.perform(get("/api/profile/securities")
            .accept("application/schema+json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("title", is("Security")));
    }


    @Test
    public void should_not_allow_security_without_isin()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content("{\"symbol\":\"Alphabet Inc\"}").with(asEmittent))
            .andExpect(status().isBadRequest());
    }


    @Test
    public void should_not_allow_security_without_conform_isin()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content("{\"isin\": \"US02079K1079000\",\"symbol\":\"To many digits\"}")
            .with(asEmittent))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("errors[0].message", is("ISIN not Valid.")));
    }


    @Test
    public void should_not_allow_negative_nominalValue()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content(
                "{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\",\"nominalValue\": -1}")
            .with(asEmittent))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("errors[0].message", is("must be greater than or equal to 0")));
    }


    @Test
    public void should_create_securities_with_state_open()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}")
            .with(asEmittent))
            .andExpect(status().isCreated());
        Security security = securityRepository.findByIsin("US02079K1079").get(0);
        assertThat(security.getState(), is("Open"));
    }


    @Test
    public void should_provide_links_to_events()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}")
            .with(asEmittent))
            .andExpect(status().isCreated());

        mockMvc.perform(get("/api/securities/search/findByIsin").param("isin", "US02079K1079").with(asEmittent))
            .andExpect(status().isOk())
            .andExpect(jsonPath("_embedded.securities[0].isin", is("US02079K1079")))
            .andExpect(jsonPath("_embedded.securities[0]._links.request.href", containsString("/request")));
    }


    @Test
    public void should_switch_state_to_requested_on_request_event()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}")
            .with(asEmittent))
            .andExpect(status().isCreated());

        String result = mockMvc.perform(
            get("/api/securities/search/findByIsin").param("isin", "US02079K1079").with(asEmittent))
            .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        Object requestUrl = JsonPath.read(result, "_embedded.securities[0]._links.request.href");

        mockMvc.perform(post(requestUrl.toString()).with(asEmittent)).andExpect(status().isAccepted());

        Security requestedSecurity = securityRepository.findByIsin("US02079K1079").get(0);

        assertThat(requestedSecurity.getState(), is("Requested"));
    }


    @Test
    public void should_not_allow_accept_for_emittent()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}")
            .with(asEmittent))
            .andExpect(status().isCreated());

        Security security = securityRepository.findByIsin("US02079K1079").get(0);
        mockMvc.perform(post("/api/securities/" + security.getId() + "/request")
            .with(asEmittent))
            .andExpect(status().isAccepted());

        mockMvc.perform(post("/api/securities/" + security.getId() + "/accept")
            .with(asEmittent))
            .andExpect(status().isForbidden());
    }


    @Test
    public void should_allow_accept_for_admin()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}")
            .with(asEmittent))
            .andExpect(status().isCreated());

        Security security = securityRepository.findByIsin("US02079K1079").get(0);
        mockMvc.perform(post("/api/securities/" + security.getId() + "/request")
            .with(asEmittent))
            .andExpect(status().isAccepted());

        mockMvc.perform(post("/api/securities/" + security.getId() + "/accept")
            .with(asAdmin))
            .andExpect(status().isAccepted());

        security = securityRepository.findOne(security.getId());
        assertThat(security.getState(), is("Accepted"));
    }


    @Test
    public void should_return_history_for_created_security()
        throws Exception {
        mockMvc.perform(post("/api/securities")
            .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}")
            .with(asEmittent))
            .andExpect(status().isCreated());

        Security createdSecurity = securityRepository.findByIsin("US02079K1079").get(0);
        String a = mockMvc.perform(get("/api/securities/" + createdSecurity.getId() + "/history")
            .with(asEmittent))
            .andExpect(status().isOk()).andExpect(jsonPath("$[0].revisionType", is("ADD"))).andReturn().getResponse().getContentAsString();

        System.out.println(a);

    }

}
