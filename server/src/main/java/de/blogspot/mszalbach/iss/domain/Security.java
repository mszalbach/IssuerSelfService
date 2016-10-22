package de.blogspot.mszalbach.iss.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Created by foobarkilla on 22.10.16.
 */
@Entity
public class Security {

    @Id
    @GeneratedValue
    private Long id;
    public String isin;
    public String symbol;

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
        return Objects.equals(isin, security.isin) &&
                Objects.equals(symbol, security.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin, symbol);
    }

    @Override
    public String toString() {
        return "Security{" +
                "isin='" + isin + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
