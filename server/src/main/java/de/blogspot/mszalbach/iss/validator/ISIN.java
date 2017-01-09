package de.blogspot.mszalbach.iss.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation to use IsinConstraintValidator.
 */
@Documented
@Constraint(validatedBy = IsinConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ISIN {

    String message() default "ISIN not Valid.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
