package com.api.cashcard.repository;

import com.api.cashcard.dto.CashCard;
import org.springframework.data.repository.CrudRepository;

public interface CashCardRepository extends CrudRepository<CashCard, Long> {

    CashCard findById(long id);
}
