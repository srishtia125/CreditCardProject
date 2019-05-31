package com.credit.card.application.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Srishti Agrawal
 */
@Component
public class Luhn10CreditCardValidator implements ConstraintValidator<ValidCreditCard, String> {

    @Override
    public boolean isValid(String ccNumber, ConstraintValidatorContext constraintValidatorContext) {
        try{
            int sum = 0;
            boolean alternate = false;
            for (int i = ccNumber.length() - 1; i >= 0; i--)
            {
                int n = Integer.parseInt(ccNumber.substring(i, i + 1));
                if (alternate)
                {
                    n *= 2;
                    if (n > 9)
                    {
                        n = (n % 10) + 1;
                    }
                }
                sum += n;
                alternate = !alternate;
            }
            return (sum % 10 == 0);
        }catch (NumberFormatException ex){
            return false;
        }
    }
}
