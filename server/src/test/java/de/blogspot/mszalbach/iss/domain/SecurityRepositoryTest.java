package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.Application;
import de.blogspot.mszalbach.iss.statemachine.DefaultStateMachineAdapter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by foobarkilla on 29.10.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class,DefaultStateMachineAdapter.class } )
@WebAppConfiguration
public class SecurityRepositoryTest {

    @Autowired
    private SecurityRepository securityRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void cleanUp() {
        this.securityRepository.deleteAll();
    }


    @Test
    public void should_return_securities() throws Exception {
        securityRepository.save(new Security("US02079K1079", "Alphabet Inc"));
        securityRepository.save(new Security("US5949181045", "Microsoft Corp"));

        assertThat(securityRepository.count(), is(2L));

        mockMvc.perform(get("/api/securities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.securities[0].isin", is("US02079K1079")))
                .andExpect(jsonPath("_embedded.securities[1].isin", is("US5949181045")));
    }

    @Test
    public void should_find_securities_by_isin() throws Exception {
        securityRepository.save(new Security("US02079K1079", "Alphabet Inc"));


        mockMvc.perform(get("/api/securities/search/findByIsin").param("isin", "US02079K1079"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.securities[0].isin", is("US02079K1079")))
                .andExpect(jsonPath("_embedded.securities[0].symbol", is("Alphabet Inc")));
    }

    @Test
    public void should_be_able_to_create_securities() throws Exception {
        assertThat(securityRepository.count(), is(0L));
        mockMvc.perform(post("/api/securities")
                .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\"}"))
                .andExpect(status().isCreated());
        assertThat(securityRepository.count(), is(1L));
    }

    @Test
    public void should_return_shema_for_securities() throws Exception {
        mockMvc.perform(get("/api/profile/securities")
                .accept("application/schema+json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is("Security")));
    }

    @Test
    public void should_not_allow_security_without_isin() throws Exception {
        mockMvc.perform(post("/api/securities")
                .content("{\"symbol\":\"Alphabet Inc\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_not_allow_security_without_conform_isin() throws Exception {
        mockMvc.perform(post("/api/securities")
                .content("{\"isin\": \"US02079K1079000\",\"symbol\":\"To many digits\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors[0].message", is("ISIN not Valid.")));
    }

    @Test
    public void should_not_allow_negative_nominalValue() throws Exception {
        mockMvc.perform(post("/api/securities")
                .content("{\"isin\": \"US02079K1079\",\"symbol\":\"Alphabet Inc\",\"nominalValue\": -1}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors[0].message", is("must be greater than or equal to 0")));
    }

}
