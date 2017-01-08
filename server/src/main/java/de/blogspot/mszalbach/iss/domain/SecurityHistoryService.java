package de.blogspot.mszalbach.iss.domain;

import de.blogspot.mszalbach.iss.audit.SecurityHistoryEntry;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ms on 12.12.16.
 */
@Service
public class SecurityHistoryService {

    private EntityManager entityManager;


    @Autowired
    public SecurityHistoryService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    public List<SecurityHistoryEntry> getHistory(Security security) {
        AuditQuery query = getAuditQuery();
        query.add(AuditEntity.id().eq(security.getId()));

        return mapAuditResultIntoHistoryEntryList(query);
    }


    @Transactional
    public List<SecurityHistoryEntry> getHistory() {
        AuditQuery query = getAuditQuery();
        return mapAuditResultIntoHistoryEntryList(query);
    }


    private AuditQuery getAuditQuery() {
        AuditReader reader = AuditReaderFactory.get(entityManager);
        return reader.createQuery()
            .forRevisionsOfEntity(Security.class, false, true);
    }


    private List<SecurityHistoryEntry> mapAuditResultIntoHistoryEntryList(AuditQuery query) {
        List<SecurityHistoryEntry> historyList = new ArrayList<>();
        for (Object result : query.getResultList()) {
            historyList.add(new SecurityHistoryEntry((Object[]) result));
        }
        return historyList;
    }

}
