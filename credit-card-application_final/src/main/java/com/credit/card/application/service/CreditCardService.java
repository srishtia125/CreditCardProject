package com.credit.card.application.service;

import com.credit.card.application.dto.CreditCardRequestDTO;
import com.credit.card.application.dto.CreditCardResponseDTO;

import java.util.List;

public interface CreditCardService {

	
	List<CreditCardResponseDTO> getAllCreditCards();
	
	CreditCardResponseDTO addNewCreditCard(CreditCardRequestDTO creditCardRequest);

	CreditCardResponseDTO getCreditCard(String creditCardNumber);
	
}
