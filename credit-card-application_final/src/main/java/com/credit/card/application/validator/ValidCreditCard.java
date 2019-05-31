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
@Constraint(validatedBy = Luhn10CreditCardValidator.class)
public @interface ValidCreditCard {
    String message() default "Credit card number is invalid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
