package de.blogspot.mszalbach.iss.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.blogspot.mszalbach.iss.validator.ISIN;
import org.squirrelframework.foundation.fsm.StateMachineData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

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
    @JsonProperty(required = true)
    private String     isin;
    private String     symbol;
    @Column( scale = 2 )
    @DecimalMin( "0" )
    private BigDecimal nominalValue;
    private String     issuer;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String     state;

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



    public Long getId() {
        return id;
    }



    public String getIsin() {
        return isin;
    }



    public void setIsin( String isin ) {
        this.isin = isin;
    }



    public String getSymbol() {
        return symbol;
    }



    public void setSymbol( String symbol ) {
        this.symbol = symbol;
    }



    public BigDecimal getNominalValue() {
        return nominalValue;
    }



    public void setNominalValue( BigDecimal nominalValue ) {
        this.nominalValue = nominalValue;
    }



    public String getIssuer() {
        return issuer;
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

}
