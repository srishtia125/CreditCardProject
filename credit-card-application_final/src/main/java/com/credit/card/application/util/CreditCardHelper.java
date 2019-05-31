package com.credit.card.application.util;

import com.credit.card.application.dto.CreditCardRequestDTO;
import com.credit.card.application.dto.CreditCardResponseDTO;
import com.credit.card.application.entity.CreditCard;

public class CreditCardHelper {

	public static CreditCard getCreditCardRequestFromDTO(CreditCardRequestDTO creditCardRequest) {

		return new CreditCard(creditCardRequest.getCardNumber(), creditCardRequest.getCardLimit(), creditCardRequest.getName());

	}

	public static CreditCardResponseDTO getCreditCardResponseFromDB(CreditCard creditCardRespone) {

		CreditCardResponseDTO creditCardResponse = new CreditCardResponseDTO(creditCardRespone.getCardNumber(),
				creditCardRespone.getCardLimit(), creditCardRespone.getName(), creditCardRespone.getCardBalance());

		return creditCardResponse;

	}

}
