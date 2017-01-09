package de.blogspot.mszalbach.iss.audit;

import de.blogspot.mszalbach.iss.domain.Security;
import org.hibernate.envers.RevisionType;

import java.util.Date;

public class SecurityHistoryEntry {

    private long id;
    private Security security;
    private Date revisionDate;
    private RevisionType revisionType;
    private String username;
    private String roles;


    public SecurityHistoryEntry(Object[] history) {
        this.security = (Security) history[0];
        SecurityRevisionEntity revisionEntity = (SecurityRevisionEntity) history[1];
        this.id = revisionEntity.getId();
        this.username = revisionEntity.getUsername();
        this.roles = revisionEntity.getRoles();
        this.revisionDate = new Date(revisionEntity.getTimestamp());
        this.revisionType = (RevisionType) history[2];
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public Security getSecurity() {
        return security;
    }


    public void setSecurity(Security security) {
        this.security = security;
    }


    public Date getRevisionDate() {
        return revisionDate;
    }


    public void setRevisionDate(Date revisionEntity) {
        this.revisionDate = revisionDate;
    }


    public RevisionType getRevisionType() {
        return revisionType;
    }


    public void setRevisionType(RevisionType revisionType) {
        this.revisionType = revisionType;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getRoles() {
        return roles;
    }


    public void setRoles(String roles) {
        this.roles = roles;
    }
}
