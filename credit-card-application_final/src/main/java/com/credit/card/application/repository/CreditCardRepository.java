package com.credit.card.application.repository;

import com.credit.card.application.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,String> {

    @Override
    Optional<CreditCard> findById(String creditCardNumber);
}
