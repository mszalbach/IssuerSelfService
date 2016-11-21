package de.blogspot.mszalbach.iss.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.blogspot.mszalbach.iss.jackson.SecurityStateDeserializer;

/**
 * Created by ms on 21.11.16.
 */
@JsonDeserialize(using = SecurityStateDeserializer.class)
public enum SecurityState {
    Open,
    Accepted,
    Canceled
}
