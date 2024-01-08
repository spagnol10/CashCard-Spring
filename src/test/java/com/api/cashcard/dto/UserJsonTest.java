package com.api.cashcard.dto;

import com.api.cashcard.dto.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.FileInputStream;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class UserJsonTest {

    @Autowired
    private JacksonTester<User> json;

    @Test
    void userSerializationTest() throws IOException {
        User user = new User(1L, "John", "123");

        FileInputStream fileInputStream = new FileInputStream("src/test/java/com/api/cashcard/json/user.json");

        assertThat(json.write(user)).isStrictlyEqualToJson(fileInputStream);
        assertThat(json.write(user)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(user)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(1);
        assertThat(json.write(user)).hasJsonPathStringValue("@.name");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.name")
                .isEqualTo("John");
        assertThat(json.write(user)).hasJsonPathStringValue("@.password");
        assertThat(json.write(user)).extractingJsonPathStringValue("@.password")
                .isEqualTo("123");

    }

    @Test
    void cashCardDeserializationTest() throws Exception {
        String expected = """
                {
                  "id": 1,
                  "name": "John",
                  "password": "123"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new User(1L, "John", "123"));

        assertThat(json.parseObject(expected).id())
                .isEqualTo(1);

        assertThat(json.parseObject(expected)
                .name()).isEqualTo("John");

        assertThat(json.parseObject(expected)
                .password()).isEqualTo("123");

    }

}
