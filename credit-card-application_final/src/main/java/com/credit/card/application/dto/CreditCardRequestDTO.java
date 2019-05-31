package com.credit.card.application.dto;

import com.credit.card.application.validator.UniqueCreditCardNumber;
import com.credit.card.application.validator.ValidCreditCard;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditCardRequestDTO {

	public CreditCardRequestDTO() {
	}

	public CreditCardRequestDTO(@NotBlank(message = "Card Number is required")
								@Size(max = 19, message = "Max length for credit card can be 19")
								@Pattern(regexp = "^([0-9]*)$", message = "Card number can only be numbers") String cardNumber,
								@NotBlank(message = "Name is required for credit card") String name) {
		this.cardNumber = cardNumber;
		this.name = name;
	}

	@NotBlank(message = "Card Number is required")
	@Size(max = 19 , message = "Max length for credit card can be 19")
	@Pattern(regexp="^([0-9]*)$" , message = "Card number can only be numbers")
	@ValidCreditCard
	@UniqueCreditCardNumber
	private String cardNumber;

	@NotNull
	private double cardLimit;

	@NotBlank(message = "Name is required for credit card")
	private String name;


	public String getCardNumber() {
		return cardNumber;
	}


	public double getCardLimit() {
		return cardLimit;
	}

	public String getName() {
		return name;
	}




}
