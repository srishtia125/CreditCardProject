package com.credit.card.application.service.impl;

import com.credit.card.application.dto.CreditCardRequestDTO;
import com.credit.card.application.dto.CreditCardResponseDTO;
import com.credit.card.application.entity.CreditCard;
import com.credit.card.application.exceptions.CreditCardNotFoundException;
import com.credit.card.application.repository.CreditCardRepository;
import com.credit.card.application.service.CreditCardService;
import com.credit.card.application.util.CreditCardHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	private CreditCardRepository creditCardRepository;

    private Validator validator;

    @Autowired
    public CreditCardServiceImpl(CreditCardRepository creditCardRepository,Validator validator) {
        this.creditCardRepository = creditCardRepository;
        this.validator = validator;
    }



    @Override
	public List<CreditCardResponseDTO> getAllCreditCards() {

		List<CreditCard> allCards = creditCardRepository.findAll();
		return allCards.stream().map(card -> CreditCardHelper.getCreditCardResponseFromDB(card)).collect(Collectors.toList());
	}

	@Override
	public CreditCardResponseDTO addNewCreditCard(CreditCardRequestDTO creditCardRequest) {
        Set<ConstraintViolation<CreditCardRequestDTO>> constraintViolations = validator.validate(creditCardRequest);
        if(!constraintViolations.isEmpty()){
            throw new ConstraintViolationException(constraintViolations);
        }

        CreditCard creditCard = creditCardRepository.save(CreditCardHelper.getCreditCardRequestFromDTO(creditCardRequest));

		return CreditCardHelper.getCreditCardResponseFromDB(creditCard);
	}

	@Override
	public CreditCardResponseDTO getCreditCard(String creditCardNumber) {

		return CreditCardHelper.getCreditCardResponseFromDB(creditCardRepository.findById(creditCardNumber)
				.orElseThrow(()->new CreditCardNotFoundException("No Credit Card found with number : " + creditCardNumber)));
	}

}
