package com.credit.card.application.test;


import com.credit.card.application.application.ApplicationServer;
import com.credit.card.application.dto.CreditCardRequestDTO;
import com.credit.card.application.dto.CreditCardResponseDTO;
import com.credit.card.application.exceptions.CreditCardNotFoundException;
import com.credit.card.application.repository.CreditCardRepository;
import com.credit.card.application.service.CreditCardService;
import com.credit.card.application.service.impl.CreditCardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationServer.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CreditCardServiceTest {

	private CreditCardService creditCardService;

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private Validator validator;

	private final String validCreditCard01 = "4012888888881881";
    private final String validCreditCard02 = "6011000990139424";

	@Before
	public void setup() {
		creditCardService = new CreditCardServiceImpl(creditCardRepository,validator);
	}

	/***
	 * 1. Validate card is saved in database with correct details
	 * 2. Validate that balance is initialised with 0
	 */
	@Test()
	public void addNewCreditCardSuccessTest() {
		CreditCardRequestDTO card = new CreditCardRequestDTO(validCreditCard01,"Valid Card 01");
		creditCardService.addNewCreditCard(card);
		CreditCardResponseDTO creditCard = creditCardService.getCreditCard(card.getCardNumber());
		assertEquals(creditCard.getName(), "Valid Card 01");
		assertEquals(validCreditCard01,creditCard.getCardNumber());
		assertEquals(0 , creditCard.getCardBalance(),0);
	}

	/***
	 * Validate error is returned if both card number and name is blank
	 */
	@Test()
	public void addNewCreditCardWithBlankNumberAndBlankNameTest() {
		CreditCardRequestDTO card = new CreditCardRequestDTO("","");
		try {
		    creditCardService.addNewCreditCard(card);
		} catch (ConstraintViolationException ex) {
			Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
			assertEquals(constraintViolations.size() , 2);
			List<String> errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());
			assertTrue(errors.contains("Card Number is required"));
			assertTrue(errors.contains("Name is required for credit card"));
		}
	}

    /***
     * Validate that card number must not exceed 19 digits
     */
	@Test
    public void addNewCreditCardWithNumberGreaterThan19Test() {
        CreditCardRequestDTO card = new CreditCardRequestDTO("12345678901234567890","Greater than 19 numbers");
        try {
            creditCardService.addNewCreditCard(card);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            List<String> errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());
            assertTrue(errors.contains("Max length for credit card can be 19"));
        }
    }

    /***
     * Validate the credit card number with Luhn10
     */
    @Test
    public void addNewCreditCardWithInvalidNumberTest() {
        CreditCardRequestDTO card = new CreditCardRequestDTO("12448278742987432","Invalid Number");
        try {
            creditCardService.addNewCreditCard(card);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            List<String> errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());
            assertTrue(errors.contains("Credit card number is invalid."));
        }
    }

    /***
     * Validate card number is only digits
     */
    @Test
    public void addNewCreditCardWithAlphanumericTest() {
        CreditCardRequestDTO card = new CreditCardRequestDTO("WRONG_VALUE","ALPHABET in card number");
        try {
            creditCardService.addNewCreditCard(card);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            List<String> errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());
            assertTrue(errors.contains("Card number can only be numbers"));
        }
    }

    /***
     * Validate no two card with same numbers can be saved
     */
    @Test
    public void addNewCreditCardWithExistingCardNumberTest() {
        CreditCardRequestDTO cardOne = new CreditCardRequestDTO(validCreditCard01,"CARD-01");
        CreditCardRequestDTO cardTwo = new CreditCardRequestDTO(validCreditCard01,"CARD-02");
        try {
            creditCardService.addNewCreditCard(cardOne);
            creditCardService.addNewCreditCard(cardTwo);
        } catch (ConstraintViolationException ex) {
            Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
            List<String> errors = constraintViolations.stream().map(constraintViolation -> constraintViolation.getMessage()).collect(Collectors.toList());
            assertTrue(errors.contains("Credit card already exist with this number"));
        }
    }

    /***
     * Validate that credit card is saved in database properly
     */
	@Test
	public void getCreditCardFromDatabaseSuccessTest() {
		CreditCardRequestDTO validCard = new CreditCardRequestDTO(validCreditCard01,"CARD-01");
		creditCardService.addNewCreditCard(validCard);
		CreditCardResponseDTO cardDetails = creditCardService.getCreditCard(validCard.getCardNumber());
		assertEquals(cardDetails.getName(), validCard.getName());
		assertEquals( cardDetails.getCardNumber(),validCard.getCardNumber());
	}

    /***
     * Validate exception is thrown if credit card is not found in database
     */
	@Test(expected = CreditCardNotFoundException.class)
	public void getCreditCardForNonExistingCreditNumberTest() {
		CreditCardRequestDTO invalidCard = new CreditCardRequestDTO(validCreditCard01,"RANDOM_CARD_NUMBER");
		creditCardService.getCreditCard(invalidCard.getCardNumber());
	}

    /**
     * Validate all credit card are returned
     */
	@Test
	public void getAllCreditCardsSuccessTest() {
		CreditCardRequestDTO  firstCreditCard = new CreditCardRequestDTO(validCreditCard01,"CARD-01");
		creditCardService.addNewCreditCard(firstCreditCard);
		CreditCardRequestDTO  secondCreditCard = new CreditCardRequestDTO(validCreditCard02,"CARD-02");
		creditCardService.addNewCreditCard(secondCreditCard);
		assertEquals(2,creditCardService.getAllCreditCards().size());
	}

}
