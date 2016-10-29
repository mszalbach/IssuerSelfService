package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.Application;
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
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class SecurityRepositoryTest {

    @Autowired
    private SecurityRepository securityRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void cleanUp() {
        this.securityRepository.deleteAll();
    }

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void should_return_securities() throws Exception {
        securityRepository.save(new Security("testIsin1", "testSymbol1"));
        securityRepository.save(new Security("testIsin2", "testSymbol2"));

        assertThat(securityRepository.count(), is(2L));

        mockMvc.perform(get("/api/securities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.securities[0].isin", is("testIsin1")))
                .andExpect(jsonPath("_embedded.securities[1].isin", is("testIsin2")));
    }

    @Test
    public void should_find_securities_by_isin() throws Exception {
        securityRepository.save(new Security("testIsin1", "testSymbol1"));


        mockMvc.perform(get("/api/securities/search/findByIsin").param("isin", "testIsin1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.securities[0].isin", is("testIsin1")))
                .andExpect(jsonPath("_embedded.securities[0].symbol", is("testSymbol1")));
    }

    @Test
    public void should_be_able_to_create_securities() throws Exception {
        assertThat(securityRepository.count(), is(0L));
        mockMvc.perform(post("/api/securities").content("{\"isin\": \"testIsin1\",\"symbol\":\"testSymbol1\"}")).andExpect(status().isCreated());
        assertThat(securityRepository.count(), is(1L));
    }

}
