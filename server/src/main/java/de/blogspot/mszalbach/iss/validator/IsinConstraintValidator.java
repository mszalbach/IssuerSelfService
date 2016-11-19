package de.blogspot.mszalbach.iss.validator;

import org.apache.commons.validator.routines.checkdigit.ISINCheckDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by foobarkilla on 19.11.16.
 */
public class IsinConstraintValidator implements ConstraintValidator<ISIN, String> {
    public void initialize(ISIN constraint) {
    }

    @Override
    public boolean isValid(String isin, ConstraintValidatorContext context) {
        return new ISINCheckDigit().isValid(isin);
    }

}
