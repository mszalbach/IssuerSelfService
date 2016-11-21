package de.blogspot.mszalbach.iss.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.blogspot.mszalbach.iss.statemachine.ContextEntity;
import de.blogspot.mszalbach.iss.validator.ISIN;
import org.springframework.statemachine.StateMachineContext;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by foobarkilla on 22.10.16.
 */
@Entity
@JsonIgnoreProperties( ignoreUnknown = true )
public class Security
        implements ContextEntity<SecurityState, SecurityEvent, Serializable> {

    @Id
    @GeneratedValue
    private Long       id;
    @ISIN
    private String     isin;
    private String     symbol;
    @Column( scale = 2 )
    @DecimalMin( "0" )
    private BigDecimal nominalValue;
    private String     issuer;

    @Enumerated( EnumType.STRING )
    SecurityState currentState;

    @JsonIgnore
    StateMachineContext<SecurityState, SecurityEvent> stateMachineContext;



    public Security() {
    }



    public Security( String isin, String symbol ) {
        this.isin = isin;
        this.symbol = symbol;
    }



    @Override
    public Long getId() {
        return id;
    }



    public String getIsin() {
        return isin;
    }



    public String getSymbol() {
        return symbol;
    }



    public BigDecimal getNominalValue() {
        return nominalValue;
    }



    public String getIssuer() {
        return issuer;
    }



    public SecurityState getCurrentState() {
        return currentState;
    }



    @Override
    public StateMachineContext<SecurityState, SecurityEvent> getStateMachineContext() {
        return this.stateMachineContext;
    }



    @Override
    public void setStateMachineContext( StateMachineContext<SecurityState, SecurityEvent> stateMachineContext ) {
        this.currentState = stateMachineContext.getState();
        this.stateMachineContext = stateMachineContext;
    }



    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        Security security = ( Security )o;
        return Objects.equals( isin, security.isin );
    }



    @Override
    public int hashCode() {
        return Objects.hash( isin );
    }


}
