package com.credit.card.application.controller;

import com.credit.card.application.dto.CreditCardRequestDTO;
import com.credit.card.application.dto.CreditCardResponseDTO;
import com.credit.card.application.service.CreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CreditCardController {

	@Autowired
	private CreditCardService creditCardService;
	
	@PostMapping(path = "/credit-card")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CreditCardResponseDTO addCreditcard(@RequestBody CreditCardRequestDTO creditCardRequest) {
		return creditCardService.addNewCreditCard(creditCardRequest);
	}
	
	@GetMapping(path = "/credit-card")
	public List<CreditCardResponseDTO> getAllCreditcards() {
		return creditCardService.getAllCreditCards();
		
	}

}
