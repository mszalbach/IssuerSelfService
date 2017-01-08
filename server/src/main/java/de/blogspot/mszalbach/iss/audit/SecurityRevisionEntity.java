package de.blogspot.mszalbach.iss.audit;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@RevisionEntity(SecurityRevisionListener.class)
public class SecurityRevisionEntity {

    @Id
    @GeneratedValue
    @RevisionNumber
    private long id;

    @RevisionTimestamp
    private long timestamp;

    private String username;
    private String roles;


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SecurityRevisionEntity that = (SecurityRevisionEntity) o;
        return id == that.id &&
            timestamp == that.timestamp &&
            Objects.equals(username, that.username) &&
            Objects.equals(roles, that.roles);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, username, roles);
    }
}
