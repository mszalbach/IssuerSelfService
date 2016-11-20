package de.blogspot.mszalbach.iss.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import de.blogspot.mszalbach.iss.validator.ISIN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by foobarkilla on 22.10.16.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Security {

    @Id
    @GeneratedValue
    public Long id;
    @ISIN
    public String isin;
    public String symbol;
    @Column(scale = 2)
    @DecimalMin("0")
    public BigDecimal nominalValue;
    public String issuer;

    public Security() {
    }

    public Security(String isin, String symbol) {
        this.isin = isin;
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Security security = (Security) o;
        return Objects.equals(isin, security.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin);
    }
}
