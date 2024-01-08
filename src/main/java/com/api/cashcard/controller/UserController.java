package com.api.cashcard.controller;

import com.api.cashcard.dto.User;
import com.api.cashcard.repository.CashCardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final CashCardRepository cashCardRepository;

    public UserController(CashCardRepository cashCardRepository) {
        this.cashCardRepository = cashCardRepository;
    }

    @GetMapping("/{requestedId}")
    private ResponseEntity<User> findBy(@PathVariable Long requestedId) {
        if (requestedId.equals(1L)) {
            User user = new User(1L, "John", "123");
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
