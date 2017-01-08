package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.RestRepositoryTestBase;
import de.blogspot.mszalbach.iss.audit.SecurityHistoryEntry;
import de.blogspot.mszalbach.iss.audit.SecurityRevisionListener;
import org.hibernate.envers.RevisionType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by ms on 12.12.16.
 */
public class SecurityHistoryServiceTest
    extends RestRepositoryTestBase {

    @Autowired
    private SecurityHistoryService historyService;


    @Test
    public void should_have_deleted_entry_after_deleting_security() {
        securityRepository.save(new Security("CH0123456789", "Symbol"));
        Security storedSecurity = securityRepository.findByIsin("CH0123456789").get(0);
        securityRepository.delete(storedSecurity);

        assertThat(historyService.getHistory(storedSecurity).size(), is(2));
        SecurityHistoryEntry history = historyService.getHistory(storedSecurity).get(1);
        assertThat(history.getRevisionType(), is(RevisionType.DEL));
        assertThat(history.getSecurity().getIsin(), is("CH0123456789"));
    }


    @Test
    public void should_provide_access_to_old_values() {
        securityRepository.save(new Security("CH0123456789", "Symbol"));
        Security storedSecurity = securityRepository.findByIsin("CH0123456789").get(0);
        storedSecurity.setNominalValue(BigDecimal.ZERO);
        securityRepository.save(storedSecurity);

        assertThat(historyService.getHistory(storedSecurity).size(), is(2));
        SecurityHistoryEntry lastHistory = historyService.getHistory(storedSecurity).get(1);
        assertThat(lastHistory.getSecurity().getNominalValue(), is(BigDecimal.ZERO.setScale(2)));
        SecurityHistoryEntry firstHistory = historyService.getHistory(storedSecurity).get(0);
        assertThat(firstHistory.getSecurity().getNominalValue(), is(nullValue()));
    }


    @Test
    public void should_not_set_user_when_no_auth_object_is_set() {
        securityRepository.save(new Security("CH0123456789", "Symbol"));
        Security storedSecurity = securityRepository.findByIsin("CH0123456789").get(0);
        SecurityHistoryEntry history = historyService.getHistory(storedSecurity).get(0);
        assertThat(history.getUsername(), is(SecurityRevisionListener.NO_USER_DETAILS));
    }


    @Test
    @WithMockUser(username = "Ralf")
    public void should_set_user_when_auth_object_is_set() {
        securityRepository.save(new Security("CH0123456789", "Symbol"));
        Security storedSecurity = securityRepository.findByIsin("CH0123456789").get(0);
        SecurityHistoryEntry history = historyService.getHistory(storedSecurity).get(0);
        assertThat(history.getUsername(), is("Ralf"));
    }


}
