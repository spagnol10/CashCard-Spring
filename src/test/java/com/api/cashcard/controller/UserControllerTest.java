package com.api.cashcard.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnAUserWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/1", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.id");
        assertThat(id).isNotNull();

        String name = documentContext.read("$.name");
        assertThat(name).isEqualTo("John");

        String password = documentContext.read("$.password");
        assertThat(password).isEqualTo("123");
    }


    @Test
    void shouldNotReturnAUserWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/user/10", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }

}