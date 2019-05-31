package com.credit.card.application.entity;

import javax.persistence.*;

@Entity
public class CreditCard {

	protected CreditCard() {
	}

	public CreditCard(String cardNumber, double cardLimit, String name) {
		super();
		this.cardNumber = cardNumber;
		this.cardLimit = cardLimit;
		this.name = name;
	}

	@Id
	private String cardNumber;

	private double cardLimit;

	private String name;

	@Column(columnDefinition="Decimal(10,2) default '0.00'")
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


