package com.api.cashcard.dto;

import org.springframework.data.annotation.Id;

public record User(@Id Long id, String name, String password) {
}
