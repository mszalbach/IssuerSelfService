package de.blogspot.mszalbach.iss;

import de.blogspot.mszalbach.iss.repo.SecurityRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by ms on 07.12.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public abstract class RestRepositoryTestBase {

    @Autowired
    protected SecurityRepository securityRepository;
    protected MockMvc mockMvc;
    protected RequestPostProcessor asEmittent = httpBasic("Ralf", "ralf");
    protected RequestPostProcessor asAdmin = httpBasic("Marcel", "marcel");
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private FilterChainProxy filterChainProxy;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).dispatchOptions(true)
            .addFilters(filterChainProxy).build();
    }


    @After
    public void cleanUp() {
        this.securityRepository.deleteAll();
    }

}
