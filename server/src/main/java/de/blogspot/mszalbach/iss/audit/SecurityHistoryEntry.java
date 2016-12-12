package de.blogspot.mszalbach.iss.audit;

import de.blogspot.mszalbach.iss.domain.Security;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;

/**
 * Created by ms on 12.12.16.
 */
public class SecurityHistoryEntry {

    private Security              security;
    private DefaultRevisionEntity revisionEntity;
    private RevisionType          revisionType;



    public SecurityHistoryEntry( Object[] history ) {
        this.security = ( Security )history[ 0 ];
        //can not be serialized via json
       // this.revisionEntity = ( DefaultRevisionEntity )history[ 1 ];
        this.revisionType = ( RevisionType )history[ 2 ];
    }



    public Security getSecurity() {
        return security;
    }



    public void setSecurity( Security security ) {
        this.security = security;
    }



    public DefaultRevisionEntity getRevisionEntity() {
        return revisionEntity;
    }



    public void setRevisionEntity( DefaultRevisionEntity revisionEntity ) {
        this.revisionEntity = revisionEntity;
    }



    public RevisionType getRevisionType() {
        return revisionType;
    }



    public void setRevisionType( RevisionType revisionType ) {
        this.revisionType = revisionType;
    }
}
