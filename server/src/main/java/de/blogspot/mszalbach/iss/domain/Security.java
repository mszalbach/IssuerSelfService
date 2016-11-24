package de.blogspot.mszalbach.iss.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.blogspot.mszalbach.iss.validator.ISIN;
import org.squirrelframework.foundation.fsm.StateMachineData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by foobarkilla on 22.10.16.
 */
@Entity
@JsonIgnoreProperties( ignoreUnknown = true )
public class Security {

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

    private String state;

    @JsonIgnore
    @Column( columnDefinition = "TEXT" )
    private StateMachineData.Reader stateMachine;



    public Security() {
    }



    public Security( String isin, String symbol ) {
        this.isin = isin;
        this.symbol = symbol;
    }



    public String getState() {
        return state;
    }



    public void setState( String state ) {
        this.state = state;
    }



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


    public void setIsin( String isin ) {
        this.isin = isin;
    }



    public void setSymbol( String symbol ) {
        this.symbol = symbol;
    }



    public void setNominalValue( BigDecimal nominalValue ) {
        this.nominalValue = nominalValue;
    }



    public void setId( Long id ) {
        this.id = id;
    }



    public void setIssuer( String issuer ) {
        this.issuer = issuer;
    }


    public StateMachineData.Reader getStateMachine() {
        return this.stateMachine;
    }



    public void setStateMachine( StateMachineData.Reader stateMachine ) {
        this.state = stateMachine.currentState().toString();
        this.stateMachine = stateMachine;
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
