package com.credit.card.application.dto;

public class CreditCardResponseDTO {

	/**
	 * @param cardNumber
	 * @param cardLimit
	 * @param name
	 * @param cardBalance
	 */
	public CreditCardResponseDTO(String cardNumber, double cardLimit, String name, double cardBalance) {
		super();
		this.cardNumber = cardNumber;
		this.cardLimit = cardLimit;
		this.name = name;
		this.cardBalance = cardBalance;
	}
	
	private String cardNumber;

	private double cardLimit;

	private String name;

	private double cardBalance;

	public String getCardNumber() {
		return cardNumber;
	}

	public double getCardLimit() {
		return cardLimit;
	}

	public String getName() {
		return name;
	}

	public double getCardBalance() {
		return cardBalance;
	}


}
