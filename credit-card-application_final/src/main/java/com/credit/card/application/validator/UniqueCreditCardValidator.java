package com.credit.card.application.validator;

import com.credit.card.application.exceptions.CreditCardNotFoundException;
import com.credit.card.application.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Srishti Agrawal
 */
@Component
public class UniqueCreditCardValidator implements ConstraintValidator<UniqueCreditCardNumber, String> {

    @Autowired
    private CreditCardService creditCardService;

    @Override
    public boolean isValid(String ccNumber, ConstraintValidatorContext constraintValidatorContext) {

        try {
            creditCardService.getCreditCard(ccNumber);
        }catch(CreditCardNotFoundException e){
            return true;
        }
        return false;
    }
}
