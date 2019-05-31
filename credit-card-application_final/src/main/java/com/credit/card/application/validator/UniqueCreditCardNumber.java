package com.credit.card.application.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Srishti Agrawal
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCreditCardValidator.class)
public @interface UniqueCreditCardNumber {
    String message() default "Credit card already exist with this number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
