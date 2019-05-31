package com.credit.card.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CreditCardNotFoundException extends RuntimeException{

	public CreditCardNotFoundException(String message) {
		super(message);
	}

}
